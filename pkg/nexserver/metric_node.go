package nexserver

import (
	"context"
	pb "github.com/NexClipper/nexclipper-engine/api"

	"github.com/google/uuid"
	"google.golang.org/grpc/codes"
	"google.golang.org/grpc/metadata"
	"google.golang.org/grpc/status"
	"log"
)

func (s *NexServer) newNode(agent *Agent, publicIpv4 string, in *pb.Node) *Node {
	nodeUuid, _ := uuid.NewUUID()

	node := &Node{
		Host:            in.Host,
		Ipv4:            in.Ipv4,
		Ipv6:            "",
		PublicIpv4:      publicIpv4,
		PublicIpv6:      "",
		Os:              in.Os,
		Platform:        in.Platform,
		PlatformFamily:  in.PlatformFamily,
		PlatformVersion: in.PlatformVersion,
		Uuid:            nodeUuid.String(),
		AgentID:         agent.ID,
		ClusterID:       agent.ClusterID,
	}

	return node
}

func (s *NexServer) UpdateNode(ctx context.Context, in *pb.Node) (*pb.Response, error) {
	md, ok := metadata.FromIncomingContext(ctx)
	if !ok {
		log.Println("UpdateNode: failed to get metadata")
		return nil, status.Error(codes.DataLoss, "UpdateNode: failed to get metadata")
	}

	agentUuid, ok := md["uuid"]
	if !ok {
		log.Println("UpdateNode: invalid agent")
		return nil, status.Error(codes.InvalidArgument, "UpdateNode: invalid Agent")
	}

	agent := s.findAgent(agentUuid[0])
	if agent == nil {
		log.Println("UpdateNode: invalid agent")
		return nil, status.Error(codes.PermissionDenied, "UpdateNode: invalid Agent")
	}

	log.Printf("Agent UUID: %s\n", agent.Uuid)

	node := &Node{
		Os:              in.Os,
		Platform:        in.Platform,
		PlatformFamily:  in.PlatformFamily,
		PlatformVersion: in.PlatformVersion,
		Uuid:            "",
		AgentID:         agent.ID,
		ClusterID:       agent.ClusterID,
	}
	s.db.Set("gorm:save_associations", false).Create(node)

	return &pb.Response{
		Success:    true,
		Code:       200,
		Error:      "",
		DataInt32:  nil,
		DataString: nil,
	}, nil
}

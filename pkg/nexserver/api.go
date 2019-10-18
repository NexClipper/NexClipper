package nexserver

import (
	"fmt"
	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
	"log"
)

func (s *NexServer) SetupApiHandler() {
	gin.SetMode("release")
	router := gin.Default()

	config := cors.DefaultConfig()
	config.AllowOrigins = []string{"*"}
	config.AllowMethods = []string{"*"}
	config.AllowHeaders = []string{"*"}
	config.AllowCredentials = true

	router.Use(cors.New(config))

	router.GET("/api/health", s.ApiHealth)
	clusters := router.Group("/api/clusters")
	{
		clusters.GET("", s.ApiClusterList)
		clusters.GET("/:cid/agents", s.ApiAgentList)
		clusters.GET("/:cid/nodes", s.ApiNodeList)
	}

	go func() {
		err := router.Run(fmt.Sprintf("%s:%d", s.config.Server.BindAddress, s.config.Server.ApiPort))
		if err != nil {
			log.Printf("failed api handler: %v\n", err)
		}
	}()
}

func (s *NexServer) ApiResponseJson(c *gin.Context, code int, status, message string) {
	c.JSON(code, gin.H{
		"status":  status,
		"message": message,
	})
}

func (s *NexServer) ApiHealth(c *gin.Context) {
	err := s.db.DB().Ping()
	if err != nil {
		s.ApiResponseJson(c, 500, "bad", "DB connection failed")
	} else {
		s.ApiResponseJson(c, 200, "ok", "")
	}
}

func (s *NexServer) ApiClusterList(c *gin.Context) {
	var clusters []Cluster

	result := s.db.Find(&clusters)
	if result.Error != nil {
		s.ApiResponseJson(c, 500, "bad",
			fmt.Sprintf("failed to get data: %v", result.Error))
		return
	}

	type ClusterItem struct {
		Id   uint   `json:"id"`
		Name string `json:"name"`
	}
	var items []ClusterItem

	for _, cluster := range clusters {
		items = append(items, ClusterItem{
			Id:   cluster.ID,
			Name: cluster.Name,
		})
	}

	c.JSON(200, gin.H{
		"status":  "ok",
		"message": "",
		"data":    items,
	})
}

func (s *NexServer) ApiAgentList(c *gin.Context) {
	cID := c.Param("cid")
	if cID == "" {
		s.ApiResponseJson(c, 404, "bad", "invalid cluster id")
		return
	}

	var agents []Agent

	result := s.db.Where("cluster_id=?", cID).Find(&agents)
	if result.Error != nil {
		s.ApiResponseJson(c, 500, "bad",
			fmt.Sprintf("failed to get data: %v", result.Error))
		return
	}

	type AgentItem struct {
		Id      uint   `json:"id"`
		Version string `json:"version"`
		Ip      string `json:"ip"`
		Online  bool   `json:"online"`
	}
	var items []AgentItem

	for _, agent := range agents {
		items = append(items, AgentItem{
			Id:      agent.ID,
			Version: agent.Version,
			Ip:      agent.Ipv4,
			Online:  agent.Online,
		})
	}

	c.JSON(200, gin.H{
		"status":  "ok",
		"message": "",
		"data":    items,
	})
}

func (s *NexServer) ApiNodeList(c *gin.Context) {
	cID := c.Param("cid")
	if cID == "" {
		s.ApiResponseJson(c, 404, "bad", "invalid cluster id")
		return
	}

	var nodes []Node

	result := s.db.Where("cluster_id=?", cID).Find(&nodes)
	if result.Error != nil {
		s.ApiResponseJson(c, 500, "bad",
			fmt.Sprintf("failed to get data: %v", result.Error))
		return
	}

	type NodeItem struct {
		Id              uint   `json:"id"`
		Host            string `json:"host"`
		Ip              string `json:"ip"`
		Os              string `json:"os"`
		Platform        string `json:"platform"`
		PlatformFamily  string `json:"platform_family"`
		PlatformVersion string `json:"platform_version"`
		AgentId         uint   `json:"agent_id"`
	}
	var items []NodeItem

	for _, node := range nodes {
		items = append(items, NodeItem{
			Id:              node.ID,
			Host:            node.Host,
			Ip:              node.Ipv4,
			Os:              node.Os,
			Platform:        node.Platform,
			PlatformFamily:  node.PlatformFamily,
			PlatformVersion: node.PlatformVersion,
			AgentId:         node.AgentID,
		})
	}

	c.JSON(200, gin.H{
		"status":  "ok",
		"message": "",
		"data":    items,
	})
}

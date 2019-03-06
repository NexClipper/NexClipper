package com.nexcloud.fullfillment.k8s.domain;

import java.util.ArrayList;
import java.util.List;

public class Status {
	// Node Running, 
	// Succeeded: All containers in the pod have terminated in success, and will not be restarted. 
	// Failed: All containers in the pod have terminated, and at least one container has terminated in failure
	// Unknown: For some reason the state of the pod could not be obtained, typically due to an error in communicating with the host of the pod
	private String phase;
	
	private Resource capacity;
	
	private Resource allocatable;
	
	private Condition condition;
	
	private List<Condition> conditions;
	
	private List<Address> addresses;
	
	private Address address;
	
	private Endpoint daemonEndpoints;
	
	private K8SNode nodeInfo;
	
	private List<Image> images;
	
	private Image image;
	
	private String hostIP;
	
	private String podIP;
	
	private String startTime;
	
	private String qosClass;
	
	private List<ContainerStatus> containerStatuses;
	
	private int observedGeneration;
	
	private int replicas;
	
	private int updatedReplicas;
	
	private int readyReplicas;
	
	private int availableReplicas;
	
	private int currentNumberScheduled;
	
	private int numberMisscheduled;
	
	private int desiredNumberScheduled;
	
	private int numberReady;
	
	private int updatedNumberScheduled;
	
	private int numberAvailable;	
	
	private int numberUnavailable;
	
	private int collisionCount;
	
	private int unavailableReplicas;
	
	private int fullyLabeledReplicas;
	
	private int currentReplicas;
	
	private String currentRevision;
	
	private String updateRevision;
	
	private String message;
	
	private String reason;

	public int getCurrentReplicas() {
		return currentReplicas;
	}

	public void setCurrentReplicas(int currentReplicas) {
		this.currentReplicas = currentReplicas;
	}

	public String getCurrentRevision() {
		return currentRevision;
	}

	public void setCurrentRevision(String currentRevision) {
		this.currentRevision = currentRevision;
	}

	public String getUpdateRevision() {
		return updateRevision;
	}

	public void setUpdateRevision(String updateRevision) {
		this.updateRevision = updateRevision;
	}

	public int getFullyLabeledReplicas() {
		return fullyLabeledReplicas;
	}

	public void setFullyLabeledReplicas(int fullyLabeledReplicas) {
		this.fullyLabeledReplicas = fullyLabeledReplicas;
	}

	public int getCurrentNumberScheduled() {
		return currentNumberScheduled;
	}

	public void setCurrentNumberScheduled(int currentNumberScheduled) {
		this.currentNumberScheduled = currentNumberScheduled;
	}

	public int getNumberMisscheduled() {
		return numberMisscheduled;
	}

	public void setNumberMisscheduled(int numberMisscheduled) {
		this.numberMisscheduled = numberMisscheduled;
	}

	public int getDesiredNumberScheduled() {
		return desiredNumberScheduled;
	}

	public void setDesiredNumberScheduled(int desiredNumberScheduled) {
		this.desiredNumberScheduled = desiredNumberScheduled;
	}

	public int getNumberReady() {
		return numberReady;
	}

	public void setNumberReady(int numberReady) {
		this.numberReady = numberReady;
	}

	public int getUpdatedNumberScheduled() {
		return updatedNumberScheduled;
	}

	public void setUpdatedNumberScheduled(int updatedNumberScheduled) {
		this.updatedNumberScheduled = updatedNumberScheduled;
	}

	public int getNumberAvailable() {
		return numberAvailable;
	}

	public void setNumberAvailable(int numberAvailable) {
		this.numberAvailable = numberAvailable;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public Resource getCapacity() {
		if( capacity == null )
			capacity = new Resource();
		return capacity;
	}

	public void setCapacity(Resource capacity) {
		this.capacity = capacity;
	}

	public Resource getAllocatable() {
		if( allocatable == null )
			allocatable = new Resource();
		return allocatable;
	}

	public void setAllocatable(Resource allocatable) {
		this.allocatable = allocatable;
	}

	public Condition getCondition() {
		if( condition == null )
			condition = new Condition();
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public List<Condition> getConditions() {
		if( conditions == null )
			conditions = new ArrayList<Condition>();
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}
	
	public List<Address> getAddresses() {
		if( addresses == null )
			addresses = new ArrayList<Address>();
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address getAddress() {
		if( address == null )
			address = new Address();
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Endpoint getDaemonEndpoints() {
		if( daemonEndpoints == null )
			daemonEndpoints = new Endpoint();
		return daemonEndpoints;
	}

	public void setDaemonEndpoints(Endpoint daemonEndpoints) {
		this.daemonEndpoints = daemonEndpoints;
	}

	public K8SNode getNodeInfo() {
		if( nodeInfo == null )
			nodeInfo = new K8SNode();
		return nodeInfo;
	}

	public void setNodeInfo(K8SNode nodeInfo) {
		this.nodeInfo = nodeInfo;
	}

	public List<Image> getImages() {
		if( images == null )
			images = new ArrayList<Image>();
		
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Image getImage() {
		if( image == null )
			image = new Image();
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public String getPodIP() {
		return podIP;
	}

	public void setPodIP(String podIP) {
		this.podIP = podIP;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getQosClass() {
		return qosClass;
	}

	public void setQosClass(String qosClass) {
		this.qosClass = qosClass;
	}

	public List<ContainerStatus> getContainerStatuses() {
		if( containerStatuses == null )
			containerStatuses = new ArrayList<ContainerStatus>();
		return containerStatuses;
	}

	public void setContainerStatuses(List<ContainerStatus> containerStatuses) {
		this.containerStatuses = containerStatuses;
	}

	public int getObservedGeneration() {
		return observedGeneration;
	}

	public void setObservedGeneration(int observedGeneration) {
		this.observedGeneration = observedGeneration;
	}

	public int getReplicas() {
		return replicas;
	}

	public void setReplicas(int replicas) {
		this.replicas = replicas;
	}

	public int getUpdatedReplicas() {
		return updatedReplicas;
	}

	public void setUpdatedReplicas(int updatedReplicas) {
		this.updatedReplicas = updatedReplicas;
	}

	public int getReadyReplicas() {
		return readyReplicas;
	}

	public void setReadyReplicas(int readyReplicas) {
		this.readyReplicas = readyReplicas;
	}

	public int getAvailableReplicas() {
		return availableReplicas;
	}

	public void setAvailableReplicas(int availableReplicas) {
		this.availableReplicas = availableReplicas;
	}

	public int getNumberUnavailable() {
		return numberUnavailable;
	}

	public void setNumberUnavailable(int numberUnavailable) {
		this.numberUnavailable = numberUnavailable;
	}

	public int getCollisionCount() {
		return collisionCount;
	}

	public void setCollisionCount(int collisionCount) {
		this.collisionCount = collisionCount;
	}

	public int getUnavailableReplicas() {
		return unavailableReplicas;
	}

	public void setUnavailableReplicas(int unavailableReplicas) {
		this.unavailableReplicas = unavailableReplicas;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}

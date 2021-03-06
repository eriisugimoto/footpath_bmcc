package de.uvwxy.footpath.graph;

/**
 * A class to maintain an edge in the graph.
 * 
 * @author Paul Smith
 *
 */
public class GraphEdge {
	private GraphNode node0;
	private GraphNode node1;
	private double len;
	private double bearing;
	private short wheelchair;
	private boolean isStairs = false;
	private boolean isElevator = false;
  private boolean wallPath = false;
  private boolean walkPath = false;
	
	// >0 := number correct steps given
	//  0 := no steps
	// -1 := undefined number of steps
	// -2 := elevator
	private int numSteps = 0;
	
	private float level;
	private boolean isIndoor;
	
	/**
	 * Constructor to create an empty edge with everything set to 0/null/false
	 */
	public GraphEdge() {
		this.node0 = null;
		this.node1 = null;
		this.len = 0.0;
		this.wheelchair = 1;
		this.level = Float.MAX_VALUE;
		this.isIndoor = false;
	}
	
	/**
	 * Constructor to create an edge with given parameters.
	 * 
	 * @param node0 the first GraphNode
	 * @param node1 the second GraphNode
	 * @param len the length of this edge
	 * @param compDir the direction of this edge (node0 -> node1)
	 * @param wheelchair the value concerning the wheelchair attribute
	 * @param level the level of this edge
	 * @param isIndoor true if is indoor
	 *  //any method called in a constructor must be final so that they cannot be modified
	 */
	public GraphEdge(GraphNode node0, GraphNode node1, double len, double compDir, short wheelchair, float level, boolean isIndoor) {
		this.node0 = node0;
		this.node1 = node1;
		this.len = len;
		this.bearing = compDir;
		this.wheelchair = wheelchair;
		this.level = level;
		this.isIndoor = isIndoor;
	}
	
	public double getCompDir() {
		return bearing;
	}
	
	public GraphNode getNode0() {
		return node0;
	}
	
	public GraphNode getNode1() {
		return node1;
	}
	
	public double getLen() {
		return len;
	}
	
	public short getWheelchair() {
		return wheelchair;
	}
	
	public boolean isStairs(){
		return isStairs;
	}
	
	public boolean isElevator(){
		return isElevator;
	}
	
	public int getSteps(){
		return numSteps;
	}
	
	public float getLevel() {
		return level;
	}
	
	public boolean isIndoor(){
		return isIndoor;
	}

  public boolean isWallPath() {
    return wallPath; 
  }
  
  public boolean isWalkPath() {
    return walkPath; 
  }
	
	public void setCompDir(double compDir) {
		this.bearing = compDir;
	}
	
	public void setNode0(GraphNode node0) {
		this.node0 = node0;
	}
	
	public void setNode1(GraphNode node1) {
		this.node1 = node1;
	}
	
	public void setLen(double len) {
    if (walkPath)
		  this.len = len * 2;
    else 
      this.len = len;
	}
	
	public void setWheelchair(short wheelchair) {
		this.wheelchair = wheelchair;
	}
	
	public void setStairs(boolean isStairs) {
		this.isStairs = isStairs;
	}
	
	public void setElevator(boolean isElevator) {
		this.isElevator = isElevator;
	}

  public void setWallPath(boolean pathType) {
    this.wallPath = pathType; 
  }

  public void setWalkPath(boolean pathType) {
    this.walkPath = pathType;
  }

	public void setSteps(int numSteps){
		this.numSteps = numSteps;
		if(numSteps>0)
			this.setWheelchair((short)-1);//if steps, NO wheelchair
	}
	
	public void setLevel(float level) {
		this.level = level;
	}
	
	public void setLevel(boolean isIndoor){
		this.isIndoor = isIndoor;
	}

	public boolean equals(GraphEdge edge){
		if(edge == null)
			return false;
		return this.node0.equals(edge.getNode0()) && this.node1.equals(edge.getNode1())
				|| this.node0.equals(edge.getNode1()) && this.node1.equals(edge.getNode0());
	}
	
	public boolean contains(GraphNode node){
		return getNode0().equals(node) || getNode1().equals(node);
	}
	
	public String toString(){
		String ret = "\nEdge(" + this.node0.getId() + " to " + this.node1.getId() + "): ";
		ret += "\n    Length: " + this.len;
		ret += "\n    Bearing: " + this.bearing;
		if(isStairs()){
			ret += "\n    Staircase with: " + this.getSteps() + " steps";
		}
		if(isElevator()){
			ret += "\n    Elevator: yes";
		}
		ret+="\n    Level: " + level;
		return ret;
	}
}

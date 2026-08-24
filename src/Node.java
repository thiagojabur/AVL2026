import java.util.ArrayList;	
public class Node {
	public int data, bf=0; 
	Node dadNode, leftNode, rightNode;
		
	public int getBF() {
		return bf;
	}
	public void setBF(int bf) {
		this.bf = bf;
	}
	public Node getLeftNode() {
		return leftNode;
	}
	public Node getRightNode() {
		return rightNode;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}

	public Node(int data) {
		this.data = data;
	}
	public Node getDadNode() {
		return dadNode;
	}
	public void setDadNode(Node dadNode) {
		this.dadNode = dadNode;
	}

	public boolean isInternal() {
		return (leftNode != null || 
				rightNode != null);
	}
	public boolean isExternal() {
		return !isInternal();
	}
	
	public String toString() {
		return data + "";
	}
	


}

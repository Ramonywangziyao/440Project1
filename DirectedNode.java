public class DirectedNode extends Node {
	private char direction;
	public DirectedNode(int x, int y, int c, Node parent, char dir){
		super(x,y,c,parent);
		setDirection(dir);
	}
	public char getDirection() {
		return direction;
	}
	public void setDirection(char direction) {
		this.direction = direction;
	}
}

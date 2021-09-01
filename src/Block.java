import java.awt.Color;


public class Block {

	private boolean visited;
	private boolean bomb;
	private boolean transparent;
	private Color color;
	private float dropping;
	
	Block()
	{
		dropping=0;
	}
	
	Block(Block b)
	{
		this.visited=b.isVisited();
		this.color=b.getColor();
		this.bomb=b.isBomb();
		this.transparent=b.isTransparent();
		this.dropping=b.getDropping();
	}
	
	Block(boolean visit, Color c)
	{
		this.visited=visit;
		this.color=new Color(0,0,0);
		this.color=c;
		this.dropping=0;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isBomb() {
		return bomb;
	}

	public void setBomb(boolean bomb) {
		this.bomb = bomb;
	}

	public boolean isTransparent() {
		return transparent;
	}

	public void setTransparent(boolean transparent) {
		this.transparent = transparent;
	}
	
	public float getDropping() {
		return dropping;
	}

	public void setDropping(float dropping) {
		this.dropping = dropping;
	}

	public void removeStates()
	{
		this.bomb=false;
		this.transparent=false;
		this.visited=true;
	}
}

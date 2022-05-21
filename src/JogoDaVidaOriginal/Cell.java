package JogoDaVidaOriginal;
import processing.core.PApplet;

public class Cell {
	protected final int row, col;
	protected int state;
	private Cell[] neighbors;
	protected CellularAutomata ca;
	
	public Cell(CellularAutomata ca, int row, int col) {
		this.ca        = ca;
		this.row       = row;
		this.col       = col;
		this.state     = 0;
		this.neighbors = null;
	}
	
	public void setNeightbors(Cell[] neigh) {
		this.neighbors = neigh;
	}
	
	public Cell[] getNeighbors() {
		return neighbors;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public int getState() {
		return state;
	}
	
	public void display(PApplet p) {
		p.strokeWeight(0);
		p.fill(ca.getStateColors()[state]);
		p.rect(col*ca.getCellWidth(), row*ca.getCellHeight(), ca.getCellWidth(), ca.getCellHeight());
	}
}
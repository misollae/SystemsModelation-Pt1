package AutomatosCelulares2D;
import processing.core.PApplet;

public class GameOfLifeCell {
	private int row, col;
	private int state;
	private int color;
	private GameOfLifeCell[] neighbors;
	private GameOfLifeCellularAutomata ca;
	
	public GameOfLifeCell(GameOfLifeCellularAutomata ca, int row, int col) {
		this.ca        = ca;
		this.row       = row;
		this.col       = col;
		this.state     = 0;
		this.neighbors = null;
	}
	
	public void setNeightbors(GameOfLifeCell[] neigh) {
		this.neighbors = neigh;
	}
	
	public GameOfLifeCell[] getNeighbors() {
		return neighbors;
	}
	
	public int getNeighborSum() {
		int sum = 0;
		for (int i = 0 ; i < getNeighbors().length ; i++) {
			sum += getNeighbors()[i].getState();
		}
		return sum - getState();
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public int getState() {
		return state;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getMainColor() {
		int contador;
		int maiorQuantidadeAtual = 0;
		int corDominanteAtual = 250;

		for (int i = 0 ; i < getNeighbors().length ; i++) {
			if(getNeighbors()[i].getState() != 0) {
				int corAtual = getNeighbors()[i].getColor();
				contador = 0;
				for (int j = i+1 ; j < getNeighbors().length ; j++) if (getNeighbors()[j].getColor() == corAtual) contador++;
				if (contador >= maiorQuantidadeAtual) {maiorQuantidadeAtual = contador; corDominanteAtual = corAtual;}
			}
		}
		return corDominanteAtual;
	}
	
	public void display(PApplet p) {
		p.strokeWeight(0);
		p.fill(state == 1 ? color : 0);
		p.rect(col*ca.getCellWidth(), row*ca.getCellHeight(), ca.getCellWidth(),   ca.getCellHeight());
	}
}
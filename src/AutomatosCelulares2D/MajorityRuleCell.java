package AutomatosCelulares2D;
import processing.core.PApplet;

public class MajorityRuleCell {
	private int row, col;
	private int state;
	private int color;
	private MajorityRuleCell[] neighbors;
	private MajorityRuleCellularAutomata ca;
	
	public MajorityRuleCell(MajorityRuleCellularAutomata ca, int row, int col) {
		this.ca        = ca;
		this.row       = row;
		this.col       = col;
		this.state     = 0;
		this.neighbors = null;
	}
	
	public void setNeightbors(MajorityRuleCell[] neigh) {
		this.neighbors = neigh;
	}
	
	public MajorityRuleCell[] getNeighbors() {
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
	
	public int getMainState() {
		int contador = 0;
		int maiorQuantidadeAtual = 0;
		int DominanteAtual = state;
		
		for (int j = 0 ; j < getNeighbors().length ; j++) {
			if (j != 4 && getNeighbors()[j].getState() == state) contador++;
			maiorQuantidadeAtual = contador;
		}

		for (int estado = 0 ; estado < ca.nStates ; estado++) {
			contador = 0;
			for (int j = 0 ; j < getNeighbors().length ; j++) {
				if (j != 4 && getNeighbors()[j].getState() == estado) contador++;
			}
			if (contador > maiorQuantidadeAtual) {maiorQuantidadeAtual = contador; DominanteAtual = estado;}
		}
		
		return DominanteAtual;
	}
	
	public void display(PApplet p) {
		p.strokeWeight(0);
		p.fill(ca.getStateColors()[state]);
		p.rect(col*ca.getCellWidth(), row*ca.getCellHeight(), ca.getCellWidth(),   ca.getCellHeight());
	}
}
package JogoDaVidaOriginal;
public class JogoDaVidaCell extends Cell {
	
	public JogoDaVidaCell(JogoDaVidaCellAutomata jogoDaVidaCellAutomata, int row, int col) {
		super(jogoDaVidaCellAutomata, row,col);
	}
	
	public int getNeighborSum() {
		int sum = 0;
		for (int i = 0 ; i < getNeighbors().length ; i++) {
			sum += getNeighbors()[i].getState();
		}
		return sum - getState();
	}
}
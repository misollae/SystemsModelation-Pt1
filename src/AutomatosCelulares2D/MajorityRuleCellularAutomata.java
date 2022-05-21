package AutomatosCelulares2D;
import processing.core.PApplet;

public class MajorityRuleCellularAutomata {
	protected final int nrows;
	protected final int ncols;
	protected final int nStates;
	protected final int radiusNeigh;
	private MajorityRuleCell[][] cells;
	protected final int[] colors;
	protected final int cellWidth, cellHeight;
	
	public MajorityRuleCellularAutomata(PApplet p, int nrows, int ncols, int nStates, int radiusNeigh) {
		this.nrows       = nrows;
		this.ncols       = ncols;
		this.nStates     = nStates;
		this.radiusNeigh = radiusNeigh;
		cells  = new MajorityRuleCell[nrows][ncols];
		colors = new int[nStates];
		cellWidth  = p.width  / ncols;
		cellHeight = p.height / nrows;
		createCells();
		setStateColors(p);
		initRandom(p);
	}

	public int getCellWidth() {
		return cellWidth;
	}
	
	public int getCellHeight() {
		return cellHeight;
	}
	
	public void setStateColors(PApplet p) {
		for (int i = 0 ; i < nStates ; i++) {
			colors[i] = p.color(p.random(255), p.random(255), p.random(255));
		}
	}
	
	public int[] getStateColors() {
		return colors;
	}

	private void createCells() {
		for (int i=0 ; i<nrows ; i++) {
			for (int j=0 ; j<ncols ; j++) {
				cells[i][j] = new MajorityRuleCell(this, i, j);
			}
		}
		setMooreNeighbors();
	}

	public void initRandom(PApplet p) {
		for (int i=0 ; i < nrows ; i++) {
			for (int j=0 ; j < ncols ; j++) {
					cells[i][j].setState((int)((nStates)*Math.random()));
			}
		}
	}
	
	public MajorityRuleCell pixel2Cell(int x, int y) {
		int row = y/cellHeight;
		int col = x/cellWidth;
		if (row >= nrows) row = nrows-1;
		if (col >= ncols) col = ncols-1;
        return cells[row][col];
	}
	
	public void  updateCells(PApplet p) {
		// Em cells temos os estados atuais das células, vamos então criar um array de células
		// que representará os próximos estados, baseando-se no primeiro array mencionado
		MajorityRuleCell[][] cellsAtualizadas = new MajorityRuleCell[nrows][ncols];
		for(int i=0; i<nrows;i++) {
			for(int j=0;j<ncols;j++) {
				cellsAtualizadas[i][j] = new MajorityRuleCell(this, i, j);
			}
		}
		setMooreNeighbors();
		
		// Percorremos depois célula a célula, e aplicamos a regra da maioria:
		for(int i=0; i<nrows;i++) {
			for(int j=0;j<ncols;j++) {
				cellsAtualizadas[i][j].setState(cells[i][j].getMainState());
			}
		}

		this.cells = cellsAtualizadas; // Coloca-se este novo array como o "atual"
		display(p);	 // E realiza-se o display	
	}
	
	private void setMooreNeighbors() {
		// Definir quantos vizinhos se tem (fórmula de Moore)
		int nNeighbors = (int)Math.pow(2*radiusNeigh + 1, 2);
		
		// Percorrer cada célula
		for (int i=0 ; i < nrows ; i++) {
			for (int j=0 ; j < ncols ; j++) {
				
				MajorityRuleCell[] neigh = new MajorityRuleCell[nNeighbors]; // Estando em cada célula cria-se o array da vizinhança
				int n = 0;	                         // E uma variável auxiliar
				
				// E percorre-se os vizinhos
				for (int ii=-radiusNeigh ; ii <= radiusNeigh ; ii++) {
					for (int jj=-radiusNeigh ; jj <= radiusNeigh ; jj++) {
						int row = (i + ii + nrows) % nrows; // Aritmética circular
						int col = (j + jj + ncols) % ncols; 
						neigh[n++] = cells[row][col]; // Colocando-os no array
					}
				}
				cells[i][j].setNeightbors(neigh);
			}
		}	
	}
	
	public void display(PApplet p) {
		for (int i=0 ; i < nrows ; i++) {
			for (int j=0 ; j < ncols ; j++) {
				cells[i][j].display(p);
			}
		}
	}
	
}

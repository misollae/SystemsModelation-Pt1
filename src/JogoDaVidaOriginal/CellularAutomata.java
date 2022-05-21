package JogoDaVidaOriginal;

import processing.core.PApplet;

public class CellularAutomata {
	protected final int nrows;
	protected final int ncols;
	public final int nStates;
	protected final int radiusNeigh;
	private Cell[][] cells;
	protected final int[] colors;
	protected final int cellWidth, cellHeight;
	
	public CellularAutomata(PApplet p, int nrows, int ncols, int nStates, int radiusNeigh) {
		this.nrows       = nrows;
		this.ncols       = ncols;
		this.nStates     = nStates;
		this.radiusNeigh = radiusNeigh;
		cells  = new Cell[nrows][ncols];
		colors = new int[nStates];
		cellWidth  = p.width  / ncols;
		cellHeight = p.height / nrows;
		createCells();
		setStateColors(p);
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
				cells[i][j] = new Cell(this, i, j);
			}
		}
		setMooreNeighbors();
	}

	public void initRandom() {
		for (int i=0 ; i < nrows ; i++) {
			for (int j=0 ; j < ncols ; j++) {
				cells[i][j].setState((int)((nStates-1)*Math.random()));
			}
		}
	}
	
	public Cell pixel2Cell(int x, int y) {
		int row = y/cellHeight;
		int col = x/cellWidth;
		if (row >= nrows) row = nrows-1;
		if (col >= ncols) col = ncols-1;
        return cells[row][col];
	}
	
	private void setMooreNeighbors() {
		// Definir quantos vizinhos se tem (fórmula de Moore)
		int nNeighbors = (int)Math.pow(2*radiusNeigh + 1, 2);
		
		// Percorrer cada célula
		for (int i=0 ; i < nrows ; i++) {
			for (int j=0 ; j < ncols ; j++) {
				
				Cell[] neigh = new Cell[nNeighbors]; // Estando em cada célula cria-se o array da vizinhança
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

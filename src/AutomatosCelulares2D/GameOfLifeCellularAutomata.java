package AutomatosCelulares2D;
import java.util.ArrayList;

import processing.core.PApplet;

public class GameOfLifeCellularAutomata {
	private int nrows;
	private int  ncols;
	private int  nStates;
	private int  radiusNeigh;
	private GameOfLifeCell[][] cells;
	private  int[] colors;
	private  int cellWidth, cellHeight;
	
	public GameOfLifeCellularAutomata(PApplet p, int nrows, int ncols, int nStates, int radiusNeigh) {
		this.nrows          = nrows;
		this.ncols          = ncols;
		this.nStates        = nStates;
		this.radiusNeigh    = radiusNeigh;
		cells  = new GameOfLifeCell[nrows][ncols];
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
		colors[1] = p.color(0);
		for (int i = 1 ; i < nStates ; i++) {
			colors[i] = p.color(p.random(255), p.random(255), p.random(255));
		}
	}
	
	public int[] getStateColors() {
		return colors;
	}

	private void createCells() {
		for (int i=0 ; i<nrows ; i++) {
			for (int j=0 ; j<ncols ; j++) {
				cells[i][j] = new GameOfLifeCell(this, i, j);
			}
		}
		setMooreNeighbors();
	}

	public void initRandom(PApplet p) {
		for (int i=0 ; i < nrows ; i++) {
			for (int j=0 ; j < ncols ; j++) {
				
				if (Math.floor(10*Math.random())==0) {
					cells[i][j].setState(1);
					cells[i][j].setColor(p.color(p.random(255), p.random(255), p.random(255)));
				} else {
					cells[i][j].setState(0);
					cells[i][j].setColor(p.color(0));
				}
			}
		}
	}
	
	public GameOfLifeCell pixel2Cell(int x, int y) {
		int row = y/cellHeight;
		int col = x/cellWidth;
		if (row >= nrows) row = nrows-1;
		if (col >= ncols) col = ncols-1;
        return cells[row][col];
	}
	
	public void  updateCells(PApplet p, ArrayList<Integer> birth_rules, ArrayList<Integer> survival_rules) {
		// Em cells temos os estados atuais das células, vamos então criar um array de células
		// que representará os próximos estados, baseando-se no primeiro array mencionado
		GameOfLifeCell[][] cellsAtualizadas = new GameOfLifeCell[nrows][ncols];
		for(int i=0; i<nrows;i++) {
			for(int j=0;j<ncols;j++) {
				cellsAtualizadas[i][j] = new GameOfLifeCell(this, i, j);
			}
		}
		setMooreNeighbors();
		for(int i=0; i<nrows;i++) {
			for(int j=0;j<ncols;j++) {
				cellsAtualizadas[i][j].setColor(cells[i][j].getColor());
			}
		}
		
		// Percorremos depois célula a célula, e aplicamos as regras do Jogo da Vida:
		for(int i=0; i<nrows;i++) {
			for(int j=0;j<ncols;j++) {
				// Se estiver morta
				if (cells[i][j].getState() == 0) {
					for (int ruleB : birth_rules) {
						// Nasce se tiver um número válido de vizinhas vivas
						if (cells[i][j].getNeighborSum() == ruleB) {cellsAtualizadas[i][j].setState(1);}
						// De outra forma, permanece morta
						}
				}
				// Se estiver viva
				if (cells[i][j].getState() == 1) {
					for (int ruleS : survival_rules) {
					// Na verão base diz-se morrer por sobrevizinhança (overcrowd) ou por solidão (mais de 3 ou menos de 2 vizinhas vivas)
					// Aqui, verificamos se acede às regras propostas
					if (cells[i][j].getNeighborSum() == ruleS) {cellsAtualizadas[i][j].setState(1);}
					// De outra forma, morre
					}
				}
				cellsAtualizadas[i][j].setColor(cells[i][j].getMainColor());
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
				
				GameOfLifeCell[] neigh = new GameOfLifeCell[nNeighbors]; // Estando em cada célula cria-se o array da vizinhança
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

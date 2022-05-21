package AutomatosCelulares2D;
import g4p_controls.GLabel;
import processing.core.PApplet;
import project1.IProcessingApp;

public class MajorityRule implements IProcessingApp{
	private int nrows       = 20;
	private int ncols       = 20;
	private int nStates     = 4;
	private int radiusNeigh = 1;
	private int iteracao;
	private boolean gameState;
	private MajorityRuleCellularAutomata ca;
	
	@Override
	public void setup(PApplet p) {
		ca = new MajorityRuleCellularAutomata(p, nrows, ncols, nStates, radiusNeigh);
		gameState = true;
		iteracao = 0;
		p.background(0);
		ca.display(p);
		setUpG4P(p);
	}
	
	// Adiciona as componentes G4P à PApplet
	private void setUpG4P(PApplet p) {		
		GLabel label2 = new GLabel(p, p.width-250, p.height-30, 1000, 20, "Press R to Reset and SPACE to pause/start");
		label2.setLocalColorScheme(p.color(125, 127, 201));
	}
	
	public void setup(PApplet p, int chosenNStates) {
		this.nStates = chosenNStates;
		ca = new MajorityRuleCellularAutomata(p, nrows, ncols, nStates, radiusNeigh);
		gameState = true;
		iteracao = 0;
		p.background(0);
		ca.display(p);
	}
	
	@Override
	public void draw(PApplet p, float dt) {
		if (gameState) {
			ca.updateCells(p);
			System.out.printf("Iteração número: %d\n", iteracao++);
		}
	}

	public void keyPressed(PApplet p) {
		if (p.keyPressed) gameState = !gameState;
		if (gameState) System.out.println("Game Started"); else System.out.println("Game Paused");
		if (p.key == 'r' || p.key == 'R') setup(p);
	}
	
	@Override
	public void mousePressed(PApplet p) {
	}
}

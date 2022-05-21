package JogoDaVidaOriginal;
import java.util.ArrayList;
import java.util.Arrays;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import g4p_controls.GLabel;
import g4p_controls.GTextField;
import processing.core.PApplet;

public class JogoDaVida implements setup.IProcessingApp{
	private int nrows       = 75;
	private int ncols       = 100;
	private int nStates     = 2;
	private int radiusNeigh = 1;
	private ArrayList<Integer> birth_rules = new ArrayList<Integer>(Arrays.asList(3));
	private ArrayList<Integer> survival_rules = new ArrayList<Integer>(Arrays.asList(2,3));
	private int iteracao;
	private boolean gameState;
	private JogoDaVidaCellAutomata ca;
	private GTextField birthRulesField;
	private GTextField survivalRulesField;
	private GButton updateBttn;
	
	@Override
	public void setup(PApplet p) {
		ca = new JogoDaVidaCellAutomata(p, nrows, ncols, nStates, radiusNeigh);
		gameState = false;
		iteracao = 0;
		ca.display(p);
		setUpG4P(p);
	}

	// Adiciona as componentes G4P à PApplet
	private void setUpG4P(PApplet p) {		
		survivalRulesField = new GTextField(p, p.width/2 - 55, 5, 50, 20);
		birthRulesField    = new GTextField(p, p.width/2 + 5, 5, 50, 20);
		survivalRulesField.setNumeric(0, 999999999, 23);
		birthRulesField.setNumeric(0, 999999999, 3);
		GLabel label1 = new GLabel(p, p.width/2-89, 5, 100, 20, "Rule:                 /");
		label1.setLocalColorScheme(p.color(125, 127, 201));
		GLabel label2 = new GLabel(p, p.width-250, p.height-30, 1000, 20, "Press R to Reset and SPACE to pause/start");
		label2.setLocalColorScheme(p.color(125, 127, 201));
		updateBttn = new GButton(p, p.width/2 + 60, 5, 55, 20, "Update!");
		updateBttn.addEventHandler(this, "handleUpdate");
	}

	public void handleUpdate(GButton button, GEvent event) {
		if (button == updateBttn && event == GEvent.CLICKED) {
		    int ruleS = survivalRulesField.getValueI();
			int ruleB = birthRulesField.getValueI();
			  
			ArrayList<Integer> newBirth_rules    = new ArrayList<Integer>();
			ArrayList<Integer> newSurvival_rules = new ArrayList<Integer>();
			while (ruleS != 0) {
				newSurvival_rules.add(ruleS % 10); ruleS = ruleS / 10;
			}
			while (ruleB != 0) {
				newBirth_rules.add(ruleB % 10); ruleB = ruleB / 10;
			}
			changeRules(newBirth_rules, newSurvival_rules);
		}
	}
	
	public void changeRules(ArrayList<Integer> birth_rules, ArrayList<Integer> survival_rules) {
		this.birth_rules = birth_rules;
		this.survival_rules = survival_rules;
	}
	
	@Override
	public void draw(PApplet p, float dt) {
		if (gameState) {
			ca.updateCells(p, birth_rules, survival_rules);
			System.out.printf("Iteração número: %d\n", iteracao++);
		}
	}

	public void keyPressed(PApplet p) {
		if (p.key == ' ') gameState = !gameState;
		if (gameState) System.out.println("Game Started"); else System.out.println("Game Paused");
		if (p.key == 'r' || p.key == 'R') setup(p);
	}
	
	@Override
	public void mousePressed(PApplet p) {
		JogoDaVidaCell cell = ca.pixel2Cell(p.mouseX, p.mouseY);
		if (!gameState) {
			if (cell.getState()==1) cell.setState(0); else cell.setState(1);
			ca.display(p);
		}
	}
}

package AutomatosCelulares2D;
import processing.core.PApplet;
import project1.IProcessingApp;

public class MajorityRuleSetUp extends PApplet {
	public static IProcessingApp app = new MajorityRule();
	private int lastUpdate;
	public static int chosenNStates = 0;
	
	@Override
	public void settings()
	{
		size(1024, 700);
	}
	
	// Apenas para quando se inicializa através do menu
	public void connectMenu(int chosenNStates) {
		this.chosenNStates = chosenNStates;
	}
	
	@Override
	public void setup()
	{
		if (chosenNStates == 0) app.setup(this); else ((MajorityRule)app).setup(this, chosenNStates);
		lastUpdate = millis();
	}
	
	@Override
	public void exitActual() {
	}
	
	@Override
	public void draw()
	{
		int now = millis();
		if (now - lastUpdate >= 500) {
			float dt = (now - lastUpdate)/1000f;
			lastUpdate = now;
			app.draw(this, dt);
		}
	}
	
	@Override
	public void mousePressed() {
		app.mousePressed(this);
	}
	
	@Override
	public void keyPressed() {
		app.keyPressed(this);
	}
	
	public static void main(String[] args) {
		app = new MajorityRule();
		PApplet.main(MajorityRuleSetUp.class);
	}
}

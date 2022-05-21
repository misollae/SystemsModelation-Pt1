package AutomatosCelulares2D;
import processing.core.PApplet;
import project1.IProcessingApp;

public class GameOfLifeSetUp extends PApplet {
	public static IProcessingApp app = new GameOfLife();
	private int lastUpdate;
	
	@Override
	public void settings()
	{
		size(1024, 700);
	}
	
	@Override
	public void setup()
	{
		app.setup(this);
		lastUpdate = millis();
	}
	public IProcessingApp getApp() {
		return app;
	}
	
	@Override
	public void draw()
	{
		int now = millis();
		if (now - lastUpdate >= 80) {
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
		app = new GameOfLife();
		PApplet.main(GameOfLifeSetUp.class);
	}
}

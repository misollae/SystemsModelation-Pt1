package JogoDaVidaOriginal;
import processing.core.PApplet;
import project1.IProcessingApp;

public class JogoDaVidaSetUp extends PApplet {
	public static IProcessingApp app = new JogoDaVida();
	private int lastUpdate;
	
	@Override
	public void settings()
	{
		size(800, 600);
	}
	
	@Override
	public void setup()
	{
		app.setup(this);
		lastUpdate = millis();
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
	
	// Devolve a app de forma a poder ser manipulada pelo menu
	public IProcessingApp getApp() {
		return app;
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
		app = new JogoDaVida();
		PApplet.main(JogoDaVidaSetUp.class);
	}
}

package dla;
import processing.core.PApplet;
import project1.IProcessingApp;

public class DiffusionAggregationSetup extends PApplet
{
	public IProcessingApp app = new dla();
	private int lastUpdate;
	
	@Override
	public void settings()
	{
		size(800, 600);
	}
	
	public dla getApp() {
		return (dla) app;
	}
	
	@Override
	public void setup()
	{
		app.setup(this);
		lastUpdate = millis();
	}
	
	@Override
	public void exitActual() {
	}
	
	@Override
	public void draw()
	{
		int now = millis();
		float dt = (now - lastUpdate)/1000f;
		lastUpdate = now;
		app.draw(this, dt);
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
		//app = new dla();
		PApplet.main(DiffusionAggregationSetup.class);
	}
}
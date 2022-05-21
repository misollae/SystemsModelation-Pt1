package setup;
import processing.core.PApplet;

public class TPC0 implements IProcessingApp{
	
	
	private VolkswagenSymbol simbolo;
	private float randomX, randomY, easingX, easingY;
	private boolean easingOngoing = false;
	private int lastUpdate = 0;
	private float easing = 0.05f;

	public void setup(PApplet p)
	{
		simbolo = new VolkswagenSymbol(400,300);
		simbolo.drawSymbol(p);
	}
	
	public void draw(PApplet p, float dt) {
		// Alínea B
//		if (p.millis() - lastUpdate >= 2000) {
//			float randomX = p.random(120, 680);
//			float randomY = p.random(120, 380);
//			p.clear();
//			p.background(200);
//			simbolo.updateCenter(randomX, randomY);
//			simbolo.drawSymbol(p);
//			lastUpdate = p.millis();
//		}
		
		// Alínea C
		if (!easingOngoing) {
			randomX = p.random(120, 680);
			randomY = p.random(120, 380);
			easingX = 0.01f * (randomX-simbolo.getCenterX());
			easingY = 0.01f * (randomY-simbolo.getCenterY());
			easingOngoing = true;
		} else {
			p.clear();
			simbolo.updateCenter(simbolo.getCenterX() + easingX, simbolo.getCenterY() + easingY);
			simbolo.drawSymbol(p);
			if (java.lang.Math.abs(randomX - simbolo.getCenterX()) <= 0.1 &&
				java.lang.Math.abs(randomY - simbolo.getCenterY()) <= 0.1) easingOngoing = false;
		}		
	}

	@Override
	public void mousePressed(PApplet p) {
		p.clear();
		simbolo.updateCenter(p.mouseX, p.mouseY);
		simbolo.drawSymbol(p);
		easingOngoing = false;
	}
}

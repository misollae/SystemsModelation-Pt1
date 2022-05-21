package dla;
import java.util.List;
import processing.core.PApplet;
import processing.core.PVector;

public class walker {
	
	public enum State{
		STOPPED,
		WANDER
	}
	
	private PVector pos;
	private State state;
	private int color;
	private int radius = 4;
	
	public static float stickiness = 0.01f;  // Quanto maior a probabilidade de mudarem de estado, mas "esticados" ficará o DLA -> tem menos chance de
	                                  		 // se deslocar para o interior
	public static int num_wanders = 0;
	public static int num_stopped = 0;
	
	public walker(PApplet p) {
		pos = new PVector(p.random(p.width), p.random(p.height));
		pos = new PVector(p.width/2, p.height/2);
		PVector r = PVector.random2D();
		r.mult(p.width/2);
		pos.add(r);
		setState(p, State.WANDER);
	}
	
	public walker(PApplet p, PVector pos) {
		this.pos = pos;
		setState(p, State.STOPPED);
	}
	
	public State getState() {
		return state;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setState(PApplet p, State state) {
		this.state = state;
		if (state == State.STOPPED){
			color = p.color(253,231,0);
			num_stopped++;
		}
		else {
			color = p.color(10);
			num_wanders++;
		}
	}
	
	public void setColor(PApplet p, int color) {
		this.color = color;
	}
	
//	public void setStickiness(float stick) {
//		this.stickiness = stick;
//	}
	
	public boolean insideWindow(PApplet p, float x, float y) {
		return x > radius && x < p.width - radius && y > radius && y < p.height - radius;
	}
	
	public void updateState(PApplet p, List<walker> walkers) {
		if (state == State.STOPPED)
			return;
		for (walker w : walkers) {
			if (w.state == State.STOPPED && (Math.random() <= stickiness)) {
				float dist = PVector.dist(pos, w.pos);
				if (dist < 2*radius && insideWindow(p, pos.x, pos.y)) {
					setState(p, State.STOPPED);
					int touchedC = w.getColor();
					int newColor = p.color(p.red(touchedC)-(253-149)/30f, p.green(touchedC)-(231-27)/30f, p.blue(touchedC)+(112)/30f);
					setColor(p, newColor);
					num_wanders--;
					break;
				}
			}
		}
	}
		
	public void wander(PApplet p) {
		PVector step = PVector.random2D();
		pos.add(step);
		pos.lerp(new PVector(p.width/2, p.height/2), 0.0002f);
		pos.x = PApplet.constrain(pos.x, 0, p.width);
		pos.y = PApplet.constrain(pos.y, 0, p.height);
	}
	
	public void display(PApplet p) {
		p.noStroke();
		p.fill(color);
		p.circle(pos.x, pos.y, 2*radius);
	}
}

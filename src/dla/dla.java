package dla;

import java.util.ArrayList;
import java.util.List;
import g4p_controls.*;

import dla.walker.State;
import processing.core.PApplet;
import processing.core.PVector;

public class dla implements setup.IProcessingApp {
	private List<walker> walkers;
	private int NUM_WALKERS = 50;
	private int NUM_STEPS_PER_FRAME = 100;
	private GTextField stickinessField;
	private GButton updateBttn;
	private boolean allowClick = false;
	
	@Override
	public void setup(PApplet p) {
		walkers = new ArrayList<walker>();
		walker.num_wanders = 0;
		walker.num_stopped = 0;		
		walker w = new walker(p, new PVector(p.width/2, p.height/2));
		walkers.add(w);
				
		for (int i=0; i < NUM_WALKERS; i++) {
			w = new walker(p);
			walkers.add(w);
		}
		setUpG4P(p);
	}
	
	public void setUpG4P(PApplet p) {
		stickinessField = new GTextField(p, p.width/2+2, 5, 50, 20);
		stickinessField.setNumeric(0f, 1f, 1f);
		GLabel label1 = new GLabel(p, p.width/2-61, 5, 100, 20, "Stickiness:");
		label1.setLocalColorScheme(p.color(125, 127, 201));
		GLabel label2 = new GLabel(p, p.width-370, p.height-30, 1500, 20, "Press R to Reset and A to allow/disallow new seed by mouse click.");
		label2.setLocalColorScheme(p.color(125, 127, 201));
		updateBttn = new GButton(p, p.width/2 + 60, 5, 55, 20, "Update!");
		updateBttn.addEventHandler(this, "handleUpdate");
	}
	
	public void handleUpdate(GButton button, GEvent event) {
		if (button == updateBttn && event == GEvent.CLICKED) {
			setStickiness(stickinessField.getValueF());
		}
	}
	
	public static void setStickiness(float stickiness) {
		walker.stickiness = stickiness;
	}
	
	@Override
	public void draw(PApplet p, float dt) {
		p.background(0);

		for(int i = 0; i<NUM_STEPS_PER_FRAME ; i++) {
			for (walker w : walkers) {
				if (w.getState() == State.WANDER) {
					w.wander(p);
					w.updateState(p, walkers);
				}
			}
		}
			
		if (walkers.size() < 2000) {
			for(int i = 0; i < NUM_WALKERS + 1 - walker.num_wanders ; i++) {
				walker w = new walker(p);
				walkers.add(w);
			}
		}

		for (walker w : walkers) w.display(p);
		System.out.println("Stopped = " + walker.num_stopped + ", Wandering = " + walker.num_wanders + ", Stickiness = " + walker.stickiness);
	}
	
	@Override
	public void keyPressed(PApplet p) {
		if (p.key == 'r' || p.key == 'R') setup(p);
		if (p.key == 'a' || p.key == 'A') allowClick = !allowClick;
	}
	
	@Override
	public void mousePressed(PApplet p) {
		if (allowClick) {
			walker w = new walker(p, new PVector(p.mouseX, p.mouseY));
			walkers.add(w);
		}
	}
}

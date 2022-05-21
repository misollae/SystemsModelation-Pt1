package setup;
import processing.core.PApplet;

public class VolkswagenSymbol {
	private int preto;
	private int branco;
	private float centroX;
	private float centroY;
	
	VolkswagenSymbol(float centroX, float centroY){
		this.centroX = centroX;
		this.centroY = centroY;
	}
	
	// Método que desenha o círculo
	public void drawSymbol(PApplet p)
	{		
		p.background(200);
		preto = p.color(0,0,0);
		branco = p.color(255,255,255);
		
		// Desenha a circunferência exterior
		p.stroke(preto);
		p.fill(branco);
		p.strokeWeight(4); 
		p.circle(centroX, centroY, 120);
		// Desenha o círculo preto interior
		p.fill(preto);
		p.strokeWeight(0); 
		p.circle(centroX, centroY, 90);
		// Desenha 2 riscas diagonais do lado direito...
		p.fill(branco);
		p.strokeWeight(13); 
		p.stroke(branco);
		p.line(17 + centroX, centroY - 40, centroX - 20, centroY + 40);
		p.line(42 + centroX, centroY - 30, centroX + 20, centroY + 32);
		// ... e duas simétricas do lado esquerdo
		p.line(centroX - 17, centroY - 40, centroX + 20, centroY + 40);
		p.line(centroX - 42, centroY - 20, centroX - 20, centroY + 32);
		p.strokeWeight(2); 
		p.stroke(preto);
		// Por fim, desenha a linha preta horizontal, no meio do logotipo
		p.line(centroX - 10, centroY - 3, centroX + 10, centroY - 3);
	}
	
	// Método que atualiza o centro do logotipo
	public void updateCenter(float newCenterX, float newCenterY) {
		this.centroX = newCenterX;
		this.centroY = newCenterY;
	}
	
	// Métodos que retornam as coordenadas do centro do logotipo
	public float getCenterX() {return this.centroX;}
	public float getCenterY() {return this.centroY;}
}

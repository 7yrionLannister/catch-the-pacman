package ui;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import model.Pacman;


public class GameZone extends Canvas {
	private ArrayList<Pacman> pacmans;
	private GraphicsContext gc;
	public GameZone(ArrayList<Pacman> pacs) {
		super(800, 650);
		pacmans = pacs;
		gc = getGraphicsContext2D();
		gc.setLineWidth(5);
		gc.setFill(Color.YELLOW);
		gc.rect(0, 0, super.getWidth(), super.getHeight());
	}

	public void redraw() {
		gc.clearRect(0, 0,  super.getWidth(), super.getHeight());
		for(Pacman pac:pacmans) {
			gc.fillArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), pac.getStartAngle(), pac.getLength(), ArcType.ROUND);
			gc.strokeArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), pac.getStartAngle(), pac.getLength(), ArcType.ROUND);
		}
	}
}

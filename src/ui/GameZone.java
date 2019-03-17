package ui;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.Pacman;


public class GameZone extends Canvas {
	private ArrayList<Pacman> pacmans;
	private GraphicsContext gc;
	private boolean openMouth;
	private int startAngle;
	private int angleLength;

	public GameZone(ArrayList<Pacman> pacs) {
		super(800, 650);
		pacmans = pacs;
		gc = super.getGraphicsContext2D();
		gc.setLineWidth(3);
		gc.setFill(Color.YELLOW);
		openMouth = false;
		startAngle = 45;
		angleLength = 270;
	}

	public void redraw() {
		gc.clearRect(0, 0,  super.getWidth(), super.getHeight());
		startAngle = openMouth? startAngle+5 : startAngle-5;
		angleLength = openMouth? angleLength-10 : angleLength+10;
		if(startAngle==0 || startAngle==45) {
			openMouth = !openMouth;
		}
		for(Pacman pac:pacmans) {
			switch(pac.getDirection()) {
			case Pacman.UP:
				gc.fillArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), startAngle+90, angleLength, ArcType.ROUND);
				gc.strokeArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), startAngle+90, angleLength, ArcType.ROUND);
				break;
			case Pacman.DOWN:
				gc.fillArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), startAngle+270, angleLength, ArcType.ROUND);
				gc.strokeArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), startAngle+270, angleLength, ArcType.ROUND);
				break;
			case Pacman.RIGHT:
				gc.fillArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), startAngle, angleLength, ArcType.ROUND);
				gc.strokeArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), startAngle, angleLength, ArcType.ROUND);
				break;
			case Pacman.LEFT:
				gc.fillArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), startAngle+180, angleLength, ArcType.ROUND);
				gc.strokeArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), startAngle+180, angleLength, ArcType.ROUND);
				break;
			}
		}

		//Este for es peligroso
		for(int i = 0; i < pacmans.size(); i++) {
			for(int j = i+1; j < pacmans.size(); j++) {
				Rectangle r1 = new Rectangle(pacmans.get(i).getPosX(), pacmans.get(i).getPosY(), pacmans.get(i).getRadius(), pacmans.get(i).getRadius());
				if(r1.intersects(pacmans.get(j).getPosX(), pacmans.get(j).getPosY(), pacmans.get(j).getRadius(), pacmans.get(j).getRadius())) {
					if(pacmans.get(i).getDirection() == Pacman.DOWN) {
						pacmans.get(i).setDirection(Pacman.UP);
					}
					else if(pacmans.get(i).getDirection() == Pacman.UP) {
						pacmans.get(i).setDirection(Pacman.DOWN);
					}
					else if(pacmans.get(i).getDirection() == Pacman.LEFT) {
						pacmans.get(i).setDirection(Pacman.RIGHT);
					}
					else if(pacmans.get(i).getDirection() == Pacman.RIGHT) {
						pacmans.get(i).setDirection(Pacman.LEFT);
					}
					
					if(pacmans.get(j).getDirection() == Pacman.DOWN) {
						pacmans.get(j).setDirection(Pacman.UP);
					}
					else if(pacmans.get(j).getDirection() == Pacman.UP) {
						pacmans.get(j).setDirection(Pacman.DOWN);
					}
					else if(pacmans.get(j).getDirection() == Pacman.LEFT) {
						pacmans.get(j).setDirection(Pacman.RIGHT);
					}
					else if(pacmans.get(j).getDirection() == Pacman.RIGHT) {
						pacmans.get(j).setDirection(Pacman.LEFT);
					}
				}
			}
		}
	}
}

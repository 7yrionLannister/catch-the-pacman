package ui;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import model.Game;
import model.Pacman;
import threads.PacmanThread;


public class GameZone extends Canvas {
	private int score;
	private GraphicsContext gc;
	private boolean openMouth;
	private int startAngle;
	private int angleLength;
	private Game game;
	private boolean onPauseSelected;

	public GameZone() {
		super(800, 600);
		score = 0;
		onPauseSelected = true;
		gc = super.getGraphicsContext2D();
		gc.setLineWidth(3);
		gc.setFill(Color.YELLOW);
		openMouth = false;
		startAngle = 45;
		angleLength = 270;
		super.setOnMouseClicked(new VerifyPacmanCatched());
	}

	public void redraw() {
		moveMouth();
		drawPacmans();
		bounceWhenCollide();
	}
	
	private void moveMouth() {
		gc.clearRect(0, 0,  super.getWidth(), super.getHeight());
		startAngle = openMouth? startAngle+5 : startAngle-5;
		angleLength = openMouth? angleLength-10 : angleLength+10;
		if(startAngle==0 || startAngle==45) {
			openMouth = !openMouth;
		}
	}
	
	private void drawPacmans() {
		for(Pacman pac:game.getPacmans()) {
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
	}
	
	private void bounceWhenCollide() {
		for(int i = 0; i < game.getPacmans().size(); i++) {
			for(int j = i+1; j < game.getPacmans().size(); j++) {
				Rectangle r1 = new Rectangle(game.getPacmans().get(i).getPosX(), game.getPacmans().get(i).getPosY(), game.getPacmans().get(i).getRadius(), game.getPacmans().get(i).getRadius());
				if(r1.intersects(game.getPacmans().get(j).getPosX(), game.getPacmans().get(j).getPosY(), game.getPacmans().get(j).getRadius(), game.getPacmans().get(j).getRadius())) {
					switch(game.getPacmans().get(i).getDirection()) {
						case Pacman.DOWN:
							if(!game.getPacmans().get(i).isCaught()) {
								game.getPacmans().get(i).setPosY(game.getPacmans().get(i).getPosY()-30);
							}
							game.getPacmans().get(i).setDirection(Pacman.UP);
							break;
						case Pacman.UP:
							if(!game.getPacmans().get(i).isCaught()) {
								game.getPacmans().get(i).setPosY(game.getPacmans().get(i).getPosY()+30);
							}
							game.getPacmans().get(i).setDirection(Pacman.DOWN);
							break;
						case Pacman.LEFT:
							if(!game.getPacmans().get(i).isCaught()) {
								game.getPacmans().get(i).setPosX(game.getPacmans().get(i).getPosX()+30);
							}
							game.getPacmans().get(i).setDirection(Pacman.RIGHT);
							break;
						case Pacman.RIGHT:
							if(!game.getPacmans().get(i).isCaught()) {
								game.getPacmans().get(i).setPosX(game.getPacmans().get(i).getPosX()-30);
							}
							game.getPacmans().get(i).setDirection(Pacman.LEFT);
							break;
					}
					switch(game.getPacmans().get(j).getDirection()) {
						case Pacman.DOWN:
							if(!game.getPacmans().get(j).isCaught()) {
								game.getPacmans().get(j).setPosY(game.getPacmans().get(j).isCaught()? 0: game.getPacmans().get(j).getPosY()-30);
							}
							game.getPacmans().get(j).setDirection(Pacman.UP);
							break;
						case Pacman.UP:
							if(!game.getPacmans().get(j).isCaught()) {
								game.getPacmans().get(j).setPosY(game.getPacmans().get(j).getPosY()+30);
							}
							game.getPacmans().get(j).setDirection(Pacman.DOWN);
							break;
						case Pacman.LEFT:
							if(!game.getPacmans().get(j).isCaught()) {
								game.getPacmans().get(j).setPosX(game.getPacmans().get(j).getPosX()+30);
							}
							game.getPacmans().get(j).setDirection(Pacman.RIGHT);
							break;
						case Pacman.RIGHT:
							if(!game.getPacmans().get(j).isCaught()) {
								game.getPacmans().get(j).setPosX(game.getPacmans().get(j).getPosX()-30);
							}
							game.getPacmans().get(j).setDirection(Pacman.LEFT);
							break;
					}
				}
			}
		}
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public void chooseLevelOrStartSavedGame() {
		ChoiceDialog<String> cd = new ChoiceDialog<String>(Game.EASY_LEVEL_PATH, Game.MEDIUM_LEVEL_PATH, Game.HARD_LEVEL_PATH, Game.SER_PATH);
		cd.showAndWait();
		if(cd.getResult() != null) {
				try {
					game = new Game(cd.getSelectedItem().toString());
					for(Pacman pac:game.getPacmans()) {
						PacmanThread pt = new PacmanThread(pac, this);
						pt.setDaemon(true);
						pt.start();
					}
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public boolean getOnPauseSelected() {
		return onPauseSelected;
	}
	
	public void pauseGame() {
		onPauseSelected = true;
	}
	
	public void continueGame() {
		onPauseSelected = false;
	}
	
	public class VerifyPacmanCatched implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			for(Pacman pac:game.getPacmans()) {
				if(!pac.isCaught()) {
					if(pac.getPosX() <= event.getX() && pac.getPosX()+pac.getRadius() >= event.getX()
							&& pac.getPosY() <= event.getY() && pac.getPosY()+pac.getRadius() >= event.getY()) {
						pac.setCaught(true);
					}
				}
			}
		}
	}
}


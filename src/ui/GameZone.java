package ui;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import model.Game;
import model.HallMember;
import model.Pacman;
import threads.PacmanThread;


public class GameZone extends Canvas {
	private GraphicsContext gc;
	/**The attribute indicates whether or not the pacman has to open its mouth
	 * */
	private boolean openMouth;
	/**The attribute represents the starting angle of the mouth
	 * */
	private int startAngle;
	/**The attribute represents the length of the pacman's back
	 * */
	private int angleLength;
	private Game game;
	/**The attribute indicates whether or not the game is on pause
	 * */
	private boolean onPauseSelected;
	/**The total bounces if the pacmans
	 * */
	private int score;

	/**The constructor allows to create an empty game zone
	 * */
	public GameZone() {
		super(800, 600);
		onPauseSelected = true;
		gc = super.getGraphicsContext2D();
		gc.setLineWidth(3);
		gc.setFill(Color.YELLOW);
		openMouth = false;
		startAngle = 45;
		angleLength = 270;
		super.setOnMouseClicked(new VerifyPacmanCatched());
		score = 0;
	}

	/**The method allows to refresh the interface
	 * */
	public void redraw() {
		moveMouth();
		drawPacmans();
		bounceWhenCollide();
		score = 0;
		for(Pacman pac:game.getPacmans()) {
			score += pac.getBounces();
		}
		gc.setFill(Color.BLACK);
		gc.fillText("Bounces: " + score, 10, 590);
		gc.setFill(Color.YELLOW);
	}
	
	/**The  method allows to move the pacmans' mouth
	 * */
	private void moveMouth() {
		gc.clearRect(0, 0,  super.getWidth(), super.getHeight());
		startAngle = openMouth? startAngle+5 : startAngle-5;
		angleLength = openMouth? angleLength-10 : angleLength+10;
		if(startAngle==0 || startAngle==45) {
			openMouth = !openMouth;
		}
	}
	
	/**The method allows to draw all the pacmans on the game zone
	 * */
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
	
	/**The method allows two pacmans to bounce each other
	 * */
	private void bounceWhenCollide() {
		for(int i = 0; i < game.getPacmans().size(); i++) {
			for(int j = i+1; j < game.getPacmans().size(); j++) {
				Rectangle r1 = new Rectangle(game.getPacmans().get(i).getPosX(), game.getPacmans().get(i).getPosY(), game.getPacmans().get(i).getRadius(), game.getPacmans().get(i).getRadius());
				if(r1.intersects(game.getPacmans().get(j).getPosX(), game.getPacmans().get(j).getPosY(), game.getPacmans().get(j).getRadius(), game.getPacmans().get(j).getRadius())) {
					game.getPacmans().get(i).setBounces(game.getPacmans().get(i).getBounces()+1);
					game.getPacmans().get(j).setBounces(game.getPacmans().get(j).getBounces()+1);
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
	
	/**The method allows to obtain the game that contains the pacmans
	 * @return The current game
	 * */
	public Game getGame() {
		return game;
	}
	
	/**The method allows to modify the current game
	 * @param game The new game
	 * */
	public void setGame(Game game) {
		this.game = game;
	}
	
	/**The method shows a pop-up window for the player to choose a difficulty level or the saved game
	 * */
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
	
	/**The method allows to know whether or not the game is on pause
	 * @return true if the game is paused and false in the opposite case
	 * */
	public boolean getOnPauseSelected() {
		return onPauseSelected;
	}
	
	/**The method allows to pause the game
	 * */
	public void pauseGame() {
		onPauseSelected = true;
	}
	
	/**The method allows to continue playing
	 * */
	public void continueGame() {
		onPauseSelected = false;
	}
	
	public class VerifyPacmanCatched implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			for(Pacman pac:game.getPacmans()) {
				if(!pac.isCaught()) {
					if(onPauseSelected) {
						break;
					}
					if(pac.getPosX() <= event.getX() && pac.getPosX()+pac.getRadius() >= event.getX()
							&& pac.getPosY() <= event.getY() && pac.getPosY()+pac.getRadius() >= event.getY()) {
						pac.setCaught(true);
						boolean win = true;
						for(int i = 0; i < game.getPacmans().size() && win; i++) {
							if(!game.getPacmans().get(i).isCaught()) {
								win = false;
							}
						}
						if(win) {
							pauseGame();
							Alert alert = new Alert(AlertType.INFORMATION, "Your score: " + score);
							alert.setTitle("You won!!!");
							alert.setContentText("Your score: " + score);
							alert.setHeaderText("You've caught all the pacmans, congratulations");
							alert.showAndWait();
							try {
								if(game.fitForTheHallOfTheFame(score)) {
									TextInputDialog tid = new TextInputDialog("Put your nickname here");
									tid.setTitle("Welcome to the hall of the fame!!!");
									tid.setHeaderText("Write your nickname to be in the hall of fame");
									tid.showAndWait();
									game.addHallOfFameMember(new HallMember(tid.getEditor().getText(), score));
								}
							} catch (ClassNotFoundException | IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	/**The method allows to obtain the score of the player
	 * @return The player's score
	 * */
	public int getScore() {
		return score;
	}
}


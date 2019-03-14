package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import model.Pacman;
import threads.PacmanThread;


public class PacmanController {

	@FXML
	private BorderPane window;

	@FXML
	private Pane gameArea;
	
	private Stage primaryStage;

	@FXML
	public void initialize() {
		MenuBar bar = new MenuBar();
		
		Menu file = new Menu("File");
		MenuItem load = new MenuItem("Load game");
		MenuItem save = new MenuItem("Save game");
		MenuItem exit = new MenuItem("Exit");

		file.getItems().addAll(load, save, new SeparatorMenuItem(), exit);
		FileMenuItemSelected handler = new FileMenuItemSelected();
		for (MenuItem item : file.getItems()) {
			item.addEventHandler(ActionEvent.ACTION, handler);
		}
		
		Menu view = new Menu("View");
		MenuItem leaderBoard = new MenuItem("Leader board");
		MenuItem instructions = new MenuItem("How to play");
		view.getItems().addAll(leaderBoard, new SeparatorMenuItem(), instructions);

		bar.getMenus().addAll(file, view);
		window.setTop(bar);
		
		Pacman pb = new Pacman(23, 75, 75, 50, Pacman.RIGHT, 0, 0, false);
		PacmanThread pt = new PacmanThread(pb, 0, this);
		Arc pacmansito = new Arc(0, 0, pb.getRadius(), pb.getRadius(), 45, 270);
		pacmansito.setLayoutX(pb.getPosX());
		pacmansito.setLayoutY(pb.getPosY());
		pacmansito.setType(ArcType.ROUND);
		pt.start();
		gameArea.getChildren().add(pacmansito);
	}

	public class FileMenuItemSelected implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			MenuItem item = (MenuItem)event.getSource();
			switch(item.getText()) {
			case "Load game":
				break;
			case "Save game":
				break;
			case "Exit":
				primaryStage.hide();
				break;
			}
		}	
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public Pane getGameArea() {
		return gameArea;
	}
}

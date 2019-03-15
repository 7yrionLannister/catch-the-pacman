package ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import model.Pacman;


public class PacmanController {

	@FXML
	private BorderPane window;

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
				Platform.exit();
				break;
			}
		}	
	}

	public boolean movePacman(int id, Pacman pac, boolean openMouth, boolean bounce) {
		//TODO Esto es responsabilidad del CANVAS
		/*Arc currentPacman = (Arc)gameArea.getChildren().get(id);
		currentPacman.setLayoutX(pac.getPosX());
		currentPacman.setLayoutY(pac.getPosY());

		currentPacman.setStartAngle(openMouth? currentPacman.getStartAngle()+5 : currentPacman.getStartAngle()-5);
		currentPacman.setLength(openMouth? currentPacman.getLength()-10 : currentPacman.getLength()+10);
		if(currentPacman.getStartAngle()==0 || currentPacman.getStartAngle()==45) {
			openMouth = !openMouth;
		}
		if(bounce) {
			currentPacman.setRotate(currentPacman.getRotate()+180);
		}
		*/
		return openMouth;
	}
}

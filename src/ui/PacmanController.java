package ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.management.timer.Timer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import model.Pacman;
import threads.PacmanThread;


public class PacmanController {

	@FXML
	private BorderPane window;

	private GameZone gz;
	
	private boolean windowOpened;

	@FXML
	public void initialize() {
		windowOpened = true;
		MenuBar bar = new MenuBar();

		Menu file = new Menu("File");
		MenuItem load = new MenuItem("Load game");
		MenuItem save = new MenuItem("Save game");
		MenuItem exit = new MenuItem("Exit");

		file.getItems().addAll(load, save, new SeparatorMenuItem(), exit);
		FileMenuItemSelected fmis = new FileMenuItemSelected();
		for (MenuItem item : file.getItems()) {
			item.addEventHandler(ActionEvent.ACTION, fmis);
		}

		Menu view = new Menu("View");
		MenuItem leaderBoard = new MenuItem("Leader board");
		MenuItem instructions = new MenuItem("How to play");
		view.getItems().addAll(leaderBoard, new SeparatorMenuItem(), instructions);
		

		gz = new GameZone();
		window.setCenter(gz);
		bar.getMenus().addAll(file, view);
		window.setTop(bar);
		
		RefreshInterface ri = new RefreshInterface();
		ri.setDaemon(true);
		ri.start();
	}

	public class FileMenuItemSelected implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			MenuItem item = (MenuItem)event.getSource();
			switch(item.getText()) {
			case "Load game":
				gz.chooseLevelOrStartSavedGame();
				break;
			case "Save game":
				try {
					gz.getGame().save();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "Exit":
				stopApplication();
				break;
			}
		}	
	}

	public void movePacmans() {
		try {
			gz.redraw();
		}
		catch(NullPointerException npe) {
			gz.pauseGame();
		}
	}

	public void stopApplication() {
		windowOpened  = false;
		Platform.exit();
	}

	public boolean isOpened() {
		return windowOpened;
	}

	public class RefreshInterface extends Thread {
		@Override
		public void run() {
			while (true) {
				if(!gz.getOnPauseSelected()) {
					movePacmans();
				}
				try {
					Thread.sleep(45);
				} catch (InterruptedException ex) {
				}
			}
		}
	}

	public Canvas getGameZone() {
		return gz;
	}

	@FXML
	public void continueGame(ActionEvent event) {
		gz.continueGame();
	}

	@FXML
	public void pauseGame(ActionEvent event) {
		gz.pauseGame();
	}
}

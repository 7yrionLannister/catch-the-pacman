package ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.management.timer.Timer;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.HallMember;
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
		MenuItem leaderBoard = new MenuItem("Hall of fame");
		MenuItem instructions = new MenuItem("About");
		view.getItems().addAll(leaderBoard, new SeparatorMenuItem(), instructions);
		ViewMenuItemSelected vmis = new ViewMenuItemSelected();
		for(MenuItem item:view.getItems()) {
			item.addEventHandler(ActionEvent.ACTION, vmis);
		}
		
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
	
	public class ViewMenuItemSelected implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			MenuItem item = (MenuItem)event.getSource();
			switch(item.getText()) {
			case "Hall of fame":
				try {
					if(gz.getGame() != null && gz.getGame().getHallOfFame() != null) {
						TableView tview = new TableView();
						TableColumn nicknameColumn = new TableColumn("Nickname");
						nicknameColumn.setCellValueFactory(new PropertyValueFactory<HallMember, String>("nickname"));
				        TableColumn scoreColumn = new TableColumn("Score");
				        scoreColumn.setCellValueFactory(new PropertyValueFactory<HallMember, Integer>("score"));
				        
				        ObservableList<HallMember> hm = FXCollections.observableArrayList(gz.getGame().getHallOfFame());
				        
				        Scene scene = new Scene(new Group());
				        Label label = new Label("Hall of the fame");
				        label.setFont(new Font("Arial", 20));
				        tview.getColumns().addAll(nicknameColumn, scoreColumn);
				        tview.setItems((ObservableList) hm);
				        
				        VBox vbox = new VBox();
				        vbox.setSpacing(5);
				        vbox.setPadding(new Insets(10, 0, 0, 10));
				        vbox.getChildren().addAll(label, tview);
				        
				        ((Group) scene.getRoot()).getChildren().addAll(vbox);
				        Stage stage = new Stage();
				        stage.setScene(scene);
				        stage.show();
					}
					else {
						Alert alert = new Alert(AlertType.INFORMATION, "There are no members in the hall yet");
						alert.setTitle("There are no members in the hall yet");
						alert.setContentText("Play and catch all the pacmans to be in the hall");
						alert.setHeaderText("No members in the hall yet");
						alert.showAndWait();
					}
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
				break;
			case "About":
				try {
					gz.getGame().save();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
					Thread.sleep(25);
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

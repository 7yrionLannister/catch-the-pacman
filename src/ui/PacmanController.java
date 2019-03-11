package ui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeLineCap;
import model.Pacman;

public class PacmanController {

    @FXML
    private BorderPane window;
    
    @FXML
    private Pane gameArea;
    
    @FXML
    public void initialize() {
    	MenuBar bar = new MenuBar();
    	
    	Menu file = new Menu("File");
    	MenuItem load = new MenuItem("Load game");
    	MenuItem save = new MenuItem("Save game");
    	MenuItem exit = new MenuItem("Exit");
    	
    	file.getItems().addAll(load, save, new SeparatorMenuItem(), exit);
    	
    	Menu view = new Menu("View");
    	MenuItem leaderBoard = new MenuItem("Leader board");
    	MenuItem instructions = new MenuItem("How to play");
    	view.getItems().addAll(leaderBoard, new SeparatorMenuItem(), instructions);
    	
    	bar.getMenus().addAll(file, view);file.addEventHandler(ActionEvent.ACTION , new MenuItemSelected());
    	window.setTop(bar);
    	Arc pac = new Pacman(0,0,31,31,45, 270);//pac.applyCss();
    	pac.setLayoutX(400);
    	pac.setLayoutY(400);//pac.setType(ArcType.ROUND);
    	gameArea.getChildren().add(pac);
    }

    public class MenuItemSelected implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
		}	
    }
}

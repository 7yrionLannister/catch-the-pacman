package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;

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
    	
    	Menu view = new Menu("View");
    	MenuItem leaderBoard = new MenuItem("Leader board");
    	MenuItem instructions = new MenuItem("How to play");
    	view.getItems().addAll(leaderBoard, new SeparatorMenuItem(), instructions);
    	
    	bar.getMenus().addAll(file, view);
    	window.setTop(bar);
    }

}

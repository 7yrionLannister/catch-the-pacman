package ui;

import java.util.ArrayList;

import javax.management.timer.Timer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
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
	
	private ArrayList<Pacman> pacmans;
	private boolean windowOpened;
	
	//public static double sizeX;
	//public static double sizeY;
	
	@FXML
	public void initialize() {
		windowOpened = true;
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
		
		//****************************************************************************
		pacmans = new ArrayList<>();
		Pacman pac = new Pacman(70, 0, 0, 50, Pacman.RIGHT, 0, false);
		Pacman pac1 = new Pacman(90, 70,70,70, Pacman.DOWN, 0, false);
		Pacman pac2 = new Pacman(90, 80,120,20, Pacman.RIGHT, 0, false);
		pacmans.add(pac);
		pacmans.add(pac1);
		pacmans.add(pac2);
		gz = new GameZone(pacmans);
		window.setCenter(gz);gz.redraw();window.setMaxWidth(gz.getWidth());window.setMaxHeight(gz.getHeight());
		PacmanThread pt = new PacmanThread(pac, this);
		PacmanThread pt1 = new PacmanThread(pac1, this);
		PacmanThread pt2 = new PacmanThread(pac2, this);
		pt.start();
		pt1.start();
		pt2.start();
		/*
		Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                	movePacman(false);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        */
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
				break;
			case "Save game":
				break;
			case "Exit":
				Platform.exit();
				break;
			}
		}	
	}

	public boolean movePacman(boolean bounce) {
		//TODO Esto es responsabilidad del CANVAS
		gz.redraw();
		return true;
	}
	
	public void stopApplication() {
		for(Pacman p:pacmans) {
			p.setCaught(true);
		}
	}
	
	public boolean isOpened() {
		return windowOpened;
	}
	
	public class RefreshInterface extends Thread {
		@Override
        public void run() {
            while (true) {
            	movePacman(false);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                }
            }
        }
	}
	
	public Canvas getGameZone() {
		return gz;
	}
}

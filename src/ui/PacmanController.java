package ui;

import java.util.ArrayList;

import javax.management.timer.Timer;

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
import threads.PacmanThread;


public class PacmanController {

	@FXML
	private BorderPane window;
	
	private GameZone gz;
	
	private ArrayList<Pacman> pacmans;
	private boolean windowOpened;
	
	public static double sizeX;
	public static double sizeY;
	
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
		Pacman pac = new Pacman(70, 60, 60, 50, Pacman.RIGHT, 0, false);
		Pacman pac1 = new Pacman(90, 70,70,70, Pacman.LEFT, 0, false);
		Pacman pac2 = new Pacman(90, 80,120,100, Pacman.RIGHT, 0, false);
		pacmans.add(pac);
		pacmans.add(pac1);
		pacmans.add(pac2);
		gz = new GameZone(pacmans);
		sizeX = gz.getWidth();
		sizeY = gz.getHeight();
		window.setCenter(gz);gz.redraw();window.setMaxWidth(sizeX);window.setMaxHeight(sizeY);
		PacmanThread pt = new PacmanThread(pac, this);
		PacmanThread pt1 = new PacmanThread(pac1, this);
		PacmanThread pt2 = new PacmanThread(pac2, this);
		pt.start();
		pt1.start();
		pt2.start();
		
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
		
		
		//****************************************************************************
		
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

	public boolean movePacman(boolean bounce) {
		//TODO Esto es responsabilidad del CANVAS
		/*
		currentPacman.setStartAngle(openMouth? currentPacman.getStartAngle()+5 : currentPacman.getStartAngle()-5);
		currentPacman.setLength(openMouth? currentPacman.getLength()-10 : currentPacman.getLength()+10);
		if(currentPacman.getStartAngle()==0 || currentPacman.getStartAngle()==45) {
			openMouth = !openMouth;
		}
		if(bounce) {
			currentPacman.setRotate(currentPacman.getRotate()+180);
		}
		*/
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
}

package threads;

import model.Pacman;
import ui.PacmanController;


public class PacmanThread extends Thread {
	private PacmanController pmc;
	private Pacman pacman;
	private boolean openMouth;

	public PacmanThread(Pacman pacman, PacmanController pmc) {
		this.pacman = pacman;
		this.pmc = pmc;
		openMouth = false;
	}

	@Override
	public void run() {
		//TODO Hacer esto mismo de aqui pero con CANVAS, asi estara mas elegantoso
		/*
		switch(pacman.getDirection()) {
		case Pacman.UP:
			pmc.getGameArea().getChildren().get(pacmanID).setRotate(270);
			break;
		case Pacman.DOWN:
			pmc.getGameArea().getChildren().get(pacmanID).setRotate(90);
			break;
		case Pacman.LEFT:
			pmc.getGameArea().getChildren().get(pacmanID).setRotate(180);
			break;
		case Pacman.RIGHT:
			pmc.getGameArea().getChildren().get(pacmanID).setRotate(0);
			break;
		}
		*/
		while(!pacman.isCaught() && pmc.isOpened()) {
			boolean bounce = false;	
			
			switch(pacman.getDirection()) {
			case Pacman.UP:
				pacman.setPosY(pacman.getPosY() - 5);
				if(pacman.getPosY()-pacman.getRadius() <= 0) {
					pacman.setDirection(Pacman.DOWN);
					bounce = true;
				}
				break;
			case Pacman.DOWN:
				pacman.setPosY(pacman.getPosY()+5);
				if(pacman.getPosY()+pacman.getRadius() >= pmc.sizeY) {
					pacman.setDirection(Pacman.UP);
					bounce = true;
				}
				break;
			case Pacman.LEFT:
				pacman.setPosX(pacman.getPosX()-5);
				if(pacman.getPosX()-pacman.getRadius() <= 0) {
					pacman.setDirection(Pacman.RIGHT);
					bounce = true;
				}
				break;
			case Pacman.RIGHT:
				pacman.setPosX(pacman.getPosX()+5);
				if(pacman.getPosX()+pacman.getRadius() >= pmc.sizeX) {
					pacman.setDirection(Pacman.LEFT);
					bounce = true;
				}
				break;
			}
			//TODO Hacer el equivalente a esto pero en CANVAS
			//openMouth = pmc.movePacman(bounce);
			try {
				sleep(pacman.getWaitingTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Pacman getPacman() {
		return pacman;
	}

	public void setPacman(Pacman pacman) {
		this.pacman = pacman;
	}
}

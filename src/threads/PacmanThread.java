package threads;

import model.Pacman;
import ui.PacmanController;


public class PacmanThread extends Thread {
	private PacmanController pmc;
	private Pacman pacman;
	public PacmanThread(Pacman pacman, PacmanController pmc) {
		this.pacman = pacman;
		this.pmc = pmc;
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
			switch(pacman.getDirection()) {
			case Pacman.UP:
				pacman.setPosY(pacman.getPosY() - 5);
				if(pacman.getPosY()/*-pacman.getRadius()*/ <= 0) {
					pacman.setDirection(Pacman.DOWN);
				}
				break;
			case Pacman.DOWN:
				pacman.setPosY(pacman.getPosY()+5);
				if(pacman.getPosY()+pacman.getRadius()-7 >= pmc.getGameZone().getHeight()) {
					pacman.setDirection(Pacman.UP);
				}
				break;
			case Pacman.LEFT:
				pacman.setPosX(pacman.getPosX()-5);
				if(pacman.getPosX()/*-pacman.getRadius()*/ <= 0) {
					pacman.setDirection(Pacman.RIGHT);
				}
				break;
			case Pacman.RIGHT:
				pacman.setPosX(pacman.getPosX()+5);
				if(pacman.getPosX()+pacman.getRadius()-7 >= pmc.getGameZone().getWidth()) {
					pacman.setDirection(Pacman.LEFT);
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

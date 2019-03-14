package threads;

import model.Pacman;
import ui.PacmanController;


public class PacmanThread extends Thread {
	private PacmanController pmc;
	private Pacman pacman;
	private int pacmanID;
	
	public PacmanThread(Pacman pacman, int pacmanID, PacmanController pmc) {
		this.pacman = pacman;
		this.pacmanID = pacmanID;
		this.pmc = pmc;
	}
	
	@Override
	public void run() {
		while(!pacman.isCaught()) {
			//*******************************************************************************
			//TODO esto se supone que debe mover la boca, pero ahora hay que hacer que lo haga el paquete ui, no el paquete threads ni el model
			/*setStartAngle(openMouth? getStartAngle()+5 : getStartAngle()-5);
			setLength(openMouth? getLength()-10 : getLength()+10);
			if(getStartAngle()==0 || getStartAngle()==45) {
				openMouth = !openMouth;
			}
			*/
			
			switch(pacman.getDirection()) {
			case Pacman.UP:
				pacman.setPosY(pacman.getPosY()-5);
				if(pacman.getPosY()-pacman.getRadius() == 0) {
					pacman.setDirection(Pacman.DOWN);
					pacman.setVisionAngle(pacman.getVisionAngle()+180);
				}
				break;
			case Pacman.DOWN:
				pacman.setPosY(pacman.getPosY()+5);
				if(pacman.getPosY()+pacman.getRadius() == pmc.getGameArea().getLayoutBounds().getMaxY()) {
					pacman.setDirection(Pacman.UP);
					pacman.setVisionAngle(pacman.getVisionAngle()+180);
				}
				break;
			case Pacman.LEFT:
				pacman.setPosX(pacman.getPosX()-5);
				if(pacman.getPosX()-pacman.getRadius() == 0) {
					pacman.setDirection(Pacman.RIGHT);
					pacman.setVisionAngle(pacman.getVisionAngle()+180);
				}
				break;
			case Pacman.RIGHT:
				pacman.setPosX(pacman.getPosX()+5);
				if(pacman.getPosX()+pacman.getRadius() == pmc.getGameArea().getLayoutBounds().getMaxX()) {
					pacman.setDirection(Pacman.LEFT);
					pacman.setVisionAngle(pacman.getVisionAngle()+180);
				}
				break;
			}
			try {
				sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getPacmanID() {
		return pacmanID;
	}

	public void setPacmanID(int pacmanID) {
		this.pacmanID = pacmanID;
	}

	public Pacman getPacman() {
		return pacman;
	}

	public void setPacman(Pacman pacman) {
		this.pacman = pacman;
	}
}

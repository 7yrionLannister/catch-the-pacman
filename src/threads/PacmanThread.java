package threads;

import model.Pacman;
import ui.GameZone;


public class PacmanThread extends Thread {
	public final static int DELTA = 1;
	private GameZone gamez;
	private Pacman pacman;
	public PacmanThread(Pacman pacman, GameZone gamez) {
		this.pacman = pacman;
		this.gamez = gamez;
	}

	@Override
	public void run() {
		while(!pacman.isCaught()) {
			if(!gamez.getOnPauseSelected()) {
				moveForward();
			}
			try {
				sleep(pacman.getWaitingTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void moveForward() {
		switch(pacman.getDirection()) {
		case Pacman.UP:
			pacman.setPosY(pacman.getPosY() - DELTA);
			if(pacman.getPosY() <= 0) {
				pacman.setDirection(Pacman.DOWN);
			}
			break;
		case Pacman.DOWN:
			pacman.setPosY(pacman.getPosY()+DELTA);
			if(pacman.getPosY()+pacman.getRadius() >= gamez.getHeight()) {
				pacman.setDirection(Pacman.UP);
			}
			break;
		case Pacman.LEFT:
			pacman.setPosX(pacman.getPosX()-DELTA);
			if(pacman.getPosX() <= 0) {
				pacman.setDirection(Pacman.RIGHT);
			}
			break;
		case Pacman.RIGHT:
			pacman.setPosX(pacman.getPosX()+DELTA);
			if(pacman.getPosX()+pacman.getRadius() >= gamez.getWidth()) {
				pacman.setDirection(Pacman.LEFT);
			}
			break;
		}
	}

	public Pacman getPacman() {
		return pacman;
	}

	public void setPacman(Pacman pacman) {
		this.pacman = pacman;
	}
}

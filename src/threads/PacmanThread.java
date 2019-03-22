package threads;

import model.Pacman;
import ui.GameZone;


public class PacmanThread extends Thread {
	/**The constant represents the number of pixels that the pacman moves each time
	 * */
	public final static int DELTA = 6;
	/**The game zone where this pacman is contained
	 * */
	private GameZone gamez;
	/**The pacman this thread is going to move
	 * */
	private Pacman pacman;
	
	/**The constructor allows to create a new Thread to move one pacman
	 * @param pacman The pacman that is going to be moved
	 * @param gamez The game zone where this pacman is contained
	 * */
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
	
	/**The method allows to move the pacman
	 * */
	private void moveForward() {
		switch(pacman.getDirection()) {
		case Pacman.UP:
			pacman.setPosY(pacman.getPosY() - DELTA);
			if(pacman.getPosY() <= 0) {
				pacman.setBounces(pacman.getBounces()+1);
				pacman.setDirection(Pacman.DOWN);
			}
			break;
		case Pacman.DOWN:
			pacman.setPosY(pacman.getPosY()+DELTA);
			if(pacman.getPosY()+pacman.getRadius() >= gamez.getHeight()) {
				pacman.setBounces(pacman.getBounces()+1);
				pacman.setDirection(Pacman.UP);
			}
			break;
		case Pacman.LEFT:
			pacman.setPosX(pacman.getPosX()-DELTA);
			if(pacman.getPosX() <= 0) {
				pacman.setBounces(pacman.getBounces()+1);
				pacman.setDirection(Pacman.RIGHT);
			}
			break;
		case Pacman.RIGHT:
			pacman.setPosX(pacman.getPosX()+DELTA);
			if(pacman.getPosX()+pacman.getRadius() >= gamez.getWidth()) {
				pacman.setBounces(pacman.getBounces()+1);
				pacman.setDirection(Pacman.LEFT);
			}
			break;
		}
	}

	/**The method allows to obtain the pacman that this thread is moving
	 * @return The pacman that this thread is moving
	 * */
	public Pacman getPacman() {
		return pacman;
	}

	/**The method allows to change the pacman that this thread is moving
	 * @param pacman The new pacman for this thread
	 * */
	public void setPacman(Pacman pacman) {
		this.pacman = pacman;
	}
}

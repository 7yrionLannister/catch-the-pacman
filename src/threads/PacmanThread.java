package threads;

import model.Pacman;


public class PacmanThread extends Thread {
	private Pacman pacman;
	
	public PacmanThread(Pacman pacman) {
		this.pacman = pacman;
	}
	
	@Override
	public void run() {
		while(!pacman.isCaught()) {
			pacman.move();
			try {
				sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

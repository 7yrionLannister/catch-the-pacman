package model;

import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;


public class Pacman extends Arc {
	public final static byte UP = 1;
	public final static byte DOWN = 2;
	public final static byte LEFT = 3;
	public final static byte RIGHT = 4;
	
	public final static int radius = 25;
	private boolean openMouth;
	private boolean caught;
	private byte direction;
	
	public Pacman(byte direction) {
		super(0, 0, radius, radius, 45, 270);
		super.setType(ArcType.ROUND);
		openMouth = false;
		caught = false;
		this.direction = direction;
		switch(direction) {
		case UP:
			setRotate(270);
			break;
		case DOWN:
			setRotate(90);
			break;
		case LEFT:
			setRotate(180);
			break;
		case RIGHT:
			setRotate(0);
			break;
		}
	}
	
	public boolean isCaught() {
		return caught;
	}

	public void setCaught(boolean caught) {
		this.caught = caught;
	}
	
	public byte getDirection() {
		return direction;
	}
	
	public void setDirection(byte direction) {
		this.direction = direction;
	}

	public void move() {
		setStartAngle(openMouth? getStartAngle()+5 : getStartAngle()-5);
		setLength(openMouth? getLength()-10 : getLength()+10);
		if(getStartAngle()==0 || getStartAngle()==45) {
			openMouth = !openMouth;
		}
		
		switch(direction) {
		case UP:
			setLayoutY(getLayoutY()-5);
			if(getLayoutY()-radius == 0) {
				setDirection(DOWN);
				setRotate(getRotate()+180);
			}
			break;
		case DOWN:
			setLayoutY(getLayoutY()+5);
			if(getLayoutY()+radius == getParent().getLayoutBounds().getMaxY()) {
				setDirection(UP);
				setRotate(getRotate()+180);
			}
			break;
		case LEFT:
			setLayoutX(getLayoutX()-5);
			if(getLayoutX()-radius == 0) {
				setDirection(RIGHT);
				setRotate(getRotate()+180);
			}
			break;
		case RIGHT:
			setLayoutX(getLayoutX()+5);
			if(getLayoutX()+radius == getParent().getLayoutBounds().getMaxX()) {
				setDirection(LEFT);
				setRotate(getRotate()+180);
			}
			break;
		}
	}
}

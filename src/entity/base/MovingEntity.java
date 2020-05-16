package entity.base;

import logic.Direction;
import logic.GameController;

public abstract class MovingEntity extends Entity {
	private Direction direction;
	
	private int frame = 0;

	public MovingEntity() {
	}

	public boolean move() {
		int targetx = getX();
		int targety = getY();

		switch (getDirection()) {
		case LEFT:
			targetx -= 1;
			break;
		case UP:
			targety -= 1;
			break;
		case RIGHT:
			targetx += 1;
			break;
		case DOWN:
			targety += 1;
			break;
		default:
			break;
		}
		if (GameController.getCurrentMap().isMovePossible(targetx, targety, this) && (frame % 12 == 11)) {
			frame = 0;
			GameController.getCurrentMap().addEntity(this, targetx, targety);
			return true;
		} else {
			frame+=1;
			return false;
		}
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}
	
	
}

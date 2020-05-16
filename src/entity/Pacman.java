package entity;

import entity.base.MovingEntity;
import logic.Direction;
import logic.GameController;

public class Pacman extends MovingEntity {
	public Pacman() {
		setDirection(Direction.NONE);
	}

	@Override
	public int getSymbol() {
		return 0;
	}
	
	public boolean movePossible(Direction dir) {
		int targetx = getX();
		int targety = getY();

		switch (dir) {
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

		if (GameController.getCurrentMap().isMovePossible(targetx, targety, this)) {
			return true;
		}
		return false;
	}
}

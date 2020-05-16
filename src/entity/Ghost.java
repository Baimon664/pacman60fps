package entity;

import entity.base.MovingEntity;
import logic.Direction;
import logic.GameController;
import logic.Sprites;

public class Ghost extends MovingEntity {
	public boolean dead() {
		this.setX(8);
		this.setY(9);
		setDirection(Direction.UP);
		return true;
	}

	public Ghost() {
		setDirection(Direction.UP);
	}

	@Override
	public int getSymbol() {
		if (GameController.isPowerUp()) {
			if (GameController.getPowerUpTimeCount() >= 600 * GameController.getPowerupCount() - 180) {
				return Sprites.BLINKGHOST;
			}
			return Sprites.BLUEGHOST;
		}
		return Sprites.GHOST1;

	}

	public int getSymbol2() {
		if (GameController.isPowerUp()) {
			if (GameController.getPowerUpTimeCount() >= 600 * GameController.getPowerupCount() - 180) {
				return Sprites.BLINKGHOST;
			}
			return Sprites.BLUEGHOST;
		}
		return Sprites.GHOST2;

	}
	
	public boolean movePossible() {
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

		if (GameController.getCurrentMap().isMovePossible(targetx, targety, this)) {
			return true;
		}
		return false;
	}

}

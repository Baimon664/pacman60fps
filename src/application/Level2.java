package application;

import java.util.ArrayList;

import entity.base.Entity;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.Cell;
import logic.Direction;
import logic.GameController;
import logic.samePacmanDirectionException;

public class Level2 {

	private static int board_width;
	private static int board_height;

	private static int draw_originx;
	private static int draw_originy;

	private static String[][] gameMap;

	private static Thread update;

	private static Scene level2Scene;

	public static void level2Scene() {
		gameMap = CSVParser.readCSV("levelProgMeth.csv");
		GameController.IntializeMap(gameMap, 9, 13, 13, 10, 14, 10);
		board_width = GameController.getCurrentMap().getWidth() * 24;
		board_height = GameController.getCurrentMap().getHeight() * 24;
		draw_originx = 360 - board_width / 2;
		draw_originy = 252 - board_height / 2;
		StackPane root = new StackPane();

		level2Scene = new Scene(root, 720, 504);

		Canvas canvas = new Canvas(720, 504);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);

		/* Register Event */
		addEventListener(level2Scene, gc);

		update = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					GameController.movePacman();
					GameController.killcheck();
					GameController.moveGhost1();
					GameController.moveGhost2();
					GameController.killcheck();
					ArrayList<Entity> allEntity = GameController.getCurrentMap().getAllEntity();
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							drawGameBoard(gc);
						}
					});
					try {
						Thread.sleep(1000/60);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

	}

	private static void drawGameBoard(GraphicsContext gc) {

		/* Draw Background */
		gc.setFill(Color.rgb(21, 24, 31));
		gc.fillRect(0, 0, 720, 504);

		/* Draw Playable Field Background */
		gc.setFill(Color.BLACK);
		gc.fillRect(draw_originx, draw_originy, board_width, board_height);

		Cell[][] gameBoard = GameController.getCurrentMap().getMap();

		int x = 0;
		int y = 0;

		for (Cell[] row : gameBoard) {
			x = 0;
			for (Cell c : row) {
				if (!c.IsEmpty()) {
					DrawUtil.drawSprite(gc, draw_originx + x * 24, draw_originy + y * 24, c.getSymbol());
				}
				x += 1;
			}
			y += 1;
		}
		DrawUtil.drawGhost(gc, draw_originx + GameController.getGhost1X() * 24,
				draw_originy + GameController.getGhost1Y() * 24, GameController.getGhost1Sprite(),GameController.getGhost1Direction());
		DrawUtil.drawGhost(gc, draw_originx + GameController.getGhost2X() * 24,
				draw_originy + GameController.getGhost2Y() * 24, GameController.getGhost2Sprite(),GameController.getGhost2Direction());
		DrawUtil.drawPacman(gc, draw_originx + GameController.getPacmanX() * 24,
				draw_originy + GameController.getPacmanY() * 24, GameController.getPacmanDirection());
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font("Candara", FontWeight.NORMAL, 20));
		gc.fillText("Score : " + GameController.getScorePointText(), 600, 20);
		/* If lose, draw Congrats */
		if (GameController.isGameLose()) {
			/* Darken the Screen */
			Sound.stopWalkSound();
			Sound.playDieSound();
			gc.setGlobalAlpha(0.8);
			gc.setFill(Color.BLACK);
			gc.fillRect(draw_originx, draw_originy, board_width, board_height);
			/* Revert the Alpha */
			gc.setGlobalAlpha(1);
			/* Draw Congratulations */
			DrawUtil.drawGameOver(gc, 360, 252);
			getUpdate().suspend();
		}
		/* If win, draw Congrats */
		if (GameController.getScore() == 0) {
			/* Darken the Screen */
			Sound.stopWalkSound();
			Sound.playWinSound();
			GameController.setGameWin(true);
			gc.setGlobalAlpha(0.8);
			gc.setFill(Color.BLACK);
			gc.fillRect(draw_originx, draw_originy, board_width, board_height);
			/* Revert the Alpha */
			gc.setGlobalAlpha(1);
			/* Draw Congratulations */
			DrawUtil.drawCongrats(gc, 360, 252);
			getUpdate().suspend();
		}
		/* Pause Game */
		if (GameController.isGamePause()) {
			Sound.stopWalkSound();
			gc.setGlobalAlpha(0.8);
			gc.setFill(Color.BLACK);
			gc.fillRect(draw_originx, draw_originy, board_width, board_height);
			gc.setGlobalAlpha(1);
			DrawUtil.drawGamePause(gc, 360, 252);
			getUpdate().suspend();
		}
	}

	private static void addEventListener(Scene s, GraphicsContext gc) {
		s.setOnKeyPressed((event) -> {
			KeyCode keycode = event.getCode();
			switch (keycode) {
			case A:
				try {
					GameController.setPacmanDirection(Direction.LEFT);
				} catch (samePacmanDirectionException e) {
					// TODO Auto-generated catch block
				}
				break;
			case D:
				try {
					GameController.setPacmanDirection(Direction.RIGHT);
				} catch (samePacmanDirectionException e) {
					// TODO Auto-generated catch block
				}
				break;
			case W:
				try {
					GameController.setPacmanDirection(Direction.UP);
				} catch (samePacmanDirectionException e) {
					// TODO Auto-generated catch block
				}
				break;
			case S:
				try {
					GameController.setPacmanDirection(Direction.DOWN);
				} catch (samePacmanDirectionException e) {
					// TODO Auto-generated catch block
				}
				break;
			case R:
				Sound.stopDieSound();
				Sound.stopWinSound();
				Sound.stopWalkSound();
				GameController.setStart(false);
				GameController.IntializeMap(gameMap, 9, 13, 13, 10, 14, 10); // Reset Map
				getUpdate().resume();
				break;
			case ESCAPE:
				if ((GameController.isGameLose()) || (GameController.isGameWin()) || (GameController.isGamePause())) {
					Sound.stopDieSound();
					Sound.stopWinSound();
					Main.getWindow().setScene(Main.getMenuScene());
					Sound.playIntroSound();
					break;
				} else if (GameController.isGamePause() == false) {
					GameController.setGamePause(true);
					break;
				}
			case SPACE:
				if (GameController.isGamePause()) {
					GameController.setGamePause(false);
					Sound.playWalkSound();
					getUpdate().resume();
				}
			default:
				System.out.println("Invalid Key.");
				break;
			}
		});
	}

	public static Scene getLevel2Scene() {
		return level2Scene;
	}

	public static void startUpdate() {
		getUpdate().start();
	}

	public static Thread getUpdate() {
		return update;
	}
}

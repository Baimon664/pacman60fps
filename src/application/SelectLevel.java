package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SelectLevel {
	private static Scene selectLevel;

	public static void selectLevelScene() {
		Pane root = new Pane();
		BackgroundImage backgroundImage = new BackgroundImage(DrawUtil.getBg(), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, null, null);
		Background background = new Background(backgroundImage);
		root.setBackground(background);
		Text selectLevelText = new Text("SelectLevel");
		selectLevelText
		.setStyle("-fx-font-size: 50 px; -fx-font-family:\"Candara\";-fx-fill: #ffff00; -fx-font-weight:bold");
		selectLevelText.setX(320);
		selectLevelText.setY(60);
		ImageView level1 = DrawUtil.drawLevel1();
		level1.setFitHeight(200);
		level1.setFitWidth(190.94);
		level1.setX(150);
		level1.setY(110);
		Text level1Text = new Text("Level1");
		level1Text
				.setStyle("-fx-font-size: 30 px; -fx-font-family:\"Candara\";-fx-fill: #ffff00; -fx-font-weight:bold");
		level1Text.setX(210);
		level1Text.setY(360);
		level1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Sound.stopIntroSound();
				Level1.level1Scene();
				Main.getWindow().setScene(Level1.getLevel1Scene());
				Level1.startUpdate();
			}
		});
		level1Text.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Sound.stopIntroSound();
				Level1.level1Scene();
				Main.getWindow().setScene(Level1.getLevel1Scene());
				Level1.startUpdate();
			}
		});
		level1Text.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				level1Text.setStyle(
						"-fx-font-size: 30 px; -fx-font-family:\"Candara\";-fx-fill: #ffffff; -fx-font-weight:bold");
			}
		});

		level1Text.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				level1Text.setStyle(
						"-fx-font-size: 30px; -fx-font-family:\"Candara\";-fx-fill: #ffff00; -fx-font-weight:bold");
			}
		});
		ImageView level2 = DrawUtil.drawLevel2();
		level2.setFitHeight(200);
		level2.setFitWidth(292.86);
		level2.setX(450);
		level2.setY(110);
		Text level2Text = new Text("Level2");
		level2Text
				.setStyle("-fx-font-size: 30 px; -fx-font-family:\"Candara\";-fx-fill: #ffff00; -fx-font-weight:bold");
		level2Text.setX(561);
		level2Text.setY(360);
		level2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Sound.stopIntroSound();
				Level2.level2Scene();
				Main.getWindow().setScene(Level2.getLevel2Scene());
				Level2.startUpdate();
			}
		});
		level2Text.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Sound.stopIntroSound();
				Level2.level2Scene();
				Main.getWindow().setScene(Level2.getLevel2Scene());
				Level2.startUpdate();
			}
		});
		level2Text.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				level2Text.setStyle(
						"-fx-font-size: 30 px; -fx-font-family:\"Candara\";-fx-fill: #ffffff; -fx-font-weight:bold");
			}
		});

		level2Text.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				level2Text.setStyle(
						"-fx-font-size: 30px; -fx-font-family:\"Candara\";-fx-fill: #ffff00; -fx-font-weight:bold");
			}
		});

		root.getChildren().addAll(level1, level2, level1Text, level2Text,selectLevelText);

		selectLevel = new Scene(root, 854, 480);

		selectLevel.setOnKeyPressed((event) -> {
			KeyCode keycode = event.getCode();
			switch (keycode) {
			case ESCAPE:
				Main.getWindow().setScene(Main.getMenuScene());
				break;
			default:
				System.out.println("Invalid Key.");
				break;
			}
		});
	}

	public static Scene getSelectLevel() {
		return selectLevel;
	}

}

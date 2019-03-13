package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("pacman.fxml"));
		Parent root = loader.load();
		PacmanController pc = loader.getController();
		pc.setPrimaryStage(stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Catch the pacman!");
		stage.setResizable(false);
		stage.show();
	}
}

package breakout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Breakout extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Breakout Game");
		BorderPane root = new BorderPane();
		
		BallWorld world = new BallWorld(); 
		root.setCenter(world);
		
		Scene scene = new Scene(root, 800, 600); 
		stage.setScene(scene);
		world.start();
		stage.show();
		
	}

}

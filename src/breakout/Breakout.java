package breakout;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Breakout extends Application {

	private static Stage primaryStage; 
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		// ******************************************* MAIN SCREEN *******************************************
		primaryStage = stage;
		stage.setTitle("Breakout Game");
		BorderPane root = new BorderPane();
		
		BallWorld world = new BallWorld(); 
		root.setCenter(world);
		
		// ******************************************* TITLE SCREEN *******************************************
		BorderPane title = new BorderPane();
		Text breakout = new Text("BREAK OUT"); 
		breakout.setFont(new Font(100));
		Button play = new Button("Start Game"); 
		
		play.setOnAction(e -> {
			Scene playScene = new Scene(root, 800, 600); 
			primaryStage.setScene(playScene);
			world.start(); 
			
		});
		
		VBox center = new VBox(20);
        center.getChildren().addAll(breakout, play);
        center.setAlignment(Pos.CENTER);
        
        title.setCenter(center);
		
		Scene scene = new Scene(title, 800, 600); 
		stage.setScene(scene);
		stage.show();
		
		
	}

}
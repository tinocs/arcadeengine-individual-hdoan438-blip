package breakout;
import engine.World;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BallWorld extends World {

	// ******************************** ATTRIBUTES ******************************** 
	Score score; 
	int level = 1; 
	int lives = 3; 
	boolean isPaused = true; 
	private Text pausedMessage;
	
	public BallWorld() {
		setPrefSize(800, 600);
	}
	
	@Override
	public void act(long now) {
		
		if (getObjects(Brick.class).isEmpty()) {
			level++; 
			if (level > 2) {
				gameOver(true); 
			}
			else {
				int count = (level == 1) ? 5 : 10; 
				for (int i = 0; i < count; i++) {
					Brick brick = new Brick(); 
					this.add(brick);
					int x = (int) (Math.random() * (this.getWidth() - brick.getWidth()));
					int y = (int) (Math.random() * (this.getHeight() / 2 - brick.getHeight()));
					brick.setX(x);
					brick.setY(y); 
				}
			}
		}
		
		this.setOnKeyPressed(e -> {
		    if (e.getCode() == KeyCode.SPACE && isPaused) {
		    	isPaused = false;
		    	this.start();
		    	
		    	if (pausedMessage != null) {
		            this.getChildren().remove(pausedMessage);
		            pausedMessage = null;
		    	}
		    }
		    else if (e.getCode() == KeyCode.SPACE && !isPaused) {
		    	isPaused = true;
		    	this.stop(); 
		    	
		    	pausedMessage = new Text("PRESS SPACE TO RESUME");
		    	pausedMessage.setFont(new Font(40));
		    	pausedMessage.setX(this.getWidth() / 2);
		    	pausedMessage.setY(this.getHeight() / 2);
		    	this.getChildren().add(pausedMessage);
		    }
		});
		
	}

	
	private void gameOver(boolean won) {
		stop(); 
		Text message = new Text(won ? "YOU WIN THE GAME!" : "GAME OVER");
		message.setFont(new Font(100));
		this.getChildren().add(message); 
		this.requestFocus();
		
		this.requestFocus();
		
		this.setOnKeyPressed(e -> {
			// NEED HELP: TAKE THE USER BACK TO THE MAIN MENU 
		});
	}

	@Override
	public void onDimensionsInitialized() {
	
		
		Ball ball = new Ball();
		Paddle paddle = new Paddle();

		this.add(ball);
		this.add(paddle);
		
		double centerX = (this.getWidth() - ball.getWidth()) / 2;
		double centerY = (this.getHeight() - ball.getHeight()) / 2;
		
		ball.setX(300);
		ball.setY(300);
		
		paddle.setX(centerX);
		paddle.setY(centerY);
		
		setOnMouseMoved(new EventHandler<MouseEvent>() {
		   
			@Override
		    public void handle(MouseEvent event) {
		       paddle.setX(event.getX());
		    }
		});
		
		score = new Score(); 
		
		score.setX(this.getWidth() - score.getBoundsInLocal().getWidth() - 30);
		score.setY(30);
		
		this.getChildren().add(score); 

	}

	public Score getScore() {
		return score; 
	}
	
	public void setPaused(boolean pause) {
		this.isPaused = pause; 
	}
	
	public boolean getPaused() {
		return this.isPaused; 
	}
	
}

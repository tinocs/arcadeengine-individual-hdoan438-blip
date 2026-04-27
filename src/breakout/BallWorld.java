package breakout;
import engine.World;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BallWorld extends World {

	// ******************************** ATTRIBUTES ******************************** 
	Score score; 
	int level = 1; 
	
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
				createBricks(); 
			}
		}
		
	}
	
	public void createBricks() {
		int count = (level == 1) ? 1 : 1; 
		for (int i = 0; i < count; i++) {
			Brick brick = new Brick(); 
			this.add(brick);
			int x = (int) (Math.random() * (this.getWidth() - brick.getWidth()));
			int y = (int) (Math.random() * (this.getHeight() / 2 - brick.getHeight()));
			brick.setX(x);
			brick.setY(y); 
		}
	}
	
	private void gameOver(boolean won) {
		stop(); 
		Text message = new Text(won ? "YOU WIN THE GAME!" : "GAME OVER");
		message.setFont(new Font(100));
		this.getChildren().add(message); 
		this.requestFocus();
		
		this.requestFocus();
		
		this.setOnKeyPressed(e -> {
			System.exit(0);
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
	
}

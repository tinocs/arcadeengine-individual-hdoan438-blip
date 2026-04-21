package breakout;
import engine.World;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class BallWorld extends World {

	public BallWorld() {
		setPrefSize(800, 600);
	}
	
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDimensionsInitialized() {
		Ball ball = new Ball();
		Paddle paddle = new Paddle(); 
		
		this.add(ball);
		this.add(paddle);
		
		double centerX = (this.getWidth() - ball.getWidth()) / 2;
		double centerY = (this.getHeight() - ball.getHeight()) / 2;
		
		ball.setX(centerX);
		ball.setX(centerY);
		
		paddle.setX(centerX);
		paddle.setY(centerY);
		
		setOnMouseMoved(new EventHandler<MouseEvent>() {
		   
			@Override
		    public void handle(MouseEvent event) {
		       paddle.setX(event.getX());
		    }
		});
		

	}

	
}

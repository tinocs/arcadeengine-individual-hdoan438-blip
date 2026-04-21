package breakout;
import engine.Actor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;	

public class Ball extends Actor {

	// ****************************************** ATTRIBUTES ****************************************** 
	double dx;
	double dy; 
	
	// ****************************************** CONSTRUCTOR ******************************************
	public Ball() {
		this.dx = 5;
		this.dy = 5;
		String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();

		Image img = new Image(path);
		this.setImage(img);
	}
	
	// ****************************************** METHODS ******************************************
	
	@Override
	public void act(long now) {
		this.move(dx, dy);
		
		if (getX() <= 0 || getX() + getFitWidth() >= getWidth()) {
			dx *= -1;
		}
		
		if (getY() <= 0 || getY() + getFitHeight() >= getHeight()) {
			dy *= -1;
		}
		
		for (Paddle paddle : getWorld().getObjects(Paddle.class)) {
	        if (this.intersects(paddle.getBoundsInParent())) {

	            dy *= -1;
	        }
	    }
	}
	
}

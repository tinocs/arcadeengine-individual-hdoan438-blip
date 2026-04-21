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
		String path = getClass().getClassLoader().getResource("resources/fireBall.png").toString();
		Image img = new Image(path);
		this.setImage(img);
	}
	
	// ****************************************** METHODS ******************************************
	
	@Override
	public void act(long now) {
		this.move(dx, dy);
		
		if (this.getX() <= 0 || this.getX() + this.getFitWidth() >= getWidth()) {
			dx *= -1;
		}
		
		if (this.getY() <= 0 || this.getY() + this.getFitHeight() >= getHeight()) {
			dy *= -1;
		}
	}
	
}

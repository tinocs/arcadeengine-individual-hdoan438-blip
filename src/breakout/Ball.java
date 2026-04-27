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
		this.dx = 2;
		this.dy = 2;
		String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();

		Image img = new Image(path);
		this.setImage(img);
	}
	
	// ****************************************** METHODS ******************************************
	
	@Override
	public void act(long now) {
		this.move(dx, dy);
		
		// ***************** BOUNCE *****************
		if (getX() <= 0 || getX() + this.getWidth() >= getWorld().getWidth()) {
			dx *= -1;
		}
		
		if (getY() <= 0 || getY() + this.getHeight() >= getWorld().getHeight()) {
			dy *= -1;
		}
		
		// ***************** PADDLE *****************
		for (Paddle paddle : getWorld().getObjects(Paddle.class)) {
	        if (this.intersects(paddle.getBoundsInParent())) {

	            dy *= -1;
	        }
	    }
		
		// ***************** BRICK *****************
		for (Brick brick : getWorld().getObjects(Brick.class)) {
	        if (this.intersects(brick.getBoundsInParent())) {

	        	if (this.getX() >= brick.getX() && this.getX() <= brick.getX() + brick.getFitWidth()) {
	        		dy *= -1;
	        	}
	        	else if (this.getY() >= brick.getY() && this.getY() <= brick.getY() + brick.getFitHeight()) {
	        		dx *= -1;
	        	}
	        	else {
	        		dy *= -1;
	        		dx *= -1;
	        	}
	        	
	        	getWorld().remove(brick); 

	        }
	    }
		
		// ***************** SCORE *****************
		Brick brick = getOneIntersectingObject(Brick.class);
		
		if (brick != null) {
		    BallWorld world = (BallWorld) getWorld();
		    int current = world.score.getScore();
		    world.score.setScore(current + 100);
		    

		    getWorld().remove(brick); 
		}
		
		if (getY() >= getWorld().getHeight() - 1) {
			BallWorld world = (BallWorld) getWorld();
			int current = world.score.getScore();
		    world.score.setScore(current - 1000);
		}
		
	}
	
}

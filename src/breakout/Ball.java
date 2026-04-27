package breakout;
import engine.Actor;
import engine.Sound;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;	

public class Ball extends Actor {

	// ****************************************** ATTRIBUTES ****************************************** 
	double dx;
	double dy; 
	private Sound bounceSound;
	private Sound hitSound;
	private Sound looseLife; 
	
	// ****************************************** CONSTRUCTOR ******************************************
	public Ball() {
		this.dx = 5;
		this.dy = 5;
		String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();

		Image img = new Image(path);
		this.setImage(img);
		
		bounceSound = new Sound("ballbounceresources/ball_bounce.wav");
		hitSound = new Sound("ballbounceresources/brick_hit.wav");
		looseLife = new Sound("ballbounceresources/lose_life.wav");
	}
	
	// ****************************************** METHODS ******************************************
	
	@Override
	public void act(long now) {
		this.move(dx, dy);
		
		// ***************** BOUNCE *****************
		if (getX() <= 0 || getX() + this.getWidth() >= getWorld().getWidth()) {
			dx *= -1;
			bounceSound.play(); 
		}
		
		if (getY() <= 0 || getY() + this.getHeight() >= getWorld().getHeight()) {
			dy *= -1;
			bounceSound.play(); 
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
	        	
	        	hitSound.play(); 
	        	
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
		
		// ***************** PAUSED *****************
		if (((BallWorld) this.getWorld()).getPaused()) {
			if (!getWorld().getObjects(Paddle.class).isEmpty()) {
				Paddle paddle = getWorld().getObjects(Paddle.class).get(0);
				this.setX(paddle.getX() + (paddle.getWidth() / 2) - (this.getWidth() / 2));
	            this.setY(paddle.getY() - this.getHeight());
			}
		}
		else {
			this.move(dx, dy); 
		}
		
		// ***************** BOTTOM OF THE WORLD & LIVES *****************
		if (getY() >= getWorld().getHeight() - 1) {
			looseLife.play(); 
			BallWorld world = (BallWorld) this.getWorld();
			world.ballLost();
		}
		
	}
	
}

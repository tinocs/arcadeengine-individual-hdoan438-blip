/*
Name:     Hang Doan 
Date:      Apr 10, 2026
Period:    1

Is this lab fully working? Yes 
If resubmitting, explain what was wrong and what you fixed.
*/

package engine;

import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView {
	
	 World world;
	 int frameCount = 0; 

	// ****************************************** CONSTRUCTOR ******************************************
	public Actor() {
		parentProperty().addListener((obs, oldParent, newParent) -> {
            if (newParent instanceof World) {
                this.world = (World) newParent;
                addedToWorld(); // now safe to call
            }
        });
	}
	
	// ****************************************** METHOD ******************************************
	
	/**
	 * Moves this actor by the given dx and dy.
	 * @param dx
	 * @param dy
	 */
	public void move(double dx, double dy) {
		 setX(getX() + dx);
		 setY(getY() + dy);
	}
	
	/**
	 * 
	 * @return the world this actor is in, or null if it is not in a world.
	 */
	public World getWorld() {
		return world;
	}
	
	/**
	 * 
	 * @return The width of the current image of this actor
	 */
	public double getWidth() {
		 return getBoundsInParent().getWidth();
	}
	
	/**
	 * 
	 * @return The height of the current image of this actor
	 */
	public double getHeight() {
		return getBoundsInParent().getHeight();
	}
	
	/**
	 * 
	 * @param <A>
	 * @param cls
	 * @return  a list of the actors of a given type intersecting this actor
	 */
	public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
		 java.util.List<A> result = new java.util.ArrayList<>();

		    World world = getWorld();
		    if (world == null) {
		        return result;
		    }

		    for (Actor a : world.getObjects(Actor.class)) {
		        if (a != this && cls.isInstance(a)) {
		            if (a.getBoundsInParent().intersects(this.getBoundsInParent())) {
		                result.add(cls.cast(a));
		            }
		        }
		    }

		    return result;
	}
	
	/**
	 * 
	 * @param <A>
	 * @param cls
	 * @return one actor of the given class that is intersecting this actor
	 */
	public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
		World world = getWorld();
	    if (world == null) {
	        return null;
	    }

	    for (Actor a : world.getObjects(cls)) {
	        if (a != this && a.intersects(this.getBoundsInParent())) {
	            return cls.cast(a);
	        }
	    }

	    return null;
	}
	
	/**
	 * This method is called every frame once start has been called on the world.
	 */
	public void addedToWorld() {
		
	}
	
	public abstract void act(long now);
	
	public void incrementFrameCount() {
	    frameCount++;
	}
	
	public int getFrameCount() {
	    return frameCount;
	}

}

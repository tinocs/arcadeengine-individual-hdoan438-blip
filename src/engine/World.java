/*
Name:     Hang Doan 
Date:      Apr 10, 2026
Period:    1

Is this lab fully working? Yes 
If resubmitting, explain what was wrong and what you fixed.
*/

package engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class World extends Pane {

	// ****************************************** ATTRIBUTES ******************************************
	private AnimationTimer timer; 
	private boolean isTimeRun;
	private boolean setWidth;
	private boolean setHeight; 
	private boolean dimensionsInitialized;
	private Set<KeyCode> keyPressed; 
	private java.util.List<Actor> actors;
	
	// ****************************************** CONSTRUCTOR ******************************************
	public World() {
		
		this.setWidth = false;
		this.setHeight = false; 
		this.dimensionsInitialized = false; 
		this.keyPressed = new HashSet<>();
		this.isTimeRun = false;
		actors = new java.util.ArrayList<>();
		
		widthProperty().addListener((obs, oldVal, newVal) -> {
	        if (!setWidth && newVal.doubleValue() > 0) {
	        	setWidth = true;

	            if (setWidth && setHeight && !dimensionsInitialized) {
	                dimensionsInitialized = true;
	                onDimensionsInitialized();
	            }
	        }
	    });

		
		heightProperty().addListener((obs, oldVal, newVal) -> {
	        if (!setHeight && newVal.doubleValue() > 0) {
	        	setHeight = true;

	            if (setWidth && setHeight && !dimensionsInitialized) {
	                dimensionsInitialized = true;
	                onDimensionsInitialized();
	            }
	        }
	    });
		
		sceneProperty().addListener((observable, oldScene, newScene) -> {
			
			
			if (newScene != null) {
				requestFocus();
				
                newScene.setOnKeyPressed(e -> {
                	keyPressed.add(e.getCode());
                });

                newScene.setOnKeyReleased(e -> {
                	keyPressed.remove(e.getCode());
                });
			}
			
		}); 
		
		timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				
				for (Actor actor : new ArrayList<>(actors)) {
					
		            if (actors.contains(actor)) {
		            	act(now); 
		            }
		        }
				
			}
			
		};
		
	}
	
	// ****************************************** METHOD ******************************************
	
	// ***************************** START *****************************
	/**
	 * STARTS the timer that calls the act method on the world 
	 * and on each Actor in the world each frame.
	 */
	public void start() {
		timer.start(); 
		isTimeRun = true; 
	}
	
	// ***************************** STOP *****************************
	/**
	 * STOPS the timer that calls the act method on the world 
	 * and on each Actor in the world each frame.
	 */
	public void stop() {
		timer.stop(); 	
		isTimeRun = false; 
	}
	
	// ***************************** IS STOPPED *****************************
	/**
	 * @return whether or not the world's timer is stopped
	 */
	public boolean isStopped() {
		if (isTimeRun) {
			return true;
		}
		
		return false; 
	}
	
	// ***************************** ADD *****************************
	/**
	 * Adds the given actor to the world and  
	 * calls the addedToWorld() method on the actor that was added.
	 * @param actor
	 */
	public void add(Actor actor) {
		getChildren().add(actor); 
	}
	
	// ***************************** REMOVE *****************************
	/**
	 * Removes the given actor from the world.
	 * @param actor
	 */
	public void remove(Actor actor) {
		getChildren().remove(actor); 
	}
	
	// ***************************** GET OBJECTS *****************************
	/**
	 * Returns a list of all the actors in the world of the given class.
	 * @param <A>
	 * @param cls
	 * @return
	 */
	public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls){
		java.util.List<A> result = new java.util.ArrayList<>();

	    for (Actor actor : actors) {
	        if (actor != null && cls.isInstance(actor)) {
	            result.add(cls.cast(actor));
	        }
	    }

	    return result;

	}
	
	// ***************************** GET OBJECTS AT *****************************
	/**
	 * Returns a list of all actors of the given class containing the given x, y
	 * @param <A>
	 * @param x
	 * @param y
	 * @param cls
	 * @return
	 */
	public <A extends Actor> java.util.List<A> getObjectsAt(double x, double y, java.lang.Class<A> cls){
		  java.util.List<A> result = new java.util.ArrayList<>();

		    for (Actor actor : actors) { 
		        if (cls.isInstance(actor) && actor.contains((int)x, (int)y)) {
		            result.add(cls.cast(actor));
		        }
		    }

		    return result;
	}
	
	// ***************************** IS KEY PRESSED *****************************
	/**
	 * Returns true if the given key is pressed and false otherwise.
	 * @param code
	 * @return
	 */
	public boolean isKeyPressed(javafx.scene.input.KeyCode code) {
		if (keyPressed.contains(code)) {
			return true;
		}
		
		return false; 
	}
	
	// ***************************** DIMENSION INITIALIZED *****************************
	/**
	 * This method would be a good place to add actors to your world because 
	 * getWidth() and getHeight() will return the real dimensions of the world instead of 0.
	 */
	public abstract void onDimensionsInitialized(); 
	
	// ***************************** ACT *****************************
	/**
	 * This method is called every frame once start has been called.
	 * @param now
	 */
	public abstract void act(long now);

}

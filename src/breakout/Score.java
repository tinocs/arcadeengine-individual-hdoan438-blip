package breakout;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score extends Text {

	// ******************************** ATTRIBUTES ******************************** 
	private int score;
	
	// ******************************** CONSTRUCTOR ******************************** 
	public Score() {
		this.score = 0; 
		this.setFont(new Font(30));
		updateDisplay(); 
	}
	
	// ******************************** METHODS ******************************** 
	public void updateDisplay() {
		this.setText(Integer.toString(score));
	}
	
	public int getScore() {
		return this.score; 
	}
	
	public void setScore(int value) {
		System.out.println("Score updating to: " + value);
		this.score = value; 
		updateDisplay(); 
	}
	
}

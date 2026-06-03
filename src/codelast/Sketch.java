/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codelast;

/**
 *
 * @author 343084331
 */

// Importing
import processing.core.PApplet;


public class Sketch extends PApplet {
    // Object declaration
    private Human bamboo;
    private Human father;
    int stage = 0;
    
    // Setting screen
    public void settings() {
        size(800, 800);
    }
    
    // Setting up objects and text
    public void setup () {
        background(255); // BG colour set to white
        textSize(20);
        // Instantiating object
        bamboo = new Human (this, 400, 400, 2, "Bamboo", "images/human.png");
    }
    
    // Drawing stages
    public void draw() {
        background(255); // BG colour set to white
        if (stage == 0) {
            fill(0);
            text("My Cultural Story", 310, 50);
            text("Press enter to begin", 300, 100);
        } else if (stage == 1) {
            bamboo.draw(); // Drawing Bamboo character
        }
    } // end draw method
    
    // Method for different results when using keyboard
    public void keyPressed () {
        // Starting screen
        if (stage == 0) {
            if (keyCode == ENTER) { // User hits enter
                stage = 1; // Changing to stage 1
            }
        } // End of stage 0 if-statement
    } // End of key pressed
} // End of class

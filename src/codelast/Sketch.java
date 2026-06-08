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
import processing.core.PImage;

public class Sketch extends PApplet {
    // Object declaration
    private Human bamboo;
    private Human father;
    private Animal turtle;
    private Animal dragon;
    private Animal phoenix;
    private PImage bg; // background
    private PImage dialog; // dialogue
    // Variable to determine which stage to show
    int stage = 0;
    
    // Setting screen
    public void settings() {
        size(400, 400);
    }
    
    // Setting up objects and text
    public void setup () {
        bg = loadImage ("images/noon.png"); // Setting up BG image
        textSize(20);
        // Instantiating object
        bamboo = new Human (this, width/2, height/2, 2, "Bamboo", "images/human.png");
        father = new Human (this, 100, 100, 1, "Father", "images/father.png");
        turtle = new Animal (this, 300, 100, 1, "Turtle", "images/turtle.png");
        dragon = new Animal (this, 100, 200, 5, "Dragon", "images/dragon.png");
        phoenix = new Animal (this, 300, 250, 3, "Phoenix", "images/phoenix.png");
    }
    
    // Drawing stages
    public void draw() {
        //Drawing BG
        image(bg, 0, 0, width, height);
        
        // Start screen
        if (stage == 0) {   
            fill(0);
            text("My Cultural Story", 130, 50);
            text("Press enter to begin", 120, 100);
        } else if (stage == 1) {
            father.draw();
            turtle.draw();
            dragon.draw();
            phoenix.draw();
            bamboo.draw(); // Drawing Bamboo character
            // Changing the BG image for next scene
            bg = loadImage ("images/field.png"); 
        }
        
        // Character movement
        // WASD was used for movement since most games use WASD
        if (keyPressed) {
            if (key == 'w') {
                bamboo.move(0,-1);
            }
            else if (key == 'a') {
                bamboo.move(-1,0);
            }
            else if (key == 's') {
                bamboo.move(0,1);
            }
            else if (key == 'd') {
                bamboo.move(1,0);
            }
        }
        
        // Check if main character is colliding
        if (bamboo.isCollidingWith(father)) {
            fill (255); // Set text colour to white
            this.text("Colliding", bamboo.x, bamboo.y + 30);
        }
        if (bamboo.isCollidingWith(dragon)) {
             fill (255); // Set text colour to white
            this.text("Like a what??", bamboo.x, bamboo.y + 30);
        }       
        if (bamboo.isCollidingWith(phoenix)) {
            fill (255); // Set text colour to white
            this.text("Big bird?", bamboo.x, bamboo.y + 30);
        }
        if (bamboo.isCollidingWith(turtle)) {
            dialog = loadImage("images/dialog1.PNG"); // Setting dialogue img
            image(dialog, 70, 300); // Showing dialogue
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

        /**
        // Mounting the turtle
        if (bamboo.isCollidingWith(turtle)) {
            fill(255);
            this.text("Press enter to mount", turtle.x, turtle.y);
            if (keyPressed) {
                if (keyCode == ENTER) {
                   bamboo.mounted = false
                }
            }
        }
        */

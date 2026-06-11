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
    // Objects set as character but will use the HeldItem constructor version
    private Character door; // door isn't held but it is used for item checks
    private Character itemKey; // key
    
    private PImage bg; // background
    private PImage dialog; // dialogue
    // Variable to determine which stage to show
    int stage = 0;
    static int heldStage = 0; // Variable used to keep track of previous stage
    boolean pickup = false;
    
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
        // Objects use the alternate character constructor
        door = new Character (this, width/2, 25, "images/door.png");
        itemKey = new Character(this, 25, 25, "images/DaveKey.png");
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
            door.draw();
            itemKey.draw();
            father.draw();
            turtle.draw();
            dragon.draw();
            phoenix.draw();
            bamboo.draw(); // Drawing Bamboo character
            // Changing the BG image for next scene
            bg = loadImage ("images/field.png"); 
        } else if (stage == 2) {
            background(50);
            bamboo.draw();
            turtle.draw();
        }
        if (stage == 10000) { // pause menu
           background(255);
           text("Game is currently paused", 20, 50);
           text("Press Z to unpause", 20, 100);
        }
        // Character movement
        if (keyPressed) {
            // Stop player from moving during start and pause
            if (stage == 0 || stage == 10000) {
                bamboo.speed = 0;
            } else { // Set speed to normal when not paused
                bamboo.speed = 2;
            }
            
            if (pickup == true) {
                itemKey.x = bamboo.x;
                itemKey.y = bamboo.y;
            }
            // Check if mounted state is true
            if (bamboo.mounted == true) { 
                turtle.x = bamboo.x - 20; // Set new position of turtle
                turtle.y = bamboo.y; // Turtle moves with bamboo while true
            }
            // WASD was used for movement since most games use WASD
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
        if (bamboo.isCollidingWith(turtle)) {
            // Setting dialogue img with check to see if character is moutned
            if (bamboo.mounted == true) {
                dialog = loadImage("images/mountText.png");
            } else {
                dialog = loadImage("images/dialog1.PNG"); 
            }
            image(dialog, 40, 300); // Showing dialogue
            
            // Code for mounting
            if (keyCode == ENTER) {
                bamboo.mounted = true;
            }
            if (keyCode == BACKSPACE) {
                bamboo.mounted = false;
                turtle.x = 300;
                turtle.y = 100;
            }
        } // End of turtle's collision code
         if (bamboo.isCollidingWith(dragon)) {
            dialog = loadImage("images/dialog2.png"); // Set image
            image(dialog, 40, 300); // Show dialogue
        }       
        if (bamboo.isCollidingWith(father)) {
            dialog = loadImage("images/dialog3.png"); // Set img
            image(dialog, 40, 300); // Show dialogue
        }
        if (bamboo.isCollidingWith(phoenix)) {
            dialog = loadImage("images/dialog4.png"); // Set img
            image(dialog, 40, 300); // Show dialogue   
        } 
        
        if (bamboo.isCollidingWith(itemKey)){
            pickup = true;
        }
       
        if (itemKey.isCollidingWith(door)) {
            stage = 2;
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
        
        // Code for pause menu
        if (stage != 0) {
            if (keyCode == TAB) { // Starts pause
                heldStage = stage;
                stage = 10000; // Stage is set high so pause menu won't trigger normally
            }  if (key == 'z') { // Ends pause
                stage = heldStage;
                }
        } // end of pause code
        
        // Stage 1 mount check
        if (stage == 1 && bamboo.isCollidingWith(turtle)) {
            // mount check
            if (key == 'c') {
                mountCheck(turtle);
            }
            // Check character info
            if (key == 'q') {
                charInfoCheck(turtle);
            }
        } // End of stage 1
    } // End of key pressed
    
    public void mountCheck (Character c) {
        if (c instanceof Human) {
            System.out.println("Cannot mount another person");
        }
        if (c instanceof Animal) {
            if (c == turtle) {
                System.out.println("Animal can be mounted");
            }
            else {
                System.out.println("Animal cannot be mounted");
            }
        }
    }
    
    public void charInfoCheck (Character c) {
        System.out.println(c); // uses the toString method of character
    }
} // End of class

/*
    if (keyPressed) {
        if (keyCode == ENTER) {
            dialog = loadImage("images/dialog3.png");
                   
            }
        }
    */
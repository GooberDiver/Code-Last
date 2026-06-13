/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codelast;

/**
 *
 * @author 343084331
 */

// Importing for visuals
import processing.core.PApplet;
import processing.core.PImage;
// Importing to use files
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;

public class Sketch extends PApplet {
    // Object declaration
    private Human bamboo; // main character
    private Human father;
    private Animal turtle;
    private Animal dragon;
    private Animal phoenix;
    // Objects set as character but will use the HeldItem constructor version
    private Character door; // door isn't held but it is used for item checks
    private Character itemKey; // key
    // PImage is for objects that won't interact with characters
    private PImage bg; // background
    private PImage dialog; // dialogue
    // Variable for game function
    int stage = 0; // tracks current stage
    static int heldStage = 0; // Variable used to keep track of previous stage
    boolean pickup = false; // determines if player is holding a key
    
   
    
    /**
     * Screen dimensions are set
     */
    public void settings() {
        size(400, 400);
    }
    
    /**
     * Objects are instantiated and text size is set
     */
    public void setup () {
        bg = loadImage ("images/noon.png"); // Setting up BG image
        textSize(20); // sets size of text when displayed
        // Instantiating object using normal constructor
        bamboo = new Human (this, width/2, height/2, 2, "Bamboo", "images/human.png");
        father = new Human (this, 100, 100, 1, "Father", "images/father.png");
        turtle = new Animal (this, 300, 100, 4, "Turtle", "images/turtle.png");
        dragon = new Animal (this, 100, 200, 5, "Dragon", "images/dragon.png");
        phoenix = new Animal (this, 300, 250, 3, "Phoenix", "images/phoenix.png");
        // Objects use the alternate character constructor
        door = new Character (this, width/2, 25, "images/door.png");
        itemKey = new Character(this, 25, 25, "images/DaveKey.png");
    }
    
    
     // cutscene objects
    Character [][] cutsceneObjects = { {bamboo, father, itemKey},
        {turtle, dragon, phoenix} };
    
     
    /**
     * Stages are drawn
     */
    public void draw() {

        
        //Drawing BG
        image(bg, 0, 0, width, height);
        
        // Start screen
        if (stage == 0) {   
            fill(0);
            text("My Cultural Story", 130, 50);
            text("Press enter to begin from last save", 60, 100); // load progress
            text("Press shift to start from beginning", 60, 150); // start new game
        }
        // Opening cutscenes
        // stage is set to negative so stopping player from moving only needs to be less or equal to 0
        if (stage == -1) {
            bamboo.draw();
            text("Long ago, there was a boy named Bamboo", 20, 300);
        }
        if (stage == -2) {
            bamboo.x = width/3; // Moving bamboo left
            bamboo.draw();
            // Centers father character
            father.x = width/2;
            father.y = height/2;
            father.draw(); //Draw father character
            text("Bamboo lived with his father who was strict", 20, 300);
        }
        if (stage == -3) {
            bamboo.draw();
            father.draw();
            // Text was split in 2 since it went offscreen
            text("Bamboo's father forced him to handle", 20, 300);
            text("housekeeping when visitors came", 20, 325);
        }
        if (stage == -4) {
            bamboo.draw();
            father.draw();
            // Sets key's position
            itemKey.x = 300;
            itemKey.y = height/2;
            // Draws key
            itemKey.draw();
            text("Bamboo's father required Bamboo to lock", 20, 300);
            text("a chamber he was not allowed to see", 20, 325);
        }
        if (stage == -5) {
            bamboo.draw();
            father.draw();
            text("However, Bamboo's curiousity got the better", 20, 300);
            text("of him one day and he entered the room", 20, 325);
        }
        
        // cutscene BG and text to tell user how to progress ctuscene
        if (stage < 0 || stage > 500) {
            bg = loadImage("images/dawn.png"); // set BG img
            textSize(15); // Shrink text size
            text("Press enter to continue", 25, 350); // tell user how to progress
        }
        textSize(20); // reset text size to normal
        
        // 1st game scene
        if (stage == 1) {
            // Reset positions of cutscene character
            itemKey.x = itemKey.y = 25; 
            father.x = father.y = 100;
            bamboo.x = bamboo.y = width/2; // dimension is square so x and y are the same
            // Drawing objects
            door.draw();
            itemKey.draw();
            father.draw();
            turtle.draw();
            dragon.draw();
            phoenix.draw();
            bamboo.draw(); // Drawing Bamboo character
            // Changing the BG image for next scene
            bg = loadImage ("images/field.png"); 
            // Save progress
            progressSave();
            // 2nd game scene
        } if (stage == 2) {
            background(50);
            bamboo.draw();
            turtle.draw();
            // Door is put so user can return to stage 1 for now
            door.x = 20;
            door.y = 200;
            door.draw();
            // Save progress
            progressSave();
        }
        
        // Ending cutscenes
        // stage number starts at 501 so cutscene won't normally trigger
        if (stage == 501) {
            // Set character positions
            dragon.x = 25;
            phoenix.x = 300;
            turtle.x = 100;
            bamboo.x = 200;
            dragon.y = bamboo.y = phoenix.y = turtle.y = height/2;
            // Draw characters
            dragon.draw();
            phoenix.draw();
            turtle.draw();
            bamboo.draw();   
            // Text
            text("Bamboo spent time with the dragon, turtle, ", 25, 300);
            text("and phoenix and had a great time", 25, 325);
        }
        // pause menu
        if (stage == 10000) { 
           background(255); //BG set to white
           text("Game is currently paused", 20, 50); //tell user the game is paused
           text("Press Z to unpause", 20, 100); // tell user how to unpause
           text("Press M to return to opening cutscene", 20, 150); // tell user how to return to start cutscene
           text("Press P to head to ending cutscenes", 20, 200); // tells user how to go to end cutscene
        }
        
        // Actions for keyboard input
        if (keyPressed) {
            // Stop player from moving during cutscenes, start, and pause
            if (stage <= 0 || stage > 500) {
                bamboo.speed = 0;
            } else { // Set speed to normal when not paused
                bamboo.speed = 2;
            }
            // When picking up a key, it moves with the player
            if (pickup == true) {
                itemKey.x = bamboo.x;
                itemKey.y = bamboo.y;
            }
            // Check if mounted state is true
            if (bamboo.mounted == true) { 
                bamboo.speed = turtle.speed; // Changes movespeed while mounted
                turtle.x = bamboo.x - 20; // Set new position of turtle
                turtle.y = bamboo.y; // Turtle moves with bamboo while true
            }
            // Character movement
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
        
        // Turtle collision check
        if (bamboo.isCollidingWith(turtle)) {
                // Setting dialogue img with check to see if character is mounted
                if (bamboo.mounted == true) {
                    dialog = loadImage("images/mountText.png");
                } else { // set to normal dialogue when unmounted
                    dialog = loadImage("images/dialog1.PNG"); 
                }
                image(dialog, 40, 300); // Showing dialogue

                // Code for mounting
                if (keyCode == ENTER) {
                    bamboo.mounted = true;
                }
                if (keyCode == BACKSPACE) { // unmounting
                    bamboo.mounted = false;
                    turtle.x = 300;
                    turtle.y = 100;
                }
            } // End of turtle's collision code
        
        // stage 1 collision checks
        if (stage == 1) {
            // Check if main character is colliding
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
            // Pick up key
            if (bamboo.isCollidingWith(itemKey)){
                pickup = true;
            }
            // Change stages by touching door with key
            if (itemKey.isCollidingWith(door)) {
                stage = 2;
                pickup = false;
                itemKey.x = itemKey.y = 25;
                itemKey.item.itemUsed = true; // Set item use to true
            }  
        } // End of stage 1 code
        
        // Return to stage 1 and change door position
        if (stage == 2 && bamboo.isCollidingWith(door)) {
            stage = 1;
            door.x = width/2;
            door.y = 25;
            itemKey.item.itemUsed = false;
        }
    } // end draw method
    
    /**
     * Method for different results when using keyboard
     */
    public void keyPressed () {
        // Starting screen
        if (stage == 0) {
            if (keyCode == ENTER) { // User hits enter
                progressLoad(); // loads the stage stored from file
                stage = heldStage; // sets player's stage to the stored value
            }
            if (keyCode == SHIFT) { // User hits shift
                stage = -1; // starts from beginning of game
            }
        } // End of stage 0 if-statement
        
        // Used to transition through cutscenes at start
        if (stage < 0) {
            if (keyCode == ENTER) {
                // intro cutscenes count with negative values so stage is reduced
                stage -=1; 
                if (stage == -6) { // after seeing final intro scene, starts game
                    stage = 1;
                }
            }
        } // end cutscene transition
        
        // Code for pause menu
        if (stage != 0) {
            if (keyCode == TAB) { // Starts pause
                heldStage = stage; // store the stage the user was on
                stage = 10000; // Stage is set high so pause menu won't trigger normally
            }  if (key == 'z' && stage == 10000) { // Ends pause
                stage = heldStage; // sets the stage to the stored one
            } if (key == 'm' && stage == 10000) { // resets user's progress to opening cutscene
                stage = -1;
            } if (key == 'p' && stage == 10000) { // go to ending cutscenes
                stage = 501;
            } 
        } // end of pause code
        
        // Check for mount and info with turtle
        if (stage != 0 && bamboo.isCollidingWith(turtle)) {
            // mount check
            if (key == 'c') {
                mountCheck(turtle);
            }
            // Check character info
            if (key == 'q') {
                charInfoCheck(turtle);
            }
        } // End of check
    } // End of key pressed
    
    /**
     * Method saves user's stage
     */
    public void progressSave() {
        try {
            // Making and using PrintWriter to save progress
           PrintWriter w = new PrintWriter (new FileWriter("stageTrack.txt"));
           w.print(stage);
           w.close();
        // Catch error
        } catch ( IOException ioException ) {
           System.out.println("Error with saving stage"); 
        } 
    }
    
    /**
     * Loads user progress
     */
    public void progressLoad() {
       try{
           // Makes scanner
           Scanner fileStage = new Scanner(new File("stageTrack.txt"));
           // Loads stage
           String scene = fileStage.nextLine();
           heldStage = Integer.parseInt(scene);
           // Close scanner
           fileStage.close();
       } catch ( IOException e ) {
           System.out.println("No progress was saved");
       }
    }
    
    /**
     * Code to check if a character can be mounted
     * @param c Character that is checked for in the method
     */
    public void mountCheck (Character c) {
        // if c is  the human subclass
        if (c instanceof Human) {
            System.out.println("Cannot mount another person");
        } // if c is an animal
        if (c instanceof Animal) {
            if (c == turtle) { // only turtle can be mounted
                System.out.println("Animal can be mounted");
            }
            else { // text when checking other animals
                System.out.println("Animal cannot be mounted");
            }
        }
    } // ends mountCheck
    
    /**
     * Checks character info
     * @param c Character used by method to display info
     */
    public void charInfoCheck (Character c) {
        System.out.println(c); // uses the toString method of character
    } // end charInfoCheck
} // End of class
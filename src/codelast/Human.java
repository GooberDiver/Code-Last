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

public class Human extends Character {
   // Attributes
    private boolean mounted; // Determine if character is riding an animal
    
    // Constructor
    public Human(PApplet p, int x, int y, int speed, String name, String imagePath) {
        super(p, x, y, speed, name, imagePath); // Calls superclass
        mounted = false; // Default state is not riding an animal
    }
}

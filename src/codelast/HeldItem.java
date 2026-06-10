/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codelast;

/**
 *
 * @author Goober
 */

// Importing
import processing.core.PApplet;
import processing.core.PImage;

public class HeldItem {
    // Attributes
    public int itemX;
    public int itemY;
    public boolean itemUsed;
    private PApplet app;
    private PImage image;
    
    // Constructor
    public HeldItem (PApplet p, int x, int y, String imagePath) {
        app = p;
        itemX = x;
        itemY = y;
        itemUsed = false;
        image = app.loadImage(imagePath);
    }
    
    public void draw() {
        app.image(image, itemX, itemY);
    }
    
    // Rectangle collision detection for items
    public boolean isCollidingWith (HeldItem d2) {
        // Collision checks
        boolean leftOfC2Right = itemX < d2.itemX + d2.image.width;
        boolean rightOfC2Left = itemX + image.width > d2.itemX;
        boolean aboveC2Bottom = itemY < d2.itemY + d2.image.height;
        boolean belowC2Top = itemY + image.height > d2.itemY;
        
        // Returning values
        return leftOfC2Right && rightOfC2Left
                && aboveC2Bottom && belowC2Top;
    }
}

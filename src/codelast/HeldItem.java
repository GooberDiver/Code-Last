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
    
    /**
     * Constructor for HeldItem
     * @param p Sets up object to have visual
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @param imagePath image is loaded from the imagePath in image file
     */
    public HeldItem (PApplet p, int x, int y, String imagePath) {
        app = p;
        itemX = x;
        itemY = y;
        itemUsed = false;
        image = app.loadImage(imagePath);
    }
    
    /**
     * Draws object
     */
    public void draw() {
        app.image(image, itemX, itemY);
    }
}

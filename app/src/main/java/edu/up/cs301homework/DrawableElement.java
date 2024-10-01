// @author Aaron Chu
// @version/date Final Version 30 Sep 2024

package edu.up.cs301homework;

public class DrawableElement {

    // enum in Java links also commented in MainActivity.java
    // Googled how to add individual elements into the ArrayList with different parameters
    // (some circles, some rectangles), and found enum to assign Circle and Rectangle constructors
    public enum ShapeType {
        CIRCLE, RECTANGLE
    }

    private String name;
    private float x, y;
    private int color;
    private ShapeType shapeType;
    private float radius;
    private float width, height;

    // Circle constructor
    DrawableElement(String name, float x, float y, int color, float radius) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.color = color;
        this.shapeType = ShapeType.CIRCLE;
        this.radius = radius;
    }

    // Rectangle constructor
    public DrawableElement(String name, float x, float y, int color, float width, float height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.color = color;
        this.shapeType = ShapeType.RECTANGLE;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    // Check if a circle was tapped
    // Used radius to locate where the user taps, into a boolean
    public boolean isTapped(float touchX, float touchY) {
        if (shapeType == ShapeType.CIRCLE) {
            return Math.sqrt(Math.pow(touchX - x, 2) + Math.pow(touchY - y, 2)) <= radius;
        } else if (shapeType == ShapeType.RECTANGLE) {
            return touchX >= x && touchX <= x + width && touchY >= y && touchY <= y + height;
        }
        return false;
    }

    public DrawableElement(String name, float x, float y, int color) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.color = color;
    }

}

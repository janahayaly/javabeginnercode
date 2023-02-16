/*  Name: Jana Hayaly
*  PennKey: jhayaly
*  Recitation: 207
*
*  A class that represents a target to be hit in
*  Irate Avians. Can update its own position based
*  on velocity and time.
*/

public class Target {
    
    // variables for width and height of screen
    private double width, height;
    
    // Position and radius
    private double xPos, yPos, radius;
    
    // Velocity components
    private double xVel, yVel;
    
    /**
    * When a target's hit points reach zero,
    * it has been destroyed by the bird.
    */
    private int hitPoints;
    
    // Track if target has been hit this shot.
    private boolean hitThisShot;
    
    /**
    * Given a position, a radius, a velocity, and a number of hit points,
    * construct a Target.
    */
    public Target(double width, double height, double xPos, double yPos,
    double radius, double xVel, double yVel, int hitPoints) {
        this.width = width;
        this.height = height;
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.xVel = xVel;
        this.yVel = yVel;
        this.hitPoints = hitPoints;
        this.hitThisShot = false;
    }
    
    /**
    * Draw a circle centered at the target's position
    * with a radius equal to the target's radius.
    * Only draw a Target if it has more than zero
    * hit points.
    */
    public void draw() {
        if (this.hitPoints > 0) {
            PennDraw.setPenColor(PennDraw.BLUE);
            PennDraw.filledCircle(this.xPos, this.yPos, this.radius);

            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.text(this.xPos, this.yPos, hitPoints + "");
        }
    }
    
    /**
    * Given the change in time, update the target's
    * position based on its x and y velocity. When
    * a target is completely offscreen horizontally,
    * its position should wrap back around to the opposite
    * horizontal side. For example, if the target moves off the
    * right side of the screen, its xPos should be set to the
    * left side of the screen minus the target's radius.
    * The same logic should apply to the target's vertical
    * position with respect to the vertical screen boundaries.
    */
    public void update(double timeStep) {
        this.xPos = this.xPos + (this.xVel * timeStep);
        this.yPos = this.yPos + (this.yVel * timeStep);
        if ((this.xPos - this.radius) >= this.width || 
        (this.xPos + this.radius) <= 0) {
            this.xPos = this.xPos % (this.width + 2 * this.radius);
        } 
        if ((this.yPos - this.radius) >= this.height || 
        (this.yPos + this.radius) <= 0) {
            this.yPos = this.yPos % (this.height + 2 * this.radius);
        }
    }
    
    // Decrement the target's hit points by 1.
    /**
     * Inputs: None
     * Outputs: None
     * Description: Decreases the hit points by 1
    */
    public void decreaseHP() {
        --hitPoints;
    }
    
    /**
    * Setter function for whether or not target hit this round.
    */
    public void setHitThisShot(boolean hit) {
        hitThisShot = hit;
    }
    
    /**
    * Return whether or not this target is hit this round.
    */
    public boolean isHit() {
        return this.hitThisShot;
    }
    
    /**
    * Getter functions that return a copy of the
    * indicated member variable.
    */
    public int getHitPoints() {
        return hitPoints;
    }
    
    /**
     * Inputs: None
     * Outputs: X position of target
     * Description: Returns the X coordinate of the center of the target
    */
    public double getXpos() {
        return xPos;
    }
    
    /**
     * Inputs: None
     * Outputs: Y position of target
     * Description: Returns the Y coordinate of the center of the target
    */
    public double getYpos() {
        return yPos;
    }
    
    /**
     * Inputs: None
     * Outputs: Radius of target
     * Description: Returns the radius of the target object
    */
    public double getRadius() {
        return radius;
    }
    
}

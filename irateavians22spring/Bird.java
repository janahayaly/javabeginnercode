/*  Name: Jana Hayaly
*  PennKey: jhayaly
*  Recitation: 207
*
*  A class that represents the bird projectile in
*  Irate Avians. Can update its own position based
*  on velocity and time, and can compute whether
*  it overlaps a given Target.
*
*/

public class Bird {
    // The position, velocity, and radius members of the bird.
    private double xPos, yPos, xVel, yVel, radius;
    
    /**
    * How many more times the player can throw the bird
    * before losing the game.
    */
    private int numThrowsRemaining;
    
    /**
    * Initialize the bird's member variables
    * with the same names as the inputs to those values.
    * Initializes the bird's velocity components to 0.
    */
    public Bird(double xPos, double yPos, double radius, int numThrows) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = 0;
        this.yVel = 0;
        this.radius = radius;
        this.numThrowsRemaining = numThrows;
    }
    
    /**
    * Draws a circle centered at the bird's position
    * with a radius equal to the bird's radius.
    * Additionally, draws a triangular beak and two
    * circular eyes somewhere on the circle to make
    * the bird look more like a bird. Additional details
    * are up to your discretion.
    * Also draws the bird's remaining throws 0.1 units
    * above its circular body.
    */
    public void draw() {
        PennDraw.setPenColor(PennDraw.RED);
        PennDraw.filledCircle(this.xPos, this.yPos, this.radius);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.circle(this.xPos, this.yPos, this.radius);
        PennDraw.setPenColor(PennDraw.YELLOW);
        PennDraw.filledPolygon(this.xPos + (this.radius + 0.2), this.yPos, 
        this.xPos + (this.radius - 0.1), this.yPos + 0.1, 
        this.xPos + (this.radius - 0.1), this.yPos - 0.1);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.polygon(this.xPos + (this.radius + 0.2), this.yPos, this.xPos + 
        (this.radius - 0.1), this.yPos + 0.1, this.xPos + (this.radius - 0.1), 
        this.yPos - 0.1);
        PennDraw.filledCircle(this.xPos + (this.radius / 3), this.yPos + 
        (this.radius / 3), 0.05);
        PennDraw.filledCircle(this.xPos - (this.radius / 3), this.yPos + 
        (this.radius / 3), 0.05);

        PennDraw.text(this.xPos, this.yPos + (this.radius + 0.1), 
        numThrowsRemaining + "");
    }
    /**
    * Draw the line representing the bird's initial velocity
    * when the player is clicking and dragging the mouse.
    */
    public void drawVelocity() {
        double newXpos = this.xPos + xVel * 1;
        double newYpos = this.yPos + yVel * 1;

        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.line(this.xPos, this.yPos, newXpos, newYpos);
    }
    
    /**
    * Set xPos and yPos to 1.0,
    * set xVel and yVel to 0.0,
    * and clear the list of targets hit this launch.
    */
    public void reset() {
        xPos = 1.0;
        yPos = 1.0;
        xVel = 0.0;
        yVel = 0.0;
    }
    
    /**
    * Compute the bird's initial velocity as the
    * vector from the mouse's current position to
    * the bird's current position. This will be used
    * in mouse listening mode to update the launch
    * velocity.
    */
    public void setVelocityFromMousePos() {
        this.xVel = this.xPos - PennDraw.mouseX();
        this.yVel = this.yPos - PennDraw.mouseY();
    }
    
    /**
    * Given the change in time, compute the bird's
    * new position and new velocity.
    */
    public void update(double timeStep) {
        this.xPos = this.xPos + (this.xVel * timeStep);
        this.yVel = this.yVel  - (0.25 * timeStep);
        this.yPos = this.yPos + (this.yVel * timeStep);
    }
    
    /**
    * A helper function used to find the distance
    * between two 2D points. Remember to use the
    * Pythagorean Theorem.
    */
    private static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }
    
    /**
    * Given a Target, determine if the bird should
    * test for collision against it. If the bird
    * *should* see if it collides with the target,
    * then perform that test. If the bird collides,
    * then decrease the target's HP by 1 and add
    * the target to the bird's list of targets hit
    * during this launch.
    */
    public void testAndHandleCollision(Target t) {
        int hitPoints = t.getHitPoints();
        double targetXPos = t.getXpos();
        double targetYPos = t.getYpos();
        double targetRadius = t.getRadius();
        if (hitPoints > 0) {
            if (distance(targetXPos, targetYPos, this.xPos, this.yPos) < 
            targetRadius + this.radius) {
                t.setHitThisShot(true);
            }
        }
    }
    
    // Reduce numThrowsRemaining by 1.
    /**
     * Inputs: None
     * Outputs: None
     * Description: Decreases the variable numThrowsRemaining by 1
    */
    public void decrementThrows() {
        this.numThrowsRemaining--;
    }
    
    /**
    * Getter functions that return a copy
    * of the indicated member variable.
    */

    /**
     * Inputs: None
     * Outputs: X position of bird
     * Description: Returns the X coordinate of the center of the bird
    */
    public double getXpos() {
        return xPos;
    }
    /**
     * Inputs: None
     * Outputs: Y position of bird
     * Description: Returns the Y coordinate of the center of the bird
    */
    public double getYpos() {
        return yPos;
    }
    /**
     * Inputs: None
     * Outputs: radius of bird
     * Description: Returns the radius of the center of the bird
    */
    public double getRadius() {
        return radius;
    }
    public int getNumThrowsRemaining() {
        return numThrowsRemaining;
    }
}

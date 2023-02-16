/**
 * Name: Jana Hayaly
 * Pennkey: jhayaly
 * Execution: java Body
 *
 * Description: Creates objects which represent the body and writes methods 
 * which alter their behavior
**/
public class Body {
    
    /**
    * DO NOT EDIT ANY CODE BELOW THIS LINE
    *
    * You will get a style warning that reads:
    * "Variable '[some name]' must be private and have get/set methods."
    * Ignore any style warnings of this form. Our tests rely on these
    * variables being public. We will discuss public vs. private next week
    * in class. You are not expected to know it now.
    */
    public double px, py; //position
    public double vx, vy; //velocity
    public double m; //mass
    public String img; //image file
    
    public static final double G = 6.67e-11; //gravity constant
    
    /*DO NOT EDIT ANY CODE ABOVE THIS LINE*/
    
    /**
    * Constructor: This creates a new instance of a body object.
    */
    public Body(double mass, double posX, double posY,
    double velX, double velY, String imageFile) {
        m = mass;
        px = posX;
        py = posY;
        vx = velX;
        vy = velY;
        img = imageFile;
    }
    
    /**
    * Description: returns a string representation of the body for the
    * purposes of printing. We will discuss toString methods in class.
    *
    * DO NOT EDIT THIS METHOD AT ALL!
    */
    public String toString() {
        return String.format("%12.5e %12.5e %12.5e %12.5e %12.5e %12s",
        m, px, py, vx, vy, img);
    }
    
    // Methods: different formulas and calculations that can be applied to the bodies
    /**
     * Inputs: Another 'body' object with all its instance variables
     * Outputs: The horizontal distance between that object and the original one
     * Description: Gives the x distance between two bodies
    */
    public double distanceToX(Body other) {
        double dX = other.px - this.px;
        return dX;
    }
    
    /**
     * Inputs: Another 'body' object with all its instance variables
     * Outputs: The vertical distance between that object and the original one
     * Description: Gives the x distance between two bodies
    */
    public double distanceToY(Body other) {
        double dY = other.py - this.py;
        return dY;
    }
    
    /**
     * Inputs: Another 'body' object with all its instance variables
     * Outputs: The exact distance between the two bodies
     * Description: Uses X and Y distances to determine total distance
    */
    public double distanceTo(Body other) {
        double dTo = Math.sqrt((distanceToX(other)) * (distanceToX(other)) + 
        (distanceToY(other)) * (distanceToY(other)));
        return dTo; 
    }
    
    /**
     * Inputs: Another 'body' object with all its instance variables
     * Outputs: The force acting on the original object
     * Description: Uses the previous calculations to determing the force exerted by 
     * the 'other' body on the original one
    */
    public double force(Body other) {
        double force = (G * m * other.m) / ((distanceTo(other)) * 
        (distanceTo(other)));
        return force;
    }
    
    /**
     * Inputs: Another 'body' object with all its instance variables
     * Outputs: The force in the x direction
     * Description: Uses the total force and distances to determine force in x 
     * direction
    */
    public double forceX(Body other) {
        double forceX = (force(other) * distanceToX(other)) / distanceTo(other);
        return forceX; 
    }
    
    /**
     * Inputs: Another 'body' object with all its instance variables
     * Outputs: The force in the y direction
     * Description: Uses the total force and distances to determine force in y 
     * direction
    */
    public double forceY(Body other) {
        double forceY = (force(other) * distanceToY(other)) / distanceTo(other);
        return forceY;
    }
    
    /**
     * Inputs: Nothing
     * Outputs: Nothing
     * Description: Draws the planet (body)
    */
    public void draw() {
        PennDraw.picture(this.px, this.py, this.img);
    }
    
    /**
     * Inputs: A number which is used as a timestep
     * Outputs: None
     * Description: Alters the x and y coordinates of the body's position
    */
    public void move(double timeStep) {
        this.px = this.px + (timeStep * this.vx);
        this.py = this.py + (timeStep * this.vy);
    }
    
    /**
     * Inputs: Another 'body' object with all its instance variables and 
     * a number (timestep)
     * Outputs: None
     * Description: Alters the velocity in the x and y direction of the body
    */
    public void getAffectedBy(Body other, double timeStep) {
        double forceX = forceX(other);
        double forceY = forceY(other);
        
        double aX = forceX / this.m;
        double aY = forceY / this.m;

        this.vx = this.vx + (timeStep * aX);
        this.vy = this.vy + (timeStep * aY);
    }
}
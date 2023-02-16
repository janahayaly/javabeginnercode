/*  Name: Jana Hayaly
*  PennKey: jhayaly
*  Recitation: 207
*
*  A class representing the arena in which the Irate Avians
*  game takes place. Keeps track of the game's Bird and
*  Targets and receives the player's input to control the bird.
*
*/

public class Arena {
    // The width and height of the PennDraw screen
    private int width, height;
    
    // All the targets in the Arena
    private Target[] targets;
    
    // The one and only bird in the game
    private Bird bird;
    
    /**
    * Whether the game is currently listening for
    * the player's mouse input, or letting the bird
    * fly. Begins as true.
    */
    private boolean mouseListeningMode;
    
    /**
    * Tells the program if the user was pressing
    * the mouse in the previous update call. Lets
    * the program know if the user has just released
    * the mouse because mouseWasPressedLastUpdate will
    * be true, but PennDraw.mousePressed() will be false
    * in the current update call. This enables the game
    * to transition from the mouse listening state into
    * the bird flight state.
    */
    private boolean mouseWasPressedLastUpdate;
    
    /**
    * Given a file that describes the contents of the
    * Arena, parse the file and initialize all member
    * variables of the Arena.
    * The file will be in the following format:
    * numTargets width height
    * bird.numThrows
    * target0.xPos target0.yPos target0.radius target0.xVel target0.yVel target0.hitPoints
    * target1.xPos... etc.
    */
    
    public Arena(String filename) {
        In in = new In(filename);

        int numTargets = in.readInt();
        this.width = in.readInt();
        this.height = in.readInt();

        PennDraw.setXscale(0, this.width);
        PennDraw.setYscale(0, this.height);
        
        int numThrowsRemaining = in.readInt();
        this.bird = new Bird(1, 1, 0.25, numThrowsRemaining);
        
        this.targets = new Target[numTargets];

        for (int i = 0; i < numTargets; i++) {
            targets[i] = new Target(this.width, this.height, in.readDouble(), 
            in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), 
            in.readInt());
        }
        
        in.close();

        this.mouseListeningMode = true;
        this.mouseWasPressedLastUpdate = false;
    }
    
    /**
    * Returns true when all targets' hit points are 0.
    * Returns false in any other scenario.
    */
    private boolean didPlayerWin() {
        int totalHitPoints = 0;
        for (int i = 0; i < targets.length; i++) {
            totalHitPoints = totalHitPoints + targets[i].getHitPoints();
        }
        return totalHitPoints == 0;
    }
    
    /**
    * Returns true when the bird's remaining throw count is 0
    * when the game is in mouse-listening mode.
    * Returns false in any other scenario.
    */
    private boolean didPlayerLose() {
        int numThrows = bird.getNumThrowsRemaining();
        return (numThrows == 0) && this.mouseListeningMode;
    }
    
    /**
    * Returns true when either the win or lose
    * condition is fulfilled.
    * Win: All targets' hit points are 0.
    * Lose: The bird's remaining throw count reaches 0.
    * 		Additionally, the game must be in mouse listening
    * 		mode for the player to have lost so that the bird
    * 		can finish its final flight and potentially hit
    * 		the last target(s).
    */
    public boolean gameOver() {
        return didPlayerWin() || didPlayerLose();
    }
    
    /**
    * Update each of the entities within the arena.
    * 1. Call each Target's update function
    * 2. Check the game state (mouse listening or bird moving)
    * and invoke the appropriate functions for the bird.
    */
    public void update(double timeStep) {
        for (int i = 0; i < targets.length; i++) {
            targets[i].update(timeStep);
        }
        if (mouseListeningMode) {
            /**
            * If the mouse is currently pressed, then
            * set mouseWasPressedLastUpdate to true, and
            * call bird.setVelocityFromMousePos.
            */
            if (PennDraw.mousePressed()) {
                this.mouseWasPressedLastUpdate = true;
                bird.setVelocityFromMousePos();
            }
            /**
            * If the mouse is NOT currently pressed, AND
            * mouseWasPressedLastUpdate is currently true,
            * that means the player has just released the
            * mouse button, and the game should transition
            * from mouse-listening mode to bird-flight mode.
            */
            if ((!PennDraw.mousePressed()) && mouseWasPressedLastUpdate) {
                this.mouseWasPressedLastUpdate = false;
                this.mouseListeningMode = false;
                bird.decrementThrows();
            }
        }
        else {
            bird.update(timeStep);
            for (int i = 0; i < targets.length; i++) {
                bird.testAndHandleCollision(targets[i]);
            }
            if (birdIsOffscreen()) {
                for (int i = 0; i < targets.length; i++) {
                    if (targets[i].isHit()) {
                        targets[i].decreaseHP();
                        targets[i].setHitThisShot(false);
                    }
                }
            bird.reset();
            this.mouseListeningMode = true;
            }
        }
    }
    
    /**
    * A helper function for the Arena class that lets
    * it know when to reset the bird's position and velocity
    * along with the game state.
    * Returns true when the bird is offscreen to the left, right,
    * or bottom. However, the bird is allowed to go above the top
    * of the screen without resetting.
    */
    private boolean birdIsOffscreen() {
        double birdRadius = bird.getRadius();
        double birdXPos = bird.getXpos();
        double birdYPos = bird.getYpos();

        return (birdYPos + birdRadius) < 0 || (birdXPos + birdRadius) < 0 || 
        (birdXPos - birdRadius) > this.width;
    }
    
    /**
    * 1. Clear the screen
    * 2. Draw each target
    * 3. Draw the bird
    * 4. If in mouse listening mode and
    *    the mouse was pressed last update,
    *    draw the bird's velocity as a line.
    * 5. Advance PennDraw.
    */
    public void draw() {
        PennDraw.clear();
        for (int i = 0; i < targets.length; i++) {
            this.targets[i].draw();
        }
        this.bird.draw();
        if (mouseListeningMode && mouseWasPressedLastUpdate) {
            this.bird.drawVelocity();
        }
        PennDraw.advance();
    }
    
    /**
    * Draws either the victory or loss screen.
    * If all targets have 0 hit points, the player has won.
    * Otherwise they have lost.
    */
    public void drawGameCompleteScreen() {
        PennDraw.clear();
        PennDraw.setFontSize(50);
        if (gameOver() && didPlayerWin()) {
            PennDraw.text(this.width / 2, this.height / 2, "You Win!");
        } else if (gameOver() && didPlayerLose()) {
            PennDraw.text(this.width / 2, this.height / 2, "You have lost...");
        }
        PennDraw.advance();
    }
}

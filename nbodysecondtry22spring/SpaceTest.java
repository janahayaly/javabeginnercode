import org.junit.Test;
import static org.junit.Assert.*;

public class SpaceTest {

    // Do not change this!
    // Use DELTA as the tolerance for your assertEquals statements in this file.
    // This is a larger number than in the BodyTest file to account for the
    // larger tolerance for error in planetary scales.
    public static final double DELTA = 1e6;

    /*
     * REQUIRED TESTS
     */

    @Test
    public void testSpaceConstructorFields() {
        Space s = new Space("binary.txt");

        int actualLength = s.bodies.length;
        int expectedLength = 2;
        assertEquals(expectedLength, actualLength, DELTA);

        double actualRadius = s.radius;
        double expectedRadius = 5.0e10;
        assertEquals(expectedRadius, actualRadius, DELTA);
    }

    @Test
    public void testSpaceConstructorBodies() {
        Space s = new Space("binary.txt");

        double expectedPX0 = 0.0;
        double expectedVY1 = 0.0; 

        double actualPX0 = s.bodies[0].px;
        double actualVY1 = s.bodies[1].vy;
        
        assertEquals(expectedPX0, actualPX0, DELTA);
        assertEquals(expectedVY1, actualVY1, DELTA);
    }

    @Test
    public void testSpaceConstructorBodiesAgain() {
        Space s = new Space("binary.txt");

        double expectedPY1 = -4.5e10;
        double expectedVX0 = 1.0e04; 

        double actualPY1 = s.bodies[1].py;
        double actualVX0 = s.bodies[0].vx;
        
        assertEquals(expectedPY1, actualPY1, DELTA);
        assertEquals(expectedVX0, actualVX0, DELTA);
    }

    @Test
    public void testSpaceSimulateEarth() {
        Space s = new Space("solarSystem.txt");
        s.simulate(25000.0);

        double expectedPX = 1.49596e11;
        double expectedPY = 7.45000e+08;
        double expectedVX = -1.48201e+02;
        double expectedVY = 2.98000e+04;

        double actualPX = s.bodies[0].px;
        double actualPY = s.bodies[0].py;
        double actualVX = s.bodies[0].vx;
        double actualVY = s.bodies[0].vy;

        assertEquals(expectedPX, actualPX, DELTA);
        assertEquals(expectedPY, actualPY, DELTA);
        assertEquals(expectedVX, actualVX, DELTA);
        assertEquals(expectedVY, actualVY, DELTA);
    }

    @Test
    public void testSpaceSimulateMars() {
        Space s = new Space("solarSystem.txt");
        s.simulate(25000.0);

        double expectedPX = 2.27898e+11;
        double expectedPY = 6.02500e+08;
        double expectedVX = -6.38597e+01;
        double expectedVY = 2.41000e+04;

        double actualPX = s.bodies[1].px;
        double actualPY = s.bodies[1].py;
        double actualVX = s.bodies[1].vx;
        double actualVY = s.bodies[1].vy;

        assertEquals(expectedPX, actualPX, DELTA);
        assertEquals(expectedPY, actualPY, DELTA);
        assertEquals(expectedVX, actualVX, DELTA);
        assertEquals(expectedVY, actualVY, DELTA);
    }

    @Test
    public void testSpaceSimulateMercury() {
        Space s = new Space("solarSystem.txt");
        s.simulate(25000.0);

        double expectedPX = 5.78753e+10;
        double expectedPY = 1.19750e+09;
        double expectedVX = -9.89331e+02;
        double expectedVY = 4.79000e+04;

        double actualPX = s.bodies[2].px;
        double actualPY = s.bodies[2].py;
        double actualVX = s.bodies[2].vx;
        double actualVY = s.bodies[2].vy;

        assertEquals(expectedPX, actualPX, DELTA);
        assertEquals(expectedPY, actualPY, DELTA);
        assertEquals(expectedVX, actualVX, DELTA);
        assertEquals(expectedVY, actualVY, DELTA);
    }

}
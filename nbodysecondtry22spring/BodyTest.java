import org.junit.Test;
import static org.junit.Assert.*;

public class BodyTest {

    // Do not change this!
    // Use DELTA as the tolerance for your assertEquals statements.
    public static final double DELTA = 1e-4;

    /*
     * REQUIRED TESTS
     */

    /////////////////////////////////////
    // DISTANCE TESTS ///////////////////
    /////////////////////////////////////
    @Test
    public void testDistanceToX() {
        Body sun = new Body(1, 3, 4, 0, 0, "sun.gif");
        Body earth = new Body(1, 7, 7, 0, 0, "earth.gif");

        double actual = sun.distanceToX(earth);
        double expected = 4.0;

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void testDistanceToY() {
        Body sun = new Body(1, 3, 4, 0, 0, "sun.gif");
        Body earth = new Body(1, 7, 7, 0, 0, "earth.gif");

        double actual = sun.distanceToY(earth);
        double expected = 3.0;

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void testDistanceTo() {
        Body sun = new Body(1, 3, 4, 0, 0, "sun.gif");
        Body earth = new Body(1, 7, 7, 0, 0, "earth.gif");

        double actual = sun.distanceTo(earth);
        double expected = 5.0;

        assertEquals(expected, actual, DELTA);
    }

    /////////////////////////////////////
    // FORCE TESTS //////////////////////
    /////////////////////////////////////

    @Test
    public void testForce() {
        Body mars = new Body(10, 2, 4, 0, 0, "mars.gif");
        Body mercury = new Body(50, 7, 10, 0, 0, "mercury.gif");

        double actual = mars.force(mercury);
        double expected = 6.67e-10 / 3050;

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void testForceX() {
        Body mars = new Body(10, 2, 4, 0, 0, "mars.gif");
        Body mercury = new Body(50, 7, 10, 0, 0, "mercury.gif");

        double actual = mars.forceX(mercury);
        double expected = (5 * 6.67e-10 / 3050) / Math.sqrt(61);

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void testForceY() {
        Body mars = new Body(10, 2, 4, 0, 0, "mars.gif");
        Body mercury = new Body(50, 7, 10, 0, 0, "mercury.gif");

        double actual = mars.forceX(mercury);
        double expected = (6 * 6.67e-10 / 3050) / Math.sqrt(61);

        assertEquals(expected, actual, DELTA);
    }

    /////////////////////////////////////
    // VELOCITY TESTS ///////////////////
    /////////////////////////////////////

    @Test
    public void testMove() {
        Body mercury = new Body(5000, 7, 10, -3, 4, "mercury.gif");
        mercury.move(10);

        double actualX = mercury.px;
        double actualY = mercury.py;

        double expectedX = -23;
        double expectedY = 50;

        assertEquals(expectedX, actualX, DELTA);
        assertEquals(expectedY, actualY, DELTA);
    }

    @Test
    public void testGetAffectedBy() {
        Body mars = new Body(1000, 2, 4, 0, 0, "mars.gif");
        Body mercury = new Body(5000, 7, 10, -3, 4, "mercury.gif");

        mars.getAffectedBy(mercury, 100000.0);

        double actualXMerc = mercury.vx;
        double actualYMerc = mercury.vy;

        double actualXMars = mars.vx;
        double actualYMars = mars.vy;

        double expectedXMerc = -3;
        double expectedYMerc = 4;

        double expectedXMars = (2.5e9 * 6.67e-11) / (61 * Math.sqrt(61));
        double expectedYMars = (3e9 * 6.67e-11) / (61 * Math.sqrt(61));

        assertEquals(expectedXMerc, actualXMerc, DELTA);
        assertEquals(expectedYMerc, actualYMerc, DELTA);
        assertEquals(expectedXMars, actualXMars, DELTA);
        assertEquals(expectedYMars, actualYMars, DELTA);
    }
}
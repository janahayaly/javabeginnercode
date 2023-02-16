public class Sierpinski {

    public static void sierpinski(int numLevels, double halfSideLength, 
    double x, double y) {
        PennDraw.enableAnimation(100);
        if (numLevels < 1) {
            return;
        } else {
            PennDraw.filledSquare(x, y, halfSideLength);
            sierpinski(numLevels - 1, halfSideLength / 3, 
            x + 2 * halfSideLength, y);
            sierpinski(numLevels - 1, halfSideLength / 3, 
            x - 2 * halfSideLength, y);
            sierpinski(numLevels - 1, halfSideLength / 3, 
            x, y + 2 * halfSideLength);
            sierpinski(numLevels - 1, halfSideLength / 3, 
            x, y - 2 * halfSideLength);
            sierpinski(numLevels - 1, halfSideLength / 3, 
            x + 2 * halfSideLength, y + 2 * halfSideLength);
            sierpinski(numLevels - 1, halfSideLength / 3, 
            x - 2 * halfSideLength, y - 2 * halfSideLength);
            sierpinski(numLevels - 1, halfSideLength / 3, 
            x + 2 * halfSideLength, y - 2 * halfSideLength);
            sierpinski(numLevels - 1, halfSideLength / 3, 
            x - 2 * halfSideLength, y + 2 * halfSideLength);
        }
        PennDraw.advance();
    }

    public static void main(String[] args) {

        sierpinski(Integer.parseInt(args[0]), 1.0 / 6.0, 0.5, 0.5);

    }
}
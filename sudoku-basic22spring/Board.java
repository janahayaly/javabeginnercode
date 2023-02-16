public class Board {

//declaring a variable for the half width of each cell in the grid
    private double cellHalfWidth;

    public Board() {
        this.cellHalfWidth = 0.5;
    }

//drawing the board (grid)
    public void drawBoard() {
        //clearing and setting scale + declaring variables
        PennDraw.clear(PennDraw.WHITE);
        PennDraw.setXscale(0, 9);
        PennDraw.setYscale(0, 9);

        double xCenter = 0.5;
        double yCenter = 0.5;

        //drawing each cell in the grid
        PennDraw.setPenRadius(0.002);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                PennDraw.square(xCenter, yCenter, this.cellHalfWidth);
                yCenter += 1;
            }
            xCenter += 1;
            yCenter = 0.5;
        }

        //drawing the borders of the squares
        PennDraw.setPenRadius(0.01);
        PennDraw.line(3, 0, 3, 9);
        PennDraw.line(6, 0, 6, 9);
        PennDraw.line(0, 3, 9, 3);
        PennDraw.line(0, 6, 9, 6);
    }
}
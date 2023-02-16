public class SquareError implements Error {

//checking for a square error
    public boolean check(Cell[][] initial, Cell input, 
    double xClick, double yClick) {

        boolean errorPresent = false;

        int startingRow = 0;
        int startingCol = 0;

        int inputRowIdx = (int) Math.abs(yClick - 9);
        int inputColIdx = (int) xClick;

        //determines the starting row based on position
        if (inputRowIdx >= 0 && inputRowIdx <= 2) {
            startingRow = 0;
        } else if (inputRowIdx >= 3 && inputRowIdx <= 5) {
            startingRow = 3;
        } else if (inputRowIdx >= 6 && inputRowIdx <= 8) {
            startingRow = 6;
        }

        //determines the starting column based on position
        if (inputColIdx >= 0 && inputColIdx <= 2) {
            startingCol = 0;
        } else if (inputColIdx >= 3 && inputColIdx <= 5) {
            startingCol = 3;
        } else if (inputColIdx >= 6 && inputColIdx <= 8) {
            startingCol = 6;
        }

        //checks if there are duplicates in the square
        if (input.getChar() != ' ') {
            for (int i = startingRow; i <= startingRow + 2; i++) {
                for (int j = startingCol; j <= startingCol + 2; j++) {
                    if (!(i == inputRowIdx && j == inputColIdx)) {
                        if (initial[i][j].getChar() == input.getChar()) {
                            errorPresent = true;
                        }
                    }
                }
            }
        }
        return errorPresent;
    }

    public void visualize(int row, int column) {
        
        int startingRow = 0;
        int startingCol = 0;

        //determines the starting row based on position
        if (row >= 0 && row <= 2) {
            startingRow = 0;
        } else if (row >= 3 && row <= 5) {
            startingRow = 3;
        } else if (row >= 6 && row <= 8) {
            startingRow = 6;
        }

        //determines the starting column based on position
        if (column >= 0 && column <= 2) {
            startingCol = 0;
        } else if (column >= 3 && column <= 5) {
            startingCol = 3;
        } else if (column >= 6 && column <= 8) {
            startingCol = 6;
        }

        //draws the red cells into the square
        for (int i = startingRow; i < startingRow + 3; i++) {
            for (int j = startingCol; j < startingCol + 3; j++) {
                PennDraw.setPenColor(255, 0, 0, 200);
                PennDraw.filledSquare(j + 0.5, (i % 9) + 0.5, 0.5);
            
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.setPenRadius(0.002);
                PennDraw.square(j + 0.5, i + 0.5, 0.5);
            }
        }

        //redraws the borders
        PennDraw.setPenRadius(0.01);
        PennDraw.line(3, 0, 3, 9);
        PennDraw.line(6, 0, 6, 9);
        PennDraw.line(0, 3, 9, 3);
        PennDraw.line(0, 6, 9, 6);
        PennDraw.setPenRadius(0.002);
    }
    
}
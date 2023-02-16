public class Player {

//clears the board/re-draws edited array over a grid
    public void clear(Board b, Array arr, Cell[][] edited) {
        PennDraw.clear(PennDraw.WHITE);
        PennDraw.setPenColor(PennDraw.BLACK);
        b.drawBoard();
        arr.drawArray(edited);
    }

//checks if the game has ended (the array is full)
    public boolean didGameEnd(Cell[][] currentArr) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (currentArr[i][j].getChar() == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

//turns the y component of the mouse click to the appropriate row index
    public int clickToRowIndex(double yClick) {
        int rowIdx = (int) Math.abs(yClick - 9);
        return rowIdx;
    }

//turns the x component of the mouse click to the appropriate column index
    public int clickToColIndex(double xClick) {
        int colIdx = (int) xClick;
        return colIdx;
    }

//combines the error check for the 3 types to generally check if an errror is present
    public boolean checkError(Cell[][] initial, Cell input, 
    double xClick, double yClick) {
        RowError row = new RowError();
        ColumnError column = new ColumnError();
        SquareError square = new SquareError();

        if (row.check(initial, input, xClick, yClick) ||
        column.check(initial, input, xClick, yClick) || 
        square.check(initial, input, xClick, yClick)) {
            return true;
        } else {
            return false;
        }
    }

//checks if a cell is eligible for input- it is empty or it is an input by the player
    public boolean checkCellEmpty(Cell[][] array, int rowIdx, int colIdx) { 
        boolean emptyCar = array[rowIdx][colIdx].getChar() == ' ';
        boolean partOfInitial = array[rowIdx][colIdx].isOriginal();
        return emptyCar || !partOfInitial;
    }

//adds an input to the array and draws it on the board in the appropriate space
    public void addInput(Array original, Cell[][] initial, Cell input, 
    double xClick, double yClick) {
        int rowIdx = clickToRowIndex(yClick);
        int colIdx = clickToColIndex(xClick);

        //checks that the player gave a valid input
        if (!((input.getChar() < 49 || input.getChar() > 57) && 
        !(input.getChar() == 32))) {
            //checks that the cell is eligible for input
            if (checkCellEmpty(initial, rowIdx, colIdx)) {
                //draws the input in the appropriate space
                PennDraw.setPenColor(PennDraw.BLUE);
                PennDraw.setFontSize(30);
                PennDraw.text(((int) xClick) + 0.5, ((int) yClick) + 0.5, 
                input.getChar() + "");
                //adds input to array
                original.updateArray(initial, input, rowIdx, colIdx);
            }
        }
    }

//visualizes the error that is present
    public void visualizeError(Array arr, Cell[][] initial, Cell input, 
    double xClick, double yClick) { 
        Error row = new RowError();
        Error column = new ColumnError();
        Error square = new SquareError();

        Board board = new Board();

        //checks that the player gave a valid input
        if (!((input.getChar() < 49 || input.getChar() > 57) && 
        !(input.getChar() == 32))) {
            //checks that the cell is eligible for input
            if (checkCellEmpty(initial, clickToRowIndex(yClick), 
            clickToColIndex(xClick))) {
                //clears and redraws the board
                PennDraw.clear(PennDraw.WHITE);
                PennDraw.setPenColor(PennDraw.BLACK);
                board.drawBoard();

                //checks which error and visualizes accordingly
                if (row.check(initial, input, xClick, yClick)) {
                    row.visualize((int) yClick, clickToColIndex(xClick));
                } 

                if (column.check(initial, input, xClick, yClick)) {
                    column.visualize((int) yClick, clickToColIndex(xClick));
                }

                if (square.check(initial, input, xClick, yClick)) {
                    square.visualize((int) yClick, clickToColIndex(xClick));
                }

                //draw array over color coding
                PennDraw.setPenColor(PennDraw.BLACK);
                arr.drawArray(initial);

                //draw input
                PennDraw.setFontSize(30);
                PennDraw.setPenColor(PennDraw.BLUE);
                PennDraw.text(((int) xClick) + 0.5, ((int) yClick) + 0.5, 
                input.getChar() + "");
            }
        }
    }
}
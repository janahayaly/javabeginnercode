public class ColumnError implements Error {

//checking for a column error
    public boolean check(Cell[][] initial, Cell input, 
    double xClick, double yClick) {
        boolean errorPresent = false;
        char c = input.getChar();

        //checks if character is not a space
        if (c != ' ') {
            int rowIdx = (int) Math.abs(yClick - 9);
            int colIdx = (int) xClick;

            //updates error if there is a duplicate in the column
            for (int i = 1; i < 9; i++) {
                if (c == initial[(rowIdx + i) % 9][colIdx].getChar()) {
                   errorPresent = true;
                }
            }
        }
        return errorPresent;
    }

//visualizing the column error
    public void visualize(int row, int column) {

        //draws the red cells in the column
        for (int i = 0; i < 9; i++) {
            PennDraw.setPenColor(255, 0, 0, 200);
            PennDraw.filledSquare(column + 0.5, (row + 0.5) % 9, 0.5);

            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.setPenRadius(0.002);
            PennDraw.square(column + 0.5, (row + 0.5) % 9, 0.5);
            row++;
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
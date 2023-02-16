public class RowError implements Error {

//checking for a row error
    public boolean check(Cell[][] initial, Cell input, 
    double xClick, double yClick) {
        boolean errorPresent = false;
        char c = input.getChar();
        
        //checks if character is not a space
        if (c != ' ') {
            int rowIdx = (int) Math.abs(yClick - 9);
            int colIdx = (int) xClick;

            //updates error if there is a duplicate in the row
            for (int i = 1; i < 9; i++) {
                if (c == initial[rowIdx][(colIdx + i) % 9].getChar()) {
                   errorPresent = true;
                }
            }
        }
        return errorPresent;
    }

//visualizing the row errror
    public void visualize(int row, int column) {
        
        //draws the red cells in the row
        for (int i = 0; i < 9; i++) {
            PennDraw.setPenColor(255, 0, 0, 200);
            PennDraw.filledSquare((column + 0.5) % 9, row + 0.5, 0.5);
            
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.setPenRadius(0.002);
            PennDraw.square((column + 0.5) % 9, row + 0.5, 0.5);
            column++;
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
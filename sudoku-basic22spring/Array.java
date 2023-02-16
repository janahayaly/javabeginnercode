public class Array {

//instance variable: file from which to read initial array
    private String filename;

//constructor
    public Array(String filename) {
        this.filename = filename;
    }

//function to get a 2d array from the text file
    public Cell[][] getInitialArray() {
        //create array and read in file
        Cell[][] initialArray = new Cell[9][9];
        In inStream = new In(this.filename);

        //check formatting of file- read array in if correct
        if (fileWrongFormat(this.filename)) {
            throw new IllegalArgumentException("File is in incompatible format!");
        } else {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    initialArray[i][j] = new Cell(inStream.readChar(), true);
                }
                if (i < 8) {
                    int dummyEnter = inStream.readChar();   
                } 
            }
        }

        //check if there are errors in original array- initialize a 'dummy' player 
        //object
        Player dummy = new Player();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (dummy.checkError(initialArray, initialArray[row][col], col, 
                Math.abs(row - 9))) {
                    throw new IllegalArgumentException("Error present in og board");
                }
            }
        }

        return initialArray;
    }

//checks if file is the right format
    public boolean fileWrongFormat(String filename) {
        In inStream = new In(filename);

        //checks if it is 9 characters in a row: a number between 1-9 or a space
        for (int row = 1; row <= 9; row++) {
            for (int c = 1; c <= 9; c++) {
                char ch = inStream.readChar();
                if ((ch < 49 || ch > 57) && !(ch == 32)) {
                    return true;
                }
            }
            //checks to see if end of row has a line break
            if (row < 9) {
                if (!(inStream.readChar() == 10)) {
                    return true;
                }
            }
        }
        return false;
    }

//draws the array over the board
    public void drawArray(Cell[][] initialArr) {
        //draw array
        double xCenter = 0.5;
        double yCenter = 8.5;
        PennDraw.setFontSize(30);
        
        //iterates through positions and corresponding array values
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (initialArr[i][j].isOriginal()) {
                    PennDraw.setPenColor(PennDraw.BLACK);
                } else {
                    PennDraw.setPenColor(PennDraw.BLUE);
                }
                PennDraw.text(xCenter, yCenter, initialArr[i][j].getChar() + "");
                xCenter += 1;
            }
            yCenter -= 1;
            xCenter = 0.5;
        }
    }

//updates the chosen array position with the input
    public void updateArray(Cell[][] initialArr, Cell input, int row, int col) {
        initialArr[row][col] = input;
    }
}
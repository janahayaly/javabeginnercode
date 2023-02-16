public class Cell {

//instance variables for cell- character it carries & if it is part of the initial
    private char input;
    private boolean original;

    public Cell(char input, boolean original) {
        this.input = input;
        this.original = original;
    }

//getter for if it is part of initial
    public boolean isOriginal() {
        return this.original;
    }

//getter for character contained in the cell
    public char getChar() {
        return this.input;
    }

}
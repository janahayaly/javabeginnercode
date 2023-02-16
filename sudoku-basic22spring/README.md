Purpose of each file:
    Board: This is thee grid: draws the board as a 9x9 grid of cells

    Cell: Class for cell objects- each cell contains a character and we can tell if
    it is part of the original array

    Array: Takes in the file and produces the initial array, then has functions 
    which access and edit the array for different purposes (such as to draw it or
    add an input)

    Player: Functions as the 'arena' of the game. No constructor but contains the 
    bulk of the functions necessary to make the game run- for example, checking for
    and visualizing errors as well as adding input to the board

    Game: Class with main function that actually runs the game in its different 
    states. Uses functions from the many other classes to make it work.

    Error Interface: template for checking and visualizing different types of errors
        Row Error: checks and visualizes an error in the row
        Column Error: checks and visualizes an error in the column
        Square Error: checks and visualizes an error in the square

How to run the program:
    Compile and run "java Game filename"
    The 'filename' is a command line argument of a file which contains the values 
    for an original sudoku board. An example is the sudokuExample.txt we were 
    provided. I also wrote up and submitted sudokuExample2.txt that can be used. 
    Apprropriate formatting must be used or the program will throw an 
    IllegalArgumentException.

That's it! Thank you for a great semester :)

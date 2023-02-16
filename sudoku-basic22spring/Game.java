public class Game {
    
    public static void main(String[] args) {

        //initializes variables
        Cell input = new Cell(' ', false);
        double xClick = 1;
        double yClick = 1;
        boolean errorPresent = false;

        //instance of classes
        Board board = new Board();
        Array array = new Array(args[0]);
        Player player = new Player();

        //getting and drawing board and array
        Cell[][] arr = array.getInitialArray();

        board.drawBoard();
        array.drawArray(arr);

        //enabling animation
        PennDraw.enableAnimation(30);

        //loop for when the game is still being played
        while (!(player.didGameEnd(arr))) {

            //update position chosen wit mouse click
            if (PennDraw.mousePressed()) {
                player.clear(board, array, arr);
                errorPresent = false;
                input = new Cell(' ', false);

                xClick = PennDraw.mouseX();
                yClick = PennDraw.mouseY();
            }

            //update input with key click
            if (PennDraw.hasNextKeyTyped()) {
                input = new Cell(PennDraw.nextKeyTyped(), false);
                errorPresent = player.checkError(arr, input, xClick, yClick);
            }

            //act according to if an error exists
            if (errorPresent) {
                player.addInput(array, arr, new Cell(' ', false), xClick, yClick);
                player.visualizeError(array, arr, input, xClick, yClick);
            } else {
                player.addInput(array, arr, input, xClick, yClick);
                player.clear(board, array, arr);
            }

            //advance
            PennDraw.advance();
        }

        //disable the animation once game ends
        PennDraw.disableAnimation();

        //write out a congratulations message if game is won
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledRectangle(4.5, 4.5, 4, 0.6);
        PennDraw.setPenColor(81, 156, 96);
        PennDraw.setPenRadius(0.25);
        PennDraw.setFontSize(40);
        PennDraw.text(4.5, 4.5, "CONGRATULATIONS!");
    }
}
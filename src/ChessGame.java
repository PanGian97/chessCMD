import java.util.Scanner;

public class ChessGame {

    public static void main(String[] args) throws InvalidMoveException {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();  // Initialize the chessboard
        board.init();  // Set up the pieces in their starting positions

        Game game = new Game();
        game.play();
        scanner.close();  // Close the scanner when the game is finished
        System.out.println("Game finished.");
    }

}

import java.util.Scanner;

public class Game {
    private Board board;
    private Color currentPlayer;

    public Game() {
        this.board = new Board();
        this.currentPlayer = Color.WHITE; // Οι λευκοί παίζουν πρώτοι
    }

    public void play(Scanner scanner) {
        boolean gameRunning = true;

        while (gameRunning) {
            System.out.println(board);
            System.out.println("Current player: " + currentPlayer);
            System.out.print("Enter your move (or command): ");

            String input = scanner.nextLine();
            if (input.startsWith(":")) {
                if (input.equals(":exit")) {
                    gameRunning = exitGame(); // Exit the game loop if :exit command is given
                } else {
                    handleCommand(input);
                }
            } else {
                try {
                    handleMove(input);
                    currentPlayer = currentPlayer.nextColor(); // Switch players after a successful move
                } catch (InvalidMoveException e) {
                    System.out.println("Invalid move: " + e.getMessage());
                }
            }
        }
        scanner.close();
    }


    private void handleMove(String moveString) throws InvalidMoveException {
        try {
            // Assume the moveString is in the format "e2e4"
            Location from = new Location(moveString.substring(0, 2));
            Location to = new Location(moveString.substring(2, 4));
            if (board.getPieceAt(to) != null) {
                // If the destination has a piece, it's a capture move
                board.movePieceCapturing(from, to);
            } else {
                // Otherwise, it's a normal move
                board.movePiece(from, to);
            }

            System.out.println("Move made from " + from + " to " + to);

        } catch (InvalidLocationException e) {
            System.out.println(e.getMessage());
            // Don't switch players if move is invalid
            currentPlayer = currentPlayer.nextColor();
        }
    }


    private Boolean exitGame() {
        System.out.println("Exiting game.");
        return false;

    }

    private void printHelp() {
        System.out.println("Enter moves in the format 'e2e4'.");
        System.out.println("Commands:");
        System.out.println(":save - Save the game");
        System.out.println(":load - Load a game");
        System.out.println(":exit - Exit the game");
        System.out.println(":help - Print this help message");
    }

    private void handleCommand(String command) {
        switch (command) {

            case ":help":
                printHelp();
                break;
            default:
                System.out.println("Unknown command. Type ':help' for a list of commands.");
                break;
        }
    }
}

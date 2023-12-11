import java.util.Scanner;

public class Game {
    private Board board;
    private Color currentPlayer;

    public Game() {
        this.board = new Board();
        this.currentPlayer = Color.WHITE; // Οι λευκοί παίζουν πρώτοι
    }

    public void play() throws InvalidMoveException {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        while (gameRunning) {
            System.out.println(board);
            System.out.println("Current player: " + currentPlayer);
            System.out.print("Enter your move (or command): ");

            String input = scanner.nextLine();
            if (input.startsWith(":")) {
                handleCommand(input);
            } else {
                try {
                    // Example: make a move
                   handleMove(input);
                    currentPlayer = currentPlayer.nextColor();
                } catch (InvalidMoveException e) {
                    System.out.println("Invalid move: " + e.getMessage());

                }

            }
            // Switch players after each successful move

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

    private void saveGame() {
        // This is placeholder logic. Actual saving would involve writing the game state to a file.
        System.out.println("Game saved (placeholder logic).");
    }

    private void openGame() {
        // This is placeholder logic. Actual loading would involve reading the game state from a file.
        System.out.println("Game loaded (placeholder logic).");
    }

    private boolean exitGame() {
        System.out.println("Exiting game.");
        return false; // This would actually terminate the play loop and end the game
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
            case ":save":
                saveGame();
                break;
            case ":load":
                openGame();
                break;
            case ":exit":
                exitGame();
                break;
            case ":help":
                printHelp();
                break;
            default:
                System.out.println("Unknown command. Type ':help' for a list of commands.");
                break;
        }
    }
}

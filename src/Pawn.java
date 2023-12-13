public class Pawn extends Piece {
    private boolean isFirstMove = true; // Track if the pawn has moved

    public Pawn(Color color, Location location, Board board) {
        super(color, location, board);
    }

    @Override
    public Boolean canMove(Location newLoc) throws InvalidMoveException {
        int currentRow = this.location.getRow();
        int currentCol = this.location.getCol();
        int newRow = newLoc.getRow();
        int newCol = newLoc.getCol();

        int rowDiff = color == Color.BLACK? newRow - currentRow : currentRow - newRow;
        int colDiff = Math.abs(newCol - currentCol);

        // Check forward move
        if (colDiff == 0 && ((isFirstMove && rowDiff == 2) || rowDiff == 1)) {

            this.setLocation(newLoc);
            isFirstMove = false; // Set isFirstMove to false after the first move
            return true;
        }
        // Check capture move
        else if (colDiff == 1 && rowDiff == 1) {
            if (board.getPieceAt(newLoc) == null || board.getPieceAt(newLoc).getColor() == this.color) {
                throw new InvalidMoveException("Invalid move: Pawns can only move diagonally to capture.");
            } else { // Perform the move

                this.setLocation(newLoc);
                isFirstMove = false; // Set isFirstMove to false after the first move
                return true;
            }
        } else {
            throw new InvalidMoveException("Invalid move: Pawns can only move forward one square or two squares on their first move, or diagonally forward to capture.");

        }
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "P" : "p";
    }
}
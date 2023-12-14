public class King extends Piece {
    public King(Color color, Location location, Board board) {
        super(color, location, board);
    }

    @Override
    public Boolean canMove(Location newLoc) throws InvalidMoveException {
        // Calculate the difference in rows and columns between the current and new location
        int rowDiff = Math.abs(newLoc.getRow() - this.location.getRow());
        int colDiff = Math.abs(newLoc.getCol() - this.location.getCol());

        // Check if the move is only one square in any direction
        if (rowDiff <= 1 && colDiff <= 1) {
            // Check if the target location is valid (not occupied by a piece of the same color)
            Piece targetPiece = board.getPieceAt(newLoc);
            if (targetPiece == null) {
                this.setLocation(newLoc);
                return true;
            }
            else if (targetPiece.getColor() != this.color) {
                this.setLocation(newLoc);
                return true;
            } else {
                throw new InvalidMoveException("Invalid move: target square is occupied by a friendly piece.");
            }
        } else {
            throw new InvalidMoveException("Invalid move: King can only move one square in any direction.");
        }


    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "K" : "k";
    }
}
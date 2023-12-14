public class Bishop extends Piece {
    public Bishop(Color color, Location location, Board board) {
        super(color, location, board);
    }

    @Override
    public Boolean canMove(Location newLoc) throws InvalidMoveException {
        int currentRow = this.location.getRow();
        int currentCol = this.location.getCol();
        int newRow = newLoc.getRow();
        int newCol = newLoc.getCol();

        // Determine the type of move

        boolean isDiagonalMove = Math.abs(newRow - currentRow) == Math.abs(newCol - currentCol);
        boolean isAntidiagonalMove = isDiagonalMove && ((newRow - currentRow) * (newCol - currentCol) < 0);//ChatGPT thought it :)


         if (isDiagonalMove && !isAntidiagonalMove) {
            if (!board.freeDiagonalPath(this.location, newLoc)) {
                throw new InvalidMoveException("Invalid move: Path is not clear for diagonal move.");
            }
        } else if (isAntidiagonalMove) {
            if (!board.freeAntidiagonalPath(this.location, newLoc)) {
                throw new InvalidMoveException("Invalid move: Path is not clear for antidiagonal move.");
            }
        } else {
            throw new InvalidMoveException("Invalid move: Bishop can only move horizontally, vertically, or diagonally.");
        }

        // Check if the target location is valid (not occupied by a piece of the same color)
        Piece targetPiece = board.getPieceAt(newLoc);
        if (targetPiece != null && targetPiece.getColor() == this.color) {
            throw new InvalidMoveException("Invalid move: target square is occupied by a friendly piece.");
        }

        this.setLocation(newLoc); // Update the bishop's internal location
        return true;
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "B" : "b";
    }
}



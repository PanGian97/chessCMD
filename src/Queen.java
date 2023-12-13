public class Queen extends Piece {
    public Queen(Color color, Location location, Board board) {
        super(color, location, board);
    }

    @Override
    public Boolean canMove(Location newLoc) throws InvalidMoveException {
        int currentRow = this.location.getRow();
        int currentCol = this.location.getCol();
        int newRow = newLoc.getRow();
        int newCol = newLoc.getCol();

        // Determine the type of move
        boolean isHorizontalMove = currentRow == newRow;
        boolean isVerticalMove = currentCol == newCol;
        boolean isDiagonalMove = Math.abs(newRow - currentRow) == Math.abs(newCol - currentCol);
        boolean isAntidiagonalMove = isDiagonalMove && ((newRow - currentRow) * (newCol - currentCol) < 0);//ChatGPT thought it :)

        // Check if the move is valid and path is clear
        if (isHorizontalMove) {
            if (!board.freeHorizontalPath(this.location, newLoc)) {
                throw new InvalidMoveException("Invalid move: Path is not clear for horizontal move.");
            }
        } else if (isVerticalMove) {
            if (!board.freeVerticalPath(this.location, newLoc)) {
                throw new InvalidMoveException("Invalid move: Path is not clear for vertical move.");
            }
        } else if (isDiagonalMove && !isAntidiagonalMove) {
            if (!board.freeDiagonalPath(this.location, newLoc)) {
                throw new InvalidMoveException("Invalid move: Path is not clear for diagonal move.");
            }
        } else if (isAntidiagonalMove) {
            if (!board.freeAntidiagonalPath(this.location, newLoc)) {
                throw new InvalidMoveException("Invalid move: Path is not clear for antidiagonal move.");
            }
        } else {
            throw new InvalidMoveException("Invalid move: Queen can only move horizontally, vertically, or diagonally.");
        }

        // Check if the target location is valid (not occupied by a piece of the same color)
        Piece targetPiece = board.getPieceAt(newLoc);
        if (targetPiece != null && targetPiece.getColor() == this.color) {
            throw new InvalidMoveException("Invalid move: target square is occupied by a friendly piece.");
        }

        // Perform the move
        board.movePiece(this.location, newLoc);
        this.setLocation(newLoc); // Update the queen's internal location
        return null;
    }


    @Override
    public String toString() {
        return color == Color.WHITE ? "Q" : "q";
    }
}

public class Rook extends Piece {
    public Rook(Color color, Location location, Board board) {
        super(color, location, board);
    }

    @Override
    public Boolean canMove(Location newLoc) throws InvalidMoveException {
        int currentRow = this.location.getRow();
        int currentCol = this.location.getCol();
        int newRow = newLoc.getRow();
        int newCol = newLoc.getCol();

        // Check if the move is horizontal or vertical
        boolean isHorizontalMove = currentRow == newRow;
        boolean isVerticalMove = currentCol == newCol;


        if (!(isHorizontalMove ^ isVerticalMove)) {
            throw new InvalidMoveException("Invalid move: Rook must move either horizontally or vertically.");
        }
        if (!board.freeHorizontalPath(this.location, newLoc)) {
            throw new InvalidMoveException("Invalid move: Horizontal Path is not clear for this move.");
        }
        else if (!board.freeVerticalPath(this.location, newLoc)) {
            throw new InvalidMoveException("Invalid move: Vertical Path is not clear for this move.");
        }
        // Check if the target location is valid (not occupied by a piece of the same color)
        Piece targetPiece = board.getPieceAt(newLoc);
        if (targetPiece != null && targetPiece.getColor() == this.color) {
            throw new InvalidMoveException("Invalid move: target square is occupied by a friendly piece.");
        } else if (targetPiece != null && targetPiece.getColor() != this.color) {
            this.setLocation(newLoc);
            return true;
        } else {
            this.setLocation(newLoc);
            return true;
        }


    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "R" : "r";
    }
}

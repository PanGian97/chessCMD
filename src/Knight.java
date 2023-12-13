public class Knight extends Piece {
    public Knight(Color color, Location location, Board board) {
        super(color, location, board);
    }

    @Override
    public Boolean canMove(Location newLoc) throws InvalidMoveException {
        // Calculate the differences in rows and columns
        int rowDiff = Math.abs(newLoc.getRow() - this.location.getRow());
        int colDiff = Math.abs(newLoc.getCol() - this.location.getCol());

        // Check if the move is valid for a knight
        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            // Check if the target location is valid (not occupied by a piece of the same color)
            Piece targetPiece = board.getPieceAt(newLoc);
            if (targetPiece == null)  {
                // Move is valid, so update the knight's location
                board.movePiece(this.location, newLoc);
                this.setLocation(newLoc); // Update the knight's internal location
            }
            else if(targetPiece.getColor() != this.color){
                board.movePieceCapturing(this.location,newLoc);
                this.setLocation(newLoc);
            }
            else {
                throw new InvalidMoveException("Invalid move: target square is occupied by a friendly piece.");
            }
        } else {
            throw new InvalidMoveException("Invalid move: Knight can only move in an L shape.");
        }
        return null;
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "N" : "n";
    }
}

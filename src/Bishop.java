public class Bishop extends Piece {
    public Bishop(Color color, Location location, Board board) {
        super(color, location, board);
    }

    @Override
    public void moveTo(Location newLoc) throws InvalidMoveException {
        // Implement king-specific move logic
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "B" : "b";
    }
}



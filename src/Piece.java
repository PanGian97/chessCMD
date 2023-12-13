public abstract class Piece {
    protected final Color color;
    protected Location location;
    protected final Board board;

    public Piece(Color color, Location location, Board board) {
        this.color = color;
        this.location = location;
        this.board = board;
    }

    public Color getColor() {
        return color;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location newLoc) {
        this.location = newLoc;
    }

   public abstract Boolean canMove(Location newLoc) throws InvalidMoveException;

    @Override
    public abstract String toString();
}

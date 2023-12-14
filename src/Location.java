public class Location {
    private final int row;
    private final int col;

    public Location(int r, int c) {
        this.row = r;
        this.col = c;
    }

    public Location(String loc) throws InvalidLocationException {
        if (loc.length() != 2 || loc.charAt(0) < 'a' || loc.charAt(0) > 'h' || loc.charAt(1) < '1' || loc.charAt(1) > '8') {
            throw new InvalidLocationException("Invalid location: " + loc);
        }

        switch (loc.charAt(1)) {
            case '1':
                this.row = 7;
                break;
            case '2':
                this.row = 6;
                break;
            case '3':
                this.row = 5;
                break;
            case '4':
                this.row = 4;
                break;
            case '5':
                this.row = 3;
                break;
            case '6':
                this.row = 2;
                break;
            case '7':
                this.row = 1;
                break;
            case '8':
                this.row = 0; // First row in a 0-indexed 8x8 array
                break;
            default:
                throw new InvalidLocationException("Invalid location: " + loc.charAt(1));
        }
        switch (loc.charAt(0)) {
            case 'a':
                this.col = 0;
                break;
            case 'b':
                this.col = 1;
                break;
            case 'c':
                this.col = 2;
                break;
            case 'd':
                this.col = 3;
                break;
            case 'e':
                this.col = 4;
                break;
            case 'f':
                this.col = 5;
                break;
            case 'g':
                this.col = 6;
                break;
            case 'h':
                this.col = 7;
                break;
            default:
                throw new InvalidLocationException("Invalid location: " + loc.charAt(0));
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        char file = (char) ('a' + col); // Convert column index to file letter
        int rank = 8 - row;            // Convert row index to rank number
        return "" + file + rank;
    }

}



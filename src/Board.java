public class Board {
    private Piece[][] board;

    public Board() {
        this.board = new Piece[8][8];
        init();
    }

    public void init() {
        // Place pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Color.BLACK, new Location(1, i), this);
            board[6][i] = new Pawn(Color.WHITE, new Location(6, i), this);
        }

        // Place rooks
        board[0][0] = new Rook(Color.BLACK, new Location(0, 0), this);
        board[0][7] = new Rook(Color.BLACK, new Location(0, 7), this);
        board[7][0] = new Rook(Color.WHITE, new Location(7, 0), this);
        board[7][7] = new Rook(Color.WHITE, new Location(7, 7), this);

        // Place knights
        board[0][1] = new Knight(Color.BLACK, new Location(0, 1), this);
        board[0][6] = new Knight(Color.BLACK, new Location(0, 6), this);
        board[7][1] = new Knight(Color.WHITE, new Location(7, 1), this);
        board[7][6] = new Knight(Color.WHITE, new Location(7, 6), this);


        // Place bishops
        board[0][2] = new Bishop(Color.BLACK, new Location(0, 2), this);
        board[0][5] = new Bishop(Color.BLACK, new Location(0, 5), this);
        board[7][2] = new Bishop(Color.WHITE, new Location(7, 2), this);
        board[7][5] = new Bishop(Color.WHITE, new Location(7, 5), this);


        // Place king and queen for both colors
        board[0][4] = new Queen(Color.BLACK, new Location(0, 4), this);
        board[0][3] = new King(Color.BLACK, new Location(0, 3), this);
        board[7][4] = new Queen(Color.WHITE, new Location(7, 4), this);
        board[7][3] = new King(Color.WHITE, new Location(7, 3), this);

    }


    public Piece getPieceAt(Location loc) {
        return board[loc.getRow()][loc.getCol()];
    }

    public void movePiece(Location from, Location to) throws InvalidMoveException {
        // Move piece logic, assuming valid move
        Piece piece = getPieceAt(from);
        if (piece != null) {
            if (piece.canMove(to)) {
                board[to.getRow()][to.getCol()] = piece;
                board[from.getRow()][from.getCol()] = null;
                piece.setLocation(to);
            }
        }
    }

    public void movePieceCapturing(Location from, Location to) throws InvalidMoveException {
        // Similar to movePiece, but also handle the capturing of the opponent's piece
        Piece capturedPiece = getPieceAt(to);
        Piece movingPiece = getPieceAt(from);
        if (capturedPiece != null) {

            if (capturedPiece.getColor() != movingPiece.getColor()) {
                if (movingPiece.canMove(to)) {
                    board[to.getRow()][to.getCol()] = movingPiece;
                    board[from.getRow()][from.getCol()] = null;
                    movingPiece.setLocation(to);
                }
            } else {
                throw new InvalidMoveException("Invalid move: Cannot capture a piece of the same color.");
            }

        }
        movePiece(from, to);
    }

    // The following methods would require checking each square between from and to
    public boolean freeHorizontalPath(Location from, Location to) {
        // Check if path is clear horizontally
        int row = from.getRow();
        int startCol = Math.min(from.getCol(), to.getCol()) + 1;
        int endCol = Math.max(from.getCol(), to.getCol());
        for (int col = startCol; col < endCol; col++) {
            if (board[row][col] != null) {
                return false;
            }
        }
        return true;
    }

    public boolean freeVerticalPath(Location from, Location to) {
        // Check if path is clear vertically
        int col = from.getCol();
        int startRow = Math.min(from.getRow(), to.getRow()) + 1;
        int endRow = Math.max(from.getRow(), to.getRow());
        for (int row = startRow; row < endRow; row++) {
            if (board[row][col] != null) {
                return false;
            }
        }
        return true;
    }

    public boolean freeDiagonalPath(Location from, Location to) {
        // Check if path is clear diagonally
        int startRow = from.getRow();
        int startCol = from.getCol();
        int endRow = to.getRow();
        int endCol = to.getCol();
        int rowStep = startRow < endRow ? 1 : -1;
        int colStep = startCol < endCol ? 1 : -1;

        for (int row = startRow + rowStep, col = startCol + colStep; row != endRow; row += rowStep, col += colStep) {
            if (board[row][col] != null) {
                return false;
            }
        }
        return true;
    }

    public boolean freeAntidiagonalPath(Location from, Location to) {
        int startRow = from.getRow();
        int startCol = from.getCol();
        int endRow = to.getRow();
        int endCol = to.getCol();
        int rowStep = startRow < endRow ? 1 : -1;
        int colStep = startCol < endCol ? -1 : 1; // Opposite direction for columns compared to diagonal

        for (int row = startRow + rowStep, col = startCol + colStep; row != endRow; row += rowStep, col += colStep) {
            if (board[row][col] != null) {
                return false;
            }
        }
        return true; // No pieces were found, the path is free
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" abcdefgh\n");  // Column labels at the top

        for (int row = 7; row >= 0; row--) {
            sb.append(row + 1);  // Row label on the left (8 to 1)

            for (int col = 0; col < 8; col++) {
                Piece piece = board[7 - row][col];
                char pieceChar = (piece == null) ? ' ' : piece.toString().charAt(0);
                sb.append(pieceChar);
            }

            sb.append(row + 1);  // Row label on the right
            sb.append("\n");    // New line after each row
        }

        sb.append(" abcdefgh\n");  // Column labels at the bottom
        return sb.toString();
    }

//@Override
//public String toString() {
//    StringBuilder sb = new StringBuilder();
//    sb.append(" abcdefgh\n");  // Column labels at the top
//     int row=0;
//     int col=0;
//    for (col = 7; col >= 0; col--) {
//        sb.append(col+ 1);
//
//        for (row = 0; row < 8; row++) {
//            Piece piece = board[row][col];
//            char pieceChar = (piece == null) ? '_' : piece.toString().charAt(0);
//            sb.append(pieceChar);
//        }
//
//        sb.append(col + 1);  // Row label on the right
//        sb.append("\n");    // New line after each row
//    }
//
//    sb.append(" abcdefgh\n");  // Column labels at the bottom
//    return sb.toString();
//}


}

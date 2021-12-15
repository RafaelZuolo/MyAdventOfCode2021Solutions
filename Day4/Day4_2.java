import java.util.List;
import java.util.LinkedList;
public class Day4_2 {
    static class Bingo {
        boolean justWinned = true;
        int[][] board = new int[5][5];
        boolean[][] marked = new boolean[5][5];
        boolean winner = false;
        int lastVal = -1;

        public Bingo(int[][] vals) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    board[i][j] = vals[i][j];
                    marked[i][j] = false;
                }
            }
        }

        public boolean isWinner() {return winner;}
        public boolean getJustWinned() {return justWinned;}
        public void setJustWinned(boolean b) {justWinned = b;}
        public boolean markNumber(int val) {
            if (winner) return true;
            lastVal = val;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (board[i][j] == val) {
                        marked[i][j] = true;
                        check(i,j);
                        return true;
                    }
                }
            }
            // val not on board
            return false;
        }

        public void check(int i,int j) {
            boolean columnCheck = true;
            boolean rowCheck = true;
            for (int x = 0; x < 5; x++) {
                if (!marked[x][j]) columnCheck = false;
                if (!marked[i][x]) rowCheck = false;
            }
            if (columnCheck || rowCheck) winner = true;
        }

        public long score() {
            long score = 0;
            for (int i = 0; i<5; i++) {
                for (int j = 0; j<5; j++) {
                    if (!marked[i][j]) score += board[i][j];
                }
            }
            score = score*lastVal;
            return score;
        }
        public String toString() {
            return (board[0][0]+" ");
        }
    }
    public static void main(String[] args) {
        String[] valSeqString = StdIn.readLine().split(",");
        int[] vals = new int[valSeqString.length];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = Integer.parseInt(valSeqString[i]);
            //System.out.println(vals[i]);
        }

        List<Bingo> boards = new LinkedList<Bingo>();
        while (!StdIn.isEmpty()) {
            int[][] boardVal = new int[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    boardVal[i][j] = StdIn.readInt();
                }
            }
            Bingo bingo = new Bingo(boardVal);
            boards.add(bingo);
            System.out.println(bingo);
        }
        boolean allWinned = false;
        Bingo lastWinner = null;
        for (int i = 0 ; i < vals.length && !allWinned; i++) {
            for (Bingo b : boards) {
                b.markNumber(vals[i]);
                if (b.isWinner() && b.getJustWinned()) { 
                    lastWinner = b;
                    b.setJustWinned(false);
                }
            }
        }
        System.out.println("Last score: " + lastWinner.score());
    }
}

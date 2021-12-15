import java.util.List;
import java.util.LinkedList;
public class Day4_1 {
    static class Bingo {
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

        public boolean markNumber(int val) {
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
        boolean hasWinned = false;
        for (int i = 0 ; i < vals.length && !hasWinned; i++) {
            for (Bingo b : boards) {
                b.markNumber(vals[i]);
                if (b.isWinner()) {
                    System.out.println("Score of winner: " 
                                        + b.score());
                    hasWinned = true;
                    break;
                }
            }
        }
        System.out.println("booo");
    }
}

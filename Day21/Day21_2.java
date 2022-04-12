import java.util.*;
public class Day21_2 {
    
    // array of possible values         = [3,4,5,6,7,8,9]
    // array of frequency of the values = [1,3,6,7,6,3,1]
    // we will use dynamic programming tricks 
    
    // array of universes branches if a player walk [i] positions
    static int[] branching = new int[] {0, 0, 0, 1, 3, 6, 7, 6, 3, 1};
    static int totalUniversesPerPlay = 27;
    static int winningScore = 21;
    static int maximumRounds = 22;
    static int boardSize = 10;
    static long[][][] player1universes = new long[winningScore + 10][maximumRounds][boardSize + 1];
    static long[][][] player2universes = new long[winningScore + 10][maximumRounds][boardSize + 1];

    //public static void universeOfPlayer1 (int score; int round; int position) {
    //    if (score < 0 || round < 0 || position <= 0 || position > boardSize) return 0;
    //    
    //    return 
    //}
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            sc.next();
        }
        sc.next();
        while (!sc.hasNextInt()) {
            sc.next();
        }
        int player1pos = sc.nextInt();
        while (!sc.hasNextInt()) {
            sc.next();
        }
        sc.next();
        while (!sc.hasNextInt()) {
            sc.next();
        }
        int player2pos = sc.nextInt();
        sc.close();
                
        System.out.println("p1 pos, p2 pos: " + player1pos + ", " + player2pos);
        
        // ---------------------------------------------------------
        // ------ loaded the input, now we start solving -----------
        // ---------------------------------------------------------
        
        player1universes[0][0][player1pos] = 1;
        player2universes[0][0][player2pos] = 1;
        
        for (int round = 1; round < maximumRounds; round++) {
            for (int score = 0; score < winningScore + 10; score++) {
                for (int pos = 1; pos <= 10; pos++) {
                    long universe1Sum = 0;
                    long universe2Sum = 0;
                    for (int dieSum = 3; dieSum <= 9; dieSum++) {
                        int oldPosition = (pos + 10 - dieSum - 1)%10 + 1;
                        if (score - pos < 0) continue;
                        if (score - pos >= 21) continue;
                        universe1Sum += ((long) branching[dieSum])*player1universes[score - pos][round-1][oldPosition];
                        universe2Sum += ((long) branching[dieSum])*player2universes[score - pos][round-1][oldPosition];
                    }
                    player1universes[score][round][pos] = universe1Sum;
                    player2universes[score][round][pos] = universe2Sum;
                }
            }           
        }
        /* { 
            int totalSum = 0;
            int testSum = 0;
            for (int round = 1; round < maximumRounds; round++) {
                testSum = 0;
                for (int score = 0; score < winningScore + 10; score++) {
                    for (int pos = 1; pos <= 10; pos++)
                        testSum += player1universes[score][round][pos];
                }
                System.out.println("test sum must be " + Math.pow(27, round) + " = " + testSum);
                totalSum += testSum;
            }
            System.out.println("Total Sum  " + totalSum);
        } */
        long player1wins = 0;
        long player2wins = 0;
        
        long highScoreUniverses_1 = 0;
        long lowScoreUniverses_1 = 0;
        long highScoreUniverses_2 = 0;
        long lowScoreUniverses_2 = 0;
        long previousRoundLowScoreUniverses_2 = 0;
        
        for (int round = 1; round < maximumRounds; round++) {
            highScoreUniverses_1 = 0;
            lowScoreUniverses_1 = 0;
            highScoreUniverses_2 = 0;
            lowScoreUniverses_2 = 0;
            for (int pos = 1; pos <= 10; pos++) {
                
                // number of universes where player 1 end with a high score at position pos
                for (int endScore = 21; endScore < winningScore + 10; endScore++) {
                    highScoreUniverses_1 += player1universes[endScore][round][pos];
                }
                // number of universes where player 1 end with a low score at position pos
                for (int endScore = 0; endScore < winningScore; endScore++) {
                    lowScoreUniverses_1 += player1universes[endScore][round][pos];
                }
                // number of universes where player 1 end with a high score at position pos
                for (int endScore = 21; endScore < winningScore + 10; endScore++) {
                    highScoreUniverses_2 += player2universes[endScore][round][pos];
                }
                // number of universes where player 2 end with a low score at position pos
                for (int endScore = 0; endScore < winningScore; endScore++) {
                    lowScoreUniverses_2 += player2universes[endScore][round][pos];
                }
            }
            // number of victories of player 1 at this round is highScoreUniverses_1
            // times the number of universes player 2 didnt won at previous round.
            // number of victories of player 2 at this round is highScoreUniverses_2
            // times the number of universes player 1 didnt won this round.
            System.out.printf("\n\n      ----- Round %d -----    \n", round);
            System.out.println("highScoreUniverses_1 " + highScoreUniverses_1);
            System.out.println("highScoreUniverses_2 " + highScoreUniverses_2);
            System.out.println("lowScoreUniverses_1 " + lowScoreUniverses_1);
            System.out.println("lowScoreUniverses_2 " + lowScoreUniverses_2);
            System.out.println("previousRoundLowScoreUniverses_2 " + previousRoundLowScoreUniverses_2);
            player1wins += highScoreUniverses_1*previousRoundLowScoreUniverses_2;
            player2wins += highScoreUniverses_2*lowScoreUniverses_1;
            
            previousRoundLowScoreUniverses_2 = lowScoreUniverses_2;
        }
        System.out.println("Player 1 winnings: " + player1wins);
        System.out.println("Player 2 winnings: " + player2wins);
    }
}

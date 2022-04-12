import java.util.*;
public class Day21_1 {
    
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
        
        int player1score = 0;
        int player2score = 0;
        int d100 = 0;
        int numRoll = 0;
        
        System.out.println("p1 pos, p2 pos: " + player1pos + ", " + player2pos);
        
        // ---------------------------------------------------------
        // ------ loaded the input, now we start solving -----------
        // ---------------------------------------------------------

        while (player1score < 1000 && player2score < 1000) {
            // roll die 3 times and walk
            d100 = (d100%100)+1;
            player1pos += d100;
            d100 = (d100%100)+1;
            player1pos += d100;
            d100 = (d100%100)+1;
            player1pos += d100;
            player1pos = (player1pos-1)%10 +1;
            numRoll += 3; // increment roll number
            // score
            player1score += player1pos;
            if (player1score >= 1000) break;
            // now player 2
            // roll die 3 times and walk
            d100 = (d100%100)+1;
            player2pos += d100;
            d100 = (d100%100)+1;
            player2pos += d100;
            d100 = (d100%100)+1;
            player2pos += d100;
            player2pos = (player2pos-1)%10 +1;
            numRoll += 3; // increment roll number
            // score
            player2score += player2pos;
            if (player2score >= 1000) break;
        }      
        int loserScore = Math.min(player1score, player2score);
        System.out.printf("%d * %d = %d\n", numRoll, loserScore, numRoll*loserScore);
    }
}

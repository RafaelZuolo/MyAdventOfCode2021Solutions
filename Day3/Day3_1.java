public class Day3_1 {
    public static void main(String[] args) {
        int currentbit;
        int[] frequency = new int[12];
        
        while (!StdIn.isEmpty()) {
            String bin = StdIn.readString();
            currentbit = Integer.parseInt(bin, 2);
            for (int i = 0; i < 12; i++) {
//                System.out.println(Integer.toBinaryString(currentbit>>i));
                if ((currentbit >> i & 1) == 1) frequency[i]++;
                else                      frequency[i]--;
            }
        }
        int gammaRate = 0;
        for (int i = 0; i < 12; i++) {
            if (frequency[i] > 0) gammaRate = gammaRate | 1 << i;
        }
        int epsilonRate = ~gammaRate & 0b111111111111;
        System.out.printf("gammaRate in bin: %s, epsilonRate in bin: %s\n",
                            Integer.toBinaryString(gammaRate), 
                            Integer.toBinaryString(epsilonRate));
        System.out.printf("The product is: %d\n", gammaRate*epsilonRate);
    }
}

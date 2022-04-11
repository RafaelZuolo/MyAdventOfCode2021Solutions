import java.util.*;
public class Day20_1 {
    
    
    public static void printCharMatrix(char[][] inputImg) {
        for (int i = 0; i < inputImg.length; i++) {
            for (int j = 0; j < inputImg[i].length; j++) {
                System.out.print(inputImg[i][j]);
            }
            System.out.println();
        }
    }

    public static int pixelsLit(char[][] inputImg) {
        int num = 0;
        for (int i = 0; i < inputImg.length; i++) {
            for (int j = 0; j < inputImg[i].length; j++) {
                if (inputImg[i][j]=='#') num++;
            }
        }
        return num;
    }
    // we will read the input from stdIn
    public static void main(String[] args) {
        
        // begin to load the inputs
        boolean testing = args.length > 0;
        Scanner sc = new Scanner(System.in);
        String enhancementAlg;
        char[][] inputImg;
        int xSize = 0; // size of inputImg
        int ySize = 0; // size of inputImg[]
        List<String> lines = new ArrayList<>();
        enhancementAlg = sc.nextLine();
        
        sc.nextLine(); // we always have a blank line separating 
                       // the algorithm from the input 
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        } 
        xSize = lines.get(0).length();
        ySize = lines.size();
        
        // remember that the [0][0] is now [2][2]
        inputImg = new char[ySize][xSize]; // +4 for the padding
        for (int i = 0; i < ySize; i++) {
            String currentLine = lines.get(i);
            for (int j = 0; j < xSize; j++) {
                    inputImg[i][j] = currentLine.charAt(j);
                }
            
        }
        if (testing) {printCharMatrix(inputImg);System.out.println("\n");}
        // ------ loaded the input, now we start solving part 1
        
        int currentIteration = 0; // if enhancementAlg[0] is #, then the
                                  // infinite grid will keep changing
        int changing = 0;
        if (enhancementAlg.charAt(0) == '#') changing = 1;
        
        char[][] nextImg = new char[ySize + 2][xSize + 2];
        for (int i = -1; i < ySize + 1; i++) {
            for (int j = -1; j < xSize + 1; j++) {
                String binCode = "";
                // start sweeping the 9x9 grid centered at inputImg[i][j]
                for (int p = -1; p < 2; p++) {
                    for (int q = -1; q < 2; q++) {
                        if ((i + p)<0 || (i+p)>=ySize || (j + q)<0 || (j+q)>=xSize) 
                            binCode = binCode.concat(Integer.toString((currentIteration%2)*changing));
                        else {
                            if (inputImg[i+p][j+q] == '.') binCode = binCode.concat("0");
                            else                           binCode = binCode.concat("1");
                        }
                    }
                }
                nextImg[i+1][j+1] = enhancementAlg.charAt(Integer.parseInt(binCode,2));
            }
        }
        currentIteration++;
        inputImg = nextImg;
        xSize = inputImg.length;
        ySize = inputImg[0].length; 
        if (testing) {printCharMatrix(inputImg);System.out.println("\n");}
        
        nextImg = new char[ySize + 2][xSize + 2];
        for (int i = -1; i < ySize + 1; i++) {
            for (int j = -1; j < xSize + 1; j++) {
                String binCode = "";
                // start sweeping the 9x9 grid centered at inputImg[i][j]
                for (int p = -1; p < 2; p++) {
                    for (int q = -1; q < 2; q++) {
                        if ((i + p)<0 || (i+p)>=ySize || (j + q)<0 || (j+q)>=xSize) 
                            binCode = binCode.concat(Integer.toString((currentIteration%2)*changing));
                        else {
                            if (inputImg[i+p][j+q] == '.') binCode = binCode.concat("0");
                            else                           binCode = binCode.concat("1");
                        }
                    }
                }
                nextImg[i+1][j+1] = enhancementAlg.charAt(Integer.parseInt(binCode,2));
            }
        }
        currentIteration++;
        inputImg = nextImg;
        if (testing) {printCharMatrix(inputImg);System.out.println("\n");}
        System.out.println(pixelsLit(inputImg));
    }
}

import java.util.*;
public class Day18_1 {
    
    static class Node {
        int val;
        int level;
        Node next = null;

        Node(int val, int level) {
            this.val = val;
            this.level = level;
        }
        public Node clone() {
            return new Node(this.val, this.level);
        }
    }
    static class Snail {
        Node first = null;
        Node current = null;

        Snail(String s) {
            int nest = -1;
            int j = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if      (c == '[') nest++;
                else if (c == ']') nest--;
                else if (c == ',') continue;
                else {
                    if (first == null) {
                        first = new Node(Integer.parseInt(s.substring(i,i+1)), nest);
                        current = first;
                    } else {
                        Node temp = new Node(Integer.parseInt(s.substring(i,i+1)), nest);
                        current.next = temp;
                        current = temp;
                    }
                }
            }
        }
         
        Snail (Snail sn1, Snail sn2) {
            this.first = sn1.first.clone();
            Node currentCopy = first;
            Node current = sn1.first;
            while (current != null) {
                currentCopy.level++;
                current = current.next;
                if (current != null) {
                    currentCopy.next = current.clone();
                    currentCopy = currentCopy.next;
                }
            }
            current = sn2.first;
            currentCopy.next = current.clone();
            currentCopy = currentCopy.next;
            while (current != null) {
                currentCopy.level++;
                current = current.next;
                if (current != null) {
                    currentCopy.next = current.clone();
                    currentCopy = currentCopy.next;
                }
            }
        }

        public String toString() {
            String s = "";
            Node current = first;
            while (current != null) {
                s = s.concat("("+current.val +","+current.level+")");
                current = current.next;
            }
            return s;
        }
        public boolean explode() {
            Node current = first;
            Node previous = null;
            while (current != null) {
                if (current.level >= 4) {
                    if (previous != null) previous.val += current.val;
                    if (current.next.next != null) current.next.next.val += current.next.val;
                    current.val = 0;
                    current.level -= 1;
                    current.next = current.next.next;
                    return true; 
                }
                previous = current;
                current = current.next;
            }
            return false;
        }
        public boolean split() {
            Node current = first;
            while (current != null) {
                if (current.val >= 10) {
                    int downVal = current.val/2;
                    int upVal = current.val/2 + current.val%2;
                    Node curNew = new Node(upVal, current.level+1);
                    curNew.next = current.next;
                    current.val = downVal;
                    current.level++;
                    current.next = curNew;
                    return true; 
                }
                current = current.next;
            }
            return false;
        }
        public void reduce() {
            while (this.explode()) {
                continue;
            }
            while (this.split()) {
                while (this.explode()) {
                    continue;
                }
            }
        }

        public long norm() {
            int[] binCount = new int[]{3, 3, 3, 3};
            Node current = first;
            long normAcc = 0;
            while (current != null) {
                long temp = current.val;
                for (int i = 0; i <= current.level; i++) {
                    temp = temp*(long) binCount[i];
                }
                normAcc += temp;
                //System.out.println(Arrays.toString(binCount));
                binaryAdition(binCount, current.level);
                current = current.next;
            }
            return normAcc;
        }

        static void binaryAdition(int[] bin, int level) {
            if (bin[level] == 3)  bin[level] = 2;
            else { 
                bin[level] = 3;  
                if (level> 0) binaryAdition(bin, level-1);
            }
        }
    }
   
    public static Snail add(Snail sn1, Snail sn2) {
        Snail sum = new Snail(sn1, sn2);
        sum.reduce();
        return sum;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Snail sn1 = new Snail(sc.nextLine());
        Snail sum = null;
        while (sc.hasNextLine()) {
            Snail sn2 = new Snail(sc.nextLine());
            System.out.println(sn1 + "\n"+sn2+"\n");
            sum = add(sn1, sn2);
            sn1 = sum;
        }
        System.out.println(sum.norm());
    }
}

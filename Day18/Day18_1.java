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
            Node current = sn1.first;
            Node last = current;
            while (current != null) {
                current.level++;
                last = current;
                current = current.next;
            }
            current = sn2.first;
            while (current != null) {
                current.level++;
                current = current.next;
            }
            last.next = sn2.first;
            this.first = sn1.first;
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
                if (current.level == 4) {
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
            return 0;
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
        while (sc.hasNextLine()) {
            Snail sn2 = new Snail(sc.nextLine());
            sn1 = add(sn1, sn2);
        }
        System.out.println(sn1);
    }
}

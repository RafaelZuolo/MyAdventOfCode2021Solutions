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
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Snail sn = new Snail(sc.nextLine());
        System.out.println(sn);
        System.out.println(sn.explode() + " " + sn);
    }
}

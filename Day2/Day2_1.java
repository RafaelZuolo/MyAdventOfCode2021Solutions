public class Day2_1 {
	public static void main(String[] args) {
		int lenght = 0;
		int depth = 0;
		while (!StdIn.isEmpty()) {
			String command = StdIn.readString();
			int amount = StdIn.readInt();
			if     	(command.equals("forward")) lenght += amount;
			else if (command.equals("down"))    depth  += amount;
			else if (command.equals("up"))	    depth  -= amount;
			else {System.out.println("error in string"); return;}
		}
		System.out.printf("lenght = %d, depth = %d, product = %d\n", lenght, depth, lenght*depth);
	}
}

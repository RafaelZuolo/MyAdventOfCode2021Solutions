public class Day2_2 {
	public static void main(String[] args) {
		int lenght = 0;
		int depth = 0;
		int aim = 0;
		while (!StdIn.isEmpty()) {
			String command = StdIn.readString();
			int amount = StdIn.readInt();
			if     	(command.equals("forward")) { lenght += amount; depth += aim*amount;}
			else if (command.equals("down"))    aim  += amount;
			else if (command.equals("up"))	    aim  -= amount;
			else {System.out.println("error in string"); return;}
		}
		System.out.printf("lenght = %d, depth = %d, product = %d\n", lenght, depth, lenght*depth);
	}
}

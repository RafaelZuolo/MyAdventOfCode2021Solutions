public class SonarSweep {
	public static void main(String[] args) {
		int nMeasurements = 0;
		int depth = StdIn.readInt();
		while(!StdIn.isEmpty()) {
			int nextDepth = StdIn.readInt();
			if (nextDepth > depth) nMeasurements++;
			depth = nextDepth;
		}
		System.out.println(nMeasurements);
	}
}

public class SonarSweepPart2 {
	public static void main(String[] args) {
		int nMeasurements = 0;
		int tripleReads = 1;
		int depth1 = StdIn.readInt();
		int depth2 = StdIn.readInt();
		int depth3 = StdIn.readInt();
		int sum1 = depth1 + depth2 + depth3;
		while(!StdIn.isEmpty()) {
			int nextDepth = StdIn.readInt();
			int sum2 = sum1 - depth1 + nextDepth;
			tripleReads++;
			if (sum2 > sum1) nMeasurements++;
			depth1 = depth2;
			depth2 = depth3;
			depth3 = nextDepth;
			sum1 = sum2;
		}
		System.out.println(nMeasurements + ", "+ tripleReads);
	}
}

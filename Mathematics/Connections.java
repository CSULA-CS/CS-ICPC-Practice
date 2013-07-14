import java.util.ArrayList;
import java.util.List;

public class Connections {
	public static void main(String[] args) {
		System.out.println(findConnections(2));
	}

	public static int findConnections(int n) {
		List<List<Pair>> pairCombinations = new ArrayList<List<Pair>>();

		iloop: for (int i = 1; i <= 2 * n; i ++) {
			jloop: for (int j = 1; j <= 2 * n; j ++) {
				if (i != j) {
					boolean inCs = false;
					cloop: for (List<Pair> pairs : pairCombinations) {
						boolean inComb = true;
						pairloop: for (Pair pair : pairs) {
							if (pair.inPair(i) && pair.inPair(j)) {
								inCs = true;
								inComb = true;
								break pairloop;
							}
							else if (
									((i < pair.a && j > pair.a) ||
										(i > pair.a && j < pair.a)) &&
									((i < pair.b && j < pair.b)) ) {
								inComb = true;
								break pairloop;
							}
							else if (!pair.inPair(j) && !pair.inPair(i)) {
								inComb = false;
							}
							else if (pair.inPair(i) || pair.inPair(j)) {
								inComb = true;
								break pairloop;
							}
						}
						if (!inComb) {
							if (Math.abs(i - j - 1) % 2 == 0) {
								Pair newPair = new Pair(i, j);
								pairs.add(newPair);
								inCs = true;
							}
						}
					}
					if (!inCs) {
						if (Math.abs(i - j - 1) % 2 == 0) {
							Pair newPair = new Pair(i, j);
							List<Pair> newPairs = new ArrayList<Pair>();
							newPairs.add(newPair);
							pairCombinations.add(newPairs);
						}
					}
				}
			}
		}

		iloop: for (int i = 1; i <= 2 * n; i ++) {
			jloop: for (int j = 1; j <= 2 * n; j ++) {
				if (i != j) {
					boolean inCs = false;
					cloop: for (List<Pair> pairs : pairCombinations) {
						boolean inComb = true;
						pairloop: for (Pair pair : pairs) {
							if (pair.inPair(i) && pair.inPair(j)) {
								inCs = true;
								inComb = true;
								break pairloop;
							}
							else if (
									((i < pair.a && j > pair.a) ||
										(i > pair.a && j < pair.a)) &&
									((i < pair.b && j < pair.b)) ) {
								inComb = true;
								break pairloop;
							}
							else if (!pair.inPair(j) && !pair.inPair(i)) {
								inComb = false;
							}
							else if (pair.inPair(i) || pair.inPair(j)) {
								inComb = true;
								break pairloop;
							}
						}
						if (!inComb) {
							if (Math.abs(i - j - 1) % 2 == 0) {
								Pair newPair = new Pair(i, j);
								pairs.add(newPair);
								inCs = true;
							}
						}
					}
					if (!inCs) {
						if (Math.abs(i - j - 1) % 2 == 0) {
							Pair newPair = new Pair(i, j);
							List<Pair> newPairs = new ArrayList<Pair>();
							newPairs.add(newPair);
							pairCombinations.add(newPairs);
						}
					}
				}
			}
		}

		

		for (List<Pair> pairs : pairCombinations) {
			for (Pair pair : pairs) {
				System.out.println("pair: " + pair.a + ", " + pair.b);
			}
			System.out.println();
		}
		return pairCombinations.size();
	}

	static class Pair {
		int a, b;

		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}

		public boolean inPair(int i) {
			if (i == this.a || i == this.b)
				return true;
			else
				return false;
		}
	}
}
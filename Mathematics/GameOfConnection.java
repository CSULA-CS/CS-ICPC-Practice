import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

public class GameOfConnection {
	static int SIZE_OF_INPUT = 6;

	/**
	 * @param args
	 */
	static boolean duplicate(ArrayList<Pair> pair1, ArrayList<Pair> pair2) {
		boolean result = true;
		for (int i = 0; i < pair1.size(); i++) {
			if (!pair2.contains(pair1.get(i))) {
				result = false;

			}
		}
		return result;

	}

	static boolean duplicateNumber(Pair pair1, Pair pair2) {

		return (pair1.num1 == pair2.num1 || pair1.num2 == pair2.num1
				|| pair1.num1 == pair2.num2 || pair1.num2 == pair2.num2);
	}

	static boolean completePairArray(ArrayList<Pair> pair) {
		Set<Integer> numbers = new HashSet<Integer>();
		for (int i = 0; i < pair.size(); i++) {
			numbers.add(pair.get(i).num1);
			numbers.add(pair.get(i).num2);

		}

		return (numbers.size() == SIZE_OF_INPUT);
	}

	static class Pair implements Comparable<Pair> {

		int num1;
		int num2;

		public Pair(int num1, int num2) {
			this.num1 = num1;
			this.num2 = num2;
		}

		static ArrayList<ArrayList<Pair>> generateScenarios(
				ArrayList<Pair> pairs) {

			ArrayList<ArrayList<Pair>> generateScenarios = new ArrayList<ArrayList<Pair>>();

			for (int i = 0; i < pairs.size(); i++) {

				ArrayList<Pair> initial = new ArrayList<>();
				initial.add(pairs.get(i));

				for (int j = 0; j < pairs.size(); j++) {
					if (initial.size() == SIZE_OF_INPUT / 2) {
						break;
					}

					if (!pairs.get(i).equals(pairs.get(j))) {
						boolean duplicateTest = false;
						for (int k = 0; k < initial.size(); k++) {
							if (duplicateNumber(initial.get(k), pairs.get(j)))

							{
								duplicateTest = true;

							}
						}
						if (!duplicateTest) {
							initial.add(pairs.get(j));
						}

					}
				}

				boolean test = false;
				for (int j = 0; j < generateScenarios.size(); j++) {

					if (duplicate(generateScenarios.get(j), initial)) {
						test = true;
					}
				}
				if (!test) {
					generateScenarios.add(initial);
				}
			}

			return generateScenarios;

		}

		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub

			return 0;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Pair) {
				Pair test = (Pair) obj;
				return (this.num1 == test.num1 && this.num2 == test.num2 || this.num1 == test.num2
						&& this.num2 == test.num1);
			} else {
				return false;
			}
		}

		public String toString() {
			return "(" + this.num1 + "," + this.num2 + ")";
		}

	}

	public static void main(String[] args) {
		ArrayList<Pair> allGoodPairs = new ArrayList<>();
		ArrayList<Pair> allBadPairs = new ArrayList<>();

		ArrayList<Pair> allPairs = new ArrayList<>();

		// ArrayList<ArrayList<Pair>> allScenarios = new ArrayList<>();

		for (int i = 1; i <= SIZE_OF_INPUT; i++) {
			for (int j = 1; j < SIZE_OF_INPUT; j++) {

				if (i != j && ((j - i - 1) % 2 == 0)) {

					Pair pair = new Pair(i, j);
					if (!allGoodPairs.contains(pair)) {
						allGoodPairs.add(pair);
						// System.out.println(pair.toString());

					}
				} else if (i != j && ((j - i - 1) % 2 != 0)) {
					Pair pair = new Pair(i, j);
					if (!allBadPairs.contains(pair)) {
						allBadPairs.add(pair);

					}

				}

			}

		}

		for (int i = 1; i <= SIZE_OF_INPUT; i++) {
			for (int j = 1; j < SIZE_OF_INPUT; j++) {

				if (i != j) {

					Pair pair = new Pair(i, j);
					if (!allPairs.contains(pair)) {
						allPairs.add(pair);
						// System.out.println(pair.toString());

					}
				}

			}

		}
		System.out.println("all pairs:");
		for (int i = 0; i < allPairs.size(); i++) {
			System.out.println(allPairs.get(i));
		}
		System.out.println(allGoodPairs.size());
		ArrayList<ArrayList<Pair>> generatedScenarios = Pair
				.generateScenarios(allPairs);
		ArrayList<ArrayList<Pair>> goodScenarios = new ArrayList<ArrayList<Pair>>();

		for (int i = 0; i < generatedScenarios.size(); i++) {
			for (int j = 0; j < generatedScenarios.get(i).size(); j++) {
				System.out.print(generatedScenarios.get(i).get(j));
			}
			System.out.println();
		}
		System.out.println(Pair.generateScenarios(allPairs).size());
		/*
		 * for (int i = 0; i < ; i++) {
		 * 
		 * }
		 */

		// for (int i = 0; i < allBadPairs.size(); i++) {
		// for (int j = 0; j < generatedScenarios.size(); j++) {
		//
		// if (generatedScenarios.get(j).contains(allBadPairs.get(i))) {
		//
		// test = true;
		//
		// }
		// goodScenarios.add(generatedScenarios.get(j));
		// }
		//
		// }
		System.out.println("bad pairs:");
		for (int j = 0; j < allBadPairs.size(); j++) {

			System.out.println(allBadPairs.get(j));

		}

		for (int i = 0; i < generatedScenarios.size(); i++) {
			boolean test = true;
			System.out.println("checking on: ");
			for (int j = 0; j < generatedScenarios.get(i).size(); j++) {
				System.out.print(generatedScenarios.get(i).get(j));
			}
			System.out.println();

			for (int j = 0; j < allBadPairs.size(); j++) {

				if (generatedScenarios.get(i).contains(allBadPairs.get(j))) {
					System.out.println("bad");
					test = false;

				}

			}

			System.out.println("test: " + test);
			if (test == true) {
				if (!goodScenarios.contains(generatedScenarios.get(i))) {
					goodScenarios.add(generatedScenarios.get(i));
				}
			} else {

				System.out.println("should be false");
			}

		}
		for (int i = 0; i < goodScenarios.size(); i++) {
			for (int j = 0; j < goodScenarios.get(i).size(); j++) {
				System.out.print(goodScenarios.get(i).get(j));
			}
			System.out.println();
		}
		System.out.println(goodScenarios.size());

	}

}

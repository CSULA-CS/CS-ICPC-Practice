package mathematics;

import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class GameOfConnectCalculation {
	static int VALUE_OF_2N = 6;
	static int numberOfOccurencesInCombination = VALUE_OF_2N - 1;

	static HashMap<Integer, Integer> occurencesInBadScenarios = new HashMap<>();
	static HashSet<Pair> badPairs = new HashSet<>();

	static BigInteger factorial(int input) {

		BigInteger result = BigInteger.valueOf(1);
		for (int i = input; i > 1; i--) {
			result = result.multiply(BigInteger.valueOf(i));
		}
		return result;
	}

	static BigInteger combination(int n, int r) {
		BigInteger result = BigInteger.valueOf(1);
		BigInteger nFactorial = factorial(n);
		BigInteger nMinusRFactorial = factorial(n - r);
		BigInteger rFactorial = factorial(r);
		result = nFactorial.divide(nMinusRFactorial.multiply(rFactorial));

		return result;

	}

	static BigInteger numberOfBadPairs() {
		BigInteger numberOfBadPairs = BigInteger.valueOf(0);

		boolean[][] checked = new boolean[VALUE_OF_2N][VALUE_OF_2N];
		for (int i = 0; i < checked.length; i++) {
			Arrays.fill(checked[i], Boolean.FALSE);
		}
		for (int i = 0; i < VALUE_OF_2N; i++) {
			for (int j = 0; j < VALUE_OF_2N; j++) {
				if (!checked[i][j] && !checked[j][i] && i != j) {

					checked[i][j] = true;
					checked[j][i] = true;

					int difference = Math.abs(i + 1 - (j + 1 - 1));
					if (i - j == VALUE_OF_2N - 1 || j - i == VALUE_OF_2N - 1) {
						difference = 0;
					}
					// System.out.println("difference between " + (i + 1) +
					// "and "
					// + (j + 1) + ":" + difference);
					if ((difference) % 2 != 0) {
						numberOfBadPairs = numberOfBadPairs.add(BigInteger
								.valueOf(1));

						badPairs.add(new Pair(i + 1, j + 1));
						 System.out.println("bad: ("+(i+1)+","+(j+1)+")");
						if (occurencesInBadScenarios.get(i + 1) != null) {
							occurencesInBadScenarios.put(i + 1,
									occurencesInBadScenarios.get(i + 1) + 1);

						} else {
							occurencesInBadScenarios.put(i + 1, 1);
						}
						if (occurencesInBadScenarios.get(j + 1) != null) {
							occurencesInBadScenarios.put(j + 1,
									occurencesInBadScenarios.get(j + 1) + 1);

						} else {
							occurencesInBadScenarios.put(j + 1, 1);

						}
					}
				}
			}
		}

		return numberOfBadPairs;

	}

//	static BigInteger numberOfBadScenarios(int number, BigInteger allPairs) {
//		BigInteger one = BigInteger.valueOf(1);
//
//		BigInteger accumilator = new BigInteger(allPairs.toString()).subtract(one);
//		BigInteger finalAnswer = BigInteger.valueOf(1);
//		BigInteger counter = BigInteger.valueOf(0);
//		BigInteger constantDiff = BigInteger
//				.valueOf(numberOfOccurencesInCombination
//						+ numberOfOccurencesInCombination - 2);
//		
//		for (Pair pair : pair) {
//			
//		}
//		
//		
//		while (accumilator.compareTo(one) >= 0) {
//
//			finalAnswer = finalAnswer.multiply(accumilator);
//			System.out.println(accumilator);
//			accumilator = accumilator.subtract(constantDiff);
//			accumilator = accumilator.subtract(one);
//			counter = counter.add(one);
//
//		}
//
//		finalAnswer = finalAnswer.divide(factorial(counter.intValue()));
//
//		System.out.println(finalAnswer);
//
//		return finalAnswer;
//	}

	public static void main(String[] args) {

		int n = VALUE_OF_2N;
		int r = 2;

		BigInteger numberOfBadPairs = numberOfBadPairs();
		System.out.println("bad pair count: "
				+ occurencesInBadScenarios.toString());
		BigInteger result = BigInteger.valueOf(1);
		BigInteger allPairs = combination(VALUE_OF_2N, 2);
		BigInteger numberOfGoodPairs = allPairs.subtract(numberOfBadPairs);
		// while(n>=2)
		// {
		//
		// result = result.multiply(combination(n, r));
		// n-=2;
		//
		//
		// }
		// result = result.divide(factorial(VALUE_OF_2N/2));

		// System.out.println(allPairs);

		// System.out.println(result);
		System.out.println();

		System.out.println("badpairs" + numberOfBadPairs());
		// System.out.println(numberOfGoodPairs);
		BigInteger accumilator = new BigInteger(allPairs.toString());
		
		BigInteger finalAnswer = BigInteger.valueOf(1);
		BigInteger one = BigInteger.valueOf(1);
		BigInteger counter = BigInteger.valueOf(0);
		BigInteger constantDiff = BigInteger
				.valueOf(numberOfOccurencesInCombination
						+ numberOfOccurencesInCombination - 2);
		
		while (accumilator.compareTo(one) >= 0) {

			finalAnswer = finalAnswer.multiply(accumilator);
			//numberOfBadScenarios = numberOfBadScenarios.multiply(badAccumilator);
	
			System.out.println(accumilator);
			accumilator = accumilator.subtract(constantDiff);
			accumilator = accumilator.subtract(one);
			
//			badAccumilator = badAccumilator.subtract(constantDiff);
//			badAccumilator = badAccumilator.subtract(one);
			counter = counter.add(one);

		}
		
		
		
		BigInteger badAccumilator = new BigInteger(allPairs.toString()).subtract(BigInteger.valueOf(1)).subtract(constantDiff);
		System.out.println("bad accu initia: "+badAccumilator);
		BigInteger numberOfBadScenarios= BigInteger.valueOf(1);

		
		System.out.println("bad: ");
		while (badAccumilator.compareTo(one) >= 0) {

			numberOfBadScenarios = numberOfBadScenarios.multiply(badAccumilator);
	
			System.out.println(badAccumilator);
		
			
			badAccumilator = badAccumilator.subtract(constantDiff);
			badAccumilator = badAccumilator.subtract(one);
						//counter = counter.add(one);

		}

		finalAnswer = finalAnswer.divide(factorial(counter.intValue()));
		numberOfBadScenarios = numberOfBadScenarios.multiply(numberOfBadPairs).divide(factorial(counter.intValue()));
	
		System.out.println("all scenarios: "+finalAnswer);

		System.out.println("number of bad scenarios: "+numberOfBadScenarios);
		
		System.out.println(finalAnswer.subtract(numberOfBadScenarios));
		
		BigInteger catlanNumber = factorial(VALUE_OF_2N).divide(factorial(VALUE_OF_2N/2+1).multiply(factorial(VALUE_OF_2N/2)));
		System.out.println("Catlan number(one line solution): "+catlanNumber);

	}

}

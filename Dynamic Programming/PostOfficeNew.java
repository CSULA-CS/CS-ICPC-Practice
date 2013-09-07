import java.io.*;
import java.util.*;

public class Main {

	int[] positions;
	int[] distances;

	int totalCost;

	int numberOfVillages;
	int numberOfPostOffices;

	/**
	 * Constructor
	 * @param  numberOfVillages    number of villages
	 * @param  numberOfPostOffices number of offices
	 * @return                     it's constructor!
	 */
	public Main(int numberOfVillages, int numberOfPostOffices) {

		this.numberOfVillages = numberOfVillages;
		this.numberOfPostOffices = numberOfPostOffices;

		positions = new int[numberOfVillages];
		distances = new int[numberOfVillages];

		// init all the distances value to be max
		for (int i = 0; i < numberOfVillages; i ++) {
			distances[i] = Integer.MAX_VALUE;
		}
	}

	/**
	 * Simple for loop to calculate and update the total distance
	 * @return total distance
	 */
	public int calculateTotal(int[] currentDistances) {
		int result = 0;

		for (int distance : currentDistances) {
			result += distance;
		}

		return result;
	}

	/**
	 * update the state of each village
	 * @param index index of which village to update
	 */
	public void updateDistance(int index, int[] currentDistances) {
		currentDistances[index] = 0;

		for (int i = 0; i < numberOfVillages; i ++) {
			// update distance of all other villages that has no office
			if (i != index && currentDistances[i] != 0) {
				for (int j = 0; j < numberOfVillages; j ++) {
					// find the other post office
					if ( currentDistances[j] == 0 ) {
						// update the distance if the distance to this office is smaller
						if ( (int) Math.abs( positions[i] - positions[j] ) < currentDistances[i] ) {
							// update the distance
							currentDistances[i] = (int) Math.abs( positions[i] - positions[j] );
						}
					}
				}
			}
		}

	}

	/**
	 * Calculate the total distance if the village is constructed with office
	 * @param  index            index of the village
	 * @param  currentDistances the current state of the village
	 * @return                  total cost if the village is built
	 */
	public int constructPost(int index, int[] currentDistances) {

		// make a new copy of the distance
		int[] fakeDistances = currentDistances.clone();

		updateDistance( index, fakeDistances );

		return calculateTotal( fakeDistances );
	}

	/**
	 * Compute the total cost and update the cost
	 */
	public void ComputeTotalCost() {
		/**
		 * Pseudocode
		 * int[] distances
		 * for (int i = 0 to numberOfPostOffices) {
		 * 		for (int index = 0 to numberOfVillages) {
		 * 			if ( constructPost(index, distances) < min ) {
		 * 				min = constructPost(index, distances);
		 * 				minIndex = index;
		 * 			}
		 * 		}
		 * 		updateDistance( minIndex );
		 * 	}
		 * 	return calculateTotal( distances );
		 */
		
		for (int i = 0; i < numberOfPostOffices; i ++) {
			// init the min value so it can be used to track which is min;
			int min = Integer.MAX_VALUE;
			int minIndex = -1;

			for (int index = 0; index < numberOfVillages; index ++) {
				// calculate the min and its index
				int value = constructPost( index, distances );

				if ( value < min ) {
					min = value;
					minIndex = index;
				}

			}

			updateDistance( minIndex, distances );
		}

		totalCost = calculateTotal( distances );
	}

	public static void main(String[] args) {
		// scanner input
		Scanner cin = new Scanner(System.in);

		// get the first line of input
		String firstLine = cin.nextLine();

		String[] firstNumbers = firstLine.split(" ");

		int nOfVil = Integer.parseInt(firstNumbers[0]);
		int nOfOff = Integer.parseInt(firstNumbers[1]);

		Main postOfficeProblem = new Main(nOfVil, nOfOff);

		// read the next line
		String nextLine = cin.nextLine();

		String[] positionStrs = nextLine.split(" ");

		for (int i = 0; i < nOfVil; i ++) {
			postOfficeProblem.positions[i] = Integer.parseInt(positionStrs[i]);
		}

		postOfficeProblem.ComputeTotalCost();

		System.out.println(postOfficeProblem.totalCost);

	}

}

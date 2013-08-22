import java.util.Arrays;

public class PostOffice {

	int[] villages;

	int numberOfVillages;
	int numberOfPostOffices;
	int totalCost;

	public PostOffice(int numberOfVillages, int numberOfPostOffices) {

		this.numberOfVillages = numberOfVillages;
		this.numberOfPostOffices = numberOfPostOffices;
		villages = new int[numberOfVillages];
	}

	public void ComputeTotalCost() {
		int sum = 0;
		for (int i = 0; i < villages.length - 1; i++) {
			sum += villages[i + 1] - villages[i];
		}
		this.totalCost = sum;
	}

	public int min(int i, int j) {

		if (i >= j) {
			return j;
		} else {

			return i;

		}
	}

	public int DP(int indexOfVillage, int numberOfOfficesLeft) {

		if (numberOfOfficesLeft <= 0) {

			return 0;
		}

		// Two cases: either add post office at village or not
		return min(DP(indexOfVillage + 1, numberOfOfficesLeft),
				
				DP(indexOfVillage + 1, numberOfOfficesLeft - 1)

		);
	}

	public static void main(String[] args) {
		PostOffice postOfficeProblem = new PostOffice(10, 5);
		postOfficeProblem.villages[0] = 1;
		postOfficeProblem.villages[1] = 2;
		postOfficeProblem.villages[2] = 3;
		postOfficeProblem.villages[3] = 6;
		postOfficeProblem.villages[4] = 7;
		postOfficeProblem.villages[5] = 9;
		postOfficeProblem.villages[6] = 11;
		postOfficeProblem.villages[7] = 22;
		postOfficeProblem.villages[8] = 44;
		postOfficeProblem.villages[9] = 50;
		postOfficeProblem.ComputeTotalCost();
		System.out.println(postOfficeProblem.totalCost);

	}

}

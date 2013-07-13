public class BST {
	public static void main(String[] args) {
		findAnswer(8);
	}

	public static int findLevel(int root) {
		int answer = 1;

		while (true) {
			if (root % Math.pow(2, answer) == Math.pow(2, answer-1))
				break;
			else
				answer ++;
		}
		return answer;
	}

	public static void findAnswer(int root) {
		int level = findLevel(root);

		level --;

		int max = root;
		int min = root;

		while (level > 0) {
			max += Math.pow(2, level-1);
			min -= Math.pow(2, level-1);
			level --;
		}

		System.out.println("max: " + max);
		System.out.println("min: " + min);
	}
}
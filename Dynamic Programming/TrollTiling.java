

public class TrollTiling {
	public static final int BASE_D_0 = 1;

	public static int caseD(int n) {
		
		if (n % 2 == 1) {
			// if n is odd number, then there is no solution
			return -1;
		} else if(n == 0) {
			// base case for n
			return BASE_D_0;
		} else {
			return caseD(n - 2) + caseA(n - 1) * 2;
		}
	}

	public static int caseA(int n) {
		// when n > 3
		// a(n) = d(n-2) + ... + d(2) + a(1)
		int result = 1;
		for (int i = 2; i <= n-1; i += 2) {
			// result = d(n-2) + ... + d(2)
			result += caseD(i);
		}

		return result;
	}

	public static void main(String[] args) {
		for (int i = 2; i <= 30; i += 2)
			System.out.println("Case D for " + i + " : " + caseD(i));
	}
}
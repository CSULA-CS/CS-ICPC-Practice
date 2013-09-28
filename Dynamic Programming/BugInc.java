import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class BugInc {
	public static int[][] squares;
	// 0 means bad square
	// 1 means good square
	// 2 means taken
	
	public static Map<int[][], Integer> memo = new HashMap<int[][], Integer>();
	// key as situation (squares)(int[][])
	// value as how many good chips we already built

	/**
	 * Check the squares is allow to build block A
	 * @param  squares situation of squares
	 * @param  i       index of i (row)
	 * @param  j       index of j (column)
	 * @return         true if buildable, false if not
	 */
	public static boolean isBuildableA(int[][] squares, int i, int j) {
		if (squares[i][j] != 1) {
			return false;
		} else {
			if (i + 1 < squares.length && i + 2 < squares.length
				&& j + 1 < squares[0].length) {
				if ( squares[i+1][j] == 1 && squares[i+2][j] == 1
					&& squares[i][j+1] == 1 && squares[i+1][j+1] == 1
					&& squares[i+2][j+1] == 1 ) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	/**
	 * Same as isBuildableA, but check for block B
	 */
	public static boolean isBuildableB(int[][] squares, int i, int j) {
		if (squares[i][j] != 1) {
			return false;
		} else {
			if (i + 1 < squares.length
				&& j + 1 < squares[0].length && j + 2 < squares[0].length) {
				if ( squares[i+1][j] == 1
					&& squares[i][j+1] == 1 && squares[i+1][j+1] == 1
					&& squares[i][j+2] == 1 && squares[i+1][j+2] == 1) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	/**
	 * Build block A based on the squares
	 * Clone original squares as newSquares
	 * and return the newSquares
	 */
	public static int[][] buildA(int[][] squares, int i, int j) {
		int[][] newSquares = new int[ squares.length ][ squares[0].length ];

		// clone the squares
		for (int r = 0; r < squares.length; r ++) {
			for (int c = 0; c < squares[r].length; c ++) {
				newSquares[r][c] = squares[r][c];
			}
		}

		newSquares[i][j] = 2;
		newSquares[i+1][j] = 2;
		newSquares[i+2][j] = 2;
		newSquares[i][j+1] = 2;
		newSquares[i+1][j+1] = 2;
		newSquares[i+2][j+1] = 2;

		return newSquares;
	}

	/**
	 * Build block B based on the squares
	 * Clone original squares as newSquares
	 * and return the newSquares
	 */
	public static int[][] buildB(int[][] squares, int i, int j) {
		int[][] newSquares = new int[ squares.length ][ squares[0].length ];

		// clone the squares
		for (int r = 0; r < squares.length; r ++) {
			for (int c = 0; c < squares[r].length; c ++) {
				newSquares[r][c] = squares[r][c];
			}
		}

		newSquares[i][j] = 2;
		newSquares[i][j+1] = 2;
		newSquares[i][j+2] = 2;
		newSquares[i+1][j] = 2;
		newSquares[i+1][j+1] = 2;
		newSquares[i+1][j+2] = 2;

		return newSquares;
	}

	public static int solve(int[][] squares) {
		for (int i = 0; i < squares.length; i ++) {
			for (int j = 0; j < squares.length; j ++) {
				if (isBuildableA(squares, i, j)) {

					int[][] newSquares = buildA(squares, i, j);

					memo.put(newSquares, memo.get(squares) + 1);

					solve(newSquares);
				}
				if (isBuildableB(squares, i, j)) {
					int[][] newSquares = buildB(squares, i, j);

					memo.put(newSquares, memo.get(squares) + 1);

					solve(newSquares);
				}
			}
		}

		Integer max = 0;

		Iterator it = memo.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			if (max < (Integer) pairs.getValue())
				max = (Integer) pairs.getValue();
		}

		return max;
	}

	public static void main(String[] args) {
		
		 Scanner scanner =new Scanner(System.in);
          int numberOfCases = scanner.nextInt();
          ArrayList<int[][]> list = new ArrayList<int[][]>();
          for(int i=0; i<numberOfCases; i++)
          {
        	 int N = scanner.nextInt();
        	 int M = scanner.nextInt();
        	 int K = scanner.nextInt();
        	  
        	 int[][] input = new int[M][N];
        	 
        	 for (int j = 0; j < input.length; j++) {
            	 Arrays.fill(input[j], 1);

			}
        	 
        	 for(int j=0; j<K; j++)
        	 {
        		 int colBad = scanner.nextInt();
        		 int rowBad = scanner.nextInt();
        		 input[rowBad-1][colBad-1]=0;
        		 
        		 
        	 }
        	 
        	 
        	 memo.clear();
 			memo.put(input, 0);
 			System.out.println(solve(input));
        	 
        	 //list.add(input);
        	 
        	  
        	  
          }
		
//		
//		int[][] input = {
//			{ 1, 1, 1, 1, 1, 0 },
//			{ 1, 1, 1, 1, 1, 0 },
//			{ 1, 1, 0, 1, 1, 1 },
//			{ 1, 1, 1, 1, 1, 0 },
//			{ 1, 1, 1, 1, 1, 1 },
//		};

//          for (int i = 0; i < list.size(); i++) {
//			memo.clear();
//			memo.put(list.get(i), 0);
//			System.out.println(solve(list.get(i)));
//        	  
//        	  
//		}
//          

//		System.out.println("Expected: 4");
//		System.out.println("Answer is : " + solve(input));
	}
}
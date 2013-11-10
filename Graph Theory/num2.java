import java.io.*;
import java.util.*;

class num2 {
	static int size = 0;
	static int m = 0;
	static int n = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader stdin;
		String s;

		stdin = new BufferedReader(new InputStreamReader(System.in));

		while ( ( s = stdin.readLine() ) != null ) {

			m = Integer.parseInt( s.split(" ")[0] );
			n = Integer.parseInt( s.split(" ")[1] );
			size = m * n;

			boolean[] obstacles = new boolean[m * n];

			for (int i = 0; i < m; i ++) {
				s = stdin.readLine();

				for (int j = 0; j < n; j ++) {
					if (s.charAt(j) == '*') {
						obstacles[ n * i + j ] = true;
					} else {
						obstacles[ n * i + j ] = false;
					}
				}
			}

			System.out.println( getMinimulSteps(obstacles) );
		}

		System.exit(0);
	}

	public static int getMinimulSteps(boolean[] obstacles) {
		int result = -1;

		for (int i = 0; i < obstacles.length; i ++) {
			if ( !obstacles[i] ) {
				int steps = getMinimulStep(i, obstacles);
				if (steps != -1) {
					if ( steps < result || result == -1 ) {
						result = steps;
					}
				}
			}
		}

		return result;
	}

	public static int getMinimulStep(int v, boolean[] obstacles) {
		int result = -1;

		int[] next = new int[size];
		for (int i = 0; i < size; i ++)
			next[i] = -1;

		boolean[] isVisited = new boolean[size];
		for (int i = 0; i < size; i ++)
			isVisited[i] = obstacles[i];

		List<Integer> move = new LinkedList<Integer>();
		move.add(v);

		if ( getHamiltonianPath(move, next, isVisited) ) {
			int vertex = v;
			while ( vertex != -1 ) {
				result ++;
				vertex = next[vertex];
			}
		}

		return result;
	}

	public static boolean getHamiltonianPath(List<Integer> move, int[] next, boolean[] isVisited) {
		int curr = -1;

		for (int v: move) {
			isVisited[v] = true;
			curr = v;
		}

		if ( allVisited(isVisited) )
			return true;
		
		for ( LinkedList<Integer> nextMove: getNextMoves(curr, isVisited) ) {
			if ( getHamiltonianPath(nextMove, next, isVisited) ) {
				int nextPos = -1;

				for (int nextStep: nextMove) {
					nextPos = nextStep;
				}

				next[curr] = nextPos;

				return true;
			}
		}

		// backtracking
		for (int v: move)
			isVisited[v] = false;

		return false;
	}

	public static boolean allVisited(boolean[] isVisited) {
		boolean result = true;

		for (int i = 0; i < size; i ++)
			result = result && isVisited[i];

		return result;
	}

	public static List<LinkedList<Integer>> getNextMoves(int curr, boolean[] isVisited) {
		List<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();

		// move north
		LinkedList<Integer> northMove = new LinkedList<Integer>();
		int nextMove = curr - n;
		while ( nextMove >= 0 && !isVisited[nextMove] ) {
			northMove.add(nextMove);
			nextMove -= n;
		}
		if (northMove.size() != 0)
			result.add(northMove);

		// move east
		LinkedList<Integer> eastMove = new LinkedList<Integer>();
		nextMove = curr + 1;
		int boundaryX = n * ((int) (curr / n) + 1);
		while ( nextMove < boundaryX && !isVisited[nextMove] ) {
			eastMove.add(nextMove);
			nextMove += 1;
		}
		if (eastMove.size() != 0)
			result.add(eastMove);

		// move west
		LinkedList<Integer> westMove = new LinkedList<Integer>();
		nextMove = curr - 1;
		while ( nextMove >= 0 && !isVisited[nextMove] ) {
			westMove.add(nextMove);
			nextMove -= 1;
		}
		if (westMove.size() != 0)
			result.add(westMove);

		// move south
		LinkedList<Integer> southMove = new LinkedList<Integer>();
		nextMove = curr + n;
		while ( nextMove < size && !isVisited[nextMove] ) {
			southMove.add(nextMove);
			nextMove += n;
		}
		if (southMove.size() != 0)
			result.add(southMove);

		return result;
	}
}
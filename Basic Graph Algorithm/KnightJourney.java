import java.io.*;
import java.util.*;

public class KnightJourney {

	public static void main(String[] args) {
		// scanner input
		Scanner cin = new Scanner(System.in);

		// get the first line of input
		String firstLine = cin.nextLine();

		int n = Integer.valueOf(firstLine);

		for (int i = 0; i < n; i ++) {
			// read the next line
			String nextLine = cin.nextLine();

			String[] tokens = nextLine.split(" ");

			int p = Integer.parseInt(tokens[0]);
			int q = Integer.parseInt(tokens[1]);

			Scenario board = new Scenario(p, q);

			board.solve();

			System.out.println("Scenario #" + i + ":");
			System.out.println(board.answer);
			System.out.println();
		}
	}

	public static class Scenario {
		static int p, q;

		static String answer = "";

		public Scenario(int p, int q) {
			this.p = p;
			this.q = q;
		}

		/**
		 * Using BFS as approach to solve the problem, trick is the knight has to start at the corner!
		 * @return BFS solving
		 */
		public static void solve() {
			/**
			 * openlist = []
			 * closelist = []
			 * while openlist:
			 *    current = openlist.pop()
			 *    closelist.add(current)
			 *    moves = getPossibleMoves(current)
			 *    for next in Moves:
			 *        if next in openlist:
			 *            continue
			 *        else:
			 *            openlist.add(next)
			 * if closelist.length != p * q:
			 *     return "impossible"
			 * else:
			 *     return closelist
			 */
			
			LinkedList<Square> openlist = new LinkedList<Square>();
			LinkedList<Square> closelist = new LinkedList<Square>();

			// Todo: repeat the solve process for four corners
			openlist.add(new Square(0, 0));

			while (!openlist.isEmpty()) {
				Square current = openlist.pollLast();
				
				List<Square> moves = getPossibleMoves(current);

				for (Square next : moves) {
					System.out.println(next.toString());
					if (closelist.contains(next)) {
						System.out.println("BOO");
						continue;
					} else {
						openlist.add(next);
						closelist.add(next);
					}
				}
			}

			if (closelist.size() != (p * q)) {
				answer = "impossible";
			} else {
				for (Square step: closelist) {
					answer += step.toString();
				}
			}
		}

		/**
		 * Hard coded way to generate the possible moves by giving input current Square
		 * @param  current the square knight is current on
		 * @return         possible moves that knight can move to
		 */
		public static List<Square> getPossibleMoves(Square current) {
			List<Square> results = new ArrayList<Square>();

			Square s1 = new Square(current.letter - 1, current.number - 2);
			Square s2 = new Square(current.letter - 1, current.number + 2);
			Square s3 = new Square(current.letter - 2, current.number - 1);
			Square s4 = new Square(current.letter - 2, current.number + 1);
			Square s5 = new Square(current.letter + 1, current.number - 2);
			Square s6 = new Square(current.letter + 1, current.number + 2);
			Square s7 = new Square(current.letter + 2, current.number - 1);
			Square s8 = new Square(current.letter + 2, current.number + 1);

			if (s1.isValid(p, q))
				results.add(s1);
			if (s2.isValid(p, q))
				results.add(s2);
			if (s3.isValid(p, q))
				results.add(s3);
			if (s4.isValid(p, q))
				results.add(s4);
			if (s5.isValid(p, q))
				results.add(s5);
			if (s6.isValid(p, q))
				results.add(s6);
			if (s7.isValid(p, q))
				results.add(s7);
			if (s8.isValid(p, q))
				results.add(s8);

			return results;
		}

	}


	public static class Square implements Comparable {
		int letter;
		int number;

		public Square(int letter, int number) {
			this.letter = letter;
			this.number = number;
		}

		public boolean isValid(int p, int q) {
			if (number < p && letter < q && number >= 0 && letter >= 0) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public String toString() {
			// print this square using char convertion
			return "" + (char)('A' + this.letter) + "" + (this.number + 1);
		}

		@Override
		public int compareTo(Object otherObj) {
			Square other = (Square) otherObj;
			if (other.letter == this.letter && other.number == this.number) {
				return 0;
			} else if (other.letter >= this.letter && other.number >= this.number) {
				return 1;
			} else {
				return -1;
			}
		}
	}
}
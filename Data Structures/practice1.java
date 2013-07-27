import java.util.*;

public class practice1 {
	List<Node> inputList = new ArrayList<Node>();

	public static void main(String[] args) {
		System.out.println("Answer1:");
		// 7 a/7 b/6 c/5 d/4 e/3 f/2 g/1
		Node node1 = new Node("a", 7);
		Node node2 = new Node("b", 6);
		Node node3 = new Node("c", 5);
		Node node4 = new Node("d", 4);
		Node node5 = new Node("e", 3);
		Node node6 = new Node("f", 2);
		Node node7 = new Node("g", 1);

		List<Node> case1 = new ArrayList<Node>();

		case1.add(node1);
		case1.add(node2);
		case1.add(node3);
		case1.add(node4);
		case1.add(node5);
		case1.add(node6);
		case1.add(node7);
		
		List<Node> left = new ArrayList<Node>();
		List<Node> right = new ArrayList<Node>();

		Treap answer = divideAndConqure(case1);
		
		answer.inOrder(answer.root);

		System.out.println();
		System.out.println("Answer2:");

		// 7 a/1 b/2 c/3 d/4 e/5 f/6 g/7
		Node secondNode1 = new Node("a", 1);
		Node secondNode2 = new Node("b", 2);
		Node secondNode3 = new Node("c", 3);
		Node secondNode4 = new Node("d", 4);
		Node secondNode5 = new Node("e", 5);
		Node secondNode6 = new Node("f", 6);
		Node secondNode7 = new Node("g", 7);

		List<Node> case2 = new ArrayList<Node>();

		case2.add(secondNode1);
		case2.add(secondNode2);
		case2.add(secondNode3);
		case2.add(secondNode4);
		case2.add(secondNode5);
		case2.add(secondNode6);
		case2.add(secondNode7);

		Treap answer2 = divideAndConqure(case2);

		answer2.inOrder(answer2.root);

		System.out.println();
		System.out.println("Answer3:");

		// 7 a/3 b/6 c/4 d/7 e/2 f/5 g/1
		Node thirdNode1 = new Node("a", 3);
		Node thirdNode2 = new Node("b", 6);
		Node thirdNode3 = new Node("c", 4);
		Node thirdNode4 = new Node("d", 7);
		Node thirdNode5 = new Node("e", 2);
		Node thirdNode6 = new Node("f", 5);
		Node thirdNode7 = new Node("g", 1);

		List<Node> case3 = new ArrayList<Node>();

		case3.add(thirdNode1);
		case3.add(thirdNode2);
		case3.add(thirdNode3);
		case3.add(thirdNode4);
		case3.add(thirdNode5);
		case3.add(thirdNode6);
		case3.add(thirdNode7);

		Treap answer3 = divideAndConqure(case3);

		answer3.inOrder(answer3.root);

		System.out.println();
	}

	public static Node findRoot(List<Node> list) {
		Node root = new Node();

		for (Node node : list) {
			if (node.priority > root.priority) {
				root = node;
			}
		}

		if (root.priority == 0) {
			return null;
		} else {
			return root;
		}
	}

	public static void divide(List<Node> input, List<Node> left, List<Node> right) {
		// divide input into two sub-list(left and right) by label of root
		
		Node root = findRoot(input);

		for (Node node: input) {
			if (node.label.compareTo(root.label) > 0) {
				right.add(node);
			} else if (node.label.compareTo(root.label) < 0) {
				left.add(node);
			}
		}

		input.remove(root);
	}

	public static Treap conqure(Treap input, Treap left, Treap right) {
		input.root.left = left.root;
		input.root.right = right.root;

		return input;
	}

	public static Treap divideAndConqure(List<Node> input) {
		Node root = findRoot(input);

		Treap treap = new Treap(root);

		divideAndCHelper(input, treap.root);

		return treap;
	}

	public static void divideAndCHelper(List<Node> input, Node root) {
		if (input.size() == 0) {
			
		} else if (input.size() == 1) {
			
		} else {
			List<Node> left = new ArrayList<Node>();
			List<Node> right = new ArrayList<Node>();

			divide(input, left, right);

			root.left = findRoot(left);
			root.right = findRoot(right);

			divideAndCHelper(left, root.left);
			divideAndCHelper(right, root.right);
		}
	}

	static class Treap {
		Node root;

		public Treap() {

		}

		public Treap(Node root) {
			this.root = root;
		}

		public void inOrder(Node current) {
			System.out.print("(");
			if (current.left != null)
				inOrder(current.left);
			System.out.print(current.toString());
			if (current.right != null)
				inOrder(current.right);
			System.out.print(")");
		}
	}

	static class Node {
		String label;
		Integer priority;

		// pointer to children
		Node left;
		Node right;
		Node parent;

		public Node() {
			this.label = "a";
			this.priority = 0;
		}

		public Node(String label, Integer priority) {
			this.label = label;
			this.priority = priority;
		}

		public String toString() {
			return label + "/" + priority;
		}
	}
}
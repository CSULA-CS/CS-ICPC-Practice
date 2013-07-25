public class practice1 {
	public static void main(String[] args) {
		// 7 a/7 b/6 c/5 d/4 e/3 f/2 g/1
		Node node1 = new Node("a", 7);
		Node node2 = new Node("b", 6);
		Node node3 = new Node("c", 5);
		Node node4 = new Node("d", 4);
		Node node5 = new Node("e", 3);
		Node node6 = new Node("f", 2);
		Node node7 = new Node("g", 1);

		Treap treap = new Treap(node1);

		treap.add(node2);
		treap.add(node3);
		treap.add(node4);
		treap.add(node5);
		treap.add(node6);
		treap.add(node7);

		treap.inOrder(treap.root);

		System.out.println();

		// 7 a/1 b/2 c/3 d/4 e/5 f/6 g/7
		Node secondNode1 = new Node("a", 1);
		Node secondNode2 = new Node("b", 2);
		Node secondNode3 = new Node("c", 3);
		Node secondNode4 = new Node("d", 4);
		Node secondNode5 = new Node("e", 5);
		Node secondNode6 = new Node("f", 6);
		Node secondNode7 = new Node("g", 7);

		Treap treap2 = new Treap(secondNode7);

		treap2.add(secondNode2);
		treap2.add(secondNode3);
		treap2.add(secondNode4);
		treap2.add(secondNode5);
		treap2.add(secondNode6);
		treap2.add(secondNode1);

		treap2.inOrder(treap2.root);
	}

	static class Treap {
		Node root;

		public Treap() {

		}

		public Treap(Node root) {
			this.root = root;
		}

		public void add(Node node) {
			addHelper(root, node);
		}

		public void addHelper(Node current, Node other) {
			if (current.label.compareTo(other.label) > 0) {
				if (current.left == null) {
					current.left = other;
					other.parent = current;
					heapHelper(other);
				} else {
					addHelper(current.left, other);
				}
			} else {
				if (current.right == null) {
					current.right = other;
					other.parent = current;
					heapHelper(other);
				} else {
					addHelper(current.right, other);
				}
			}
		}

		public void heapHelper(Node current) {
			if (current.parent.priority.compareTo(current.priority) < 0) {
				Node temp = current.parent;
				
			}
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
			this.priority = 1;
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
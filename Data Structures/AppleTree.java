package dataStructure;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

class Tree {

}

class Fork {

	boolean hasApple;
	int label;

	HashSet<Fork> connectedForks = new HashSet<Fork>();

	public Fork(int label) {
		this.hasApple = true;
		this.label = label;
	}

	public boolean updateFork() {
		this.hasApple = !this.hasApple;
		return this.hasApple;
	}

	public void addConnectedFork(Fork fork) {
		this.connectedForks.add(fork);
	}

}

public class AppleTree {
	public static int getAppleCount(Fork fork) {
		int result=0;
		if (fork.hasApple) {
			result++;
		}
		for (Fork connectedFork : fork.connectedForks) {
			
				 result+=getAppleCount(connectedFork);

			

		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Fork fork1 = new Fork(1);
		Fork fork2 = new Fork(2);
		Fork fork3 = new Fork(3);
		Fork fork4 = new Fork(4);

		fork1.addConnectedFork(fork2);
		fork1.addConnectedFork(fork3);
		System.out.println(getAppleCount(fork1));
		
		fork2.updateFork();
		System.out.println(getAppleCount(fork1));
		fork3.addConnectedFork(fork4);
		System.out.println(getAppleCount(fork1));
		System.out.println(getAppleCount(fork4));


	}

}

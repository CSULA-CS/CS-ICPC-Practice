import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Triangle {

	int[][] numberTree;
	int dimension;
	HashMap<String, Integer> memo = new HashMap<String, Integer>();

	public Triangle(int dimension) {
		this.dimension = dimension;

		this.numberTree = new int[dimension][dimension];
	}

	public int DP(int i, int j)
	{
		if(i==dimension||j==dimension||i<0||j<0)
		{
			return 0;
		}
		
		if(i==0&&j==0)
		{
			return numberTree[0][0];
		}
		
		
		int max=0;
		
		int TempRowi=i-1;
		int TempColLeft=j-1;
		int TempColRight=j+1;

		
		String leftTreeKey = TempRowi+"-"+TempColLeft;
		String rightTreeKey = TempRowi+"-"+TempColRight;
		
		Integer leftTree = 0;
		Integer rightTree =0;
		
		if(memo.containsKey(leftTreeKey))
		{
			leftTree=memo.get(leftTreeKey);
		}
		else{
			
			leftTree=DP(i-1, j-1);
		}
		
		if(memo.containsKey(rightTreeKey))
		{
			rightTree=memo.get(rightTreeKey);
		}
		else{
			
			rightTree=DP(i-1, j);
		}
		
				
		if(leftTree>rightTree)
		{
			max=leftTree;
		}
		else{
			
			max=rightTree;
		}
		
		return numberTree[i][j]+max;
	}

	public static void main(String[] args) {
		int dimension=5;
		Triangle triangle = new Triangle(dimension);
		
		
		
		try{
			int counter=0;
		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    String line=null;
		    while((line = bufferRead.readLine())!=null&&counter<dimension)
		    {
		    	String[] input = line.split("(?<=.)");
		    	for(int col =0; col<input.length; col++)
		    	{
		    		triangle.numberTree[counter][col]=Integer.parseInt(input[col]);
		    		
		    	}
		    	System.out.println(Arrays.toString(triangle.numberTree[counter]));
		    	counter++;
		    	
		    }
	 
		    
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		int max = Integer.MIN_VALUE;
		for(int i=0; i<dimension; i++)
		{
			
			int temp = triangle.DP(dimension-1, i);
			if(temp>max)
			{
				max=temp;
			}
		}
		System.out.println("Maximum sum of path: "+max);
		
		
	}

}

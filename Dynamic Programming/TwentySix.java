import java.util.ArrayList;
import java.util.Scanner;

//n: index for String one
//m: index for String two
//Input: two strings, and a matrix with the values for the cost of matching
//scoringMatrix[5][5]
//i.e.
//		A	T	C	G	_
//	A   6   1   2   1   0
//	T   1   6   1   2   0
//	C   2   1   6   1   0
//	G   1   2   1   6   0
//	_   0   0   0   0   0

//dp(n,m) = min(dp(n-1, m-1)+scoringMatrix[n][m], 

//              dp(n-1, m) + scoringMatrix[underscore][m],
//                dp(n, m-1) +scoringMatrix[n][underscore])

class State{
	int row;
	int column;
	State previous;
	
	public State(int row, int column)
	{
		this.row=row;
		this.column=column;
		
	}
	@Override
	  public boolean equals(Object object) {
	    boolean result = false;
	    if (object == null || object.getClass() != getClass()) {
	      result = false;
	    } else {
	      State state = (State) object;
	      if (this.row == state.row
	          && this.column == state.column) {
	        result = true;
	      }
	    }
	    return result;
	  }
	 
	  // just omitted null checks
	  @Override
	  public int hashCode() {
	    int hash = 21;
	    hash = 7 * hash + this.row;
	    hash = 7 * hash + this.column;
	    return hash;
	  }
	  public String toString()
	  {
	return row+" "+column;
		  //return "";
	  }
	 
	
	
}

public class TwentySix {
	
	
ArrayList<State> states = new ArrayList<>();	
	static final 
	int[][] scoringMatrix = {
		{ 6, 1, 2, 1, 0 }, 
		{ 1, 6, 1, 2, 0 },
		{ 2, 1, 6, 1, 0 }, 
		{ 1, 2, 1, 6, 0 }, 
		{ 0, 0, 0, 0, 0 }

};

	String x;
	String y;
	static int[][] DP;
     public TwentySix(String x, String y)
     {
    	 this.x=x;
    	 this.y=y;
    	 DP = new int[x.length()+1][y.length()+1];
    	 for (int i = 0; i < x.length()+1; i++) {
			for (int j = 0; j < y.length()+1; j++) {
				states.add(new State(i, j));
			}
		}
    	 
    	 
    	 
    	 this.DP(x, y);
     }
	
    static int max(int... x)
    {
    	int max = Integer.MIN_VALUE;
    	for (int i = 0; i < x.length; i++) {
			if(x[i]>max)
			{
				max=x[i];
			}
		}
    	
    	return max;
    	
    	
    	
    }
    //Takes 
	int getScore(char X, char Y) {
		int rowIndex = 0;
		int colIndex = 0;
		switch (X) {
		case 'A':
             rowIndex =0;
			break;
		case 'T':
			rowIndex =1;
			break;

		case 'C':
			rowIndex =2;
			break;

		case 'G':
			rowIndex =3;
			break;

		case '_':
			rowIndex =4;
			break;

		}
		switch (Y) {
		case 'A':
             colIndex =0;
			break;
		case 'T':
			colIndex =1;
			break;

		case 'C':
			colIndex =2;
			break;

		case 'G':
			colIndex =3;
			break;

		case '_':
			colIndex =4;
			break;

		}
    	
    	
    	
    	return scoringMatrix[rowIndex][colIndex];
    	
    	
    }
	
  //dp(n,m) = min(dp(n-1, m-1)+scoringMatrix[n][m], 

//  dp(n-1, m) + scoringMatrix[underscore][m],
//    dp(n, m-1) +scoringMatrix[n][underscore])
	void DP(String X, String Y)
	{
		//Base cases
		DP[0][0] =getScore('_', '_');
		for (int i = 1; i < DP.length; i++) {
			 DP[i][0]=getScore(X.charAt(i-1), '_');
		}
		for (int i = 1; i < DP[0].length; i++) {
			 DP[0][i]=getScore('_', Y.charAt(i-1));
		}
		
		for (int n = 1; n < DP.length; n++) {
			for (int m = 1; m < DP[n].length; m++) {
				int match;
				int skipX;
				int skipY;
				
				   match = DP[n-1][m-1]+getScore(X.charAt(n-1), Y.charAt(m-1));
					skipX = DP[n-1][m]+getScore('_', Y.charAt(m-1));
					skipY =  DP[n][m-1]+getScore(X.charAt(n-1), '_');

			     DP[n][m] = max(match, skipX, skipY); 
			     
			     if(match==DP[n][m])
			     {
			    	 states.get(states.indexOf(new State(n, m))).previous= new State(n-1, m-1);
//			    	 char x;
//			    	 char y;
//			    	 if(n-2<0){
//			    		 x='_';
//			    	 }
//			    	 else{
//			    		 x=X.charAt((n-2));
//			    	 }
//			    	 
//			    	 if(m-2<0)
//			    	 {
//			    		 y='_';
//			    	 }
//			    	 else{
//			    		 
//			    		 y=Y.charAt((m-2));
//			    	 }
//			    	 System.out.println(x+" "+y);
			     }
			     else if(skipY==DP[n][m])
			     {
			    	 states.get(states.indexOf(new State(n, m))).previous= new State(n, m-1);

//			    	 char x;
//			    	 if(n-2<0){
//			    		 x='_';
//			    	 }
//			    	 else{
//			    		 x=X.charAt((n-2));
//			    	 }
//			    	 System.out.println(x+" "+Y.charAt((m-1)));
			     }
			     else{
			    	 
			    	 states.get(states.indexOf(new State(n, m))).previous= new State(n-1, m);


//			    	 char y;
//			    	 if(m-2<0)
//			    	 {
//			    		 y='_';
//			    	 }
//			    	 else{
//			    		 
//			    		 y=Y.charAt((m-2));
//			    	 }
//			    	 
//			    	 System.out.println(X.charAt(n-1)+" "+y);

			     }
				
			}
		}
		
	}
     
     
     
	
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String x = scanner.nextLine();
		String y= scanner.nextLine();
		TwentySix solution = new TwentySix(x, y);
		System.out.println("Solution: "+DP[x.length()][y.length()]);
		
		for (int i = 0; i < DP.length; i++) {
			for (int j = 0; j < DP[i].length; j++) {
				System.out.print(DP[i][j]+" ");
			}
			System.out.println();
		}
		//System.out.println(solution.results.size());
		
         State state = solution.states.get(solution.states.indexOf(new State(x.length(), y.length())));
         System.out.println("Path: ");
         System.out.println(state);
         while(state.previous!=null)
         {
        	 System.out.println(state.previous);
        	 state=solution.states.get(solution.states.indexOf(state.previous));
        	 

         }
//		for (int i = 0; i < 25; i++) {
//			System.out.println(solution.states.get(i)+": "+solution.states.get(i).previous);
//		}

	}

}

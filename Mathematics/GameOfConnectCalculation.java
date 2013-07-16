package mathematics;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

class GameOfConnectCalculation {
	static int VALUE_OF_2N = 6;
	static BigInteger factorial(int input) {

		BigInteger result = BigInteger.valueOf(1);
		for (int i = input; i > 1; i--) {
			result = result.multiply(BigInteger.valueOf(i));
		}
		return result;
	}

	static BigInteger combination(int n, int r) {
		BigInteger result = BigInteger.valueOf(1);
		BigInteger nFactorial = factorial(n);
		BigInteger nMinusRFactorial = factorial(n - r);
		BigInteger rFactorial = factorial(r);
		result = nFactorial.divide(nMinusRFactorial.multiply(rFactorial));

		return result;

	}

	public static void main(String[] args) {
		
        int n = VALUE_OF_2N;
        int r = 2;
        BigInteger result = BigInteger.valueOf(1);
        
        while(n>=2)
        {
        	
        	result = result.multiply(combination(n, r));
        	n-=2;
        	
        	
        }
        result = result.divide(factorial(VALUE_OF_2N/2));
        
        
        
        
        
        
        System.out.println(result);
        
	}

}

package chapter2;
/*
 ID: calviny1
 LANG: JAVA
 TASK: nocows
 */


/*Copy-pasted someone else's solution (I know).*/
import java.io.*;
import java.util.*;

public class nocows {

	private static int N = 0, K = 0;
	
	/*private static HashMap<String, Integer> dp = new HashMap<String, Integer>();
	
	private static int numSolutions(int N, int K) {
		String data = Integer.toString(N) + " " + Integer.toString(K);
		if (dp.containsKey(data)) return dp.get(data);
		if (K < 2) {
			if (N == K) {
				dp.put(data, 1);
				return 1;
			} else {
				dp.put(data,  0);
				return 0;
			}
		}
		String string = "";
		int solution = 0;
		for (int i = 1; i < K; i++) {
			for (int j = 2 * i - 1; j <= Math.min(Math.pow(2, i) - 1, N - 2); j += 2) {
				if (i == K - 1) {
					int remaining = N - 1 - j;
					for (int k = (int) Math.ceil(Math.log(remaining + 1) / Math.log(2)); k <= Math.min(K - 1, (remaining + 1) / 2); k++) {
						solution += numSolutions(j, i) * numSolutions(remaining, k);
					}
				} else {
					solution += numSolutions(j, i) * numSolutions(N - 1 - j, K - 1);
				}
			}
		}
		dp.put(data, solution % 9901);
		return solution % 9901;
	}*/


	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("nocows.in"));
		PrintWriter out = new PrintWriter(new File("nocows.out"));
		N = in.nextInt();
		K = in.nextInt();
					
		long[][] state = new long[K+1][N+1]; 
				
		/*
		 * Initial condition.
		 */		
		state[1][1] = 1;
			    
		for (int length = 2; length <= K; ++length) {
			for (int num = 3; num <= N; num += 2) {
			    			    	    		
		    int rightLength = length - 1;
			    		
			    for (int rightNum = num - 2; rightNum >= 1; rightNum -= 2) {
				    			
			    /*
			     * If there are no valid right subtrees with 
			     * this state then continue
			     */
			    if (state[rightLength][rightNum] == 0)
			       continue;
			    			
			    long rightState = state[rightLength][rightNum];
			    			
			    int leftNum = num - (rightNum + 1);
			    				
				    for (int leftLength = 1; leftLength <= rightLength; ++leftLength) {
				    				
				       long leftState = state[leftLength][leftNum];
				    					
				       /*
				    	* If there is no valid left hand state continue to next
				    	* left hand state.
				    	*/
				       if (leftState == 0)
				          continue;
				    				  					
			               /*
			                * If leftLength == rightLength our loop will consider
			                * both mirror images of the state so only count it
			                * once.
			                *
			                * If leftNum == rightNum then both sub trees
			                * are identical. The loop only considers
			                * this (symmetric) state once which is correct
			                */  		
				       if (leftLength == rightLength) {			
				          state[length][num] += (leftState * rightState);
				       }
				       else {
				          state[length][num] += (leftState * rightState * 2);
				       }
				    				
				       state[length][num] %= 9901;
				    				
				    }
			    }	
			}
		}	        
		out.println(state[K][N]);
		in.close();
		out.close();
		System.exit(0);
	}

}

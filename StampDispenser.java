import java.util.Arrays;



public class StampDispenser
{
    
	int[] stampDenominations;
	public StampDispenser(int[] stampDenominations) throws Exception
	{
		/**
		 * Code to sort array and check the presence of 1 in the given denominations.
		 */
		Arrays.sort(stampDenominations);
		int index=Arrays.binarySearch(stampDenominations,1);
		
		if (index>=0){

			/**
			 * Because of the primitive data type Collections.reversed cannot be applied 
			 * Looping over array arrange the sorted array in descending order
			 */
			for(int i=0;i<stampDenominations.length/2;i++)
			{
				int temp=stampDenominations[i];
				stampDenominations[i]=stampDenominations[stampDenominations.length-i-1];
				stampDenominations[stampDenominations.length-i-1]=temp;

			}
			this.stampDenominations=stampDenominations;
		}
		else{

			System.out.println("no denomination of 1 exists.");
			throw new Exception("Error ");
		}

//		System.out.println(Arrays.toString(stampDenominations));
	}

	/**
	 * Returns the minimum number of stamps that the machine can dispense to
	 * fill the given request.
	 *
	 * @param request The total value of the stamps to be dispensed.
	 */
	public int calcMinNumStampsToFillRequest(int request)
	{  
		/**
		 * Adopting Dynamic Programming approach to solve the problem
		 * Recursion could also be used but It would lead to higher time complexity due to overlapping 
		 * subproblems.
		 */
		
		int [] values=new int[request+1];
		values[0]=0;
		for (int i=1;i<values.length;i++)
		{
			values[i]=Integer.MAX_VALUE;
			
		}
		/** 
		 * constructing a table for all the values from 1 till requested value
		 * and storing the optimum number of coins required for each value.
		 * Then using those values to obtain values for higher request.
		 * This table avoids the problem of calculating values again and again.
		 */
		for(int i=1;i<=request;i++)
		{
			for (int j=0;j<this.stampDenominations.length;j++)
			{
				if (this.stampDenominations[j]<=i)
				{
				int count=1+values[i-this.stampDenominations[j]];
				if (count != Integer.MAX_VALUE && count<values[i])
						{
							values[i]=count;
					
						}
				}
				
			}
			
			
			
		}
	
		
		return values[request];
		
		
	}

	public static void main(String[] args) throws Exception
	{
		int[] denominations = { 90, 30, 24, 10, 6, 2, 1 };;
		StampDispenser stampDispenser = new StampDispenser(denominations);


		assert stampDispenser.calcMinNumStampsToFillRequest(18) == 3;
	}
}

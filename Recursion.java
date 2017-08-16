/**
 * Dewey L. Sia
 * L.07 Recursive Methods
 * 5/08/2016
 */


public class Recursion {
	public static void main (String[] args){
		System.out.println(sumOfDigits(123));
		System.out.println(estimateLog(9,2));
		
	}
    public static int sumOfDigits(int num){
    	if(num == 0) return 0;
    	return num%10 + sumOfDigits(num/10);
    }
    
    public static int estimateLog(int num, int base){
    	if(num == 1) return 0;
    	return 1 + estimateLog(num/base, base);
    }
    //accept two parameters
    // involves concept from IT111
    public void subset(int[] arr){
    	
    }
    
    
    
}
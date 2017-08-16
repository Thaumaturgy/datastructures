/**
 *Dewey L. Sia
 *Binary Search
 */

import java.util.Random;
import java.util.Arrays;
public class BinarySearch {

    public static void main(String[] args){
    	int[] array = new int[10];
    	Random gen = new Random(23);
    	for(int i = 0; i < 10; i++){
    		array[i] = gen.nextInt()%15;
    	}
    	Arrays.sort(array);
    	int target = array[5];
    	System.out.println(Arrays.toString(array));
    	System.out.println(binarySearch(array, -1));
    }
    
    public static int binarySearch(int[] arr, int el){
    	return binarySearch(arr, el, 0, arr.length-1);
    }
    
    public static int binarySearch(int[] arr, int el, int indexL, int indexR){
    	if(indexL > indexR)
    		return -1;
    	int mid = (indexR+indexL)/2;
    	if(el == arr[mid])
    		return mid;
    	else if(el < arr[mid])
    		return binarySearch(arr, el, indexL, mid-1);
    	else 
    		return binarySearch(arr, el, mid+1, indexR);
    }
    
    
}
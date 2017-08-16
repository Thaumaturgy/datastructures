/**
 * Insertion Sort v2
 *Made on 7/18/16 as a review
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;
public class InsertionSort {
 	public static void main(String[] args) throws FileNotFoundException{
 		Scanner in = new Scanner(new BufferedReader(new FileReader("C:\\Users\\dlsia\\Desktop\\nums.txt")));
 		int[] arr = new int[100];
 		for(int i = 0; in.hasNext(); i++){
 			arr[i] = in.nextInt();
 		}
 		insertionSort(arr);
 	}
 	
    public static void insertionSort(int arr[]){
    	for(int i = 1; i < arr.length; i ++){
    		int current = arr[i];
    		int j;
    		for(j = i; j > 0 && arr[j-1] > current; j--){
    			arr[j] = arr[j-1];
    		}
    		arr[j] = current;
    		
    	}
    	for(int x: arr){
    		System.out.println(x);
    	}
    }
    
    
}
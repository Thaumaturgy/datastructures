
public class BinarySearch {

	public int bsearch(int[] arr, int left, int right, int findMe){
		if(left > right)
			return -1; //failure
		
		int mid = (left+right)/2; //get midpoint of array or subarray
		
		if(findMe == arr[mid]) //return index if match found
			return mid;
		else if(findMe > arr[mid]) //findMe is in right side!
			return bsearch(arr, mid+1, right, findMe);
		else						//findMe must be in left side!
			return bsearch(arr, left, mid-1, findMe);
	}
}

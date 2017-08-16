import java.io.FileReader;
import java.util.Scanner;
import java.io.*;
public class SelectionSort {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader("C:\\Users\\Core i5\\workspace\\School and Study\\src\\classlist.txt"));
		String[] arr = new String[33];
		int i = 0;
		while(in.hasNextLine()){
			arr[i] = in.nextLine();
			i++;
		}
		selectionSort(arr);
	}

	public static void selectionSort(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}

		for (int x : arr) {
			System.out.print(x + " ");
		}
	}

	public static void selectionSort(String arr[]) {
		for (int i = 0; i < arr.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j].compareTo(arr[minIndex]) < 0) {
					minIndex = j;
				}
			}
			String temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}

		for (String x : arr) {
			System.out.println(x);
		}
	}
}

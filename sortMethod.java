package mulin.exercise;

import java.lang.*;
import java.util.*;
import java.io.*;

public class sortMethod {
	public static void swap(int[] arr,int i,int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// Insertion sort
	public static void insertionSort(int[] arr) {
		for(int j = 1;j < arr.length;j++) {
			int key = arr[j];
			int i = j - 1;
			while(i >= 0 && arr[i] > key) {
				arr[i + 1]  =arr[i];
				i--;
			}
			arr[i + 1] = key;
		}
		return;
	}

	// Merge sort
	public static void merge(int[] arr, int begin, int mid, int end) {
		int leftLen = mid - begin + 1;
		int rightLen = end - mid;
		int[] leftArr = new int[leftLen];
		int[] rightArr = new int[rightLen];
		int k, i, j;
		for (k = 0,i = begin;k < leftLen;k++)
			leftArr[k] = arr[i++];
		for(k = 0,j = mid + 1;k < rightLen;k++)
			rightArr[k] = arr[j++];
		for(k = begin,i = 0,j= 0;i < leftLen && j < rightLen;k++) {
			if(leftArr[i] <= rightArr[j])
				arr[k] = leftArr[i++];
			else
				arr[k] = rightArr[j++];
		}
		while(i < leftLen) 
			arr[k++] = leftArr[i++];
		while(j < rightLen) 
			arr[k++] = rightArr[j++];
	}

	public static void mergeSort(int[] arr,int begin, int end) {
		if(begin < end) {
			int mid = begin + (end - begin) / 2;
			mergeSort(arr,begin,mid);
			mergeSort(arr,mid + 1,end);
			merge(arr,begin,mid,end);
		}
	}

	// Heap sort
	// maintain a max heap
	public static void maxHeapify(int[] arr,int i,int heapSize) {
		int leftChild = i * 2;
		int rightChild = i * 2 + 1;
		int largest;
		if(leftChild <= heapSize  && arr[leftChild] > arr[i])
			largest = leftChild;
		else
			largest = i;
		if(rightChild <= heapSize  && arr[rightChild] > arr[largest])
			largest = rightChild;
		if(i != largest) {
			swap(arr,i,largest);
			maxHeapify(arr,largest,heapSize);
		}

	}

	// initialize a max heap
	public static void buildMaxHeap(int[] arr) {
		for(int i = (arr.length - 1) / 2;i >= 1;i--)
			maxHeapify(arr,i,arr.length - 1);
	}

	// actual heap sort
	public static void heapSort(int[] arr,int heapSize) {
		int size = heapSize;
		buildMaxHeap(arr);
		for(int i = heapSize;i >= 2;i--) {
			swap(arr,i,1);
			size--;
			maxHeapify(arr,1,size);
		}
	}

	public static void main(String[] args) throws IOException {
		if(args.length < 1) {
			System.out.println("The number of input parameters should be 1!");
			return;
		}

		System.setIn(new FileInputStream(args[0]));
		Scanner scanner = new Scanner(System.in);

		String line = null;
		if(scanner.hasNextLine()) {
			line = scanner.nextLine();
			line = line.trim();
		}
		else {
			System.out.println("Input file is empty!");
			return;
		}

		String[] strArr = line.split(" ");
		int[] arr = new int[strArr.length];
		for(int i = 0;i < strArr.length;i++) {
			arr[i] = Integer.parseInt(strArr[i]);
		}

		System.out.printf("%nThe unsorted array is: ");
		for(int i = 0;i < arr.length;i++) {
			System.out.printf("%d ",arr[i]);
		}
		
		int[] tmp = Arrays.copyOf(arr,arr.length);
		sortMethod.insertionSort(tmp);
		System.out.printf("%n(Insertion sort)The sorted array is: ");
		for(int i = 0;i < tmp.length;i++) {
			System.out.printf("%d ",tmp[i]);
		}

		tmp = Arrays.copyOf(arr,arr.length);
		sortMethod.mergeSort(tmp,0,tmp.length - 1);
		System.out.printf("%n(Merge sort)The sorted array is: ");
		for(int i = 0;i < tmp.length;i++) {
			System.out.printf("%d ",tmp[i]);
		}

		tmp = new int[arr.length + 1];
		tmp[0] = Integer.MAX_VALUE;
		for(int i = 1;i < tmp.length;i++)
			tmp[i] = arr[i - 1];
		sortMethod.heapSort(tmp,arr.length);
		System.out.printf("%n(Heap sort)The sorted array is: ");
		for(int i = 1;i < tmp.length;i++) {
			System.out.printf("%d ",tmp[i]);
		}
	}
}

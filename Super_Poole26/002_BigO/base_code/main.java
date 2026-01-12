import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;


class main {
	public static void main(String args[]) {
		int [] times = {10, 100, 1000, 10000, 100000, 1000000, 10000000};
		int [] nums = new int[100];
		Stopwatch s = new Stopwatch();

		System.out.println("-------------------Test-------------------");
		System.out.println("");
		for(int i : times){
			System.out.println("Interval: " + i);
			nums = new int[i];
			randomize(nums);
			s.start();	
			//  Put your method between these two comments
			mergeSort(nums, 0, nums.length-1);
			//
			s.stop();
			System.out.println("Duration: " + Stopwatch.readable(s.read()));
			System.out.println("");
		}
	}
	public static int[] randomize(int [] nums){
		for(int i=0;i<nums.length; i++){
			nums[i] = (int)(Math.random()*200001);
		}
		return nums;
	}
	
	public static int[] bubbleSort(int [] nums){
		int n = nums.length;
		boolean swapped;
		
		for(int i=0;i<n-1; i++){
			swapped = false;
		
			for(int j=0; j<n-1-i; j++){
				if(nums[j]>nums[j+1]){
						int temp = nums[j];
						nums[j] = nums[j+1];
						nums[j+1] = temp;
						swapped = true;
					}
			}
		}
		return nums;
	}
	
	public static boolean search(int [] nums){
		randomize(nums);
		int target = (int)(Math.random()*200001);
		for(int i=0;i<nums.length; i++){
			if(target==nums[i])
				return true;
		}
		return false;
	}
	
	
	public static void insertionSort(int[]nums){
		int n = nums.length;
		
		for(int i = 1; i<n; i++){
			int key = nums[i];
			int j = i-1;
			
			while(j>=0&&nums[j]>key){
				nums[j+1]=nums[j];
				j--;
			}
			nums[j+1] = key;
		}
	}
	
	public static void selectionSort(int[]nums){
		int n = nums.length;
		
		for(int i=0; i<n-1; i++){
			int minIndex = i;
			
			for (int j=i+1; j<n; j++){
				if(nums[j]<nums[minIndex]){
					minIndex = j;
				}
			}
			int temp = nums[minIndex];
			nums[minIndex] = nums[i];
			nums[i] = temp;
		}
	}
	
	public static void mergeSort(int [] arr, int left, int right){
		if(left<right){
			int mid = (left+right)/2;
			
			mergeSort(arr, left, mid);
			mergeSort(arr, mid+1, right);
			
			merge(arr, left, mid, right);
		}
	}
	
	public static void merge(int[] arr, int left, int mid, int right){
		int n1 = mid - left + 1;
		int n2 = right - mid;
		
		int [] L = new int[n1];
		int [] R = new int[n2];
		
		for(int i =0; i<n1; i++){
			L[i] = arr[left+i];
		}
		
		for(int j=0; j<n2; j++){
			R[j] = arr[mid+1+j];
		}
		
		int i = 0, j=0;
		int k = left;
		
		while (i<n1 && j<n2){
			if(L[i]<=R[j]){
				arr[k] = L[i];
				i++;
			}
			else{
				arr[k] = R[j];
				j++;
			}
			k++;
		}
		
		while(i<n1){
			arr[k] = L[i];
			i++;
			k++;
		}
	}
	
	
}




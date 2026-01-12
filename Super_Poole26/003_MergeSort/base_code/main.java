import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;
import java.io.*;


class main {
	public static void main(String args[]) {
		int [] times = {10, 100, 1000, 10000, 100000, 1000000, 10000000};
		int [] nums = new int[100];
		Stopwatch s = new Stopwatch();
		// List<DayBirth> data = DayBirth.readCSV("births.csv");
		// List<DayBirth> males = new ArrayList<>();
		// List<DayBirth> females = new ArrayList<>();

		// for (DayBirth d : data) {
		//     if ("M".equalsIgnoreCase(d.getGender())) males.add(d);
		//     else if ("F".equalsIgnoreCase(d.getGender())) females.add(d);
		// }
		
		// mergeSort(males);
		// mergeSort(females);
		
		// writeCSV("sorted_	males.csv", males);
		// writeCSV("sorted_females.csv", females);

		System.out.println("-------------------Test-------------------");
		System.out.println("");
		
		for(int i : times){
			System.out.println("Interval: " + i);
			nums = new int[i];
			randomize(nums);
			s.start();	
			//  Put your method between these two comments
			quickSort(nums, 0, nums.length-1);
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
	
	public static void mergeSort(List<DayBirth> list) {
	    if (list.size() < 2) return;
	
	    int mid = list.size() / 2;
	
	    List<DayBirth> left = new ArrayList<>(list.subList(0, mid));
	    List<DayBirth> right = new ArrayList<>(list.subList(mid, list.size()));
	
	    mergeSort(left);
	    mergeSort(right);
	
	    merge(list, left, right);
}
	
	public static void merge(List<DayBirth> list, List<DayBirth> left, List<DayBirth> right) {
	    int i = 0, j = 0, k = 0;
	
	    while (i < left.size() && j < right.size()) {
	        if (left.get(i).getBirths() <= right.get(j).getBirths()) {
	            list.set(k++, left.get(i++));
	        } else {
	            list.set(k++, right.get(j++));
	        }
	    }
	
	    while (i < left.size()) list.set(k++, left.get(i++));
	    while (j < right.size()) list.set(k++, right.get(j++));
	}
	
	public static void writeCSV(String file, List<DayBirth> data){
		try(PrintWriter pw = new PrintWriter (new FileWriter(file))){
			pw.println("year,month,day,gender,births");
			
			for(DayBirth d : data){
				pw.println(d.toString());
			}
		}
		catch (IOException e) {
        System.err.println("Error writing CSV: " + e.getMessage());
        e.printStackTrace();
	}
	}
	
	public static void quickSort(int []arr, int left, int right){
		if(left<right){
			int pivotIndex = partition(arr, left, right);
			quickSort(arr, left, pivotIndex - 1);
			quickSort(arr, pivotIndex+1, right);
		}
	}
	
	public static int partition(int[] arr, int left, int right){
		int pivot = arr[right];
		int i = left-1;
		
		for(int j = left; j<right; j++){
			if (arr[j]<=pivot){
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		
		
		int temp = arr[i+1];
		arr[i+1] = arr[right];
		arr[right] = temp;
		
		return i+1;
	}
	
}




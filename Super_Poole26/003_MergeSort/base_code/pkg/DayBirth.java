package pkg;
import java.util.Scanner;
import java.util.Random;
import java.io.*;
import java.util.*;


public class DayBirth{
		int year;
		int month;
		int day; 
		String gender;
		int births;
	
	public DayBirth(int year, int month, int day, String gender, int births){
		this.year = year;
		this.month = month;
		this.day = day;
		this.gender = gender;
		this.births = births;
	}
	
	public int getDay(){
		return day;
	}
	
	public int getMonth(){
		return month;
	}
	
	public String getGender(){
		return gender;
	}
	
	public int getYear(){
		return year;
	}
	
	public int getBirths(){
		return births;
	}
	
	public String toString(){
		return year+ "," + month + "," + day+","+gender+","+births;
	}
	
	public static List<DayBirth> readCSV(String fileName){
		List<DayBirth> data = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
			String line = br.readLine();
			while((line = br.readLine()) != null){
				line = line.trim();
				if(line.isEmpty())
					continue;
				
				String[] parts = line.split(",");
				if (parts.length != 5)
					continue;
				try{
					int year = Integer.parseInt(parts[0]);
					int month = Integer.parseInt(parts[1]);
					int day;
					if(parts[2].equalsIgnoreCase("null"))
						day = 0;
					else
						day = Integer.parseInt(parts[4]);
						
					String gender = parts[3];
					int births = Integer.parseInt(parts[4]);
					data.add(new DayBirth(year, month, day, gender, births));
				}
				catch(NumberFormatException e){
					System.err.println("Skipping invalid line: " + line);
				}
			}
		}
		catch (IOException e) {
		        System.err.println("Error reading CSV: " + e.getMessage());
		        e.printStackTrace();
    		}
		return data;
	}

}

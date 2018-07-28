package frontframe;

import java.util.*;
import java.text.DateFormatSymbols;

/**
 * author
 */
 
public class StartTest {

	public static void main(String[] args) {		
		showString();
		//arraySort();
		CalendarTest();
	}

	public static void showString()
	{
		String greeting = "Hello";
		String str = "World!";
		int strlen = greeting.length();
		int cpCount = greeting.codePointCount(0, strlen);
		System.out.println("code point lenght is " + cpCount);
		
		StringBuilder builder = new StringBuilder();
		builder.append(greeting);
		builder.append(str);
		System.out.println(builder.toString());
		
		
	}
	
	public static void arraySort()
	{
		Scanner in = new Scanner(System.in);
		
		System.out.print("number count:");
		int k = in.nextInt();
		
		System.out.println("Maxnumber:");
		int n = in.nextInt();
		
		in.close();
		
		int[] numbers = new int[k];
		for(int i = 0; i < numbers.length; i++)
			numbers[i] = i + 1;
		
		int[] result = new int[n];
		for(int j = 0; j < result.length; j++)
		{
			int index = (int)(Math.random()* n);
			
			result[j] = numbers[index];
			numbers[index] = numbers[n -1];
			n--;
		}
		
		for(int index: result)
			System.out.print(index +" ");
		Arrays.sort(result);
		
		System.out.println("\r\nsort result: ");
		
		for(int index: result)
			System.out.print(index + " ");
	}
	
	/*
	 * calculate day, weekday
	 */
	public static void CalendarTest()
	{
		GregorianCalendar curdate = new GregorianCalendar();
		
		int today = curdate.get(Calendar.DAY_OF_MONTH);
		int month = curdate.get(Calendar.MONTH);
		
		curdate.set(Calendar.DAY_OF_MONTH, 1);
		
		int weekday = curdate.get(Calendar.DAY_OF_WEEK);
		
		int firstDay = curdate.getFirstDayOfWeek();
		
		int indent = 0;
		while(weekday != firstDay)
		{
			indent++;
			curdate.add(Calendar.DAY_OF_MONTH, -1);
			weekday =  curdate.get(Calendar.DAY_OF_WEEK);
		}
		
		String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();
		do 
		{
			System.out.printf("%4s", weekdayNames[weekday]);
			curdate.add(Calendar.DAY_OF_MONTH, 1);
			weekday = curdate.get(Calendar.DAY_OF_WEEK);
		}
		while(weekday != firstDay);
		
		System.out.println();
		
		for(int i = 0; i <= indent; i++)
			System.out.print(" ");
		
		curdate.set(Calendar.DAY_OF_MONTH, 1);
		do 
		{
			int day = curdate.get(Calendar.DAY_OF_MONTH);
			System.out.printf("%3d", day);
			
			if(day == today) System.out.print("*");
			else System.out.print(" ");
			
			curdate.add(Calendar.DAY_OF_MONTH, 1);
			weekday = curdate.get(Calendar.DAY_OF_WEEK);
			
			if(weekday == firstDay) System.out.println();
		}
		while (curdate.get(Calendar.MONTH) == month);
		
		if(weekday != firstDay) System.out.println();		
	}
}

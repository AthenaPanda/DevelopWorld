package frontframe;

import java.util.*;

/*
 * class Employee sample
 */
public class Employee implements Comparable<Employee>
{
	private static int nextId ;
	
	private String name ="";
	private double salary;
	private Date hireDay;
	private int id;

	static 
	{
		Random generator = new Random();
		nextId = generator.nextInt(10);
	}
	
	public Employee(String n, double s, int year, int month, int day)
	{
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);
		hireDay = calendar.getTime();
		id = 0;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getSalary()
	{
		return salary;
	}
	
	public Date getHireDay()
	{
		return hireDay;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void raiseSalary(double bypercent)
	{
		double raise = salary * bypercent/100;
		salary += raise;
	}
	
	public  void setId()
	{
		id = nextId;
		nextId++;
	}
	
	public static int getNextId()
	{
		return nextId;
	}
	
	/*
	 * Compares employees by salary
	 * @param other other Employee Object
	 * @return a negative value if this employee has a lower salary than 
	 * other object, 0 if the salaries are the same, a positive value oyherwise
	 */
	public int compareTo(Employee other)
	{
		return Double.compare(salary, other.salary);
	}
}

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
		employeeCode();
		
		analyzerTest();	
		RefleationTest(args);
		copyOfTest();
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
	
	public static void employeeCode()
	{
		Manager boss  = new Manager("Carl Cracker", 7500, 1987, 12, 15);
		boss.setBonus(500);
		
		Employee[] staff = new Employee[3];
		staff[0] = boss;
		staff[1] = new Employee("Harry Hacker", 5000, 1989, 10, 9);
		staff[2] = new Employee("Tony Tester", 4000, 1990, 3, 27);
		
		for(Employee e: staff)
		  e.raiseSalary(5);
		
		Arrays.sort(staff);
		for(Employee e: staff) 
		{
			e.setId();
			System.out.println("name=" + e.getName()+ " , salary=" + e.getSalary() + " , hireDay=" + e.getHireDay()
								+ ", id=" + e.getId());
		}
		
		int n = Employee.getNextId();
		System.out.println("The next id=" + n);
	}
	
	/*
	 * use reflection to print all features of a class
	 */
	public static void RefleationTest(String[] args)
	{
		String name;
		if(args.length > 0) name = args[0];
		else
		{
			Scanner in = new Scanner(System.in);
			System.out.println("Enter class name: ");
			name = in.next();
		}
		
		try 
		{
			Class cl = Class.forName(name);
			Class supercl =cl.getSuperclass();
			String modifiers = Modifier.toString(cl.getModifiers());
			if(modifiers.length() > 0) System.out.print(modifiers + " ");
			System.out.println("class" + name);
			if(supercl != null && supercl != Object.class)  System.out.print("extends" + supercl.getName());
			
			System.out.print("\n{\n");
			printConstructors(cl);
			System.out.println();
			printMethods(cl);
			System.out.println();
			printFields(cl);
			System.out.println("}");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		//System.exit(0);
	}
	
	/*
	 * print all constructors of a class
	 */
	public static  void printConstructors(Class cl)
	{
		Constructor[] constructors = cl.getDeclaredConstructors();
		for(Constructor c:constructors)
		{
			String name = cl.getName();
			System.out.println(" ");
			String modifiers = Modifier.toString(cl.getModifiers());
			if(modifiers.length() > 0) System.out.print(modifiers + " ");
			System.out.print(name +"(");
			
			Class[] paramTypes = c.getParameterTypes();
			for(int j = 0; j < paramTypes.length; j++)
			{
				if(j > 0) System.out.println(", ");
				System.out.print(paramTypes[j].getName());
			}
			System.out.println(");");
		}
	}
	
	/*
	 * print all methods of a class
	 */
	public static void printMethods(Class cl)
	{
		Method[] methods = cl.getDeclaredMethods();
		for(Method m: methods)
		{
			Class retType = m.getReturnType();
			String name =m.getName();
			
			System.out.print(" ");
			String modifiers = Modifier.toString(m.getModifiers());
			if(modifiers.length() > 0) System.out.print(modifiers + " ");
			System.out.print(retType.getName() + " " + name + "(");
			
			Class[] paramTypes = m.getParameterTypes();
			for(int j = 0; j < paramTypes.length; j++)
			{
				if(j > 0) System.out.print(", ");
				System.out.print(paramTypes[j].getName());
			}
			System.out.println(");");
		}
	}
	
	/*
	 * print all fields of a class
	 */
	public static void printFields(Class cl)
	{
		Field[] fields =cl.getDeclaredFields();
		
		for(Field f: fields)
		{
			Class type = f.getType();
			String name = f.getName();
			System.out.print(" ");
			String modifiers = Modifier.toString(f.getModifiers());
			if(modifiers.length() >0) System.out.print(modifiers + " ");
			System.out.println(type.getName() + " "+ name + ";");
		}
	}
	
	public static void analyzerTest()
	{
		ArrayList<Integer> squares = new ArrayList<>();
		for(int i = 0; i <= 5; i++) 
			squares.add(i*i);
		
		System.out.println(new ObjectAnalyzer().toString(squares));
	}
	
	public static void copyOfTest()
	{
		int[]  a = {1, 2, 3};
		a = (int[]) goodCopyOf(a, 10);
		System.out.println(Arrays.toString(a));
		
		String[] b = {"Tom", "Dick", "Harry"};
		b = (String[])goodCopyOf(b, 10);
		System.out.println(Arrays.toString(b));		
	}
	
	/*
	 * This method grows an array by allocating a new array of the same type and
	 * copying all elments
	 * @param a the array to grow. This can be an object array or a primitive type array
	 * @return a larger array that contains all elments of a.
	 */
	public static Object goodCopyOf(Object a, int newLength)
	{
		Class class1 = a.getClass();
		if(!class1.isArray()) return null;
		Class componentType = class1.getComponentType();
		int length = Array.getLength(a);
		Object newArray = Array.newInstance(componentType, newLength);
		System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
		return newArray;
		
	}
}

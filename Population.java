import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *	Population.java uses Bubble, Selection, Insertion, and Merge sort 
 *  to sort US cities by population, name, and populations within a certain
 *  city or state. The user can select one of 6 options: sorting cities
 *  by ascending or descending population, ascending or descending name, 
 *  cities with the same name by population, and cities in a particular 
 *  state by population. 
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Sehej Singh
 *	@since	January 9, 2023
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	// measures time taken for each sort
	private long time;
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	// constructor
	public Population()
	{
		time = 0;
		cities = new ArrayList<City>();
	}
	
	// calls run method
	public static void main(String[] args)
	{
		Population p = new Population();
		p.run();
	}
	
	/**	
	 *  Description:
	 *  Calls methods to set up program (printIntroduction(), openFile() to 
	 *  load city contents), then asks for user input and calls methods
	 *  for sorting cities accordingly.
	 */
	public void run()
	{
		Prompt p = new Prompt();
		printIntroduction();
		printMenu();
		openFile();
		int selection = 0;
		boolean quit = false;
		while(quit==false)
		{
			do
			{
				selection = p.getInt("Enter Selection");
				if(selection==1)
				{
					System.out.println(String.format("\n%-25s %-22s %-15s"+
					" %s", "State", "City", "City Type", "Population"));
					populationAscending();
					for(int i = 0; i < 50; i++)
						if(i<9)
							System.out.println(i+1 + ": " + cities.get(i));
						else
							System.out.println(i+1 + ":" + cities.get(i));
						
					System.out.println("\n\nElapsed time " + time + " milliseconds\n\n");
				}
				else if(selection==2)
				{
					System.out.println(String.format("\n%-25s %-22s %-15s %s",
					 "State", "City", "City Type", "Population"));
					populationDescending();
					for(int i = 0; i < 50; i++)
						if(i<9)
							System.out.println(i+1 + ": " + cities.get(i));
						else
							System.out.println(i+1 + ":" + cities.get(i));
						
					System.out.println("\n\nElapsed time " + time +
					 " milliseconds\n\n");
				}
				else if(selection==3)
				{
					System.out.println(String.format("\n%-25s %-22s %-15s %s", 
					"State", "City", "City Type", "Population"));
					nameAscending();
					for(int i = 0; i < 50; i++)
						if(i<9)
							System.out.println(i+1 + ": " + cities.get(i));
						else
							System.out.println(i+1 + ":" + cities.get(i));
						
					System.out.println("\n\nElapsed time " + time + 
					" milliseconds\n\n");
				}
				else if(selection==4)
				{
					System.out.println(String.format("\n%-25s %-22s %-15s %s",
					 "State", "City", "City Type", "Population"));
					nameDescending();
					for(int i = 0; i < 50; i++)
						if(i<9)
							System.out.println(i+1 + ": " + cities.get(i));
						else
							System.out.println(i+1 + ":" + cities.get(i));
						
					System.out.println("\n\nElapsed time " + time + 
					" milliseconds\n\n");
				}
				else if(selection==6)
				{
					boolean valid = false;
					String enteredName = "";
					// ask for city name
					do
					{
						enteredName = p.getString("Please enter city name ");
						for(City c : cities)
						{
							if(c.getCityName().equals(enteredName))
								valid = true;
						}
					
					} while(!valid);
					System.out.println(String.format("\n%-25s %-22s %-15s %s",
					 "State", "City", "City Type", "Population"));
					chosenCity(enteredName);
				}
				else if(selection==5)
				{
					boolean valid = false;
					String enteredName = "";
					// ask for city name
					do
					{
						enteredName = p.getString("Please enter state name ");
						for(City c : cities)
						{
							if(c.getStateName().equals(enteredName))
								valid = true;
						}
					
					} while(!valid);
					System.out.println(String.format("\n%-25s %-22s %-15s %s",
					 "State", "City", "City Type", "Population"));
					chosenState(enteredName);
				}
				else if(selection==9)
				{
					System.out.println("Thanks for using Population!");
					quit = true;
					System.exit(1);
				}
			
			}
			while(selection<1 || (selection>6 && selection<9) || selection>9);
		}
	}
	
	/**	
	 *  Description:
	 *  Runs through each line in data file and saves contents to add
	 *  new City object to ArrayList cities. 
	 */
	public void openFile()
	{
		String stateName = "";
		String cityName = ""; 
		String cityType = "";
		int population = 0;
		FileUtils f = new FileUtils();	
		Scanner scan = f.openToRead(DATA_FILE);
		while(scan.hasNext())
		{
			stateName = scan.next();
			cityName = scan.next();
			cityType = scan.next();
			population = Integer.parseInt(scan.next());
			cities.add(new City(cityName, cityType, stateName, population));
		}
	}
	
	/**	
	 *  Description:
	 *  Uses selection sort method (copied from SortMethods.java) to 
	 *  sort populations in ascending order (for 2nd option in menu).
	 *  Records time before and after, sets time FV accordingly. 
	 */
	public void populationAscending()
	{
		long startMillisec = System.currentTimeMillis();
		for(int n = 31765; n > 1; n--)
		{
			int max = 0;
			for(int i = 1; i < n; i++)
			{
				if(cities.get(i).compareTo(cities.get(max)) > 0)
					max = i;
			}
			
			City arrTemp = cities.get(max);
			cities.set(max, cities.get(n-1));
			cities.set(n-1, arrTemp);
		}
		long endMillisec = System.currentTimeMillis();
		time = endMillisec - startMillisec;
	}
	
	/**	
	 *  Description:
	 *  Uses merge sort method (copied from SortMethods.java) to 
	 *  sort populations in descending order (for 3rd option in menu).
	 *  Records time before and after, sets time FV accordingly. 
	 */
	public void populationDescending()
	{
		long startMillisec = System.currentTimeMillis();
		
		cities = mergeSort(cities, 31765);
		
		long endMillisec = System.currentTimeMillis();
		time = endMillisec - startMillisec;
	}
	
	// merge sort method for populationDescending()
	public List<City> mergeSort(List<City> arr, int n)
	{
		n = arr.size();
		if (n < 2)
			return arr;
		
		int mid = n / 2;
		List<City> l = new ArrayList<City>(mid);
		List<City> r = new ArrayList<City>(n - mid);

		for (int i = 0; i < mid; i++)
			l.add(arr.get(i));
			
		for (int i = mid; i < n; i++) 
			r.add(arr.get(i));
		 
		mergeSort(l, mid);
		mergeSort(r, n - mid);

		merge1(arr, l, r, mid, n - mid);
		
		return arr;
		
	}
	
	// merge method for merge sort method (for populationDescending())
	public void merge1(List<City> arr, List<City> l, List<City> r, int left, int right) 
	{
		int i = 0, j = 0, k = 0;
		while (i < left && j < right) 
		{
			if (l.get(i).compareTo(r.get(j)) > 0) 
			{
				arr.set(k++, l.get(i++));
			}
			else 
			{
				arr.set(k++, r.get(j++));
			}
		}
		while (i < left) 
		{
			arr.set((k++), l.get(i++));
		}
		while (j < right) 
		{
			arr.set((k++), r.get(j++));
		}
	}
	
	/**	
	 *  Description:
	 *  Uses insertion sort method (copied from SortMethods.java) to 
	 *  sort names in ascending order (for 4rd option in menu).
	 *  Records time before and after, sets time FV accordingly. 
	 */
	public void nameAscending()
	{
		long startMillisec = System.currentTimeMillis();
		for(int n = 1; n < 31765; n++)
		{
			// Save the next element to be inserted
			City arrTemp = cities.get(n);
			
			int i = n;
			while(i > 0 && (arrTemp.getCityName().compareTo(cities.get(i-1).getCityName()) < 0))
			{
				cities.set(i, cities.get(i-1));
				i--;
			}
			
			cities.set(i, arrTemp);
		}
		long endMillisec = System.currentTimeMillis();
		time = endMillisec - startMillisec;
		
	}
	
	/**	
	 *  Description:
	 *  Uses merge sort method (copied from SortMethods.java) to 
	 *  sort names in descending order (for 4rd option in menu).
	 *  Records time before and after, sets time FV accordingly. 
	 */
	public void nameDescending()
	{
		long startMillisec = System.currentTimeMillis();
		
		cities = mergeSort2(cities, cities.size());
		
		long endMillisec = System.currentTimeMillis();
		time = endMillisec - startMillisec;
	}
	
	// merge sort method for nameDescending()
	public List<City> mergeSort2(List<City> arr, int n)
	{
		n = arr.size();
		if (n < 2)
			return arr;
		
		int mid = n / 2;
		List<City> l = new ArrayList<City>(mid);
		List<City> r = new ArrayList<City>(n - mid);

		for (int i = 0; i < mid; i++)
			l.add(arr.get(i));
			
		for (int i = mid; i < n; i++) 
			r.add(arr.get(i));
		 
		mergeSort2(l, mid);
		mergeSort2(r, n - mid);

		merge2(arr, l, r, mid, n - mid);
		
		return arr;
		
	}
	
	// merge method for merge sort method (for nameDescending())
	public void merge2(List<City> arr, List<City> l, List<City> r, int left, int right) 
	{
		int i = 0, j = 0, k = 0;
		while (i < left && j < right) 
		{
			if (l.get(i).getCityName().compareTo(r.get(j).getCityName()) > 0) 
			{
				arr.set(k++, l.get(i++));
			}
			else 
			{
				arr.set(k++, r.get(j++));
			}
		}
		while (i < left) 
		{
			arr.set((k++), l.get(i++));
		}
		while (j < right) 
		{
			arr.set((k++), r.get(j++));
		}
	}
	
	/**	
	 *  Description:
	 *  Copies all cities from data file matching with state name entered 
	 *  by user into a new ArrayList. Uses merge sort method (copied 
	 *  from SortMethods.java) to sort populations in state in ascending order 
	 *  (for 5th option in menu). Records time before and after, sets 
	 *  time FV accordingly. Prints results. 
	 */
	public void chosenState(String enteredName)
	{
		long startMillisec = System.currentTimeMillis();
		List<City> citiesInState = new ArrayList<City>();
		for(int i = 0; i < 31765; i++)
		{
			if(cities.get(i).getStateName().equals(enteredName))
				citiesInState.add(cities.get(i));
		}
		
		citiesInState = mergeSort(citiesInState, citiesInState.size());
		
		long endMillisec = System.currentTimeMillis();
		time = endMillisec - startMillisec;
		
		if(citiesInState.size()>50)
		{
			for(int i = 0; i < 50; i++)
				if(i<9)
							System.out.println(i+1 + ": " + citiesInState.get(i));
						else
							System.out.println(i+1 + ":" + citiesInState.get(i));
		}
		else 
		{
			for(int i = 0; i < citiesInState.size(); i++)
			if(i<9)
						System.out.println(i+1 + ": " + citiesInState.get(i));
					else
						System.out.println(i+1 + ":" + citiesInState.get(i));
		}
		
		System.out.println("\n\nElapsed time " + time + " milliseconds\n\n");
		// loop through cities
		// if getStateName() matches name typed in by user, add to new ArrayList
		// sort new ArrayList by population (with selection or insertion)
		// print first 50 (or less)
	}
	
	/**	
	 *  Description:
	 *  Copies all cities from data file matching with city name entered 
	 *  by user into a new ArrayList. Uses merge sort method (copied 
	 *  from SortMethods.java) to sort populations of cities in ascending order 
	 *  (for 6th option in menu). Records time before and after, sets 
	 *  time FV accordingly. Prints results. 
	 */
	public void chosenCity(String enteredName)
	{
		long startMillisec = System.currentTimeMillis();
		List<City> enteredCity = new ArrayList<City>();
		for(int i = 0; i < 31765; i++)
		{
			if(cities.get(i).getCityName().equals(enteredName))
				enteredCity.add(cities.get(i));
		}
		
		enteredCity = mergeSort(enteredCity, enteredCity.size());
		
		long endMillisec = System.currentTimeMillis();
		time = endMillisec - startMillisec;
		
		for(int i = 0; i < enteredCity.size(); i++)
			if(i<9)
						System.out.println(i+1 + ": " + enteredCity.get(i));
					else
						System.out.println(i+1 + ":" + enteredCity.get(i));
						
		System.out.println("\n\nElapsed time " + time + " milliseconds\n\n");
		
		// loop through cities
		// if getCityName() matches name typed in by user, add to new ArrayList
		// print all items in new ArrayList
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	
}

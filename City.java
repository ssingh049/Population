/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Sehej Singh
 *	@since	January 9, 2023
 */
public class City implements Comparable<City> {
	
	// fields
	private String cityName;
	private String cityType;
	private String stateName;
	private int population;
	
	// constructor
	public City(String cityNameIn, String cityTypeIn, String stateNameIn, int populationIn)
	{
		cityName = cityNameIn;
		cityType = cityTypeIn;
		stateName = stateNameIn;
		population = populationIn;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other)
	{
		if(this.population != other.population)	
			return this.population - other.population;
		else if(!this.stateName.equals(other.stateName)) // needs to be fixed so 
														 // its performing same function 
														 // as String class's equals()
														 // notfrom this class
			return this.stateName.compareTo(other.stateName); // same here ??
		else
			return this.cityName.compareTo(other.cityName);   // same here ??
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	//public boolean equals(Object other)
	//{
	//	return (this.cityName.equals((City)other.cityName) 
	//		&& this.stateName.equals((City)other.stateName))
	//}
	
	/**	Accessor methods */
	public String getCityName()
	{
		return cityName;
	}
		
	public String getStateName()
	{
		return stateName;
	}
		
	public int getPopulation()
	{
		return population;
	}
		
	public String getCityType()
	{
		return cityType;
	}
		
	public void setCityName(String nameIn)
	{
		cityName = nameIn;
	}
		
	public void setStateName(String stateNameIn)
	{
		stateName = stateNameIn;
	}
		
	public void setPopulation(int populationIn)
	{
		population = populationIn;
	}
		
	public void setCityType(String cityTypeIn)
	{
		cityType = cityTypeIn;
	}
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", stateName, cityName, cityType,
						population);
	}
}

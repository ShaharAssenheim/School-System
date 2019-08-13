package Entity;

import java.util.ArrayList;

/**
 * The Class TeachingUnit to create Teaching Unit instance.
 */
public class TeachingUnit 
{

	/** The unit. */
	private ArrayList<String> unit = new ArrayList<String>();
	
	
	/**
	 * Instantiates a new teaching unit.
	 *
	 * @param tunit the teaching unit
	 */
	public TeachingUnit(ArrayList<String> tunit) 
	{
		this.unit=tunit;
	}
	
	/**
	 * Instantiates a new teaching unit.
	 */
	public TeachingUnit() 
	{
		
	}


	/**
	 * Gets the teaching unit.
	 *
	 * @return the teaching unit
	 */
	public ArrayList<String> getUnit() {
		return unit;
	}


	/**
	 * Sets the teaching unit.
	 *
	 * @param unit the new teaching unit
	 */
	public void setUnit(ArrayList<String> unit) {
		this.unit = unit;
	}
}

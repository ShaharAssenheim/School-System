package Entity;

/**
 * The Class Teacher to create Teacher instance.
 */
public class Teacher extends User
{
	
	/** The Max hour. */
	private String MaxHour;
	
	/** The Actual hours. */
	private String ActualHours;

	/**
	 * Instantiates a new teacher.
	 *
	 * @param uid the user id
	 * @param rid the role id
	 * @param firstname the first name
	 * @param lastname the last name
	 * @param birthdate the birth date
	 * @param password the password
	 * @param ip the IP
	 * @param phonenumber the phone number
	 * @param maxhour the max hour
	 */
	public Teacher(String uid, String rid, String firstname, String lastname, String birthdate, String password,String ip, String phonenumber,String maxhour)
	{
		super(uid, rid, firstname, lastname, birthdate, password, ip, phonenumber);
		this.MaxHour=maxhour;
	}

	/**
	 * Gets the max hour.
	 *
	 * @return the max hour
	 */
	public String getMaxHour() 
	{
		return MaxHour;
	}

	/**
	 * Sets the max hour.
	 *
	 * @param maxHour the new max hour
	 */
	public void setMaxHour(String maxHour) 
	{
		MaxHour = maxHour;
	}

	/**
	 * Gets the actual hours.
	 *
	 * @return the actual hours
	 */
	public String getActualHours() {
		return ActualHours;
	}

	/**
	 * Sets the actual hours.
	 *
	 * @param actualHours the new actual hours
	 */
	public void setActualHours(String actualHours) {
		ActualHours = actualHours;
	}

}

package Entity;

/**
 * The Class StdInfo.
 */
public class StdInfo
{
	
	/** The Student ID. */
	private String StdID;
	
	/** The First name. */
	private String FirstName;
	
	/** The Last name. */
	private String LastName;
	
	/** The Phone number. */
	private String PhoneNumber;
	
	/** The Address. */
	private String Address;
	
	/** The Birthdate. */
	private String Birthdate;
	
	/** The Class name. */
	private String ClassName;
	
	/**
	 * Instantiates a new student info.
	 *
	 * @param ID the student id
	 * @param FName the first name
	 * @param LName the last name
	 * @param PhoneNum the phone number
	 * @param Add the address
	 * @param BDate the birth date
	 * @param ClassN the class name
	 */
	public StdInfo(String ID, String FName, String LName, String PhoneNum, String Add, String BDate, String ClassN)
	{
		StdID = ID;
		FirstName = FName;
		LastName = LName;
		PhoneNumber = PhoneNum;
		Address = Add;
		Birthdate = BDate;
		ClassName = ClassN;
	}
	
	/**
	 * Instantiates a new student info.
	 */
	public StdInfo()
	{
		
	}
	
	/**
	 * Gets the student ID.
	 *
	 * @return the student ID
	 */
	public String getStdID()
	{
		return StdID;
	}
	
	/**
	 * Sets the student ID.
	 *
	 * @param stdID the new student ID
	 */
	public void setStdID(String stdID)
	{
		StdID = stdID;
	}
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName()
	{
		return FirstName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName)
	{
		FirstName = firstName;
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName()
	{
		return LastName;
	}
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName)
	{
		LastName = lastName;
	}
	
	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber()
	{
		return PhoneNumber;
	}
	
	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		PhoneNumber = phoneNumber;
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress()
	{
		return Address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address)
	{
		Address = address;
	}
	
	/**
	 * Gets the birthdate.
	 *
	 * @return the birthdate
	 */
	public String getBirthdate()
	{
		return Birthdate;
	}
	
	/**
	 * Sets the birthdate.
	 *
	 * @param birthdate the new birthdate
	 */
	public void setBirthdate(String birthdate) 
	{
		Birthdate = birthdate;
	}
	
	/**
	 * Gets the class name.
	 *
	 * @return the class name
	 */
	public String getClassName() 
	{
		return ClassName;
	}
	
	/**
	 * Sets the class name.
	 *
	 * @param className the new class name
	 */
	public void setClassName(String className)
	{
		ClassName = className;
	}
	
	
}

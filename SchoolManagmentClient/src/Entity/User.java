package Entity;

/**
 * The Class User to create User instance.
 */
public class User 
{
	
	/** The user id. */
	private String UID;
	
	/** The role id. */
	private String RID;
	
	/** The First name. */
	private String FirstName;
	
	/** The Last name. */
	private String LastName;
	
	/** The Birth date. */
	private String BirthDate;
	
	/** The Password. */
	private String Password;
	
	/** The IP. */
	private String IP;
	
	/** The Phone number. */
	private String PhoneNumber;
	
	/**
	 * Instantiates a new user.
	 *
	 * @param uid the user id
	 * @param rid the role id
	 * @param firstname the first name
	 * @param lastname the last name
	 * @param birthdate the birth date
	 * @param password the password
	 * @param ip the IP
	 * @param phonenumber the phone number
	 */
	public User(String uid, String rid, String firstname,String lastname, String birthdate, String password, String ip,String phonenumber)
	{
		this.UID=uid;
		this.RID=rid;
		this.FirstName=firstname;
		this.LastName=lastname;
		this.BirthDate=birthdate;
		this.Password=password;
		this.IP=ip;
		this.PhoneNumber=phonenumber;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUID() 
	{
		return UID;
	}

	/**
	 * Sets the user id.
	 *
	 * @param uID the new user id
	 */
	public void setUID(String uID) 
	{
		UID = uID;
	}

	/**
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public String getRID() 
	{
		return RID;
	}

	/**
	 * Sets the role id.
	 *
	 * @param rID the new role id
	 */
	public void setRID(String rID) 
	{
		RID = rID;
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
	 * Gets the birth date.
	 *
	 * @return the birth date
	 */
	public String getBirthDate() 
	{
		return BirthDate;
	}

	/**
	 * Sets the birth date.
	 *
	 * @param birthDate the new birth date
	 */
	public void setBirthDate(String birthDate) 
	{
		BirthDate = birthDate;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) 
	{
		Password = password;
	}

	/**
	 * Gets the IP.
	 *
	 * @return the IP
	 */
	public String getIP() 
	{
		return IP;
	}

	/**
	 * Sets the IP.
	 *
	 * @param iP the new IP
	 */
	public void setIP(String iP) 
	{
		IP = iP;
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

}

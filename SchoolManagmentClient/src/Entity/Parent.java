package Entity;

/**
 * The Class Parent to create Parent instance.
 */
public class Parent extends User
{
	
	/** The block status. */
	private String IsBlock;

	/**
	 * Instantiates a new parent.
	 *
	 * @param uid the user id
	 * @param rid the role id
	 * @param firstname the first name
	 * @param lastname the last name
	 * @param birthdate the birth date
	 * @param password the password
	 * @param ip the IP
	 * @param phonenumber the phone number
	 * @param block the block status
	 */
	public Parent(String uid, String rid, String firstname, String lastname, String birthdate, String password,String ip, String phonenumber,String block)
	{
		super(uid, rid, firstname, lastname, birthdate, password, ip, phonenumber);
		this.IsBlock=block;
		
		
	}

	/**
	 * Gets the block status.
	 *
	 * @return IsBlock the block status
	 */
	public String getIsBlock() 
	{
		return IsBlock;
	}

	/**
	 * Sets the block status.
	 *
	 * @param isBlock the new block status
	 */
	public void setIsBlock(String isBlock) 
	{
		IsBlock = isBlock;
	}
	

}

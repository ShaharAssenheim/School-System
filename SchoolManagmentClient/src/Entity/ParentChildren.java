package Entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * The Class ParentChildren to create Parent Children instance.
 */
public class ParentChildren
{
	
	/** The Child ID. */
	private final SimpleStringProperty ChildID;
	
	/** The Child name. */
	private final SimpleStringProperty ChildName;
	
	/** The Child class. */
	private final SimpleStringProperty ChildClass;
	
	/** The Child birth date. */
	private final SimpleStringProperty ChildBirthdate;
	
	/** The Child phone number. */
	private final SimpleStringProperty ChildPhoneNum;

	/**
	 * Instantiates a new parent children.
	 *
	 * @param ChildID the child ID
	 * @param ChildName the child name
	 * @param ChildClass the child class
	 * @param ChildBirthdate the child birth date
	 * @param ChildPhoneNum the child phone number
	 */
	public ParentChildren(String ChildID, String ChildName, String ChildClass, String ChildBirthdate, String ChildPhoneNum)
	{
		super();
		this.ChildID = new SimpleStringProperty(ChildID);
		this.ChildName = new SimpleStringProperty(ChildName);
		this.ChildClass = new SimpleStringProperty(ChildClass);
		this.ChildBirthdate = new SimpleStringProperty(ChildBirthdate);
		this.ChildPhoneNum = new SimpleStringProperty(ChildPhoneNum);
		;
	}

	/**
	 * Gets the child ID.
	 *
	 * @return the child ID
	 */
	public String getChildID()
	{
		return ChildID.get();
	}

	/**
	 * Gets the child name.
	 *
	 * @return the child name
	 */
	public String getChildName()
	{
		return ChildName.get();
	}

	/**
	 * Gets the child class.
	 *
	 * @return the child class
	 */
	public String getChildClass()
	{
		return ChildClass.get();
	}

	/**
	 * Gets the child birth date.
	 *
	 * @return the child birth date
	 */
	public String getChildBirthdate()
	{
		return ChildBirthdate.get();
	}

	/**
	 * Gets the child phone number.
	 *
	 * @return the child phone number
	 */
	public String getChildPhoneNum()
	{
		return ChildPhoneNum.get();
	}
}

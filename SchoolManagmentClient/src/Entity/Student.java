package Entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * The Class Student to create Student instance.
 */
public class Student {
	
	/** The Student ID. */
	private final SimpleStringProperty StIDCol;
	
	/** The First name. */
	private final SimpleStringProperty FirstNameCol;
	
	/** The Last name. */
	private final SimpleStringProperty LastNameCol;
	
	/** The Birth date. */
	private final SimpleStringProperty BirthCol;
	
	/** The Phone number. */
	private final SimpleStringProperty PNumCol;
	
	/** The Address. */
	private final SimpleStringProperty AddrCol;
	
	/** The Class name. */
	private final SimpleStringProperty ClNameCol;

	/**
	 * Instantiates a new student.
	 *
	 * @param StIDCol the student ID
	 * @param FirstNameCol the first name
	 * @param LastNameCol the last name
	 * @param BirthCol the birth date
	 * @param PNumCol the phone number
	 * @param AddrCol the address
	 * @param ClNameCol the class name
	 */
	public Student(String StIDCol, String FirstNameCol, String LastNameCol, String BirthCol,  String PNumCol, String AddrCol, String ClNameCol) {
		super();
		this.StIDCol = new SimpleStringProperty(StIDCol);
		this.FirstNameCol = new SimpleStringProperty(FirstNameCol);
		this.LastNameCol = new SimpleStringProperty(LastNameCol);
		this.BirthCol = new SimpleStringProperty(BirthCol);
		this.PNumCol = new SimpleStringProperty(PNumCol);
		this.AddrCol = new SimpleStringProperty(AddrCol);
		this.ClNameCol = new SimpleStringProperty(ClNameCol);
		;
		
		
	}
	
	/**
	 * Gets the student ID.
	 *
	 * @return the student ID
	 */
	public String getStIDCol() {
		return StIDCol.get();
	}
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstNameCol() {
		return FirstNameCol.get();
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastNameCol() {
		return LastNameCol.get();
	}
	
	/**
	 * Gets the birth date.
	 *
	 * @return the birth date
	 */
	public String getBirthCol() {
		return BirthCol.get();
	}
	
	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPNumCol() {
		return PNumCol.get();
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddrCol() {
		return AddrCol.get();
	}
	
	/**
	 * Gets the class name.
	 *
	 * @return the class name
	 */
	public String getClNameCol() {
		return ClNameCol.get();
	}
	
		
}

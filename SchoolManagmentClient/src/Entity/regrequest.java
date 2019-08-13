package Entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * The Class regrequest to create registration request instance.
 */
public class regrequest {
	
	/** The Request ID. */
	private final SimpleStringProperty RequestCol;
	
	/** The student ID. */
	private final SimpleStringProperty Stidcol;
	
	/** The First name. */
	private final SimpleStringProperty Firstnamecol;
	
	/** The Last name. */
	private final SimpleStringProperty Lastnamecol;
	
	/** The Course name. */
	private final SimpleStringProperty Coursecol;
	
	/** The Class name. */
	private final SimpleStringProperty Classcol;
	
	/** The request type. */
	private final SimpleStringProperty Typecol;
	
	/** The Status. */
	private final SimpleStringProperty Statuscol;

	/**
	 * Instantiates a new registration request.
	 *
	 * @param RequestCol the request ID
	 * @param Stidcol the student ID
	 * @param Firstnamecol the first name
	 * @param Lastnamecol the last name
	 * @param Coursecol the course name
	 * @param Classcol the class name
	 * @param Typecol the request type
	 * @param Statuscol the status
	 */
	public regrequest( String RequestCol, String Stidcol, String Firstnamecol, String Lastnamecol, String Coursecol,  String Classcol, String Typecol,  String Statuscol) 
	{
		super();
		this.RequestCol = new SimpleStringProperty(RequestCol);
		this.Stidcol = new SimpleStringProperty(Stidcol);
		this.Firstnamecol = new SimpleStringProperty(Firstnamecol);
		this.Lastnamecol = new SimpleStringProperty(Lastnamecol);
		this.Coursecol = new SimpleStringProperty(Coursecol);
		this.Classcol = new SimpleStringProperty(Classcol);
		this.Typecol = new SimpleStringProperty(Typecol);
		this.Statuscol = new SimpleStringProperty(Statuscol);
		;
	}

	/**
	 * Gets the student ID.
	 *
	 * @return the student ID
	 */
	public String getStidcol() {
		return Stidcol.get();
	}

	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstnamecol() {
		return Firstnamecol.get();
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastnamecol() {
		return Lastnamecol.get();
	}

	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCoursecol() {
		return Coursecol.get();
	}

	/**
	 * Gets the class name.
	 *
	 * @return the class name
	 */
	public String getClasscol() {
		return Classcol.get();
	}

	/**
	 * Gets the request type.
	 *
	 * @return the request type
	 */
	public String getTypecol() {
		return Typecol.get();
	}

	/**
	 * Gets the status.
	 *
	 * @return the status 
	 */
	public String getStatuscol() {
		return Statuscol.get();
	}
	
	/**
	 * Gets the request ID.
	 *
	 * @return the request ID
	 */
	public String getRequestCol() {
		return RequestCol.get();
	}	
}

package Entity;

import javafx.beans.property.SimpleStringProperty;


/**
 * The Class ChangeAppointList to create Change Appoint request instance.
 */
public class ChangeAppointList {
	
	/** The Request ID. */
	private final SimpleStringProperty RequestCol;
	
	/** The Teacher ID. */
	private final SimpleStringProperty TIDcol;
	
	/** The First name. */
	private final SimpleStringProperty Fnamecol;
	
	/** The Last name. */
	private final SimpleStringProperty Lnamecol;
	
	/** The Class name. */
	private final SimpleStringProperty Cnamecol;
	
	/** The Course name. */
	private final SimpleStringProperty CourseNAMEcol;
	
	/** The Teaching unit. */
	private final SimpleStringProperty TUnitCOL;
	
	/** The New teacher ID. */
	private final SimpleStringProperty NEWteachercol;
	
	/** The New status. */
	private final SimpleStringProperty Newstatuscol;

	/**
	 * Instantiates a new change appoint list.
	 *
	 * @param RequestCol the request ID
	 * @param TIDcol the Teacher ID
	 * @param Fnamecol the first name
	 * @param Lnamecol the last name
	 * @param Cnamecol the class name
	 * @param CourseNAMEcol the course name
	 * @param TUnitCOL the teaching unit
	 * @param NEWteachercol the New teacher ID
	 * @param Newstatuscol the new status
	 */
	public ChangeAppointList(String RequestCol, String TIDcol, String Fnamecol, String Lnamecol, String Cnamecol,  String CourseNAMEcol, String TUnitCOL, String NEWteachercol,String Newstatuscol) {
		super();
		this.RequestCol = new SimpleStringProperty(RequestCol);
		this.TIDcol = new SimpleStringProperty(TIDcol);
		this.Fnamecol = new SimpleStringProperty(Fnamecol);
		this.Lnamecol = new SimpleStringProperty(Lnamecol);
		this.Cnamecol = new SimpleStringProperty(Cnamecol);
		this.CourseNAMEcol = new SimpleStringProperty(CourseNAMEcol);
		this.TUnitCOL = new SimpleStringProperty(TUnitCOL);
		this.NEWteachercol = new SimpleStringProperty(NEWteachercol);
		this.Newstatuscol = new SimpleStringProperty(Newstatuscol);
		;
			
	}

	/**
	 * Gets the Teacher ID.
	 *
	 * @return the TI Teacher ID
	 */
	public String getTIDcol() {
		return TIDcol.get();
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFnamecol() {
		return Fnamecol.get();
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLnamecol() {
		return Lnamecol.get();
	}

	/**
	 * Gets the class name.
	 *
	 * @return the class name
	 */
	public String getCnamecol() {
		return Cnamecol.get();
	}

	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCourseNAMEcol() {
		return CourseNAMEcol.get();
	}

	/**
	 * Gets the teaching unit.
	 *
	 * @return the teaching unit
	 */
	public String getTUnitCOL() {
		return TUnitCOL.get();
	}

	/**
	 * Gets the new teacher ID.
	 *
	 * @return the new teacher ID
	 */
	public String getNEWteachercol() {
		return NEWteachercol.get();
	}

	/**
	 * Gets the new status.
	 *
	 * @return the new status
	 */
	public String getNewstatuscol() {
		return Newstatuscol.get();
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

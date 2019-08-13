package Entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * The Class Appointing to create Appointing instance.
 */
public class Appointing {
	
	/** The Class name. */
	private final SimpleStringProperty ClassCol;
	
	/** The Course name. */
	private final SimpleStringProperty CourseCol;
	
	/** The Teaching unit. */
	private final SimpleStringProperty TUCol;
	
	/** The WH name. */
	private final SimpleStringProperty WHCol;

	/**
	 * Instantiates a new appointing.
	 *
	 * @param ClassCol the class name
	 * @param CourseCol the course name
	 * @param TUCol the Teaching unit
	 * @param WHCol the WH name
	 */
	public Appointing(String ClassCol, String CourseCol, String TUCol, String WHCol) {
		super();
		this.ClassCol = new SimpleStringProperty(ClassCol);
		this.CourseCol = new SimpleStringProperty(CourseCol);
		this.TUCol = new SimpleStringProperty(TUCol);
		this.WHCol = new SimpleStringProperty(WHCol);
		;
		
		
		
	}
	
	/**
	 * Gets the class name.
	 *
	 * @return the class name
	 */
	public String getClassCol() {
		return ClassCol.get();
	}
	
	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCourseCol() {
		return CourseCol.get();
	}
	
	/**
	 * Gets the Teaching unit.
	 *
	 * @return the Teaching unit
	 */
	public String getTUCol() {
		return TUCol.get();
	}
	
	/**
	 * Gets the WH name.
	 *
	 * @return the WH name
	 */
	public String getWHCol() {
		return WHCol.get();
	}
	
}

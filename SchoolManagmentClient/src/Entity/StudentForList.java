package Entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * The Class StudentForList to create Student instance.
 */
public class StudentForList {
	
	/** The Student ID. */
	private final SimpleStringProperty StudentIDCol;
	
	/** The Student name. */
	private final SimpleStringProperty StudentNameCol;
	
	/** The Submission status. */
	private final SimpleStringProperty SubCol;

	/**
	 * Instantiates a new student for list.
	 *
	 * @param StudentIDCol the student ID
	 * @param StudentNameCol the student name
	 * @param SubCol the Submission status
	 */
	public StudentForList(String StudentIDCol, String StudentNameCol, String SubCol) {
		super();
		this.StudentIDCol = new SimpleStringProperty(StudentIDCol);
		this.StudentNameCol = new SimpleStringProperty(StudentNameCol);
		this.SubCol = new SimpleStringProperty(SubCol);
		;
		
		
	}
	
	/**
	 * Gets the student ID.
	 *
	 * @return the student ID
	 */
	public String getStudentIDCol() {
		return StudentIDCol.get();
	}
	
	/**
	 * Gets the student name.
	 *
	 * @return the student name
	 */
	public String getStudentNameCol() {
		return StudentNameCol.get();
	}
	
	/**
	 * Gets the Submission status.
	 *
	 * @return the Submission status
	 */
	public String getSubCol() {
		return SubCol.get();
	}
	
		
}

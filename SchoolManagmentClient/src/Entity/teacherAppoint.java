package Entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * The Class teacherAppoint to create teacher appointing request instance.
 */
public class teacherAppoint {
	
	/** The Teacher ID. */
	private final SimpleStringProperty TeacherIDcol;
	
	/** The Class ID. */
	private final SimpleStringProperty ClassIDcol;
	
	/** The Course ID. */
	private final SimpleStringProperty CourseIDcol;
	
	/** The teaching unit. */
	private final SimpleStringProperty TUDcol;
	
	/** The Work hours. */
	private final SimpleStringProperty WHsIDcol;

	/**
	 * Instantiates a new teacher appoint.
	 *
	 * @param TeacherIDcol the teacher ID
	 * @param ClassIDcol the class ID
	 * @param CourseIDcol the course ID
	 * @param TUDcol the teaching unit
	 * @param WHsIDcol the Work hours
	 */
	public teacherAppoint(String TeacherIDcol, String ClassIDcol, String CourseIDcol, String TUDcol,  String WHsIDcol) {
		super();
		this.TeacherIDcol = new SimpleStringProperty(TeacherIDcol);
		this.ClassIDcol = new SimpleStringProperty(ClassIDcol);
		this.CourseIDcol = new SimpleStringProperty(CourseIDcol);
		this.TUDcol = new SimpleStringProperty(TUDcol);
		this.WHsIDcol = new SimpleStringProperty(WHsIDcol);
		;
		
		
	}
	
	/**
	 * Gets the teacher Id
	 *
	 * @return the teacher Id
	 */
	public String getTeacherIDcol() {
		return TeacherIDcol.get();
	}
	
	/**
	 * Gets the class Id
	 *
	 * @return the class Id
	 */
	public String getClassIDcol() {
		return ClassIDcol.get();
	}
	
	/**
	 * Gets the course Id
	 *
	 * @return the course Id
	 */
	public String getCourseIDcol() {
		return CourseIDcol.get();
	}
	
	/**
	 * Gets the teaching unit.
	 *
	 * @return the teaching unit
	 */
	public String getTUDcol() {
		return TUDcol.get();
	}
	
	/**
	 * Gets the Work hours.
	 *
	 * @return the Work hours
	 */
	public String getWHsIDcol() {
		return WHsIDcol.get();
	}

	
		
}

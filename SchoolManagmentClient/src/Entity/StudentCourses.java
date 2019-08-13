package Entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * The Class StudentCourses to create Student Courses instance.
 */
public class StudentCourses
{
	
	/** The Course number. */
	private final SimpleStringProperty CourseNumColumn;
	
	/** The Course name. */
	private final SimpleStringProperty CourseNameColumn;
	
	/** The Week hours. */
	private final SimpleStringProperty WeekHoursColumn;
	
	/** The Teacher name. */
	private final SimpleStringProperty TeacherNameColumn;
	
	/** The Exceptional registration status. */
	private final SimpleStringProperty ExpRegColumn;

	/**
	 * Instantiates a new student courses.
	 *
	 * @param CourseNumColumn the course number
	 * @param CourseNameColumn the course name
	 * @param WeekHoursColumn the week hours
	 * @param TeacherNameColumn the teacher name
	 * @param ExpRegColumn the Exceptional registration status
	 */
	public StudentCourses(String CourseNumColumn, String CourseNameColumn, String WeekHoursColumn, String TeacherNameColumn, String ExpRegColumn)
	{
		super();
		this.CourseNumColumn = new SimpleStringProperty(CourseNumColumn);
		this.CourseNameColumn = new SimpleStringProperty(CourseNameColumn);
		this.WeekHoursColumn = new SimpleStringProperty(WeekHoursColumn);
		this.TeacherNameColumn = new SimpleStringProperty(TeacherNameColumn);
		this.ExpRegColumn = new SimpleStringProperty(ExpRegColumn);
		;
	}

	/**
	 * Gets the course number.
	 *
	 * @return the course number
	 */
	public String getCourseNumColumn()
	{
		return CourseNumColumn.get();
	}

	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCourseNameColumn()
	{
		return CourseNameColumn.get();
	}

	/**
	 * Gets the week hours.
	 *
	 * @return the week hours
	 */
	public String getWeekHoursColumn()
	{
		return WeekHoursColumn.get();
	}

	/**
	 * Gets the teacher name.
	 *
	 * @return the teacher name
	 */
	public String getTeacherNameColumn()
	{
		return TeacherNameColumn.get();
	}

	/**
	 * Gets the Exceptional registration status.
	 *
	 * @return the Exceptional registration status
	 */
	public String getExpRegColumn()
	{
		return ExpRegColumn.get();
	}
}

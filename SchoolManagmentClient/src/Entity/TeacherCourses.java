package Entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * The Class TeacherCourses to create Teacher Courses instance.
 */
public class TeacherCourses
{
	
	/** The Course number. */
	private final SimpleStringProperty CourseNumColumn;
	
	/** The Course name. */
	private final SimpleStringProperty CourseNameColumn;
	
	/** The Class name. */
	private final SimpleStringProperty ClassColumn;
	
	/** The Week hours. */
	private final SimpleStringProperty WeekHoursColumn;

	/**
	 * Instantiates a new teacher courses.
	 *
	 * @param CourseNumColumn the course number
	 * @param CourseNameColumn the course name
	 * @param ClassColumn the class name
	 * @param WeekHoursColumn the week hours
	 */
	public TeacherCourses(String CourseNumColumn, String CourseNameColumn, String ClassColumn, String WeekHoursColumn)
	{
		super();
		this.CourseNumColumn = new SimpleStringProperty(CourseNumColumn);
		this.CourseNameColumn = new SimpleStringProperty(CourseNameColumn);
		this.ClassColumn = new SimpleStringProperty(ClassColumn);
		this.WeekHoursColumn = new SimpleStringProperty(WeekHoursColumn);
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
	 * Gets the class name.
	 *
	 * @return the class name
	 */
	public String getClassColumn()
	{
		return ClassColumn.get();
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
}

package Entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * The Class Courses.
 */
public class Courses 
{
	
	/** The Course number column. */
	private final SimpleStringProperty CourseNumColumn;
	
	/** The Course name column. */
	private final SimpleStringProperty CourseNColumn;
	
	/** The Teaching unit column. */
	private final SimpleStringProperty TeachingUnitColumn;
	
	/** The Week hours column. */
	private final SimpleStringProperty WeekHoursColumn;
	
	/** The Pre-courses column. */
	private final SimpleStringProperty PreCoursesColumn;
	

	/**
	 * Instantiates a new courses.
	 *
	 * @param CourseNumColumn the course num column
	 * @param CourseNColumn the course N column
	 * @param TeachingUnitColumn the teaching unit column
	 * @param WeekHoursColumn the week hours column
	 * @param PreCoursesColumn the pre courses column
	 */
	public Courses(String CourseNumColumn, String CourseNColumn, String TeachingUnitColumn, String WeekHoursColumn,  String PreCoursesColumn)
	{
		super();
		this.CourseNumColumn = new SimpleStringProperty(CourseNumColumn);
		this.CourseNColumn = new SimpleStringProperty(CourseNColumn);
		this.TeachingUnitColumn = new SimpleStringProperty(TeachingUnitColumn);
		this.WeekHoursColumn = new SimpleStringProperty(WeekHoursColumn);
		this.PreCoursesColumn = new SimpleStringProperty(PreCoursesColumn);	
	}

	/**
	 * Gets the course number column.
	 *
	 * @return the course number column
	 */
	public String getCourseNumColumn()
	{
		return CourseNumColumn.get();
	}

	/**
	 * Gets the course name column.
	 *
	 * @return the course name column
	 */
	public String getCourseNColumn()
	{
		return CourseNColumn.get();
	}

	/**
	 * Gets the teaching unit column.
	 *
	 * @return the teaching unit column
	 */
	public String getTeachingUnitColumn()
	{
		return TeachingUnitColumn.get();
	}

	/**
	 * Gets the week hours column.
	 *
	 * @return the week hours column
	 */
	public String getWeekHoursColumn()
	{
		return WeekHoursColumn.get();
	}

	/**
	 * Gets the pre-courses column.
	 *
	 * @return the pre-courses column
	 */
	public String getPreCoursesColumn()
	{
		return PreCoursesColumn.get();
	}
}

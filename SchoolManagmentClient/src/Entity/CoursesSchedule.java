package Entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * The Class CoursesSchedule.
 */
public class CoursesSchedule
{
	
	/** The Index column. */
	private final SimpleStringProperty IndexColumn;
	
	/** The Course name column. */
	private final SimpleStringProperty CourseNColumn;
	
	/** The Teaching unit column. */
	private final SimpleStringProperty TeachingUnitColumn;
	
	/** The Class name column. */
	private final SimpleStringProperty ClassNColumn;
	
	/** The Teacher name column. */
	private final SimpleStringProperty TeacherNColumn;
	
	/** The Weekly hours column. */
	private final SimpleStringProperty WeeklyHoursColumn;
	

	/**
	 * Instantiates a new courses schedule.
	 *
	 * @param IndexColumn the index column
	 * @param CourseNColumn the course name column
	 * @param TeachingUnitColumn the teaching unit column
	 * @param ClassNColumn the class name column
	 * @param TeacherNColumn the teacher name column
	 * @param WeeklyHoursColumn the weekly hours column
	 */
	public CoursesSchedule(String IndexColumn, String CourseNColumn, String TeachingUnitColumn, String ClassNColumn, String TeacherNColumn,  String WeeklyHoursColumn)
	{
		super();
		this.IndexColumn = new SimpleStringProperty(IndexColumn);
		this.CourseNColumn = new SimpleStringProperty(CourseNColumn);
		this.TeachingUnitColumn = new SimpleStringProperty(TeachingUnitColumn);
		this.ClassNColumn = new SimpleStringProperty(ClassNColumn);
		this.TeacherNColumn = new SimpleStringProperty(TeacherNColumn);
		this.WeeklyHoursColumn = new SimpleStringProperty(WeeklyHoursColumn);
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
	 * Gets the class name column.
	 *
	 * @return the class name column
	 */
	public String getClassNColumn()
	{
		return ClassNColumn.get();
	}

	/**
	 * Gets the teacher name column.
	 *
	 * @return the teacher name column
	 */
	public String getTeacherNColumn()
	{
		return TeacherNColumn.get();
	}

	/**
	 * Gets the weekly hours column.
	 *
	 * @return the weekly hours column
	 */
	public String getWeeklyHoursColumn()
	{
		return WeeklyHoursColumn.get();
	}

	/**
	 * Gets the index column.
	 *
	 * @return the index column
	 */
	public String getIndexColumn()
	{
		return IndexColumn.get();
	}
}

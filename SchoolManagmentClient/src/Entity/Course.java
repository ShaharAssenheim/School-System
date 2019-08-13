package Entity;

/**
 * The Class Course to create Course instance.
 */
public class Course 
{
	
	/** The Course number. */
	private String CourseNumber;
	
	/** The Course name. */
	private String CourseName;
	
	/** The Teaching unit. */
	private String TeachingUnit;
	
	/** The Week hours. */
	private String WeekHours;
	
	/** The Pre-courses. */
	private String Precourses;
	
	
	
	/**
	 * Instantiates a new course.
	 *
	 * @param cnum the Course number
	 * @param cname the Course name
	 * @param teachunit the Teaching unit
	 * @param weekhoers the Week hours
	 * @param precourses the Pre-courses
	 */
	public Course(String cnum, String cname, String teachunit,String weekhoers,String precourses)
	{
		this.CourseNumber=cnum;
		this.CourseName=cname;
		this.TeachingUnit=teachunit;
		this.WeekHours=weekhoers;
		this.Precourses=precourses;
	}
	
	/**
	 * Instantiates a new course.
	 */
	public Course()
	{
		
	}

	/**
	 * Gets the course number.
	 *
	 * @return the course number
	 */
	public String getCourseNumber()
	{
		return CourseNumber;
	}



	/**
	 * Sets the course number.
	 *
	 * @param courseNumber the new course number
	 */
	public void setCourseNumber(String courseNumber)
	{
		CourseNumber = courseNumber;
	}



	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCourseName()
	{
		return CourseName;
	}



	/**
	 * Sets the course name.
	 *
	 * @param courseName the new course name
	 */
	public void setCourseName(String courseName)
	{
		CourseName = courseName;
	}



	/**
	 * Gets the teaching unit.
	 *
	 * @return the teaching unit
	 */
	public String getTeachingUnit()
	{
		return TeachingUnit;
	}



	/**
	 * Sets the teaching unit.
	 *
	 * @param teachingUnit the new teaching unit
	 */
	public void setTeachingUnit(String teachingUnit) 
	{
		TeachingUnit = teachingUnit;
	}



	/**
	 * Gets the week hours.
	 *
	 * @return the week hours
	 */
	public String getWeekHours()
	{
		return WeekHours;
	}



	/**
	 * Sets the week hours.
	 *
	 * @param weekHours the new week hours
	 */
	public void setWeekHours(String weekHours)
	{
		WeekHours = weekHours;
	}



	/**
	 * Gets the Pre-courses.
	 *
	 * @return the Pre-courses
	 */
	public String getPrecourses()
	{
		return Precourses;
	}



	/**
	 * Sets the Pre-courses.
	 *
	 * @param precourses the new Pre-courses
	 */
	public void setPrecourses(String precourses)
	{
		Precourses = precourses;
	}
}

package Entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * The Class HomeWork to create HomeWork instance.
 */
public class HomeWork {
	
	/** The Homework ID. */
	private final SimpleStringProperty HomeworkIDColumn;
	
	/** The Grade. */
	private final SimpleStringProperty GradeColumn;
	
	/** The Homework name. */
	private final SimpleStringProperty HomeworkNameColumn;
	
	/** The Checked status. */
	private final SimpleStringProperty CheckedColumn;
	
	/** The Class name. */
	private final SimpleStringProperty ClassNameColumn;
	
	/** The Course name. */
	private final SimpleStringProperty CourseNameColumn;
	
	/** The Deadline date. */
	private final SimpleStringProperty DeadlineDateColumn;

	/**
	 * Instantiates a new homework.
	 *
	 * @param HomeworkIDColumn the homework ID
	 * @param HomeworkNameColumn the homework name
	 * @param DeadlineDateColumn the deadline date
	 * @param CourseNameColumn the course name
	 * @param ClassNameColumn the class name
	 * @param CheckedColumn the checked status
	 * @param GradeColumn the grade
	 */
	public HomeWork(String HomeworkIDColumn, String HomeworkNameColumn, String DeadlineDateColumn, String CourseNameColumn,  String ClassNameColumn, String CheckedColumn, String GradeColumn) {
		super();
		this.HomeworkIDColumn = new SimpleStringProperty(HomeworkIDColumn);
		this.HomeworkNameColumn = new SimpleStringProperty(HomeworkNameColumn);
		this.DeadlineDateColumn = new SimpleStringProperty(DeadlineDateColumn);
		this.CourseNameColumn = new SimpleStringProperty(CourseNameColumn);
		this.ClassNameColumn = new SimpleStringProperty(ClassNameColumn);
		this.CheckedColumn = new SimpleStringProperty(CheckedColumn);
		this.GradeColumn = new SimpleStringProperty(GradeColumn);
		;
		
		
		
	}
	
	/**
	 * Gets the homework ID.
	 *
	 * @return the homework ID
	 */
	public String getHomeworkIDColumn() {
		return HomeworkIDColumn.get();
	}
	
	/**
	 * Gets the grade.
	 *
	 * @return the grade
	 */
	public String getGradeColumn() {
		return GradeColumn.get();
	}
	
	/**
	 * Gets the homework name.
	 *
	 * @return the homework name
	 */
	public String getHomeworkNameColumn() {
		return HomeworkNameColumn.get();
	}
	
	/**
	 * Gets the checked status.
	 *
	 * @return the checked status
	 */
	public String getCheckedColumn() {
		return CheckedColumn.get();
	}
	
	/**
	 * Gets the class name.
	 *
	 * @return the class name
	 */
	public String getClassNameColumn() {
		return ClassNameColumn.get();
	}
	
	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCourseNameColumn() {
		return CourseNameColumn.get();
	}
	
	/**
	 * Gets the deadline date.
	 *
	 * @return the deadline date
	 */
	public String getDeadlineDateColumn() {
		return DeadlineDateColumn.get();
	}
	
	
}

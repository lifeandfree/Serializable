import java.io.Serializable;
import java.util.Date;

public class Visit implements Serializable, Comparable<Visit>{

	private Student student;
	private Lesson lesson;
	private long data;
	private boolean isVisited;

	public Visit(Student student, Lesson lesson, long data, boolean isVisited) {
		this.student = student;
		this.lesson = lesson;
		this.data = data;
		this.isVisited = isVisited;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean visited) {
		isVisited = visited;
	}

	@Override
	public int compareTo(Visit o) {
		if (this.getData() ==  o.getData()){
			return 0;
		}
		if (this.getData() >  o.getData())		{
			return 1;
		}
			return -1;
	}
}

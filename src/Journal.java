import java.io.Serializable;
import java.util.*;

public class Journal implements Serializable{
	private Lesson lesson;
	private Set<Student> presentSet = new HashSet<>();

	public Journal(Lesson lesson, Set<Student> presentSet, Set<Group> groups) {
		this.lesson = lesson;
		this.presentSet = presentSet;
		this.groups = groups;
	}

	private Set<Group> groups = new HashSet<>();

	public Journal(Lesson lesson, Set<Student> presentSet) {
		this.lesson = lesson;
		this.presentSet = presentSet;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public Set<Student> getPresentSet() {
		return presentSet;
	}

	public void setPresentSet(Set<Student> presentSet) {
		this.presentSet = presentSet;
	}
}

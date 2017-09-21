import java.io.*;
import java.util.ArrayList;
import java.util.Set;

public class MySerializationAction {

	public static final String JOURNALGR_TXT = "journalgr.txt";
	public static final String GROUP_TXT = "group.txt";
	public static final String JOURNAL_STUD_TXT = "journalstud.txt";
	public static final String JOURNAL_LES_TXT = "journal_les.txt";

	static Group readGroup(String fileName) throws IOException,
			ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(fileName)
		);
		Group group = (Group) ois.readObject();
		return group;
	}

	static void serializeGroup(Group group) throws IOException {
		File file = new File(GROUP_TXT);
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(file)
		);
		oos.writeObject(group);
	}

	static ArrayList<Visit> readGR(String fileName) throws IOException,
			ClassNotFoundException {
		return readVisits(fileName);
	}

	private static ArrayList<Visit> readVisits(String fileName) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(fileName)
		);
		ArrayList<Visit> visit = (ArrayList<Visit>) ois.readObject();
		return visit;
	}

	static ArrayList<Visit> readStud(String fileName) throws IOException,
			ClassNotFoundException {
		return readVisits(fileName);
	}

	 static ArrayList<Visit> readLes(String fileName) throws IOException, ClassNotFoundException {
		 return readVisits(fileName);
	}

	static void serializeGR(Group group, Set<Journal> journals) throws IOException {
		File file = new File(JOURNALGR_TXT);
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(file)
		);
		ArrayList<Visit> visits = new ArrayList<Visit>();

		for(Journal journal : journals) {

				for(Student st : group.getStudents()) {
					identifyFisitors(visits, journal, st);
			}
		}
		oos.writeObject(visits);

	}

	private static void identifyFisitors(ArrayList<Visit> visits, Journal journal, Student st) {
		if (journal.getGroups().isEmpty())
		{
			Visit visit = new Visit(st, journal.getLesson(), journal.getLesson().getDateTime(), false);

			if(journal.getPresentSet().contains(st)) {
				visit.setVisited(true);
			}

			visits.add(visit);
		}
		else {
			for(Group gp : journal.getGroups()
					) {
				if(gp.getStudents().contains(st)) {
					Visit visit = new Visit(st, journal.getLesson(), journal.getLesson().getDateTime(), false);

					if(journal.getPresentSet().contains(st)) {
						visit.setVisited(true);
					}

					visits.add(visit);
				}
			}
		}
	}

	public static void serializeStud(Student s01, Set<Journal> journals) throws IOException {

		File file = new File(JOURNAL_STUD_TXT);
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(file)
		);

		ArrayList<Visit> visits = new ArrayList<Visit>();

		for(Journal journal : journals) {
			identifyFisitors(visits, journal, s01);
		}
		oos.writeObject(visits);
	}

	public static void serializeLes(Lesson lesson01, Set<Journal> journals) throws IOException {
		File file = new File(JOURNAL_LES_TXT);
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(file)
		);
		ArrayList<Visit> visits = new ArrayList<Visit>();

		for(Journal journal : journals) {
			if (journal.getLesson().equals(lesson01)) {
				for(Group group : journal.getGroups()
						) {
					for(Student st : group.getStudents()
							) {
						Visit visit = new Visit(st, journal.getLesson(), journal.getLesson().getDateTime(), false);
						if(journal.getPresentSet().contains(st)) {
							visit.setVisited(true);
						}

						visits.add(visit);
					}
				}
			}
		}
		oos.writeObject(visits);
	}


}

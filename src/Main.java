import java.io.IOException;
import java.util.*;

public class Main {
	public static void main(String... args) {
		Group group01 =
				new Group(1,
						"Группа доп. подготовки");
		Group group02 =
				new Group(1,
						"Группа доп. подготовки2");

		Student s01 = new Student((short) 1, "Иван",
				"Иванович",
				"Иванов", Date.parse("1995/07/07"));
		Student s02 = new Student((short) 2, "Петр",
				"Иванович",
				"Иванов", Date.parse("1995/07/07"));
		Student s03 = new Student((short) 3, "Сергей",
				"Иванович",
				"Иванов", Date.parse("1995/07/07"));
		Student s04 = new Student((short) 4, "Федя",
				"Иванович",
				"Иванов", Date.parse("1995/07/07"));
		Student s05 = new Student((short) 5, "Андрей",
				"Иванович",
				"Иванов", Date.parse("1995/07/07"));

		group01.addStudent(s01);
		group01.addStudent(s02);
		group01.addStudent(s03);
		group02.addStudent(s04);
		group02.addStudent(s05);

		Lesson lesson01 =
				new Lesson(Date.parse("2017/09/21"),
						"Параметризованные типы", (short) 1,
						"Артем");

		Lesson lesson02 =
				new Lesson(Date.parse("2017/09/22"),
						"Параметризованные типы2", (short) 2,
						"Артем");

		Lesson lesson03 =
				new Lesson(Date.parse("2017/09/22"),
						"Параметризованные типы3", (short) 3,
						"Артем");

		Set<Student> studentsTmp1 = new HashSet<Student>();
		studentsTmp1.add(s01);
		studentsTmp1.add(s02);

		Set<Student> studentsTmp2 = new HashSet<Student>();
		studentsTmp2.add(s01);
		studentsTmp2.add(s03);

		Set<Group> groups01 =  new HashSet<Group>();
		groups01.add(group01);

		Set<Group> groups02 =  new HashSet<Group>();
		groups02.add(group02);


		Journal journal01 =
				new Journal(lesson01, studentsTmp1, groups01);

		Journal journal02 =
				new Journal(lesson02, studentsTmp2, groups01);

		Journal journal03 =
				new Journal(lesson03, group02.getStudents(), groups02);

		Set<Journal> journals = new HashSet<>();
		journals.add(journal01);
		journals.add(journal02);
		journals.add(journal03);


		try {
			MySerializationAction.serializeGR(group01, journals);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			ArrayList<Visit> restoredVisits =
					MySerializationAction.readGR(MySerializationAction.JOURNALGR_TXT);
			for(Visit restoredVisit : restoredVisits
					) {
				System.out.println(restoredVisit.getStudent().getSecondName() + " " + restoredVisit.getStudent().getFirstName() +
						"	" + restoredVisit.getLesson().getTopic() + "	" + restoredVisit.getData() +
						"	" + restoredVisit.isVisited());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			MySerializationAction.serializeStud(s02, journals);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(" ");
		try {
			ArrayList<Visit> restoredStuds =
					MySerializationAction.readStud(MySerializationAction.JOURNAL_STUD_TXT);
			for(Visit restoredVisit : restoredStuds
					) {
				System.out.println(restoredVisit.getStudent().getSecondName() + " " + restoredVisit.getStudent().getFirstName() +
						"	" + restoredVisit.getLesson().getTopic() + "	" + restoredVisit.getData() +
						"	" + restoredVisit.isVisited());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println(" ");

		try {
			MySerializationAction.serializeLes(lesson01, journals);
		} catch (IOException e) {
			e.printStackTrace();
		}


		try {
			ArrayList<Visit> restoredLes =
					MySerializationAction.readLes(MySerializationAction.JOURNAL_LES_TXT);
			for(Visit restoredVisit : restoredLes
					) {
				System.out.println(restoredVisit.getStudent().getSecondName() + " " + restoredVisit.getStudent().getFirstName() +
						"	" + restoredVisit.getLesson().getTopic() + "	" + restoredVisit.getData() +
						"	" + restoredVisit.isVisited());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			MySerializationAction.serializeGroup(group01);
		} catch (IOException e) {
			e.printStackTrace();
		}


		try {
			Group restoredGroup =
					MySerializationAction.readGroup(MySerializationAction.GROUP_TXT);
			System.out.println(restoredGroup.getGroupName());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


	}

}

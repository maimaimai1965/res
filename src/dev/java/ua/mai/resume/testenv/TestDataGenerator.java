package ua.mai.resume.testenv;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import net.coobird.thumbnailator.Thumbnails;
import ua.mai.resume.model.LanguageLevel;
import ua.mai.resume.model.LanguageType;

/**
 * 
 * Please add postgresql JDBC driver to classpath before launch this generator
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class TestDataGenerator {

	// JDBC setting for database
	private static final String JDBC_URL = "jdbc:postgresql://localhost/res";
	private static final String JDBC_USERNAME = "res";
	private static final String JDBC_PASSWORD = "res";

	private static final String PHOTO_PATH = "external/test-data/photos/";
	private static final String CERTIFICATES_PATH = "external/test-data/certificates/";
	private static final String MEDIA_DIR =
		"D:/JAVA/WORK/workspace-sourceit/res/src/main/webapp/media";	
      //"D:/devstudy.net/workspace/resume/src/main/webapp/media";
	private static final String COUTRY = "Ukraine";
	private static final String[] CITIES = { "Kharkiv", "Kiyv", "Odessa" };
	private static final String[] FOREGIN_LANGUAGES = { "English", "Spanish", "French", "German", "Italian"};
	private static final String PASSWORD_HASH = "$2a$10$q7732w6Rj3kZGhfDYSIXI.wFp.uwTSi2inB2rYHvm1iDIAf1J1eVq";

	private static final String[] HOBBIES = { "Cycling", "Handball", "Football", "Basketball", "Bowling", "Boxing", "Volleyball", "Baseball", "Skating", "Skiing", "Table tennis", "Tennis",
			"Weightlifting", "Automobiles", "Book reading", "Cricket", "Photo", "Shopping", "Cooking", "Codding", "Animals", "Traveling", "Movie", "Painting", "Darts", "Fishing", "Kayak slalom",
			"Games of chance", "Ice hockey", "Roller skating", "Swimming", "Diving", "Golf", "Shooting", "Rowing", "Camping", "Archery", "Pubs", "Music", "Computer games", "Authorship", "Singing",
			"Foreign lang", "Billiards", "Skateboarding", "Collecting", "Badminton", "Disco" };

	private static List<LanguageType> languageTypes = new ArrayList<>(EnumSet.allOf(LanguageType.class));
	private static List<LanguageLevel> languageLevels = new ArrayList<>(EnumSet.allOf(LanguageLevel.class));
	// Test sentences for content generation
	private static final String DUMMY_CONTENT_TEXT = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, quis gravida magna mi a libero. Fusce vulputate eleifend sapien. Vestibulum purus quam, scelerisque ut, mollis sed, nonummy id, metus. Nullam accumsan lorem in dui. Cras ultricies mi eu turpis hendrerit fringilla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In ac dui quis mi consectetuer lacinia. Nam pretium turpis et arcu. Duis arcu tortor, suscipit eget, imperdiet nec, imperdiet iaculis, ipsum. Sed aliquam ultrices mauris. Integer ante arcu, accumsan a, consectetuer eget, posuere ut, mauris. Praesent adipiscing. Phasellus ullamcorper ipsum rutrum nunc. Nunc nonummy metus. Vestibulum volutpat pretium libero. Cras id dui. Aenean ut eros et nisl sagittis vestibulum. Nullam nulla eros, ultricies sit amet, nonummy id, imperdiet feugiat, pede. Sed lectus. Donec mollis hendrerit risus. Phasellus nec sem in justo pellentesque facilisis. Etiam imperdiet imperdiet orci. Nunc nec neque. Phasellus leo dolor, tempus non, auctor et, hendrerit quis, nisi. Curabitur ligula sapien, tincidunt non, euismod vitae, posuere imperdiet, leo. Maecenas malesuada. Praesent congue erat at massa. Sed cursus turpis vitae tortor. Donec posuere vulputate arcu. Phasellus accumsan cursus velit. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Sed aliquam, nisi quis porttitor congue, elit erat euismod orci, ac placerat dolor lectus quis orci. Phasellus consectetuer vestibulum elit. Aenean tellus metus, bibendum sed, posuere ac, mattis non, nunc. Vestibulum fringilla pede sit amet augue. In turpis. Pellentesque posuere. Praesent turpis. Aenean posuere, tortor sed cursus feugiat, nunc augue blandit nunc, eu sollicitudin urna dolor sagittis lacus. Donec elit libero, sodales nec, volutpat a, suscipit non, turpis. Nullam sagittis. Suspendisse pulvinar, augue ac venenatis condimentum, sem libero volutpat nibh, nec pellentesque velit pede quis nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Fusce id purus. Ut varius tincidunt libero. Phasellus dolor. Maecenas vestibulum mollis diam. Pellentesque ut neque. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In dui magna, posuere eget, vestibulum et, tempor auctor, justo. In ac felis quis tortor malesuada pretium. Pellentesque auctor neque nec urna. Proin sapien ipsum, porta a, auctor quis, euismod ut, mi. Aenean viverra rhoncus pede. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Ut non enim eleifend felis pretium feugiat. Vivamus quis mi. Phasellus a est. Phasellus magna. In hac habitasse platea dictumst. Curabitur at lacus ac velit ornare lobortis. Curabitur a felis in nunc fringilla tristique. Morbi mattis ullamcorper velit. Phasellus gravida semper nisi. Nullam vel sem. Pellentesque libero tortor, tincidunt et, tincidunt eget, semper nec, quam. Sed hendrerit. Morbi ac felis. Nunc egestas, augue at pellentesque laoreet, felis eros vehicula leo, at malesuada velit leo quis pede. Donec interdum, metus et hendrerit aliquet, dolor diam sagittis ligula, eget egestas libero turpis vel mi. Nunc nulla. Fusce risus nisl, viverra et, tempor et, pretium in, sapien. Donec venenatis vulputate lorem. Morbi nec metus. Phasellus blandit leo ut odio. Maecenas ullamcorper, dui et placerat feugiat, eros pede varius nisi, condimentum viverra felis nunc et lorem. Sed magna purus, fermentum eu, tincidunt eu, varius ut, felis. In auctor lobortis lacus. Quisque libero metus, condimentum nec, tempor a, commodo mollis, magna. Vestibulum ullamcorper mauris at ligula. Fusce fermentum. Nullam cursus lacinia erat. Praesent blandit laoreet nibh. Fusce convallis metus id felis luctus adipiscing. Pellentesque egestas, neque sit amet convallis pulvinar, justo nulla eleifend augue, ac auctor orci leo non est. Quisque id mi. Ut tincidunt tincidunt erat. Etiam feugiat lorem non metus. Vestibulum dapibus nunc ac augue. Curabitur vestibulum aliquam leo. Praesent egestas neque eu enim. In hac habitasse platea dictumst. Fusce a quam. Etiam ut purus mattis mauris sodales aliquam. Curabitur nisi. Quisque malesuada placerat nisl. Nam ipsum risus, rutrum vitae, vestibulum eu, molestie vel, lacus. Sed augue ipsum, egestas nec, vestibulum et, malesuada adipiscing, dui. Vestibulum facilisis, purus nec pulvinar iaculis, ligula mi congue nunc, vitae euismod ligula urna in dolor. Mauris sollicitudin fermentum libero. Praesent nonummy mi in odio. Nunc interdum lacus sit amet orci. Vestibulum rutrum, mi nec elementum vehicula, eros quam gravida nisl, id fringilla neque ante vel mi. Morbi mollis tellus ac sapien. Phasellus volutpat, metus eget egestas mollis, lacus lacus blandit dui, id egestas quam mauris ut lacus. Fusce vel dui. Sed in libero ut nibh placerat accumsan. Proin faucibus arcu quis ante. In consectetuer turpis ut velit. Nulla sit amet est. Praesent metus tellus, elementum eu, semper a, adipiscing nec, purus. Cras risus ipsum, faucibus ut, ullamcorper id, varius ac, leo. Suspendisse feugiat. Suspendisse enim turpis, dictum sed, iaculis a, condimentum nec, nisi. Praesent nec nisl a purus blandit viverra. Praesent ac massa at ligula laoreet iaculis. Nulla neque dolor, sagittis eget, iaculis quis, molestie non, velit. Mauris turpis nunc, blandit et, volutpat molestie, porta ut, ligula. Fusce pharetra convallis urna. Quisque ut nisi. Donec mi odio, faucibus at, scelerisque quis, convallis in, nisi. ";
	private static final List<String> SENTENCES;

	static {
		String[] sentences = DUMMY_CONTENT_TEXT.split("\\.");
		List<String> sentencesList = new ArrayList<>(sentences.length);
		for (String sentence : sentences) {
			sentence = sentence.trim();
			if (sentence.length() > 0) {
				sentencesList.add(sentence + ".");
			}
		}
		SENTENCES = Collections.unmodifiableList(sentencesList);
	}

	private static final Random r = new Random();
	private static int idPerson = 0;
	private static Date birthDay = null;

	public static void main(String[] args) throws SQLException, IOException {
		clearMedia();
		List<Certificate> certificates = loadCertificates();
		List<Person> persons = loadPersons();
		List<PersonConfig> personConfigs = getPersonConfigs();
		System.out.println("Connect  " + JDBC_USERNAME);
		try (Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
			c.setAutoCommit(false);
			clearDb(c);
			insertHobbies(c);
			insertLangs(c);
			insertSkillTypes(c);
			for (Person p : persons) {
				PersonConfig personConfig = personConfigs.get(r.nextInt(personConfigs.size()));
				createPerson(c, p, personConfig, certificates);
				System.out.println("Created person for " + p.f + " " + p.i);
			}
//			insertSkillCategories(c);
			c.commit();
			System.out.println("Data generated successful");
		}
	    catch (SQLException e) {
		  e.printStackTrace();
		  throw e;
        }
		
	}

	private static void clearMedia() throws IOException {
		if (Files.exists(Paths.get(MEDIA_DIR))) {
			Files.walkFileTree(Paths.get(MEDIA_DIR), new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				}
			});
		}
		System.out.println("Media dir cleared");
	}

	private static void clearDb(Connection c) throws SQLException {
		Statement st = c.createStatement();
		st.executeUpdate("delete from person");
		st.executeUpdate("delete from skill_type");
		st.executeUpdate("delete from hobby");
		st.executeUpdate("delete from lang");
		st.executeQuery("select setval('sq_person', 1, false)");
		st.executeQuery("select setval('sq_person_hobby', 1, false)");
		st.executeQuery("select setval('sq_certificate', 1, false)");
		st.executeQuery("select setval('sq_education', 1, false)");
		st.executeQuery("select setval('sq_lang_level', 1, false)");
		st.executeQuery("select setval('sq_practic', 1, false)");
		st.executeQuery("select setval('sq_skill', 1, false)");
		st.executeQuery("select setval('sq_course', 1, false)");
		System.out.println("Db cleared");
	}

	private static List<Person> loadPersons() {
		File[] photos = new File(PHOTO_PATH).listFiles();
		List<Person> list = new ArrayList<>(photos.length);
		for (File f : photos) {
			String[] names = f.getName().replace(".jpg", "").split(" ");
			list.add(new Person(names[0], names[1], f.getAbsolutePath()));
		}
		Collections.sort(list, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				int fCompare = o1.f.compareTo(o2.f);
				if (fCompare != 0) {
					return fCompare;
				} else {
					return o1.i.compareTo(o2.i);
				}
			}
		});
		return list;
	}

	private static List<Certificate> loadCertificates() {
		File[] files = new File(CERTIFICATES_PATH).listFiles();
		List<Certificate> list = new ArrayList<>(files.length);
		for (File f : files) {
			String name = f.getName().replace("-", " ");
			name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
			list.add(new Certificate(name, f.getAbsolutePath()));
		}
		return list;
	}

	private static void createPerson(Connection c, Person person, PersonConfig personConfig, List<Certificate> certificates) throws SQLException, IOException {
		insertPersonData(c, person, personConfig);
		if (personConfig.certificates > 0) {
			insertCertificates(c, personConfig.certificates, certificates);
		}
		insertEducation(c);
		insertPersonHobbies(c);
		insertPersonLangs(c);
		insertPractics(c, personConfig);
		insertSkills(c, personConfig);
		insertCourses(c);
	}

	private static void insertSkillTypes(Connection c) throws SQLException {
		Map<String, Set<String>> map = createSkillMap();
		PreparedStatement ps = c.prepareStatement("insert into skill_type values(?,?)");
		int i = 1;
		for (String str: map.keySet()) {
			ps.setInt(1, i);
			ps.setString(2, str);
			ps.addBatch();
			i++;
		}
        ps.executeBatch();
		ps.close();
	}

	private static void insertSkills(Connection c, PersonConfig personConfig) throws SQLException {
		PreparedStatement ps = c.prepareStatement("insert into skill values (nextval('sq_skill'),?,?,?)");
		Map<String, Set<String>> map = createSkillMap();
		for (Course course : personConfig.courses) {
			for (String key : map.keySet()) {
				map.get(key).addAll(course.skills.get(key));
			}
		}
		for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
			if (!entry.getValue().isEmpty()) {
				ps.setLong(1, idPerson);
                int j = r.nextInt(map.size()-1) + 1;
				ps.setInt(2, j);
				String str = StringUtils.join(entry.getValue().toArray(), ",");
//System.out.println("person_id="+idPerson+", skill_type_id="+j+", info="+str);				
				ps.setString(3, str);
				ps.addBatch();
			}
		}
		ps.executeBatch();
		ps.close();
	}

	private static void insertPractics(Connection c, PersonConfig personConfig) throws SQLException {
		PreparedStatement ps = c.prepareStatement("insert into practic values (nextval('sq_practic'),?,?,?,?,?,?,?,?)");
		boolean currentCourse = r.nextBoolean();
		Date finish = addField(new Date(System.currentTimeMillis()), Calendar.MONTH, -(r.nextInt(3) + 1), false);
		for (Course course : personConfig.courses) {
			ps.setLong(1, idPerson);
			ps.setString(2, course.company);
			ps.setString(3, course.name);
			if (currentCourse) {
				ps.setDate(4, addField(new Date(System.currentTimeMillis()), Calendar.MONTH, -1, false));
				ps.setNull(5, Types.DATE);
			} else {
				ps.setDate(4, addField(finish, Calendar.MONTH, -1, false));
				ps.setDate(5, finish);
				finish = addField(finish, Calendar.MONTH, -(r.nextInt(3) + 1), false);
			}
			ps.setString(6, course.info);
			if (course.demo == null) {
				ps.setNull(7, Types.VARCHAR);
			} else {
				ps.setString(7, course.demo);
			}
			if (course.github == null) {
				ps.setNull(8, Types.VARCHAR);
			} else {
				ps.setString(8, course.github);
			}
			ps.addBatch();
		}
		ps.executeBatch();
		ps.close();
	}

	private static void insertLangs(Connection c) throws SQLException {
		PreparedStatement ps = c.prepareStatement("insert into lang values(?,?)");
		int i = 1;
		for (String str: FOREGIN_LANGUAGES) {
			ps.setInt(1, i);
			ps.setString(2, str);
			ps.addBatch();
			i++;
		}
        ps.executeBatch();
		ps.close();
	}

	private static void insertPersonLangs(Connection c) throws SQLException {
		List<Integer> languages = new ArrayList<>();
		languages.add(1); //English
		if (r.nextBoolean()) {
          int j = r.nextInt(FOREGIN_LANGUAGES.length-2) + 2;
          languages.add(j);
//          for (int i=1; i<=2; i++) {
//            int j = r.nextInt(FOREGIN_LANGUAGES.length-2) + 2;
//            languages.add(j);
//		  }
		}
		PreparedStatement ps = c.prepareStatement("insert into lang_level values (nextval('sq_lang_level'),?,?,?,?)");
		for (Integer language : languages) {
			LanguageType langType = languageTypes.get(r.nextInt(languageTypes.size()));
			LanguageLevel langLevel = languageLevels.get(r.nextInt(languageLevels.size()));
			ps.setLong(1, idPerson);
			ps.setInt(2, language);
			ps.setString(3, String.valueOf(langType.getDbType()));
			ps.setString(4, String.valueOf(langLevel.getDbLevel()));
 			ps.addBatch();
//			if (langType != LanguageType.ALL) {
//				ps.setLong(1, idPerson);
//				ps.setInt(2, language);
//				LanguageLevel newLangLevel = languageLevels.get(r.nextInt(languageLevels.size()));
//				while (newLangLevel == langLevel) {
//					newLangLevel = languageLevels.get(r.nextInt(languageLevels.size()));
//				}
//				ps.setString(3, String.valueOf(langType.getReverseType().getDbType()));
//				ps.setString(4, String.valueOf(langLevel.getDbLevel()));
//				ps.addBatch();
//			}
		}
		ps.executeBatch();
		ps.close();
	}

	private static void insertHobbies(Connection c) throws SQLException {
		PreparedStatement ps = c.prepareStatement("insert into hobby values(?,?,?)");
		int i = 1;
		for (String str: HOBBIES) {
			ps.setInt(1, i);
			ps.setString(2, str);
			ps.setString(3, str);
			ps.addBatch();
			i++;
		}
        ps.executeBatch();
		ps.close();
	}

	private static void insertPersonHobbies(Connection c) throws SQLException {
		PreparedStatement ps = c.prepareStatement("insert into person_hobby values (nextval('sq_person_hobby'),?,?)");
		List<String> hobbies = new ArrayList<>(Arrays.asList(HOBBIES));
//		Collections.shuffle(hobbies);
		//Увеличить кол-во хоби
		for (int i = 0; i < 1; i++) {
			ps.setLong(1, idPerson);
			int j = r.nextInt(HOBBIES.length-1) + 1;
			ps.setInt(2, j);
			ps.addBatch();
		}
		ps.executeBatch();
		ps.close();
	}

	private static void insertEducation(Connection c) throws SQLException {
		PreparedStatement ps = c.prepareStatement("insert into education values (nextval('sq_education'),?,?,?,?,?,?)");
		ps.setLong(1, idPerson);
		//SUMMARY
		ps.setString(2, "The specialist degree in Electronic Engineering");
		ps.setString(3, "Kharkiv National Technical University, Ukraine");
		ps.setString(4, "Computer Science");
		Date finish = randomFinishEducation();
		Date begin = addField(finish, Calendar.YEAR, -5, true);
		ps.setDate(5, begin);
		if (finish.getTime() > System.currentTimeMillis()) {
			ps.setNull(6, Types.DATE);
		} else {
			ps.setDate(6, finish);
		}
		ps.executeUpdate();
		ps.close();
	}

	private static Date addField(Date finish, int field, int value, boolean isBeginEducation) {
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(finish.getTime());
		cl.add(field, value);
		if (isBeginEducation) {
			cl.set(Calendar.DAY_OF_MONTH, 1);
			cl.set(Calendar.MONTH, Calendar.SEPTEMBER);
		}
		return new Date(cl.getTimeInMillis());
	}

	private static Date randomFinishEducation() {
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(birthDay.getTime());
		cl.set(Calendar.DAY_OF_MONTH, 30);
		cl.set(Calendar.MONTH, Calendar.JUNE);
		int year = cl.get(Calendar.YEAR) + 21;
		cl.set(Calendar.YEAR, year + r.nextInt(3));
		return new Date(cl.getTimeInMillis());
	}

	private static void insertCourses(Connection c) throws SQLException {
		if (r.nextBoolean()) {
			PreparedStatement ps = c.prepareStatement("insert into course values (nextval('sq_course'),?,?,?,?)");
			ps.setLong(1, idPerson);
			ps.setString(2, "Java Advanced Course");
			ps.setString(3, "SourceIt");
			Date finish = randomFinishEducation();
			if (finish.getTime() > System.currentTimeMillis()) {
				ps.setNull(4, Types.DATE);
			} else {
				ps.setDate(4, finish);
			}
			ps.executeUpdate();
			ps.close();
		}
	}

	private static void insertCertificates(Connection c, int certificatesCount, List<Certificate> certificates) throws SQLException, IOException {
		Collections.shuffle(certificates);
		PreparedStatement ps = c.prepareStatement("insert into certificate values (nextval('sq_certificate'),?,?,?,?)");
		for (int i = 0; i < certificatesCount && i < certificates.size(); i++) {
			Certificate certificate = certificates.get(i);
			ps.setLong(1, idPerson);
			ps.setString(2, certificate.info);
			String uid = UUID.randomUUID().toString() + ".jpg";
			File photo = new File(MEDIA_DIR + "/certificates/" + uid);
			if (!photo.getParentFile().exists()) {
				photo.getParentFile().mkdirs();
			}
			String smallUid = uid.replace(".jpg", "-sm.jpg");
			Files.copy(Paths.get(certificate.largeImg), Paths.get(photo.getAbsolutePath()));
			ps.setString(3, "/media/certificates/" + uid);
			Thumbnails.of(photo).size(100, 100).toFile(Paths.get(photo.getAbsolutePath().replace(".jpg", "-sm.jpg")).toFile());
			ps.setString(4, "/media/certificates/" + smallUid);
			ps.addBatch();
		}

		ps.executeBatch();
		ps.close();
	}

	private static String getInfo() {
		int endIndex = r.nextInt(SENTENCES.size());
		int startIndex = r.nextInt(endIndex);
		if (endIndex - startIndex > 4) {
			endIndex = startIndex + 3;
		}
		return StringUtils.join(SENTENCES.subList(startIndex, endIndex), " ");
	}

	private static void insertPersonData(Connection c, Person person, PersonConfig personConfig) throws SQLException, IOException {
		PreparedStatement ps = c.prepareStatement(
"insert into person("
   //    1   2 3 4 5         6        7       8    9     10    11   
   + "ID,UID,F,I,O,BIRTH_DAY,PASSWORD,COUNTRY,CITY,PHONE,EMAIL,CAREER_OBJECTIVE,"
   // 12            13          14          15    16        17       18   
   + "QUALIFICATION,SMALL_PHOTO,LARGE_PHOTO,SKYPE,VKONTAKTE,FACEBOOK,LINKEDIN,"
   // 19      20            21             22       23  
   + "GITHUB, STACKOVERFLOW,INFO,COMPLETED,INSERTED,DELETED"   				
   + ") values (nextval('sq_person'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,true,?,?)");
		ps.setString(1, (person.f + "-" + person.i).toLowerCase());
		ps.setString(2, person.f);
		ps.setString(3, person.i);
		ps.setNull(4, Types.VARCHAR);
		birthDay = randomBirthDate();
		ps.setDate(5, birthDay);
		ps.setString(6, PASSWORD_HASH);
		ps.setString(7, COUTRY);
		ps.setString(8, CITIES[r.nextInt(CITIES.length)]);
		ps.setString(9, generatePhone());
		ps.setString(10, (person.f + "-" + person.i).toLowerCase() + "@gmail.com");
		ps.setString(11, personConfig.careerObjective);
		ps.setString(12, personConfig.qualification);

		String uid = UUID.randomUUID().toString() + ".jpg";
		File photo = new File(MEDIA_DIR + "/avatar/" + uid);
		if (!photo.getParentFile().exists()) {
			photo.getParentFile().mkdirs();
		}
		Files.copy(Paths.get(person.photo), Paths.get(photo.getAbsolutePath()));
		ps.setString(14, "/media/avatar/" + uid);

		String smallUid = uid.replace(".jpg", "-sm.jpg");
		Thumbnails.of(photo).size(110, 110).toFile(new File(MEDIA_DIR + "/avatar/" + smallUid));
		ps.setString(13, "/media/avatar/" + smallUid);
		
		if (r.nextBoolean()) {
			ps.setString(15, (person.f + "-" + person.i).toLowerCase());
		} else {
			ps.setNull(15, Types.VARCHAR);
		}
		if (r.nextBoolean()) {
			ps.setString(16, "https://vk.com/" + (person.f + "-" + person.i).toLowerCase());
		} else {
			ps.setNull(16, Types.VARCHAR);
		}
		if (r.nextBoolean()) {
			ps.setString(17, "https://facebook.com/" + (person.f + "-" + person.i).toLowerCase());
		} else {
			ps.setNull(17, Types.VARCHAR);
		}
		if (r.nextBoolean()) {
			ps.setString(18, "https://linkedin.com/" + (person.f + "-" + person.i).toLowerCase());
		} else {
			ps.setNull(18, Types.VARCHAR);
		}
		if (r.nextBoolean()) {
			ps.setString(19, "https://github.com/" + (person.f + "-" + person.i).toLowerCase());
		} else {
			ps.setNull(19, Types.VARCHAR);
		}
		if (r.nextBoolean()) {
			ps.setString(20, "https://stackoverflow.com/" + (person.f + "-" + person.i).toLowerCase());
		} else {
			ps.setNull(20, Types.VARCHAR);
		}
		
		if (r.nextBoolean()) {
			ps.setString(21, getInfo());
		} else {
			ps.setNull(21, Types.VARCHAR);
		}

		//INSERTED
		ps.setTimestamp(22, new Timestamp(System.currentTimeMillis()));  
		ps.setNull(23, Types.TIMESTAMP);

		ps.executeUpdate();
		ps.close();
		idPerson++;
	}

	private static void insertSkillCategories(Connection c) throws SQLException {
		int id = 1;
		Map<String, Set<String>> categories = createSkillMap();
		PreparedStatement ps = c.prepareStatement("insert into skill_category values (?,?)");
		for (String category : categories.keySet()) {
			ps.setLong(1, id++);
			ps.setString(2, category);
			ps.addBatch();
		}
		ps.executeBatch();
		ps.close();
	}

	private static String generatePhone() {
		StringBuilder phone = new StringBuilder("+38050");
		for (int i = 0; i < 7; i++) {
			int code = '1' + r.nextInt(9);
			phone.append(((char) code));
		}
		return phone.toString();
	}

	private static Date randomBirthDate() {
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.DAY_OF_MONTH, r.nextInt(30));
		cl.set(Calendar.MONTH, r.nextInt(12));
		int year = cl.get(Calendar.YEAR) - 30;
		cl.set(Calendar.YEAR, year + r.nextInt(10));
		return new Date(cl.getTimeInMillis());
	}

	private static List<PersonConfig> getPersonConfigs() {
		List<PersonConfig> res = new ArrayList<>();
		res.add(new PersonConfig("Junior java trainee position", "Java core course with developing one simple console application", new Course[] { Course.createCoreCourse() }, 0));
		res.add(new PersonConfig("Junior java trainee position", "One Java professional course with developing web application blog (Link to demo is provided)",
				new Course[] { Course.createBaseCourse() }, 0));
		res.add(new PersonConfig("Junior java developer position", "One Java professional course with developing web application resume (Link to demo is provided)",
				new Course[] { Course.createAdvancedCourse() }, 0));
		res.add(new PersonConfig("Junior java developer position", "One Java professional course with developing web application resume (Link to demo is provided)",
				new Course[] { Course.createAdvancedCourse() }, 1));
		res.add(new PersonConfig("Junior java developer position", "Two Java professional courses with developing two web applications: blog and resume (Links to demo are provided)",
				new Course[] { Course.createAdvancedCourse(), Course.createBaseCourse() }, 1));
		res.add(new PersonConfig("Junior java developer position", "Two Java professional courses with developing two web applications: blog and resume (Links to demo are provided)",
				new Course[] { Course.createAdvancedCourse(), Course.createBaseCourse() }, 1));
		res.add(new PersonConfig("Junior java developer position", "Two Java professional courses with developing two web applications: blog and resume (Links to demo are provided)",
				new Course[] { Course.createAdvancedCourse(), Course.createBaseCourse() }, 1));
		res.add(new PersonConfig("Junior java developer position", "Two Java professional courses with developing two web applications: blog and resume (Links to demo are provided)",
				new Course[] { Course.createAdvancedCourse(), Course.createBaseCourse() }, 2));
		res.add(new PersonConfig("Junior java developer position",
				"Three Java professional courses with developing one console application and two web applications: blog and resume (Links to demo are provided)",
				new Course[] { Course.createAdvancedCourse(), Course.createBaseCourse(), Course.createCoreCourse() }, 2));
		return res;
	}

	private static Map<String, Set<String>> createSkillMap() {
		Map<String, Set<String>> skills = new LinkedHashMap<>();
		skills.put("Languages", new LinkedHashSet<String>());
		skills.put("DBMS", new LinkedHashSet<String>());
		skills.put("Web", new LinkedHashSet<String>());
		skills.put("Java", new LinkedHashSet<String>());
		skills.put("IDE", new LinkedHashSet<String>());
		skills.put("CVS", new LinkedHashSet<String>());
		skills.put("Web Servers", new LinkedHashSet<String>());
		skills.put("Build system", new LinkedHashSet<String>());
		skills.put("Cloud", new LinkedHashSet<String>());
		return skills;
	}

	/**
	 * 
	 * @author devstudy
	 * @see http://devstudy.net
	 */
	private static final class PersonConfig {
		private final String careerObjective;
		private final String qualification;
		private final Course[] courses;
		private final int certificates;

		private PersonConfig(String objective, String qualification, Course[] courses, int certificates) {
			super();
			this.careerObjective = objective;
			this.qualification = qualification;
			this.courses = courses;
			this.certificates = certificates;
		}
	}

	/**
	 * 
	 * @author devstudy
	 * @see http://devstudy.net
	 */
	private static final class Person {
		private final String f;
		private final String i;
		private final String photo;

		private Person(String firstName, String lastName, String photo) {
			super();
			this.f = firstName;
			this.i = lastName;
			this.photo = photo;
		}

		@Override
		public String toString() {
			return String.format("Person [f=%s, i=%s]", f, i);
		}
	}

	/**
	 * 
	 * @author devstudy
	 * @see http://devstudy.net
	 */
	private static final class Certificate {
		private final String info;
		private final String largeImg;

		private Certificate(String info, String largeImg) {
			super();
			this.info = info;
			this.largeImg = largeImg;
		}
	}

	/**
	 * 
	 * @author devstudy
	 * @see http://devstudy.net
	 */
	private static final class Course {
		private final String name;
		private final String company;
		private final String github;
		private final String info;
		private final String demo;
		private final Map<String, Set<String>> skills;

		private Course(String name, String company, String github, String info, String demo, Map<String, Set<String>> skills) {
			super();
			this.name = name;
			this.company = company;
			this.github = github;
			this.info = info;
			this.demo = demo;
			this.skills = skills;
		}

		static Course createCoreCourse() {
			Map<String, Set<String>> skills = createSkillMap();
			skills.get("Languages").add("Java");

			skills.get("DBMS").add("Mysql");

			skills.get("Java").add("Threads");
			skills.get("Java").add("IO");
			skills.get("Java").add("JAXB");
			skills.get("Java").add("GSON");

			skills.get("IDE").add("Eclipse for JEE Developer");

			skills.get("CVS").add("Git");
			skills.get("CVS").add("Github");

			skills.get("Build system").add("Maven");

			return new Course("Java Core Course", "DevStudy.net", null, "Developing the java console application which imports XML, JSON, Properties, CVS to Db via JDBC", null, skills);
		}

		static Course createBaseCourse() {
			Map<String, Set<String>> skills = createSkillMap();
			skills.get("Languages").add("Java");
			skills.get("Languages").add("SQL");

			skills.get("DBMS").add("Postgresql");

			skills.get("Web").add("HTML");
			skills.get("Web").add("CSS");
			skills.get("Web").add("JS");
			skills.get("Web").add("JS");
			skills.get("Web").add("Foundation");
			skills.get("Web").add("JQuery");

			skills.get("Java").add("Servlets");
			skills.get("Java").add("Logback");
			skills.get("Java").add("JSP");
			skills.get("Java").add("JSTL");
			skills.get("Java").add("JDBC");
			skills.get("Java").add("Apache Commons");
			skills.get("Java").add("Google+ Social API");

			skills.get("IDE").add("Eclipse for JEE Developer");

			skills.get("CVS").add("Git");
			skills.get("CVS").add("Github");

			skills.get("Web Servers").add("Tomcat");

			skills.get("Build system").add("Maven");

			skills.get("Cloud").add("OpenShift");

			return new Course("Java Base Course", "DevStudy.net", "https://github.com/TODO",
					"Developing the web application 'blog' using free HTML template, downloaded from intenet. Populating database by test data and uploading web project to OpenShift free hosting",
					"http://LINK_TO_DEMO_SITE", skills);
		}

		static Course createAdvancedCourse() {
			Map<String, Set<String>> skills = createSkillMap();
			skills.get("Languages").add("Java");
			skills.get("Languages").add("SQL");
			skills.get("Languages").add("PLSQL");

			skills.get("DBMS").add("Postgresql");

			skills.get("Web").add("HTML");
			skills.get("Web").add("CSS");
			skills.get("Web").add("JS");
			skills.get("Web").add("JS");
			skills.get("Web").add("Bootstrap");
			skills.get("Web").add("JQuery");

			skills.get("Java").add("Spring MVC");
			skills.get("Java").add("Logback");
			skills.get("Java").add("JSP");
			skills.get("Java").add("JSTL");
			skills.get("Java").add("Spring Data JPA");
			skills.get("Java").add("Apache Commons");
			skills.get("Java").add("Spring Security");
			skills.get("Java").add("Hibernate JPA");
			skills.get("Java").add("Facebook Social API");

			skills.get("IDE").add("Eclipse for JEE Developer");

			skills.get("CVS").add("Git");
			skills.get("CVS").add("Github");

			skills.get("Web Servers").add("Tomcat");
			skills.get("Web Servers").add("Nginx");

			skills.get("Build system").add("Maven");

			skills.get("Cloud").add("AWS");

			return new Course("Java Advanced Course", "DevStudy.net", "https://github.com/TODO",
					"Developing the web application 'online-resume' using bootstrap HTML template, downloaded from intenet. Populating database by test data and uploading web project to AWS EC2 instance",
					"http://LINK_TO_DEMO_SITE", skills);
		}
	}
}

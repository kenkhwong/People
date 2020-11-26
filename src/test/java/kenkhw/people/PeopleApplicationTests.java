package kenkhw.people;

import kenkhw.people.jpa.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PeopleApplicationTests {

	@Autowired
	private DirectoryRepository directoryRepository;
	@Autowired
	private UserRepository userRepository;

	//Test data
	String[] directoryNames = {"Meido","Track&Trace"};
	String[][] userNames = {{"Karma","Azu"},{"nhs","phe"}};

	@Test
	void contextLoads() {
		initDatabase();
		testFindBy_Integrated();
	}

	private void initDatabase() {

		for (String name : directoryNames) {
			assertThat(directoryRepository.save(new Directory(name)).getName())
					.isEqualTo(name);
		}

		List<Directory> directories = directoryRepository.findAll();
		assertThat(directories.size()).isEqualTo(directoryNames.length);

		int directoryIndex = 0;
		for (Directory directory : directories) {
			for (String username : userNames[directoryIndex]) {
				assertThat(userRepository.save(new User(username,"password",directory))
						.getPrincipal())
						.isEqualTo(username);
			}
			directoryIndex++;
		}
	}

	private void testFindBy_Integrated() {
		//By Id
		assertThat(directoryRepository.findById(0L)).isEmpty();
		assertThat(directoryRepository.findById(1L)).isPresent();
		assertThat(userRepository.findById(0L)).isEmpty();
		assertThat(userRepository.findById(3L)).isPresent();

		//By values
		for (int i = 0; i < directoryNames.length; i++) {
			Optional<Directory> optionalDirectory = directoryRepository.findByName(directoryNames[i]);
			assertThat(optionalDirectory).isPresent();

			Directory directory = optionalDirectory.get();
			assertThat(directory.getName()).isEqualTo(directoryNames[i]);

			for (String username : userNames[i]) {
				Optional<User> optionalUser = userRepository.findByPrincipalAndDirectory(username,directory);
				assertThat(optionalUser).isPresent();
				assertThat(optionalUser.get().getPrincipal()).isEqualTo(username);
			}
		}

		//Not present
		Optional<Directory> optionalDirectory = directoryRepository.findByName("Null");
		assertThat(optionalDirectory).isNotPresent();
	}
}

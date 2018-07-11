package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public void test() {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			user.setEmail("xxx");
			userRepository.findAll();
			userRepository.getOne(1);
			userRepository.save(user);
			roleRepository.findAll();
			User u = new User();
			u.setEmail("email");
			userRepository.save(u);

		}
		// user0.setName("181");
	}

}

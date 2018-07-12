package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;

	public void test() {
		List<User> users = userRepo.findAll();
		// for (User user : users) {
		// user.setEmail("xxx");
		// userRepo.findAll();
		// userRepo.getOne(1);
		// userRepo.save(user);
		// roleRepo.findAll();
		// User u = new User();
		// u.setEmail("email");
		// userRepo.save(u);
		User u = new User();
		Role role = new Role();
		// testRuntimeEx(u, role);
//		testRuntimeExPublic(u, role);
		u.setEmail("ex_email");
		userRepo.save(u);
		// if (!StringUtils.isEmpty(user.getEmail())) {
		// throw new RuntimeException();
		// }
		role.setEmail("role_email");
		roleRepo.save(role);
		if (!StringUtils.isEmpty(u.getEmail())) {
			throw new RuntimeException();
		}

		// }
		// user0.setName("181");
	}

	private void testRuntimeEx(User user, Role role) {
		user.setEmail("ex_email");
		userRepo.save(user);
		// if (!StringUtils.isEmpty(user.getEmail())) {
		// throw new RuntimeException();
		// }
		role.setEmail("role_email");
		roleRepo.save(role);
		if (!StringUtils.isEmpty(user.getEmail())) {
			throw new RuntimeException();
		}
	}

	public void testRuntimeExPublic(User user, Role role) {
		user.setEmail("ex_email");
		userRepo.save(user);
		// if (!StringUtils.isEmpty(user.getEmail())) {
		// throw new RuntimeException();
		// }
		role.setEmail("role_email");
		roleRepo.save(role);
		if (!StringUtils.isEmpty(user.getEmail())) {
			throw new RuntimeException();
		}
	}

}

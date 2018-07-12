package hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import hello.entity.Role;
import hello.entity.User;
import hello.repo.RoleRepository;
import hello.repo.UserRepository;

@Service
public class MyService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private UserService userSvc;
	@Autowired
	private RoleService roleSvc;

	public void test() {
		List<User> users = userRepo.findAll();
		if (users.isEmpty()) {
			User user = new User();
			user.setName("Marry");
			user.setEmail("hello@marry.net");
			userRepo.save(user);
		}
		List<Role> roles = roleRepo.findAll();
		if (roles.isEmpty()) {
			Role role = new Role();
			role.setFlag("admin");
			roleRepo.save(role);
		}
		// testChangeEntityBeforeOtherQueryByHQL();
		// testChangeEntityBeforeOtherQueryByHQLUsePublic();
		// testChangeEntityBeforeOtherQueryByJPA();
		// User user = userRepo.findByName("Marry");
		// testByGivenParamsPrivate(user);
		
		testTransactional();

	}

	private void testTransactional() {
		User user = userSvc.findByName("Marry");
		user.setEmail(user.getEmail().toUpperCase() + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		Role role = roleSvc.findByFlag("admin");

	}

	private void testChangeEntityBeforeOtherQueryByJPA() {
		User user = userRepo.findByName("Marry");
		user.setEmail(user.getEmail().toUpperCase() + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		Role role = roleRepo.findByFlag("admin");

	}

	public void testChangeEntityBeforeOtherQueryByJPAUsePublic() {
		User user = userRepo.findByName("Marry");
		user.setEmail(user.getEmail().toUpperCase() + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		Role role = roleRepo.findByFlag("admin");

	}

	private void testChangeEntityBeforeOtherQueryByHQL() {
		User user = userRepo.findByNameByQuery("Marry");
		user.setEmail(user.getEmail().toUpperCase() + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		Role role = roleRepo.findByFlag("admin");

	}

	public void testChangeEntityBeforeOtherQueryByHQLUsePublic() {
		User user = userRepo.findByNameByQuery("Marry");
		user.setEmail(user.getEmail().toUpperCase() + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		Role role = roleRepo.findByFlagByQuery("admin");

	}

	private void testByGivenParamsPrivate(User user) {
		user.setEmail("ex_email");
		roleRepo.findByFlag("admin");

	}

	public void testByGivenParamsPublic(User user) {
		user.setEmail("ex_email");
		roleRepo.findByFlag("admin");

	}

}

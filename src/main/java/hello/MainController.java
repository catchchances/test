package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

	@Autowired
	private UserService userService;

	// @GetMapping(path = "/add")
	// public @ResponseBody String addNewUser(@RequestParam String name,
	// @RequestParam String email) {
	// User n = new User();
	// n.setName(name);
	// n.setEmail(email);
	// userRepository.save(n);
	// return "Saved";
	// }
	//
	// @GetMapping(path = "/all")
	// public @ResponseBody Iterable<User> getAllUsers() {
	// return userRepository.findAll();
	// }

	@GetMapping(path = "/test")
	public @ResponseBody void test() {
		userService.test();
	}

}
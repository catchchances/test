package hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.service.MyService;
import hello.service.UserService;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

	@Autowired
	private MyService myService;
	
	@Autowired
	private UserService userService;

	@GetMapping(path = "/test")
	public @ResponseBody void test() {
		myService.test();
	}
	
	@GetMapping(path = "/repotest")
	public @ResponseBody void repotest() {
		userService.findByName("xxx");
//		userService.findByNameByQuery("yyy");
	}

}
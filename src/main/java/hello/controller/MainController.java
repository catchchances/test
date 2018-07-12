package hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.service.MyService;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

	@Autowired
	private MyService userService;

	@GetMapping(path = "/test")
	public @ResponseBody void test() {
		userService.test();
	}

}
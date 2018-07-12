package hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.entity.User;
import hello.repo.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public User findByName(String name) {
		return userRepo.findByName(name);
	}

	public User findByNameByQuery(String name) {
		return userRepo.findByNameByQuery(name);
	}

}

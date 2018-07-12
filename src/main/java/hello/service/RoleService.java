package hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.entity.Role;
import hello.repo.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleRepository roleRepo;

	public Role findByFlag(String flag) {
		return roleRepo.findByFlag(flag);
	}

	public Role findByFlagByQuery(String flag) {
		return roleRepo.findByFlagByQuery(flag);
	}

}

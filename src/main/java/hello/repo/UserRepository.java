package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hello.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByName(String name);
	
	@Query("from User where name=?1")
	User findByNameByQuery(String name);

}
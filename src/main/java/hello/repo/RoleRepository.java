package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hello.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByFlag(String flag);

	@Query("from Role where flag=?1")
	Role findByFlagByQuery(String flag);

}
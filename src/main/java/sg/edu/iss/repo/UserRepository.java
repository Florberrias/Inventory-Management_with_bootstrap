package sg.edu.iss.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.edu.iss.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> 
{
	 
	 @Query("SELECT u FROM User u WHERE u.userName=:name")
	 User findByUserName(@Param("name")String name);
	 
	 @Query("SELECT u FROM User u WHERE u.email=:email")
	 User findByEmail(@Param("email")String email);
	 
	 @Query("SELECT u from User u WHERE u.userName=:username and u.password=:password")
	 User findByUsernameAndPassword(@Param("username")String username,@Param("password")String password);
}

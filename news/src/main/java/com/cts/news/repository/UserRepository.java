package com.cts.news.repository;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cts.news.entity.Users;

@Repository // @Repository annotation is used to indicate that the class// provides the mechanism for storage, retrieval, search, update and// delete operation on objects.
public interface UserRepository extends JpaRepository<Users, Long>

{

	Users findByUserMailId(String userMailId);

	Users findByUserName(String userName);

	boolean existsByUserName(String userName);

	boolean existsByUserMailId(String userMailId);

	boolean existsByUserPassword(String userPassword);

	List<Users> findByUserMailIdAndUserPassword(String userMailId, String userPassword);

	Set<Users> findByUserRoleIdAndUserAvailabilityStatus(int userRoleId, int userAvailabilityStatus);

	boolean existsByUserRoleNameAndUserMailId(String mailId, String roleName);

	List<Users> findByUserNameAndUserMailId(String name, String mailId);

	boolean existsByUserId(long userId);

	Users findByUserId(long userId);

}

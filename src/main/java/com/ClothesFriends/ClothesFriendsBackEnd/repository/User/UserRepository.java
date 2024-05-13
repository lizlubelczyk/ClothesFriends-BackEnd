package com.ClothesFriends.ClothesFriendsBackEnd.repository.User;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

}

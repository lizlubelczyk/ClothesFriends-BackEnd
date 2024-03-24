package com.clothesfriends.ClothesFriends.repository;


import com.clothesfriends.ClothesFriends.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}

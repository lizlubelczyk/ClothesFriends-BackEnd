package com.ClothesFriends.ClothesFriendsBackEnd.repository.Inspiration;

import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.Inspiration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspirationRepository extends JpaRepository<Inspiration, Integer> {
    List findAllByUserId(Integer userId);

    List<Inspiration> findByUserIdInOrderByIdDesc(List<Integer> userIds);
}

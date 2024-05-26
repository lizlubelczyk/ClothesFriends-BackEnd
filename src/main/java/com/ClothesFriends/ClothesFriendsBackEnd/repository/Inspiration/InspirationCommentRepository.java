package com.ClothesFriends.ClothesFriendsBackEnd.repository.Inspiration;

import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.InspirationComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspirationCommentRepository extends JpaRepository<InspirationComment, Integer> {


    List<InspirationComment> findAllByInspirationId(Integer inspirationId);
}

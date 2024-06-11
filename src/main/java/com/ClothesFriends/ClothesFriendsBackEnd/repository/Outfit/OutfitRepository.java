package com.ClothesFriends.ClothesFriendsBackEnd.repository.Outfit;

import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.Outfit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutfitRepository extends JpaRepository<Outfit, Integer> {
    Outfit findByUserId(Integer userId);

    @Query(value = "SELECT * FROM Outfit WHERE user_id = :userId ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
    Outfit findLatestByUserId(@Param("userId") Integer userId);

    List<Outfit> findAllByUserId(Integer userId);
}

package com.example.farmeasyserver.repository.post;

import com.example.farmeasyserver.entity.board.community.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommunityRepository extends JpaRepository<CommunityPost,Long> {
    @Query("SELECT cp FROM CommunityPost cp ORDER BY cp.id DESC")
    List<CommunityPost> findTop5OrderByIdDesc();

    @Query("select cp from CommunityPost cp join fetch cp.author where cp.id = :id")
    Optional<CommunityPost> findByIdWithUser(Long id);

}

package com.corbandalas.data.repository;

import com.corbandalas.data.model.Post;
import com.corbandalas.domain.model.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("SELECT distinct p from Post p left JOIN FETCH p.comments left join fetch p.tags")
    List<Post> findAllPosts(Pageable pageable);

    @Query("SELECT distinct p from Post p left JOIN FETCH p.comments left join fetch p.tags where 'date'  between ?1 AND ?2")
    List<Post> findByDateIsBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

//    @Query("SELECT distinct p from Post p left JOIN FETCH p.comments left join fetch p.tags where :tag in p.tags")
    List<Post> findAllPostsByTags(@Param("tag") Tag tag, Pageable pageable);

}
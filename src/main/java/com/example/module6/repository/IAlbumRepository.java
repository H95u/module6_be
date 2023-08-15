package com.example.module6.repository;

import com.example.module6.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findAllByUserId(Long userId);

    @Modifying
    @Query(value = "insert into album(img,user_id) values (:img,:userId)", nativeQuery = true)
    void uploadAlbumImg(@Param("img") String img, @Param("userId") Long userId);
}

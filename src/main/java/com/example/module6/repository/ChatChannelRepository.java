package com.example.module6.repository;

import com.example.module6.model.ChatChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ChatChannelRepository extends JpaRepository<ChatChannel, String> {
    @Query(" FROM"
            + "    ChatChannel c"
            + "  WHERE"
            + "    c.userOne.id IN (:userOnCeId, :userTwoId) "
            + "  AND"
            + "    c.userTwo.id IN (:userOneId, :userTwoId)")
    public List<ChatChannel> findExistingChannel(
            @Param("userOneId") long userOneId, @Param("userTwoId") long userTwoId);

    @Query(value = "SELECT c.uuid FROM ChatChannel c WHERE c.userOne.id IN (:userIdOne, :userIdTwo) AND c.userTwo.id IN (:userIdOne, :userIdTwo)")
    public String getChannelUuid(@Param("userIdOne") long userIdOne, @Param("userIdTwo") long userIdTwo);


    @Query("SELECT c FROM ChatChannel c WHERE c.uuid = :uuid")
    public ChatChannel getChannelDetails(@Param("uuid") String uuid);


}

package com.kakaopay.sprinkle.repository;

import com.kakaopay.sprinkle.model.entity.RoomUser;
import com.kakaopay.sprinkle.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomUserRepository extends JpaRepository<RoomUser,String> {

    Optional<RoomUser> findAllByRoomIdAndUserId(String roomId,long userId);
}

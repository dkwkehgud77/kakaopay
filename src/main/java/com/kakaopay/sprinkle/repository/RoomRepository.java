package com.kakaopay.sprinkle.repository;

import com.kakaopay.sprinkle.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,String> {

}

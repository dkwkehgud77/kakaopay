package com.kakaopay.sprinkle.repository;

import com.kakaopay.sprinkle.model.entity.MoneySprinkle;
import com.kakaopay.sprinkle.model.entity.RoomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoneySprinkleRepository extends JpaRepository<MoneySprinkle,Long> {


    Optional<MoneySprinkle> findByTokenAndSprinkleUserId(String token,String sprinkleUserId);

    Optional<MoneySprinkle> findByTokenAndSprinkleUserIdStartsWith(String token,String roomId);
}

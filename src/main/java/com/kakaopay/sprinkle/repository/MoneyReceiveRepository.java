package com.kakaopay.sprinkle.repository;

import com.kakaopay.sprinkle.model.entity.MoneyReceive;
import com.kakaopay.sprinkle.model.entity.MoneySprinkle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoneyReceiveRepository extends JpaRepository<MoneyReceive,Long> {

    List<MoneyReceive> findByMoneySprinkleSeqAndReceiveStatus(long moneySprinkleSeq, String receiveStatus);

}

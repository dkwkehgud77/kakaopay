package com.kakaopay.sprinkle.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class MoneyReceive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private long receiveMoney;
    private String receiveStatus;
    private String receiveUser;
    private long receiveAt;
    private long expireAt;

    // MoneyReceive N : 1 MoneySprinkle
    @ManyToOne
    private MoneySprinkle moneySprinkle;


}

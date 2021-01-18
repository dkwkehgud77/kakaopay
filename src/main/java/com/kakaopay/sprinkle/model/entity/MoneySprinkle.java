package com.kakaopay.sprinkle.model.entity;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class MoneySprinkle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @NotNull
    @Column(unique = true)
    private String token;

    private long sprinkleMoney;
    private int receiveCount;
    private long sprinkleAt;
    private long expireAt;

    // MoneySprinkle N : 1 RoomUser
    @ManyToOne
    private RoomUser sprinkleUser;


    // MoneyReceive 1 : N MoneySprinkle
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<RoomUser> roomUserList;


}

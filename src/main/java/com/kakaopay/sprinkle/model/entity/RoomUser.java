package com.kakaopay.sprinkle.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class RoomUser {

    @Id
    @Column(unique = true)
    private String id;

    @CreatedDate
    private long joinAt;


    // RoomUser N : 1 room
    @ManyToOne
    private Room room;

    // RoomUser N : 1 user
    @ManyToOne
    private User user;



}

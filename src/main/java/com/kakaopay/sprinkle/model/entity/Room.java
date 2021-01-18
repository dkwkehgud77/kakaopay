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
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class Room {

    @Id
    @NotNull
    @Column(unique = true)
    private String id;

    private String name;

    @CreatedDate
    private long createdAt;

    // User 1 : N RoomUser
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<RoomUser> roomUserList;


}

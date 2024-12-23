package com.ra.module5_project.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "gift_user")
public class GiftUser {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id ;

    @ManyToOne
    @JoinColumn(name = "userId" , referencedColumnName = "id")
    private User user ;

    @ManyToOne
    @JoinColumn(name = "giftId" , referencedColumnName = "id")
    private Gift gift ;

    private boolean status ;
}

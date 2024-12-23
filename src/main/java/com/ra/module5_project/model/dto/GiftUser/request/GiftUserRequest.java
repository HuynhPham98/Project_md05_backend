package com.ra.module5_project.model.dto.GiftUser.request;

import com.ra.module5_project.model.entity.Gift;
import com.ra.module5_project.model.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GiftUserRequest {
    private long userId ;
    private long giftId ;
    private boolean status ;
}

package com.ra.module5_project.model.dto.user.response;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserPaginationAdmin {
   private List<UserResponse> users ;
   private int totalPage;
   private int size ;
   private int currentPage ;
   private long totalElement ;
}

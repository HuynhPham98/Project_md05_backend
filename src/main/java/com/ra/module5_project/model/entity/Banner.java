package com.ra.module5_project.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "banner")

public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", nullable = false)
    private String imageUrl; // Đường dẫn đến hình ảnh banner

    @Column(name = "title", nullable = false)
    private String title; // Tiêu đề của banner
}
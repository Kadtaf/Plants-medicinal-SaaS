package com.plants.api.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "affiliate_products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AffiliateProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String vendor;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price")
    private Double price;

    @Column(nullable = false)
    private String category;

    @Column(name = "associated_plant_id")
    private Long associatedPlantId;
}
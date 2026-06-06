package com.plants.api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "oils")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Oil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(name = "oil_benefits", joinColumns = @JoinColumn(name = "oil_id"))
    @Column(name = "benefit")
    private List<String> benefits;

    @ElementCollection
    @CollectionTable(name = "oil_precautions", joinColumns = @JoinColumn(name = "oil_id"))
    @Column(name = "precaution")
    private List<String> precautions;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "affiliate_link")
    private String affiliateLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id", nullable = false)
    private Plant plant;

}

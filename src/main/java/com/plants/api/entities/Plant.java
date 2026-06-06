package com.plants.api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "plants")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String origin;

    @Column(name = "season_found")
    private String seasonFound;

    @ElementCollection
    @CollectionTable(name = "plant_properties", joinColumns = @JoinColumn(name = "plant_id"))
    @Column(name = "property")
    private List<String> properties;

    @ElementCollection
    @CollectionTable(name = "plant_uses", joinColumns = @JoinColumn(name = "plant_id"))
    @Column(name = "use")
    private List<String> uses;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "affiliate_link")
    private String affiliateLink;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Oil> oils;

}
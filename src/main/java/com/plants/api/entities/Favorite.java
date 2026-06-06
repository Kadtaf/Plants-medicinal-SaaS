package com.plants.api.entities;

import com.plants.api.entities.enums.FavoriteType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "favorites",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "target_id", "type"})
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Utilisateur propriétaire du favori
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // ID de la ressource favorite (plante ou huile)
    @Column(name = "target_id", nullable = false)
    private Long targetId;

    // Type de la ressource favorite
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private FavoriteType type;

    // Date d'ajout
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Permet d'épingler un favori
    @Column(name = "pinned", nullable = false)
    @Builder.Default
    private boolean pinned = false;
}
package dev.heygabo.examapi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList; 
import java.util.List;

@Entity
@Table(name = "text_blocks")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TextBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    
    @OneToMany(mappedBy = "textBlock", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TextBlockImage> images = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
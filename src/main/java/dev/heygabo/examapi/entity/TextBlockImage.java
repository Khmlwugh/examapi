package dev.heygabo.examapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "text_block_images")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TextBlockImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "text_block_id", nullable = false)
    private TextBlock textBlock;

    @Column(name = "image_url", nullable = false, length = 500)
    private String imageUrl;

    @Column(nullable = false)
    @Builder.Default
    private Short position = 1;
}
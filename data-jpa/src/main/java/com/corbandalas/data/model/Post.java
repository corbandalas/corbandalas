package com.corbandalas.data.model;

import com.corbandalas.domain.model.Tag;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "post")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(length = 100_000, nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

    @Column(name = "date")
    private LocalDateTime date;

    @Column
    private boolean deleted;

    @ElementCollection(targetClass = Tag.class, fetch = FetchType.LAZY)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Set<Tag> tags = new LinkedHashSet<>();


}

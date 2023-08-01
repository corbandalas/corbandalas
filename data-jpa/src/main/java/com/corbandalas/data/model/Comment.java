package com.corbandalas.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends BaseEntity{

    @Column(nullable = false, length = 1_000)
    private String text;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "create_date")
    private LocalDateTime createDate;

}

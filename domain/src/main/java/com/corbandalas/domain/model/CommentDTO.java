package com.corbandalas.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class CommentDTO extends BaseDTO {
    private CustomerDTO customer;
    private LocalDateTime date;
    private String text;
}

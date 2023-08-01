package com.corbandalas.domain.model;

import com.corbandalas.domain.utils.Utils;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.beans.Transient;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class PostDTO extends BaseDTO {

    private static final int TRUNC_LIMIT = 200;
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private String title;
    private String text;
    private LocalDateTime date;
    private CustomerDTO customer;
    private Set<Tag> tags = new LinkedHashSet<>();

    @Transient
    public String getFormattedDate() {
        return date.format(dateFormat);
    }

    @Transient
    public String getTruncatedText() {
        return Utils.abbreviate(getText(), TRUNC_LIMIT);
    }

    @Transient
    public String getTruncatedText(int truncLimit) {
        var text = Utils.removeTags(getText());

        return Utils.abbreviate(text, truncLimit);
    }


}

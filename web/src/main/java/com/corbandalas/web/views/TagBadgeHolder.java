package com.corbandalas.web.views;

import com.corbandalas.domain.model.Tag;

import java.util.Map;

public class TagBadgeHolder {
    public static Map<Tag, String> tagsBadgeThemeNames =
            Map.of(Tag.JAVA, "badge pill", Tag.SPRING, "badge success pill",
                    Tag.SQL, "badge error pill", Tag.OTHER, "badge contrast pill");
}

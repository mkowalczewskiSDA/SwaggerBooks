package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class IndustryIdentifier {

    private String type;
    private String identifier;

}

package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class  VolumeInfo{

    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private IndustryIdentifier[] industryIdentifiers;
    private List<String> categories;

}


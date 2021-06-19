package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
public @Data
class Book {
    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private Map<String, String> industryCodes;
    private List<String> categories;
    private boolean isAvailibleInPl;
    private Double priceInPl;

    public Book(Item item) {
        this.title = item.getVolumeInfo().getTitle();
        this.authors = item.getVolumeInfo().getAuthors();
        this.publisher = item.getVolumeInfo().getPublisher();
        this.publishedDate = item.getVolumeInfo().getPublishedDate();
        industryCodes = new HashMap<>();
        for (IndustryIdentifier industryIdentifier : item.getVolumeInfo().getIndustryIdentifiers()){
            this.industryCodes.put(industryIdentifier.getType(), industryIdentifier.getIdentifier());
        }
        this.categories = item.getVolumeInfo().getCategories();
        this.isAvailibleInPl=!item.getSaleInfo().getSaleability().equals("NOT_FOR_SALE");
        if (this.isAvailibleInPl) {
            if (item.getSaleInfo().getSaleability().equals("FREE")) {
                priceInPl = 0.0;
            } else {
                this.priceInPl = item.getSaleInfo().getListPrice().getAmount();
            }
        }



    }
}

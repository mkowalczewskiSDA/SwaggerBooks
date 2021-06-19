package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data
class Item {
    private VolumeInfo volumeInfo;
    private SaleInfo saleInfo;
}

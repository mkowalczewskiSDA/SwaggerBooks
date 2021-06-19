package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Book;
import org.example.model.IndustryIdentifier;
import org.example.model.Item;
import org.example.model.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@PropertySource("classpath:application.properties")
public class JsonParser {
    @Value("${parser.path}")
    private String path;
    @Value("${parser.source}")
    private String source;
    @Value("${parser.url}")
    private String url;

    private List<Book> booksList = new ArrayList<>();

    public List<Book> getBooksList() {
        return booksList;
    }

    @PostConstruct
    public void parseJson() throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        Response response = null;
        switch (source.toLowerCase()){
            case "file": {
                response = mapper.readValue(new FileReader(path), Response.class);
                break;
            }
            case "url": {
                response = mapper.readValue(new URL(url), Response.class);
                break;
            }
            default:
                throw new IOException("wrong source");
        }

        List<Item> items = response.getItems();

        for (Item item : items){
            booksList.add(new Book(item));
        }
        var test = "2";
    }
}

package org.example.service;

import org.example.model.Book;
import org.example.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BooksService {

    @Autowired
    JsonParser jsonParser;

    public Optional<Book> findBookByIndustryIdentifier(String industryIdentifier) {
        return jsonParser.getBooksList()
                .stream()
                .filter(book ->
                        book.getIndustryCodes().containsValue(industryIdentifier))
                .findAny();
    }

    public List<Book> findBooksByCategory(String category) {
        return jsonParser.getBooksList().stream()
                .filter(book -> {
                    if (book.getCategories() != null) {
                        return book.getCategories().contains(category);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public List<Book> getAllBooksByPublisher(String publisher) {
        return  jsonParser.getBooksList()
                .stream()
                .filter(book -> publisher.equals(book.getPublisher()))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByPrice() {
        return jsonParser.getBooksList()
                .stream()
                .filter(Book::isAvailibleInPl)
                .sorted(Comparator.comparing(Book::getPriceInPl).reversed())
                .collect(Collectors.toList());
    }

}

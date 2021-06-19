package org.example.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.example.exception.NoBooksException;
import org.example.model.Book;
import org.example.model.Error;
import org.example.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
public class BooksController {

    @Autowired
    BooksService booksService;

    @ApiOperation("Gets the books by Industry Identifier")
    @GetMapping("api/books/byIdentifier")
    public Book getBooksByIndustryIdentifier(@RequestParam String id, HttpServletResponse response) {
        return booksService.findBookByIndustryIdentifier(id).orElseThrow(NoBooksException::new);
    }

    @GetMapping("/api/category/{category}/books")
    public List<Book> findByCategory(@PathVariable("category") @ApiParam(value = "Category of the books you want to be provided") String category){
        return booksService.findBooksByCategory(category);
    }

    @GetMapping("/api/publisher")
    public List<Book> getByPublisher(@RequestParam String publisher) {
        return booksService.getAllBooksByPublisher(publisher);
    }

    @GetMapping(value = "/api/price")
    public List<Book> getByPrice(){
        return booksService.getBooksByPrice();
    }

    @PutMapping("/api/new")
    public Book addBook(@RequestBody Book book) {
        System.out.println(book);
        return book;
    }

    @ExceptionHandler(NoBooksException .class)
    @GetMapping("/api/book/nonexisting")
    public Error nonExisting(){
        return new Error("Book does not exist");
    }
}

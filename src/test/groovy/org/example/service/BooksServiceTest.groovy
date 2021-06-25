package org.example.service

import org.example.model.Book
import org.example.util.JsonParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
@Unroll
class BooksServiceTest extends Specification  {

    @Autowired
    BooksService service
    JsonParser jsonParser = Mock()
    List<Book> books = [new Book(
                                title: "Test2",
                                authors: ["Test2"],
                                publisher: "Test2",
                                publishedDate: "1998",
                                description: "Test2",
                                industryCodes: ["isbn":"test2"],
                                categories: ["test"],
                                isAvailibleInPl: true,
                                priceInPl: 10
    ),
                        new Book(
                                title: "Test",
                                authors: ["Test"],
                                publisher: "Test",
                                publishedDate: "1999",
                                description: "Test",
                                industryCodes: ["isbn":"test"],
                                categories: ["test"],
                                isAvailibleInPl: true,
                                priceInPl: 20
    ),
                        new Book(
                                title: "Test3",
                                authors: ["Test3"],
                                publisher: "Test3",
                                publishedDate: "1997",
                                description: "Test3",
                                industryCodes: ["isbn2":"test3", "isbn":"test4"],
                                categories: ["test2"],
                                isAvailibleInPl: false,
                                priceInPl: null
                        )
    ]


    def setup() {
        service.setJsonParser(jsonParser)
    }

    def "should return Test3 book by isbn"() {
        given:
        jsonParser.getBooksList() >> books
        when:
        def book = service.findBookByIndustryIdentifier("test4")
        then:
        book.get().title == books.get(2).getTitle()
        book.get().authors.get(0) == books.get(2).getAuthors().get(0)
        book.get().publisher == books.get(2).getPublisher()
    }

    def "should return 2 books"() {
        given:
        jsonParser.getBooksList() >> books
        when:
        def booksFromService = service.findBooksByCategory("test")
        then:

        booksFromService.size() == 2
        booksFromService.get(0).getTitle() == "Test2"
    }

    def "should return each book for each publisher - #publisher"() {
        given:
        jsonParser.getBooksList() >> books
        when:
        def booksFromService = service.getAllBooksByPublisher(publisher)
        then:
        booksFromService.forEach({ book -> book.publishedDate == publishedDate })

        where:
        publisher | publishedDate
        "Test2"   | "1998"
        "Test1"   | "1999"
        "Test3"   | "1997"
    }

    def "should return 2 books sorted by price"() {
        given:
        jsonParser.getBooksList() >> books
        when:
        def booksFromService = service.getBooksByPrice()
        then:
        booksFromService.size() == 2
        booksFromService.get(0).getPriceInPl() == books.get(1).getPriceInPl()
        booksFromService.get(1).getPriceInPl() == books.get(0).getPriceInPl()
    }





}

package book.bookselling.controller;

import book.bookselling.model.Book;
import book.bookselling.model.Country;
import book.bookselling.model.EBookCategory;
import book.bookselling.repository.BookRepository;
import book.bookselling.service.BookService;
import book.bookselling.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final CountryService countryService;

    @Autowired
    public BookController(BookService bookService, BookRepository bookRepository, CountryService countryService) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
        this.countryService = countryService;
    }

    // home page with list of books
    @GetMapping
    public String home(Model model) {
        List<Book> allBooks = bookService.getAllBooks();
        model.addAttribute("allBooks", allBooks);
        return "/book/books";
    }

    // create page: where we save new book
    @GetMapping("/new_book")
    public String newBookForm(Model model) {
        Book book = new Book();
        List<Country> allCountries = countryService.getAllCountries(); // get all countries to be populated in country combo box
        List<EBookCategory> bookCategories = Arrays.stream(EBookCategory.values()).toList(); // get all book categories to be populated in category combo box
        model.addAttribute("book", book);
        model.addAttribute("allCountries", allCountries);
        model.addAttribute("bookCategories", bookCategories);
        return "/book/new-book";
    }
    // create book
    @PostMapping("/new_book")
    public String newBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book); // save book
        return "redirect:/book";
    }

    // update page: where we update book info
    @GetMapping("/{bookSbn}/edit_book")
    public String updateBookForm(@PathVariable("bookSbn") String bookSbn, Model model) {
        Book book = bookRepository.findBookBySbn(bookSbn);
        List<Country> allCountries = countryService.getAllCountries(); // get all countries to be populated in country combo box
        List<EBookCategory> bookCategories = Arrays.stream(EBookCategory.values()).toList(); // get all book categories to be populated in category combo box
        model.addAttribute("allCountries", allCountries);
        model.addAttribute("bookCategories", bookCategories);
        model.addAttribute("book", book);
        return "/book/edit-book";
    }
    // update book
    @PostMapping("/{bookSbn}/edit_book")
    public String editBook(@PathVariable("bookSbn") String bookSbn, @ModelAttribute("book") Book book) {
        book.setSbn(book.sbn);
        bookService.updateBook(book);
        return "redirect:/book";
    }
    // delete book
    @GetMapping("/{bookSbn}/delete_book")
    public String removeBook(@PathVariable("bookSbn") String bookSbn) {
        bookService.deleteBook(bookSbn);
        return "redirect:/book";
    }
}

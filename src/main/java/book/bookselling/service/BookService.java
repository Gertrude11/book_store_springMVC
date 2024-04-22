package book.bookselling.service;

import book.bookselling.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    void saveBook(Book book);
    void updateBook(Book book);
    void deleteBook(String sbn);
}

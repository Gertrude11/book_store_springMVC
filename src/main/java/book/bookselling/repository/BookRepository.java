package book.bookselling.repository;

import book.bookselling.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Book findBookBySbn(String sbn);
}

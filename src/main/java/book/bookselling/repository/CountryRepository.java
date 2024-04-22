package book.bookselling.repository;

import book.bookselling.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    Country findCountryByCountryCode(String countryCode);
}

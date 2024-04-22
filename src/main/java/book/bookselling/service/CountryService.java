package book.bookselling.service;

import book.bookselling.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();
    void saveCountry(Country country);
    void updateCountry(Country country);
    void deleteCountry(String countryCode);
}

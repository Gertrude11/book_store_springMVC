package book.bookselling.service.Impl;

import book.bookselling.model.Country;
import book.bookselling.repository.CountryRepository;
import book.bookselling.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public void saveCountry(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void updateCountry(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void deleteCountry(String countryCode) {
        countryRepository.deleteById(countryCode);
    }
}

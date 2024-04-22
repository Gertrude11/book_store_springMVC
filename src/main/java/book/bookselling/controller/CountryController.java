package book.bookselling.controller;

import book.bookselling.model.Country;
import book.bookselling.repository.CountryRepository;
import book.bookselling.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/country")
public class CountryController {
    private final CountryService countryService;
    private final CountryRepository countryRepository;

    @Autowired
    public CountryController(CountryService countryService, CountryRepository countryRepository) {
        this.countryService = countryService;
        this.countryRepository = countryRepository;
    }

    // home page with list of countries
    @GetMapping
    public String home(Model model) {
        List<Country> allCountries = countryService.getAllCountries();
        model.addAttribute("allCountries", allCountries);
        return "/country/countries";
    }

    // create page: where we save new country
    @GetMapping("/new_country")
    public String newCountryForm(Model model) {
        Country country = new Country();
        model.addAttribute("country", country);
        return "/country/new-country";
    }
    // create country
    @PostMapping("/new_country")
    public String newCountry(@ModelAttribute("country") Country country) {
        countryService.saveCountry(country); // save country
        return "redirect:/country";
    }

    // update page: where we update country info
    @GetMapping("/{countryCode}/edit_country")
    public String updateCountryForm(@PathVariable("countryCode") String countryCode, Model model) {
        Country country = countryRepository.findCountryByCountryCode(countryCode);
        model.addAttribute("country", country);
        return "/country/edit-country";
    }
    // update country
    @PostMapping("/{countryCode}/edit_country")
    public String editCountry(@PathVariable("countryCode") String countryCode, @ModelAttribute("country") Country country) {
        country.setCountryCode(country.countryCode);
        countryService.updateCountry(country);
        return "redirect:/country";
    }
    // delete country
    @GetMapping("/{countryCode}/delete_country")
    public String removeCountry(@PathVariable("countryCode") String countryCode) {
        countryService.deleteCountry(countryCode);
        return "redirect:/country";
    }
}

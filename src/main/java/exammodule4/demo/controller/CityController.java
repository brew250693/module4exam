package exammodule4.demo.controller;

import exammodule4.demo.model.City;
import exammodule4.demo.model.Nation;
import exammodule4.demo.service.ICityService;
import exammodule4.demo.service.INationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class CityController {

    @Autowired
    private ICityService cityService;

    @Autowired
    private INationService nationService;

    @GetMapping("/create-city")
    public ModelAndView showCreateProductForm() {
        ModelAndView modelAndView = new ModelAndView("/city/create-city");
        modelAndView.addObject("cities", new City());
        return modelAndView;
    }

    @ModelAttribute("nations")
    public List<Nation> nations(){
        return (List<Nation>) nationService.findAll();
    }

    @PostMapping("/create-city")
    public ModelAndView saveCity(@ModelAttribute("city") City city) {
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("/city/create-city");
        modelAndView.addObject("nations",nationService.findAll());
        modelAndView.addObject("cities", new City());
        modelAndView.addObject("message", "New city created successfully");
        return modelAndView;
    }

    @GetMapping("/list-city")
    public ModelAndView showListCity() {
        ModelAndView modelAndView = new ModelAndView("/city/list-city");
        modelAndView.addObject("cities", cityService.findAll());
        return modelAndView;
    }

    @GetMapping("/edit-city/{id}")
    public ModelAndView showEditCityForm(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/city/edit-city");
            modelAndView.addObject("cities", city.get());
            return modelAndView;
        } else {
            return new ModelAndView("error-404");
        }
    }

    @PostMapping("/edit-city")
    public ModelAndView updateCity(@ModelAttribute("city") City city) {
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("/city/edit-city");
        modelAndView.addObject("cities", city);
        modelAndView.addObject("message", "City updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-city/{id}")
    public ModelAndView showDeleteCityForm(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/city/delete-city");
            modelAndView.addObject("cities", city.get());
            return modelAndView;

        } else {
            return new ModelAndView("error-404");
        }
    }

    @PostMapping("/delete-city")
    public String deleteCity(@ModelAttribute("city") City city) {
        cityService.remove(city.getId());
        return "redirect:/list-city";
    }
}

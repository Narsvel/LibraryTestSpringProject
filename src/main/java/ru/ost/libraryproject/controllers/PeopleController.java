package ru.ost.libraryproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ost.libraryproject.dao.BookDAO;
import ru.ost.libraryproject.dao.PersonDAO;
import ru.ost.libraryproject.models.Person;
import ru.ost.libraryproject.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        //Получим всех людей из DAO и передадим на отображение в представление
        model.addAttribute("people", personDAO.index());
        return "/people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, ModelMap modelMap) { //@PathVariable("id") int id извлекает знвчение поля {id} и сохраняет его в int id
        modelMap.addAttribute("person", personDAO.show(id));
        modelMap.addAttribute("books", bookDAO.indexPerson(id));
        return "/people/show";
    }

    @GetMapping("/new") //@ModelAttribute создает пустой new Person()
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, //@Valid значения полей person будут проверяться и в случае не удоблетворительных значентий bindingResult выдаст ошибку
                         BindingResult bindingResult) { //BindingResult bindingResult всегда пишится после класса с аннотацией @Valid, в bindingResult будут лежать все ошибки класса person
        personValidator.validate(person, bindingResult); //в bindingResult находятся как ошибки валидации класса Person так и ошибки из personValidator

        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) { //@PathVariable("id") int id извлекает знвчение поля {id} и сохраняет его в int id
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

}

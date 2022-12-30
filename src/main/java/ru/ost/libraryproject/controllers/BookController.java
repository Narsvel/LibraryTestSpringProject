package ru.ost.libraryproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ost.libraryproject.dao.BookDAO;
import ru.ost.libraryproject.dao.PersonDAO;
import ru.ost.libraryproject.models.Book;
import ru.ost.libraryproject.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    private final int NULLBOOKPERSONID = 0;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "/books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, ModelMap modelMap, @ModelAttribute("person") Person person) { //@PathVariable("id") int id извлекает знвчение поля {id} и сохраняет его в int id
        modelMap.addAttribute("book", bookDAO.show(id));
        modelMap.addAttribute("people", personDAO.index());
        modelMap.addAttribute("persons", personDAO.show((bookDAO.show(id).isPersonId() ? NULLBOOKPERSONID : bookDAO.show(id).getPerson_id())));
        return "/books/show";
    }

    @GetMapping("/new") //@ModelAttribute создает пустой new Person()
    public String newPerson(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, //@Valid значения полей person будут проверяться и в случае не удоблетворительных значентий bindingResult выдаст ошибку
                         BindingResult bindingResult) { //BindingResult bindingResult всегда пишится после класса с аннотацией @Valid, в bindingResult будут лежать все ошибки класса person

        if (bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) { //@PathVariable("id") int id извлекает знвчение поля {id} и сохраняет его в int id
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/addPerson/{id}")
    public String addBookPersonId(@ModelAttribute("person") Person person,
                                  @PathVariable("id") int id, ModelMap modelMap) {
        bookDAO.addBookPersonId(id, person.getId());
        modelMap.addAttribute("book", bookDAO.show(id));
        modelMap.addAttribute("people", personDAO.index());
        modelMap.addAttribute("persons", personDAO.show((bookDAO.show(id).isPersonId() ? NULLBOOKPERSONID : bookDAO.show(id).getPerson_id())));
        return "/books/show";
    }

    @PatchMapping("/deletePerson/{id}")
    public String deleteBookPersonId(@ModelAttribute("person") Person person,
                                  @PathVariable("id") int id, ModelMap modelMap) {
        bookDAO.deleteBookPersonId(id);
        modelMap.addAttribute("book", bookDAO.show(id));
        modelMap.addAttribute("people", personDAO.index());
        modelMap.addAttribute("persons", personDAO.show((bookDAO.show(id).isPersonId() ? NULLBOOKPERSONID : bookDAO.show(id).getPerson_id())));
        return "/books/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

}

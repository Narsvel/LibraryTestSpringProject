package ru.ost.libraryproject.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ost.libraryproject.dao.PersonDAO;
import ru.ost.libraryproject.models.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz); //проверка что класс используемый для валидации Person
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target; //после проверки можем сделать приведение к Person

        if (personDAO.show(person.getName()).isPresent())
            if (person.getId() != personDAO.show(person.getName()).get().getId()) //проверка для редактирования что id не совпадают
                errors.rejectValue("name", "", "This name is already taken");
        //первым значение указываем поле где произошла ошибка "name"
        //второй аргумен должен содержать код ошибки, пропускаем его ""
        //третий аргумент содержит сообщение об ошибке
    }

}

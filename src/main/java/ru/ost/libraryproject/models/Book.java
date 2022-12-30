package ru.ost.libraryproject.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {

    private int id;

    private Integer person_id;

    @NotEmpty(message = "Title should not be empty") //поле title не модет быть null
    @Size(min = 1, max = 300, message = "Title should be between 1 and 300 characters") //устанавливаем длинну поля title от 1 до 300
    private String title;

    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 100, message = "Author should be between 1 and 100 characters")
    private String author;

    @NotNull(message = "Введите год")
    private Integer age;

    public Book(int id, String title, String author, Integer age) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.age = age;
    }

    public Book() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public boolean isPersonId() {
        return person_id == null;
    }
}

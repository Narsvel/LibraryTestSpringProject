package ru.ost.libraryproject.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {

    private int id;

    private Integer person_id;

    @NotEmpty(message = "Необходимо ввести название книги") //поле title не модет быть null
    @Size(min = 1, max = 300, message = "Название книги должно быть от 1 до 300 символов") //устанавливаем длинну поля title от 1 до 300
    private String title;

    @NotEmpty(message = "Необходимо ввести автора книги")
    @Size(min = 2, max = 100, message = "Автор книги должно быть от 2 до 100 символов")
    private String author;

    @NotNull(message = "Необходимо ввести год")
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

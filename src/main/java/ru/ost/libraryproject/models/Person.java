package ru.ost.libraryproject.models;

import javax.validation.constraints.*;

public class Person {

    private int id;
    @NotEmpty(message = "Необходимо ввести ФИО") //поле name не модет быть null
    @Size(min = 2, max = 100, message = "ФИО должно быть от 2 до 100 символов") //устанавливаем длинну поля name от 2 до 100
    private String name;

    @NotNull(message = "Введите год рождения")
    @Min(value = 1900, message = "Год рождения должен быть раньше чем 1900")
    private Integer birthYear;

    @NotEmpty(message = "Необходимо ввести адрес")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+, [А-ЯЁ][а-яё]+, \\d{6}", message = "Your address should be in this format: Страна, Город, Почтовый индекс (6 цифр)")
    private String address;

    public Person(int id, String name, Integer birthYear,String address) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.address = address;
    }

    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

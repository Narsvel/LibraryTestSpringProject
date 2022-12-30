package ru.ost.libraryproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ost.libraryproject.models.Book;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Book> indexPerson(int idPerson) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{idPerson},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, age) VALUES( ?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getAge());
    }

    public void update(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, age=? WHERE id=?",
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public void addBookPersonId(int idBook, int idPerson) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?",idPerson , idBook);
    }

    public void deleteBookPersonId(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?",null , id);
    }

}

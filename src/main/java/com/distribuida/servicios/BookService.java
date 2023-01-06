package com.distribuida.servicios;

import com.distribuida.db.Book;

import java.util.List;

public interface BookService {

    List <Book> findAll();

    Book findById(Integer id);

    Book createBook(String jsonBook);

    String deleteBook(Integer id);

    Book updateBook(Integer id, String jsonBook);
}

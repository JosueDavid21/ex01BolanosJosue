package com.distribuida.servicios;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.jdbi.v3.core.Handle;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements BookService {

    @Inject private DbConfig dbConfig;

    public List<Book> findAll() {
        List<Book> list = null;
        try {
            Handle handle = dbConfig.conectionDB().open();
            list = handle.createQuery("SELECT * FROM Books").mapToBean(Book.class).list();
            handle.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Book findById(Integer id) {
        Book book = null;
        try {
            Handle handle = dbConfig.conectionDB().open();
            List<Book> books = handle.createQuery("SELECT * FROM public.books WHERE id = " + id + ";")
                    .mapToBean(Book.class).list();
            if(books.size()>0){
                book = books.get(0);
            }
            handle.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    private List<Book> lista= new ArrayList<>();
    public Book createBook(String json){
        Jsonb jsonb = JsonbBuilder.create();
        Book book = null;
        try{
            book = jsonb.fromJson(json, Book.class);
            Handle handle = dbConfig.conectionDB().open();
            handle.createUpdate("INSERT INTO books (id, isbn, title, author, price) " +
                            "VALUES (:id, :isbn, :title, :author, :price)")
                    .bindBean(book)
                    .execute();
            handle.close();
        }catch (Exception e){
            System.err.println(e + "\nHubo problemas al crear el libro.....");
        }
        return book;
    }

    public String deleteBook(Integer id){
        String response = "No se pudo eliminar el libro....";
        try{
            Handle handle = dbConfig.conectionDB().open();
            int state=handle.execute("DELETE FROM books " +
                            "WHERE id=" + id + ";");
            if(state==1){
                response = "Libro eliminado con exito...";
            }
            handle.close();
        }catch (Exception e){
            System.err.println(e + "\nHubo problemas al eliminar el libro.....");
        }
        return response;
    }

    public Book updateBook(Integer id, String jsonBook) {
        Jsonb jsonb = JsonbBuilder.create();
        Book book = null;
        try{
            book = jsonb.fromJson(jsonBook, Book.class);
            book.setId(id);
            Handle handle = dbConfig.conectionDB().open();
            handle.createUpdate("UPDATE books SET id= :id, isbn= :isbn, title= :title, author= :author, price= :price " +
                            "WHERE id=" + id + ";")
                    .bindBean(book)
                    .execute();
            handle.close();
        }catch (Exception e){
            System.err.println(e + "\nHubo problemas al actualizar el libro.....");
        }
        return book;
    }
}

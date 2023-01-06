package com.distribuida.rest;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import com.distribuida.servicios.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.MediaType;

import java.util.List;


@ApplicationScoped
@Path("/books")
public class BookRest {
    @Inject private BookService bookService;
    @Inject private DbConfig dbConfig;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book findBy(@PathParam("id") Integer id){
        return bookService.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Book createBook(String jsonBook) {
        return bookService.createBook(jsonBook);
    }

    @DELETE
    @Path("/{id}")
    public String deleteBook(@PathParam("id") Integer id){
        return bookService.deleteBook(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Book updateBook(@PathParam("id") Integer id, String jsonBook ){
      return bookService.updateBook(id, jsonBook);
    }
}

package com.codixlab.mvvm.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.codixlab.mvvm.model.Book;

import java.util.List;

@Dao
public interface BookDAO {

    @Insert
    void insert(Book book);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);

    @Query("Select * From books_table")
    LiveData<List<Book>> getAllBooks();

    @Query("Select * From books_table where book_category_id ==:categoryId")
    LiveData<List<Book>> getBooks(int categoryId);
}

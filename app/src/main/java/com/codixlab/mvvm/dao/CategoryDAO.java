package com.codixlab.mvvm.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.codixlab.mvvm.model.Category;

import java.util.List;

@Dao
public interface CategoryDAO {

    @Insert
    long insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    @Query("Select * From categories_table")
    LiveData<List<Category>> getAllCategories();
}

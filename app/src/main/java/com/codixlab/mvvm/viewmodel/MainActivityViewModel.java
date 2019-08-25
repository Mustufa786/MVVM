package com.codixlab.mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.codixlab.mvvm.model.Book;
import com.codixlab.mvvm.model.Category;
import com.codixlab.mvvm.repo.BookAppRepo;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    BookAppRepo repo;
    LiveData<List<Book>> getAllBooks;
    LiveData<List<Category>> getAllCategories;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repo = new BookAppRepo(application);

    }

    public LiveData<List<Book>> getGetAllBooks(int id) {
        return repo.getBooks(id);
    }

    public LiveData<List<Category>> getGetAllCategories() {
        return repo.getCategories();
    }

    public void addBook(Book book) {

        repo.insertBook(book);
    }

    public void updateBook(Book book) {

        repo.updateBook(book);
    }

    public void deleteBook(Book book) {

        repo.deleteBook(book);
    }
}

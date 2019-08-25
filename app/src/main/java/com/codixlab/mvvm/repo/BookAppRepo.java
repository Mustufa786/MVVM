package com.codixlab.mvvm.repo;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.codixlab.mvvm.dao.BookDAO;
import com.codixlab.mvvm.dao.CategoryDAO;
import com.codixlab.mvvm.database.AppDatabase;
import com.codixlab.mvvm.model.Book;
import com.codixlab.mvvm.model.Category;

import java.util.List;

public class BookAppRepo {

    private CategoryDAO categoryDAO;
    private BookDAO bookDAO;
    private LiveData<List<Category>> categories;
    private LiveData<List<Book>> books;

    private static final String TAG = "BookAppRepo";

    public BookAppRepo(Application application) {

        AppDatabase database = AppDatabase.getInstance(application);
        categoryDAO = database.categoryDAO();
        bookDAO = database.bookDAO();
    }

    public LiveData<List<Category>> getCategories() {
        return categoryDAO.getAllCategories();
    }

    public LiveData<List<Book>> getBooks(int catID) {
        return bookDAO.getBooks(catID);
    }

    public void insertCategory(Category category) {

        new CategoryInsetTask(categoryDAO).execute(category);

    }

    public void insertBook(Book book) {

        new BookInsetTask(bookDAO).execute(book);

    }

    public void updateCategory(Category category) {

        new UpdateCategoryTask(categoryDAO).execute(category);

    }

    public void deleteCategory(Category category) {

        new DeleteCategoryTask(categoryDAO).execute(category);

    }

    public void updateBook(Book book) {

        new UpdateBookTask(bookDAO).execute(book);

    }

    public void deleteBook(Book book) {

        new DeleteBookTask(bookDAO).execute(book);

    }

    private static class CategoryInsetTask extends AsyncTask<Category, Void, Void> {

        CategoryDAO categoryDAO;

        public CategoryInsetTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {

            long id = categoryDAO.insert(categories[0]);



            return null;
        }
    }

    private static class BookInsetTask extends AsyncTask<Book, Void, Void> {

        BookDAO bookDAO;

        public BookInsetTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {

            bookDAO.insert(books[0]);
            return null;
        }
    }

    private static class UpdateBookTask extends AsyncTask<Book, Void, Void> {

        BookDAO bookDAO;

        public UpdateBookTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {

            bookDAO.update(books[0]);
            return null;
        }
    }

    private static class DeleteBookTask extends AsyncTask<Book, Void, Void> {

        BookDAO bookDAO;

        public DeleteBookTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {

            bookDAO.delete(books[0]);
            return null;
        }
    }

    private static class UpdateCategoryTask extends AsyncTask<Category, Void, Void> {

        CategoryDAO categoryDAO;

        public UpdateCategoryTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {

            categoryDAO.update(categories[0]);


            return null;
        }
    }

    private static class DeleteCategoryTask extends AsyncTask<Category, Void, Void> {

        CategoryDAO categoryDAO;

        public DeleteCategoryTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {

            categoryDAO.delete(categories[0]);


            return null;
        }
    }
}

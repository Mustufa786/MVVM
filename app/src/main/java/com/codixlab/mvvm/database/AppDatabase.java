package com.codixlab.mvvm.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.codixlab.mvvm.dao.BookDAO;
import com.codixlab.mvvm.dao.CategoryDAO;
import com.codixlab.mvvm.model.Book;
import com.codixlab.mvvm.model.Category;

@Database(entities = {Category.class, Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = "AppDatabase";


    public abstract CategoryDAO categoryDAO();

    public abstract BookDAO bookDAO();

    private static AppDatabase INSTANCE;

    public static synchronized AppDatabase getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "books_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallbacks)
                    .build();
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallbacks = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            Log.d(TAG, "onCreate:  runsssssss ");

            new InitialData(INSTANCE).execute();


        }
    };

    private static class InitialData extends AsyncTask<Void, Void, Void> {

        BookDAO bookDAO;
        CategoryDAO categoryDAO;

        public InitialData(AppDatabase database) {

            bookDAO = database.bookDAO();
            categoryDAO = database.categoryDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {


            Category category1 = new Category();
            category1.setName("Romantic");
            category1.setDesc("Romantic Novels");
            Category category2 = new Category();
            category2.setName("Killer");
            category2.setDesc("Killer Novels");

            long id = categoryDAO.insert(category1);

            categoryDAO.insert(category2);


            Book book1 = new Book();
            book1.setBookName("df Me");
            book1.setCategoryId(1);
            book1.setUnitPrice("$130");

            Book book2 = new Book();
            book2.setBookName("cv Me");
            book2.setCategoryId(1);
            book2.setUnitPrice("$130");
            Book book3 = new Book();
            book3.setBookName("gg Me");
            book3.setCategoryId(1);
            book3.setUnitPrice("$130");
            Book book4 = new Book();
            book4.setBookName("wer Me");
            book4.setCategoryId(2);
            book4.setUnitPrice("$130");
            Book book5 = new Book();
            book5.setBookName("wr Me");
            book5.setCategoryId(2);
            book5.setUnitPrice("$130");
            Book book6 = new Book();
            book6.setBookName("erer Me");
            book6.setCategoryId(2);
            book6.setUnitPrice("$130");
            Book book7 = new Book();
            book7.setBookName("dsfsd Me");
            book7.setCategoryId(2);
            book7.setUnitPrice("$130");

            bookDAO.insert(book1);
            bookDAO.insert(book2);
            bookDAO.insert(book3);
            bookDAO.insert(book4);
            bookDAO.insert(book5);
            bookDAO.insert(book6);
            bookDAO.insert(book7);


            return null;
        }
    }

}

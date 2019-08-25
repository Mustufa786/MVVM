package com.codixlab.mvvm;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codixlab.mvvm.adapter.BooksAdapter;
import com.codixlab.mvvm.databinding.ActivityMainBinding;
import com.codixlab.mvvm.model.Book;
import com.codixlab.mvvm.model.Category;
import com.codixlab.mvvm.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bi;
    MainActivityClickHandlers handlers;
    private Category selectedCategory;
    private ArrayList<Category> categoriesList;
    MainActivityViewModel viewModel;
    ArrayList<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handlers = new MainActivityClickHandlers();
        bi.setClickHandler(handlers);

        setSupportActionBar(bi.toolbar);


        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.getGetAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {

                categoriesList = (ArrayList<Category>) categories;

                showOnSpinner();
            }
        });


        bi.secondLayout.categoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedCategory = (Category) adapterView.getItemAtPosition(i);

                loadBooks(selectedCategory.getId());

//

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void loadBooks(int categoryId) {

        viewModel.getGetAllBooks(categoryId).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {

                bookList = (ArrayList<Book>) books;
                loadRecyclerView();
            }
        });

    }

    private void loadRecyclerView() {

        RecyclerView rv = bi.secondLayout.bookList;
        BooksAdapter adapter = new BooksAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        adapter.setBooks(bookList);
    }

    private void showOnSpinner() {

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, categoriesList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        bi.setSpinnerAdapter(arrayAdapter);
    }

    public class MainActivityClickHandlers {

        public void onFabClick(View view) {
            Toast.makeText(MainActivity.this, "fab clicked", Toast.LENGTH_SHORT).show();
        }


    }

}

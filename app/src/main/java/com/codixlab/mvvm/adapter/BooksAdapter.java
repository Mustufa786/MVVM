package com.codixlab.mvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.codixlab.mvvm.R;
import com.codixlab.mvvm.databinding.BookListItemBinding;
import com.codixlab.mvvm.model.Book;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    OnItemClickListener listener;
    ArrayList<Book> books;

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        BookListItemBinding item = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.book_list_item, parent, false);
        return new BookViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        Book book = books.get(position);
        if (book != null) {
            holder.bi.setBook(book);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {


        BookListItemBinding bi;

        public BookViewHolder(@NonNull BookListItemBinding binding) {
            super(binding.getRoot());

            this.bi = binding;
            bi.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int clickedItem = getAdapterPosition();
                    if (listener != null && clickedItem != RecyclerView.NO_POSITION)
                        listener.OnItemClick(books.get(clickedItem));

                }
            });
        }
    }

    public interface OnItemClickListener {

        void OnItemClick(Book book);
    }


    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}


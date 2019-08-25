package com.codixlab.mvvm.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "categories_table")
public class Category extends BaseObservable {

    @PrimaryKey(autoGenerate = true )
    private int id;
    @ColumnInfo(name = "category_name")
    private String name;
    @ColumnInfo(name = "category_desc")
    private String desc;

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

        notifyPropertyChanged(com.codixlab.mvvm.BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
        notifyPropertyChanged(BR.desc);
    }

    @Override
    public String toString() {
        return getName();
    }

    public Category() {
    }
}



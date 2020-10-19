package com.example.prefetch_recyclerview;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, TiktokDataModel.Msg>> itemlivedatasource = new MutableLiveData<>();


    @NonNull
    @Override
    public DataSource create() {

        ItemDataSource itemDataSource = new ItemDataSource();
        itemlivedatasource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, TiktokDataModel.Msg>> getItemlivedatasource() {
        return itemlivedatasource;
    }
}


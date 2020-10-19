package com.example.prefetch_recyclerview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

public class ItemViewModel extends ViewModel {

    LiveData<PagedList<TiktokDataModel.Msg>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, TiktokDataModel.Msg>> liveDataSource;

    public ItemViewModel(){
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getItemlivedatasource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setPageSize(5)
                        .setPrefetchDistance(5)
                        .setEnablePlaceholders(true)
                        .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory,config)).build();

    }
}

package com.example.prefetch_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.prefetch_recyclerview.NetworkCalls.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private List<TiktokDataModel.Msg> datamodel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidNetworking.initialize(getApplicationContext());
        textView = findViewById(R.id.text);
        datamodel = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
//        SnapHelper snapHelper =  new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);


        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        final RecyclerviewAdapter adapter = new RecyclerviewAdapter(this);

        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<TiktokDataModel.Msg>>() {
            @Override
            public void onChanged(PagedList<TiktokDataModel.Msg> resultsBeans) {
                adapter.submitList(resultsBeans);
            }
        });

        recyclerView.setAdapter(adapter);


        //Getvideos();
      //  loadData();

    }

    private void Getvideos() {

        JsonObject postParam = new JsonObject();
        try {
            postParam.addProperty("fb_id", "");
            postParam.addProperty("my_fb_id", "");
        } catch (Exception e){
            e.printStackTrace();
        }

        final String result = postParam.toString();

        RetrofitClient.getInstance()
                .getApi()
                .getvideos(postParam)
                .enqueue(new Callback<DataModel2>() {
                    @Override
                    public void onResponse(Call<DataModel2> call, Response<DataModel2> response) {
                        if(response.body()  != null){
                           // Toast.makeText(MainActivity.this,String.valueOf(response),Toast.LENGTH_LONG).show();
                            textView.setText(String.valueOf(response.body().getMsg()));

                           // datamodel = response.body().getMsg();

                            Log.e("e", response.toString());
                        }else {
                           // Toast.makeText(MainActivity.this,"No Response",Toast.LENGTH_LONG).show();
                            textView.setText("No Response");
                        }
                    }

                    @Override
                    public void onFailure(Call<DataModel2> call, Throwable t) {
                      //  Toast.makeText(MainActivity.this,t.toString(),Toast.LENGTH_LONG).show();
                        textView.setText("Error "+String.valueOf(t));
                        Log.e("e", t.toString());
                    }


                });
    }

    private void loadData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("fb_id", "");
            jsonObject.put("my_fb_id", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post("https://hcapi.helocherry.com/?p=showAllVideos")
                .addJSONObjectBody(jsonObject) // posting json
                // .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("msg");
                          //      Toast.makeText(MainActivity.this, jsonArray.toString(), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject data = jsonArray.getJSONObject(i);
                                //   Toast.makeText(MainActivity.this, product.getString("video"), Toast.LENGTH_LONG).show();
                                datamodel.add(new TiktokDataModel.Msg(
                                        data.getString("created"),
                                        data.getString("description"),
                                        data.getString("privacy_type"),
                                        data.getString("gif"),
                                        data.getString("thum"),
                                        data.getString("video"),
                                        data.getString("liked"),
                                        data.getString("fb_id"),
                                        data.getString("id")
                                ));
                                Log.d("da",  datamodel.get(i).getVideo() );

                            }
                            Toast.makeText(MainActivity.this, datamodel.get(0).getVideo(), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MainActivity.this, anError.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
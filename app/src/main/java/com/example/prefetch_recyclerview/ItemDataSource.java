package com.example.prefetch_recyclerview;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemDataSource extends PageKeyedDataSource<Integer, TiktokDataModel.Msg> {

    private static final int PAGE_NO = 1;
    private static final String API_KEY = "434fcadef5103207fecca9176385a533";
    private List<TiktokDataModel.Msg> datamodel;


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, TiktokDataModel.Msg> callback) {

        // ===========================================================================================================

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("fb_id", "");
            jsonObject.put("my_fb_id", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        datamodel = new ArrayList<>();
        // ===========================================================================================================
        AndroidNetworking.post("https://hcapi.helocherry.com/?p=showAllVideos&page={pageNumber}")
                .addJSONObjectBody(jsonObject) // posting json
                .addPathParameter("pageNumber", String.valueOf(PAGE_NO))
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("msg");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject data = jsonArray.getJSONObject(i);
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
                                Log.d("da",  datamodel.get(i).getThum() );

                            }
                            callback.onResult(datamodel,null, PAGE_NO + 1);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                    }
                });

        //==============================================================================================================




    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, TiktokDataModel.Msg> callback) {
        // ===========================================================================================================

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("fb_id", "");
            jsonObject.put("my_fb_id", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        datamodel = new ArrayList<>();
        // ===========================================================================================================
        AndroidNetworking.post("https://hcapi.helocherry.com/?p=showAllVideos&page={pageNumber}")
                .addJSONObjectBody(jsonObject) // posting json
                .addPathParameter("pageNumber", String.valueOf(params.key))
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("msg");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject data = jsonArray.getJSONObject(i);
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
                                Log.d("da",  datamodel.get(i).getThum() );

                            }
                            Integer key = (params.key < 992) ? params.key +1 : null;
                            callback.onResult(datamodel, key);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                    }
                });

        //==============================================================================================================

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, TiktokDataModel.Msg> callback) {

        // ===========================================================================================================

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("fb_id", "");
            jsonObject.put("my_fb_id", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        datamodel = new ArrayList<>();
        // ===========================================================================================================
        AndroidNetworking.post("https://hcapi.helocherry.com/?p=showAllVideos&page={pageNumber}")
                .addJSONObjectBody(jsonObject) // posting json
                .addPathParameter("pageNumber", String.valueOf(params.key))
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("msg");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject data = jsonArray.getJSONObject(i);
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
                                Log.d("da",  datamodel.get(i).getThum() );

                            }
                            Integer key = (params.key < 992) ? params.key +1 : null;
                            callback.onResult(datamodel, key);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                    }
                });

        //==============================================================================================================

    }
}

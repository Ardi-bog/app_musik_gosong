package com.example.boss.go_song;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.boss.go_song.app.AdapterLagu;
import com.example.boss.go_song.app.AppController;
import com.example.boss.go_song.app.Server;
import com.example.boss.go_song.app.dataLagu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class search extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    ListView list;
    SwipeRefreshLayout swipe;
    List<dataLagu> itemList = new ArrayList<dataLagu>();
    AdapterLagu adapter;

    private static final String TAG = search.class.getSimpleName();
    private static String url_select     = Server.URL + "tampil/tampil_genre.php";
    String tag_json_obj = "json_obj_req";

    public static final String TAG_ID_LAGU       = "id";
    public static final String TAG_JUDUL     = "judul";
    public static final String TAG_ARTIS   = "artis";

    public search() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        swipe   = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        list    = (ListView) v.findViewById(R.id.list);

        // untuk mengisi data dari JSON ke dalam adapter
        adapter = new AdapterLagu(getActivity(), itemList);
        list.setAdapter(adapter);

        // menamilkan widget refresh
        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
               @Override
               public void run() {
                   swipe.setRefreshing(true);
                   itemList.clear();
                   adapter.notifyDataSetChanged();
                   callVolley();
               }
           }
        );




        return v;
    }

    @Override
    public void onRefresh() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        callVolley();
    }
    private void callVolley(){
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        // membuat request JSON
        JsonArrayRequest jArr = new JsonArrayRequest(url_select, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        dataLagu item = new dataLagu();

                        item.setId(obj.getString(TAG_ID_LAGU));
                        item.setJudul(obj.getString(TAG_JUDUL));
                        item.setArtis(obj.getString(TAG_ARTIS));

                        // menambah item ke array
                        itemList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifikasi adanya perubahan data pada adapter
                adapter.notifyDataSetChanged();

                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        // menambah request ke request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }
}

package com.appbasic.task3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String URL = "http://45.79.180.7/api/v1/categories";
    Button click;
    static int width, height;
    RecyclerView recyclerview;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RequestQueue reqQueue;
    ArrayList al;
    List<GetDataAdapter> GetDataAdapter1;
    RecyclerView.Adapter recyclerViewadapter;
    GetDataAdapter getdataadapter;
    String tag_json_object = "json_obj_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(recyclerViewlayoutManager);
        GetDataAdapter1 = new ArrayList<>();


        click = (Button) findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSON_DATA_WEB_CALL();
            }
        });


    }

    private void JSON_DATA_WEB_CALL() {
        JsonObjectRequest jsonobjreq = new JsonObjectRequest(URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSON_PARSE_AFTER_WEBCALL(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
       /* reqQueue = Volley.newRequestQueue(this);
        reqQueue.add(jsonobjreq);*/

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonobjreq);
    }

    private void JSON_PARSE_AFTER_WEBCALL(JSONObject response) {

        try {
            JSONArray jsonarry = response.getJSONArray("data");
            JSONObject jsonobj = null;

            for (int i = 0; i < jsonarry.length(); i++) {
                jsonobj = jsonarry.getJSONObject(i);
                //   Log.d("bbbb","===="+jsonobj);
                // Log.d("8888","999"+jsonobj.getString("categoryName"));
                getdataadapter = new GetDataAdapter();
                getdataadapter.set_id(jsonobj.getString("_id"));
                getdataadapter.setCategoryName(jsonobj.getString("categoryName"));
                getdataadapter.setCaption(jsonobj.getString("caption"));
                getdataadapter.setOrientation(jsonobj.getString("orientation"));
                getdataadapter.setPlatform(jsonobj.getString("platform"));
                getdataadapter.setThumbnail(jsonobj.getString("thumbnail"));

                GetDataAdapter1.add(getdataadapter);




            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.d("8888","---"+GetDataAdapter1.size());
        recyclerViewadapter = new RecyclerViewAdapter(GetDataAdapter1, this);
        recyclerview.setAdapter(recyclerViewadapter);

    }
}

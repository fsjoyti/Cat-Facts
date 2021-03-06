package com.example.anastasia.funfacts;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class FunFactsActivity extends AppCompatActivity {
    public static final String TAG = FunFactsActivity.class.getSimpleName();
    private Button mShowbutton;
    private TextView mFactTextView;
    private RelativeLayout mRelativeLayout;

    String url = "http://catfacts-api.appspot.com/api/facts?number=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);
        mFactTextView = (TextView)findViewById(R.id.factTextView);
        mShowbutton = (Button)findViewById(R.id.showFactbutton);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        mShowbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The button was clicked, so update the fact Textview with a new fac
                final RequestQueue queue = Volley.newRequestQueue(FunFactsActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jObject = null;
                        try {
                            jObject = new JSONObject(response);
                            ColorGenerator c =  new ColorGenerator();
                            JSONArray facts = jObject.getJSONArray("facts");
                            for (int i = 0; i < facts.length();i++){

                                mFactTextView.setText(facts.getString(i));
                                int color = c.getColor();
                                mRelativeLayout.setBackgroundColor(color);
                                mShowbutton.setTextColor(color);

                            }

                            queue.stop();

                        }
                        catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mFactTextView.setText("Something went wrong");
                    }
                });
                queue.add(stringRequest);
            }
        });
        //Toast.makeText(FunFactsActivity.this,"Welcome to Cat Facts!!",Toast.LENGTH_SHORT).show();
        Log.d(TAG,"On onCreate method");
    }
}

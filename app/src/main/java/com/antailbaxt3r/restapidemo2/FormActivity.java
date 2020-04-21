package com.antailbaxt3r.restapidemo2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.antailbaxt3r.restapidemo2.retrofit.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity {

    private EditText nameEt;
    private EditText ageEt;
    private EditText latEt;
    private EditText longEt;
    private Button sendButton;
    private String name = "", age = "", lat = "", longTxt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        initView();

        sendButton.setOnClickListener((v) -> {
            name = nameEt.getText().toString().trim();
            age = ageEt.getText().toString().trim();
            lat = latEt.getText().toString().trim();
            longTxt = longEt.getText().toString().trim();

            if(name.isEmpty()) nameEt.setError("Invalid Name");
            else nameEt.setError(null);

            if(age.isEmpty()) ageEt.setError("Invalid Age");
            else ageEt.setError(null);

            if(lat.isEmpty()) latEt.setError("Invalid Latitude");
            else latEt.setError(null);

            if(longTxt.isEmpty()) longEt.setError("Invalid Longitude");
            else longEt.setError(null);

            if(nameEt.getError() == null && ageEt.getError() == null && latEt.getError() == null && longEt.getError() == null){
                send(name, age, lat, longTxt);
            }
        });

    }

    private void send(String name, String age, String lat, String longTxt) {

        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        map.put("foundLost", "NotFound");
        map.put("lastLocationLat", lat);
        map.put("lastLocationLong", longTxt);

        Call<Void> call = RetrofitClient.getClient().sendPerson(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("Request", "Sent!");
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Person added!", Toast.LENGTH_SHORT).show();
                    Log.i("Request", "Success");
                }else{
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    Log.i("Request", "Failure!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                Log.i("Request", "Failed with error message");
            }
        });
    }

    private void initView() {
        nameEt = (EditText) findViewById(R.id.name_et);
        ageEt = (EditText) findViewById(R.id.age_et);
        latEt = (EditText) findViewById(R.id.lat_et);
        longEt = (EditText) findViewById(R.id.long_et);
        sendButton = (Button) findViewById(R.id.send_button);
    }
}

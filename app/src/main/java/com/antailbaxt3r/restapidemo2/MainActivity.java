package com.antailbaxt3r.restapidemo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.antailbaxt3r.restapidemo2.models.Person;
import com.antailbaxt3r.restapidemo2.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.form_button).setOnClickListener((v) -> {
            Intent form = new Intent(MainActivity.this, FormActivity.class);
            startActivity(form);
        });

        recyclerView = findViewById(R.id.person_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Map<String, Object> map = new HashMap<>();
        map.put("results", 5);
        map.put("gender", "male");
        map.put("age", 20);
        map.put("location", "California");


        Call<List<Person>> call = RetrofitClient.getClient().getPeople();
        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                Log.i("Started","TRUE");
                List<Person> list = response.body();
                adapter = new PersonAdapter(list);
                Log.i("List first", "TEST " + list.get(0).getName());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Log.i("Started","FALSE");
                t.printStackTrace();
            }
        });

    }
}

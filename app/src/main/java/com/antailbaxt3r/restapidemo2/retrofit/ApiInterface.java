package com.antailbaxt3r.restapidemo2.retrofit;

import com.antailbaxt3r.restapidemo2.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET("safePeople")
    Call<List<Person>> getPeople();

    @POST("form")
    Call<Void> sendPerson(@Body Map<String, String> map);
}

package com.example.restapi_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
ApiInterface apiInterface;
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        apiInterface=RetrofitInstance.getRetrofit().create(ApiInterface.class);

        apiInterface.getPosts().enqueue(new Callback<List<PostPOJO>>() {
            @Override
            public void onResponse(Call<List<PostPOJO>> call, Response<List<PostPOJO>> response) {
               if(response.body().size()>0){
                  List<PostPOJO>postList=response.body();
                   Adapter adapter=new Adapter(postList,MainActivity.this);
                   LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
                   recyclerView.setLayoutManager(linearLayoutManager);
                   recyclerView.setAdapter(adapter);
               }else{
                   Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_SHORT).show();
               }


            }

            @Override
            public void onFailure(Call<List<PostPOJO>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
package com.lks.zendetta_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lks.zendetta_app.Adapter.DetailAdapter;
import com.lks.zendetta_app.Interface.api;
import com.lks.zendetta_app.Model.requestModel;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailRequestActivity extends AppCompatActivity {
    String idNumber;
    RecyclerView recyclerViewDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_request);

        recyclerViewDetail = findViewById(R.id.recyclerViewDetail);
        Bundle extras = getIntent().getExtras();
        idNumber = extras.getString("id");

        apicall();
    }

    private void apicall() {
        recyclerViewDetail.setLayoutManager(new LinearLayoutManager(this));

        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient okHttpClient = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        api api = retrofit.create(api.class);

        Call<List<requestModel>> cal = api.getVisit2(idNumber);

        cal.enqueue(new Callback<List<requestModel>>() {
            @Override
            public void onResponse(Call<List<requestModel>> call, Response<List<requestModel>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(DetailRequestActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(DetailRequestActivity.this, "berhasil", Toast.LENGTH_SHORT).show();
                List<requestModel> requestModelList = response.body();
                DetailAdapter detailAdapter = new DetailAdapter(requestModelList, DetailRequestActivity.this);
                recyclerViewDetail.setAdapter(detailAdapter);
            }

            @Override
            public void onFailure(Call<List<requestModel>> call, Throwable t) {
                Toast.makeText(DetailRequestActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
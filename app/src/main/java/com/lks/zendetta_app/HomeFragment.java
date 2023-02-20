package com.lks.zendetta_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lks.zendetta_app.Adapter.HomeAdapter;
import com.lks.zendetta_app.Adapter.RequestAdapter;
import com.lks.zendetta_app.Interface.api;
import com.lks.zendetta_app.Model.home;
import com.lks.zendetta_app.Model.requestModel;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    View itemView;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = itemView.findViewById(R.id.recyclerViewHome);
        Button btn = itemView.findViewById(R.id.btnSeeAll);

        recyclerViewHomePage();

        return itemView;
    }

    private void recyclerViewHomePage() {
        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));

        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient okHttpClient = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        api api = retrofit.create(api.class);

        Call<List<requestModel>> call = api.getFirst();

        call.enqueue(new Callback<List<requestModel>>() {
            @Override
            public void onResponse(Call<List<requestModel>> call, Response<List<requestModel>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(itemView.getContext(), "gagal", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(itemView.getContext(), "berhasil", Toast.LENGTH_LONG).show();
                List<requestModel> requestModelList = response.body();
                RequestAdapter requestAdapter = new RequestAdapter(requestModelList, itemView.getContext());
                recyclerView.setAdapter(requestAdapter);
            }

            @Override
            public void onFailure(Call<List<requestModel>> call, Throwable t) {
                Toast.makeText(itemView.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
//        Call<List<home>> call = api.getHome();

//        call.enqueue(new Callback<List<home>>() {
//            @Override
//            public void onResponse(Call<List<home>> call, Response<List<home>> response) {
//                if (!response.isSuccessful()){
//                    Toast.makeText(itemView.getContext(), "gagal", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                Toast.makeText(itemView.getContext(), "berhasil", Toast.LENGTH_LONG).show();
//
////                List<home> homeList = response.body();
////                HomeAdapter homeAdapter = new HomeAdapter(homeList, itemView.getContext());
////                recyclerView.setAdapter(homeAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<home>> call, Throwable t) {
//                Toast.makeText(itemView.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });

    }


}
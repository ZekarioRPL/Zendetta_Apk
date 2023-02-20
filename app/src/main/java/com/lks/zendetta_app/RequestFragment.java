package com.lks.zendetta_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lks.zendetta_app.Adapter.HomeAdapter;
import com.lks.zendetta_app.Interface.api;
import com.lks.zendetta_app.Model.home;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RequestFragment newInstance(String param1, String param2) {
        RequestFragment fragment = new RequestFragment();
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
    RecyclerView recyclerViewRequest;
    View itemView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemView = inflater.inflate(R.layout.fragment_request, container, false);

        recyclerViewRequest = itemView.findViewById(R.id.recyclerViewRequest);

        recyclerViewRequestAdapter();

        return  itemView;
    }

    private void recyclerViewRequestAdapter() {
        recyclerViewRequest.setLayoutManager(new LinearLayoutManager(itemView.getContext()));

        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient okHttpClient = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        api api = retrofit.create(api.class);

        Call<List<home>> call = api.getRequest();

        call.enqueue(new Callback<List<home>>() {
            @Override
            public void onResponse(Call<List<home>> call, Response<List<home>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(itemView.getContext(), "gagal", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(itemView.getContext(), "barhasil Request", Toast.LENGTH_SHORT).show();
                List<home> homeList = response.body();
                HomeAdapter homeAdapter = new HomeAdapter(homeList, itemView.getContext());
                recyclerViewRequest.setAdapter(homeAdapter);

            }

            @Override
            public void onFailure(Call<List<home>> call, Throwable t) {
                Toast.makeText(itemView.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
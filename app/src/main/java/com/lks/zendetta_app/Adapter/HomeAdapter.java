package com.lks.zendetta_app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.lks.zendetta_app.DetailRequestActivity;
import com.lks.zendetta_app.Model.home;
import com.lks.zendetta_app.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    List<home> homeList;
    Context context;

    public HomeAdapter(List<home> homeList, Context context) {
        this.homeList = homeList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_request, parent, false);

        return new HomeViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position) {
        holder.name.setText(homeList.get(position).getName().toString());

        String tglLama=homeList.get(position).getDate().toString();
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");

        String tglBaru= null;
        try {
            tglBaru = dateFormat.format(df.parse(tglLama));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.date.setText(tglBaru);
        holder.time.setText(homeList.get(position).getEarlyVisit().toString() + " - " + homeList.get(position).getEndVisit().toString());

        Bitmap img = stringToBitmap(homeList.get(position).getGambar().toString());
        holder.imageRequest.setImageBitmap(img);

        holder.btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, homeList.get(position).getId().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailRequestActivity.class);
                intent.putExtra("id", homeList.get(position).getId().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageFilterView imageRequest;
        ImageView btnRequest;
        TextView name, date, time;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameRequest);
            date = itemView.findViewById(R.id.dateRequest);
            time = itemView.findViewById(R.id.timeRequest);
            imageRequest = itemView.findViewById(R.id.imageRequest);
            btnRequest = itemView.findViewById(R.id.btnRequest);
        }
    }

    private Bitmap stringToBitmap(String kode){
        try{
            byte[] encodeKode = Base64.decode(kode, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeKode, 0, encodeKode.length);
            return bitmap;
        }catch (Exception G){
            G.getMessage();
            return null;
        }
    }
}

package com.lks.zendetta_app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import com.lks.zendetta_app.Model.requestModel;
import com.lks.zendetta_app.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {
    List<requestModel> requestModelList;
    Context context;

    public RequestAdapter(List<requestModel> requestModelList, Context context) {
        this.requestModelList = requestModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public RequestAdapter.RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_request, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestAdapter.RequestViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(requestModelList.get(position).getName().toString());
//        String data = convertStringToData(requestModelList.get(position).getDate().toString());

        String tglLama=requestModelList.get(position).getDate().toString();
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");

        String tglBaru= null;
        try {
            tglBaru = dateFormat.format(df.parse(tglLama));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.date.setText(tglBaru);
        holder.time.setText(requestModelList.get(position).getEarlyVisit().toString() + " - " + requestModelList.get(position).getEndVisit().toString());

        Bitmap img = stringToBitmap(requestModelList.get(position).getGambar().toString());
        holder.imageRequest.setImageBitmap(img);
        holder.btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, requestModelList.get(position).getId().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailRequestActivity.class);
                intent.putExtra("id", requestModelList.get(position).getId().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return requestModelList.size();
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        ImageFilterView imageRequest;
        ImageView btnRequest;
        View line;
        TextView name, date, time;
        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameRequest);
            date = itemView.findViewById(R.id.dateRequest);
            time = itemView.findViewById(R.id.timeRequest);
            imageRequest = itemView.findViewById(R.id.imageRequest);
            btnRequest = itemView.findViewById(R.id.btnRequest);
            line = itemView.findViewById(R.id.line);
        }
    }

    public Bitmap stringToBitmap(String kode){
        try{
            byte[] encodeKode = Base64.decode(kode, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeKode, 0, encodeKode.length);
            return bitmap;
        }catch (Exception G){
            G.getMessage();
            return null;
        }
    }
//    public String convertStringToData(String stringData){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");//yyyy-MM-dd'T'HH:mm:ss
//        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date data = sdf.(stringData);
//        String formattedTime = output.format(data);
//        return formattedTime;
//        }
}

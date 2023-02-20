package com.lks.zendetta_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lks.zendetta_app.Model.requestModel;
import com.lks.zendetta_app.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {

    List<requestModel> requestModelList;
    Context context;

    public DetailAdapter(List<requestModel> requestModelList, Context context) {
        this.requestModelList = requestModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public DetailAdapter.DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_detail, parent, false);

        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.DetailViewHolder holder, int position) {
        holder.name.setText(requestModelList.get(position).getName().toString());
        holder.note.setText(requestModelList.get(position).getNote().toString());

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

        holder.early.setText(requestModelList.get(position).getEarlyVisit().toString());
        holder.end.setText(requestModelList.get(position).getEndVisit().toString());


    }

    @Override
    public int getItemCount() {
        return requestModelList.size();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {
        TextView name, note, date, early, end;
        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameDetail);
            note = itemView.findViewById(R.id.noteDetail);
            date = itemView.findViewById(R.id.dateDetail2);
            early = itemView.findViewById(R.id.earlyTimeDetail);
            end = itemView.findViewById(R.id.endTimeDetail);
        }
    }
}

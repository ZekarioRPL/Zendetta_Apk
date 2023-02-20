package com.lks.zendetta_app.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.lks.zendetta_app.Model.patientModel;
import com.lks.zendetta_app.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.zip.Inflater;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    List<patientModel> patientModelList;
    Context context;

    public PatientAdapter(List<patientModel> patientModelList, Context context) {
        this.patientModelList = patientModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public PatientAdapter.PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_patient, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientAdapter.PatientViewHolder holder, int position) {
        holder.name.setText(patientModelList.get(position).getName().toString());
        String tglLama=patientModelList.get(position).getBirthdate().toString();
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");

        String tglBaru= null;
        try {
            tglBaru = dateFormat.format(df.parse(tglLama));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.date.setText("birthdate : " +tglBaru);
        holder.city.setText(patientModelList.get(position).getCity().toString());
        holder.phone.setText("phone : " + patientModelList.get(position).getPhone().toString());

        Bitmap bitmap = stringToBitmap(patientModelList.get(position).getGambar().toString());
        holder.imagePatient.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return patientModelList.size();
    }

    public class PatientViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, city, phone;
        ImageFilterView imagePatient;
        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.namePatient);
            date = itemView.findViewById(R.id.datePatient);
            city = itemView.findViewById(R.id.cityPatient);
            phone = itemView.findViewById(R.id.phonePatient);

            imagePatient = itemView.findViewById(R.id.imagePatient);
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
}

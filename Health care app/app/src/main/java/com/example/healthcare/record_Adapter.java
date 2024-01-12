package com.example.healthcare;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class record_Adapter extends RecyclerView.Adapter<record_Adapter.ViewHolder> {
    private ArrayList<Patient> mDataset;
    private Context mContext;

    public record_Adapter(ArrayList<Patient> dataSet, Context context) {
        mDataset = dataSet;
        mContext = context;
    }
    public record_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_record_design, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @SuppressLint("SetTextI18n")
    public void onBindViewHolder(ViewHolder holder, int position) {

        Patient patient = mDataset.get(position);
        String name=patient.getName();
        holder.name.setText(name);
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent= new Intent(mContext,AppointmentList.class);
                    intent.putExtra("user",name);
                     mContext.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;

        public ViewHolder(View v) {
            super(v);
            name=v.findViewById(R.id.name_record);
        }
    }
}

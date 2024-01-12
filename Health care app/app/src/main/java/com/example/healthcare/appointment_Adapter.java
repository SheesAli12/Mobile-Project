package com.example.healthcare;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class appointment_Adapter extends RecyclerView.Adapter<appointment_Adapter.ViewHolder> {
    private ArrayList<Patient> mDataset;

    public appointment_Adapter(ArrayList<Patient> dataSet) {
        mDataset=dataSet;
    }

    public appointment_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_appointment_design, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @SuppressLint("SetTextI18n")
    public void onBindViewHolder(ViewHolder holder, int position) {

        Patient patient = mDataset.get(position);
        String appoint=patient.getAppointment_num();
        String age= patient.getAge();
        String illness= patient.getIllness();
        String time=patient.getTime();
        String name=patient.getName();

        holder.appoint.setText("Appoint: "+ appoint);
        holder.age.setText("Age: " + age);
        holder.illness.setText("Illness: " + illness);
        holder.time.setText("Time: " + time);
        holder.name.setText("Name: "+name);
    }

    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView appoint;
        public TextView age;
        public TextView illness;
        public TextView time;

        public TextView name;

        public ViewHolder(View v) {
            super(v);
            appoint = (TextView) v.findViewById(R.id.num_appoint);
            age = (TextView) v.findViewById(R.id.age_appoint);
            illness = (TextView) v.findViewById(R.id.illness_appoint);
            time=v.findViewById(R.id.time_appoint);
            name=v.findViewById(R.id.name_appoint);
        }
    }
}

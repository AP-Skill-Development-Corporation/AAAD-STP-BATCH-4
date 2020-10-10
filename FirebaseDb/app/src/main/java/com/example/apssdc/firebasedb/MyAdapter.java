package com.example.apssdc.firebasedb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.HoldView> {
    Context ct;
    List<MyModel> list;
    DatabaseReference reference;
    public MyAdapter(Context ct, List<MyModel> list) {
        this.ct = ct;
        this.list = list;
    }

    @NonNull
    @Override
    public HoldView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HoldView(LayoutInflater.from(ct)
                .inflate(R.layout.row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HoldView holder, final int position) {
        holder.tv.setText(list.get(position).getSname());
        holder.tv1.setText(list.get(position).getSroll());
        holder.tv2.setText(list.get(position).getSnumber());
        reference = FirebaseDatabase.getInstance().getReference("StudentsData");
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To delete the data from the firebase database
                reference.child(list.get(position).sroll).removeValue();
                Toast.makeText(ct, "deleted", Toast.LENGTH_SHORT).show();
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ct);
                View v = LayoutInflater.from(ct).inflate(R.layout.update,null,false);
                final EditText name = v.findViewById(R.id.name);
                final EditText roll = v.findViewById(R.id.roll);
                final EditText number = v.findViewById(R.id.number);
                builder.setView(v);
                builder.setCancelable(false);

                name.setText(list.get(position).getSname());
                roll.setText(list.get(position).getSroll());
                roll.setEnabled(false);
                number.setText(list.get(position).getSnumber());

                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HashMap<String,Object> map = new HashMap<>();
                        map.put("sname",name.getText().toString());
                        map.put("snumber",number.getText().toString());
                        //To update the data in the firebase database
                        reference.child(list.get(position).getSroll()).updateChildren(map);
                        Toast.makeText(ct, "Updated", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HoldView extends RecyclerView.ViewHolder {
        TextView tv,tv1,tv2;
        ImageButton edit,del;
        public HoldView(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.sname);
            tv1 = itemView.findViewById(R.id.sroll);
            tv2 = itemView.findViewById(R.id.snumber);
            edit = itemView.findViewById(R.id.edit);
            del = itemView.findViewById(R.id.del);
        }
    }
}

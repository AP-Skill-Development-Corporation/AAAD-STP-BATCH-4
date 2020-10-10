package com.example.apssdc.firebasedb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText sname,sroll,snumber;
    RecyclerView rv;
    ArrayList<MyModel> list;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sname = findViewById(R.id.sname);
        sroll = findViewById(R.id.sroll);
        snumber = findViewById(R.id.snumber);
        rv = findViewById(R.id.rv);
        list = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("StudentsData");
        //To read the data from the firebase database
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MyModel model = dataSnapshot.getValue(MyModel.class);
                    list.add(model);
                }
                MyAdapter adapter = new MyAdapter(MainActivity.this,list);
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rv.addItemDecoration(new DividerItemDecoration(MainActivity.this,
                        DividerItemDecoration.VERTICAL));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "failed "+error,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void save(View view) {
        String name = sname.getText().toString();
        String roll = sroll.getText().toString();
        String number = snumber.getText().toString();
        MyModel model = new MyModel(name,roll,number);
        // To write the data into the firebase database
        reference.child(roll).setValue(model);
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }
}
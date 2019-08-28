package com.example.accountrecord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class RecordActivity extends AppCompatActivity {

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference ref = db.getReference();
    private ListView listView;
    private ArrayList arrayList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        listView = findViewById(R.id.List);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                arrayList.clear();
                for(DataSnapshot d:dataSnapshot.getChildren()){
                    for(DataSnapshot d2:d.getChildren()){
                        //Log.d("data",d.getKey()+" "+d2.getKey()+" "+d2.getValue());
                        arrayList.add(d.getKey()+" "+d2.getKey()+" "+d2.getValue());
                    }
                }
                ArrayAdapter adapter = new ArrayAdapter(RecordActivity.this,android.R.layout.simple_list_item_1, Arrays.copyOf(arrayList.toArray(), arrayList.toArray().length, String[].class));
                listView.setAdapter(adapter);
                //Log.d("data",String.valueOf(dataSnapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

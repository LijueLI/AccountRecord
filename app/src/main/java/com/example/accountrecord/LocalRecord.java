package com.example.accountrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class LocalRecord extends AppCompatActivity {

    private ListView list;
    private ArrayList arrayList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_record);

        list = findViewById(R.id.LocalList);

        DBHelper LR = new DBHelper(this,1);
        Cursor cursor = LR.select();
        while(cursor.moveToNext()){
            String S = cursor.getString(cursor.getColumnIndexOrThrow("Date")) + "  " + cursor.getString(cursor.getColumnIndexOrThrow("Item")) + "  " + cursor.getString(cursor.getColumnIndexOrThrow("Price"));
            arrayList.add(S);
        }
        ArrayAdapter adapter = new ArrayAdapter(LocalRecord.this,android.R.layout.simple_list_item_1, Arrays.copyOf(arrayList.toArray(), arrayList.toArray().length, String[].class));
        list.setAdapter(adapter);
    }
}

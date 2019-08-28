package com.example.accountrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private EditText Date,Item,Price;
    private Button NewR,Record,Local_Record;
    private String regex = "([^0-9]+)";
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date = findViewById(R.id.Date);
        Item = findViewById(R.id.Item);
        Price = findViewById(R.id.Price);

        NewR = findViewById(R.id.New);
        Record = findViewById(R.id.Record);
        Local_Record = findViewById(R.id.Local_Record);

        NewR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("data",Date.getText().toString()+" "+Item.getText().toString()+" "+Price.getText().toString()+"\n");

                DBHelper dbHelper = new DBHelper(MainActivity.this,1);
                String DateS = Date.getText().toString();
                String ItemS = Item.getText().toString();
                String PriceS = Price.getText().toString();

                String[] S = DateS.split(regex);
                DateS = "";
                for (String i:S) {
                    DateS+=i;
                }
                //Log.d("Dates",String.valueOf(DateS.length()));
                if(DateS.length()==8&&!ItemS.isEmpty()&&!ItemS.trim().isEmpty()&&!PriceS.isEmpty()&&!PriceS.trim().isEmpty()){
                    reference.child(DateS).child(ItemS).setValue(PriceS);
                    long i = dbHelper.insert(DateS,ItemS,PriceS);
                    Log.d("DB",String.valueOf(i));
                    Date.setText("");
                    Item.setText("");
                    Price.setText("");

                }
                else{
                    Toast toast = Toast.makeText(MainActivity.this,"請依照提示輸入並不要輸入空白",Toast.LENGTH_LONG);
                    toast.show();
                }
                dbHelper.close();
            }
        });


        Record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,RecordActivity.class);
                startActivity(intent);
            }
        });

        Local_Record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,LocalRecord.class);
                startActivity(intent);
            }
        });
    }
}

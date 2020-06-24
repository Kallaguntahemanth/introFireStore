package com.example.introfirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button logout;
    private EditText edit;
    private Button add;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout=findViewById(R.id.logout);
        edit=findViewById(R.id.edit);
        add=findViewById(R.id.add);
        listView=findViewById(R.id.listview);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, StartActivity.class));

            }
        });
       add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String txt_name=edit.getText().toString();
               if(txt_name.isEmpty()){
                   Toast.makeText(MainActivity.this, "No name entered", Toast.LENGTH_SHORT).show();
               }
               else{
                   FirebaseDatabase.getInstance().getReference().child("Languages").child("n5").setValue(txt_name);
               }
           }

       });
       /*HashMap<String, Object> map= new HashMap<>();
       map.put("Name","Madhukar");
       map.put("Pulse Rate","79");
       map.put("Medication","given");
       map.put("Hungry","yes");
       map.put("Breath","Normal");
       FirebaseDatabase.getInstance().getReference().child("Patients").child("Patient3").updateChildren(map);*/
        final ArrayList<String> list= new ArrayList<>();
        final ArrayAdapter adapter= new ArrayAdapter<String>(this,R.layout.list_item,list);
        listView.setAdapter(adapter);

        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Patients");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    information info=snapshot.getValue(information.class);
                    String txt="Name:                 "+info.getName()+"\n"+"Breath:               "+info.getBreath()+"\n"+"Medication:       "+info.getMedication()+"\n"
                            +"Hungry:               "+info.getHungry()+"\n";
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

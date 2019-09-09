package com.example.prasannakumar.fireapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    EditText Plate_No;
    EditText S_Point;
    EditText Destination;
    EditText Amount_In_Rs;
    Button buttonAdd;
    private static final String tag = MainActivity.class.getSimpleName();

    DatabaseReference databaseArtists;

    ListView listViewArtists;

    List<Artist> artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseArtists = FirebaseDatabase.getInstance().getReference("artists"); Log.d(tag ,"checkpoint1");
        Plate_No = (EditText) findViewById(R.id.Plate_No);
        S_Point  =(EditText) findViewById(R.id.S_Point);
        Destination = (EditText) findViewById(R.id.Destination);
        Amount_In_Rs = (EditText) findViewById(R.id.Amount_In_Rs);
        buttonAdd = (Button) findViewById(R.id.buttonAddArtist);

        listViewArtists = (ListView)findViewById(R.id.ListViewArtists);

        artistList = new ArrayList<>();
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtist();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList.clear();

                for(DataSnapshot artistSnapshot: dataSnapshot.getChildren()){
                    Artist artist = artistSnapshot.getValue(Artist.class);

                    artistList.add(artist);
                }
                Artistlist adapter = new Artistlist(MainActivity.this,artistList);
                listViewArtists.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addArtist() {
        String name = Plate_No.getText().toString().trim();
        String start =S_Point.getText().toString().trim();
        String dest = Destination.getText().toString().trim();
        String cost = Amount_In_Rs.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {
            String id =databaseArtists.push().getKey();
            Artist artist =new Artist(id,name,start,dest,cost);
            databaseArtists.child(id).setValue(artist);
             Toast.makeText(this, "added" ,Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "enter the plate no", Toast.LENGTH_LONG).show();
        }
    }
}
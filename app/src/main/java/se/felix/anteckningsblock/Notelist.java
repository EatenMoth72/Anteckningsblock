package se.felix.anteckningsblock;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Note {
    public String title;
    public String content;
}

public class Notelist extends ListActivity {

    static ArrayList<Note> notes = new ArrayList<Note>();
    static ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notes.clear();
        titles.clear();
        loadAllNotes();

        setContentView(R.layout.notelist);

        ArrayAdapter<String> list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
        setListAdapter(list);

        Button button = (Button) findViewById(R.id.New);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Notelist.this, anteckningsblock.class);
                startActivity(i);
                //android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

    }

    void loadAllNotes() {
        try {
            File file = new File(getApplicationContext().getFilesDir(), "anteckning.txt");
            FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);
            String title = dis.readUTF();
            String content = dis.readUTF();

            System.out.println("Content=" + content);
            // String firstPartOfContent = content.substring(0, 10);

            Note n = new Note();
            n.title = title;
            n.content = content;

            notes.add(n);
            titles.add(title);

            dis.close();
        } catch (Exception e) {

        }
    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent(Notelist.this, anteckningsblock.class);
        startActivity(i);
        //android.os.Process.killProcess(android.os.Process.myPid());
    }

}
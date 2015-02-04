package se.felix.anteckningsblock;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static android.widget.Toast.*;


public class anteckningsblock extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anteckningsblock);

        Button button = (Button)findViewById(R.id.GoBack);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                maybeSave();
                Intent i = new Intent(anteckningsblock.this, Notelist.class);
                startActivity(i);
            }
        });

        System.out.println("Note created!");
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("Note started!");

        System.out.println("Num notes = " + Notelist.notes.size());

        EditText editText = (EditText) findViewById(R.id.EditText);

        if (Notelist.notes.size() > 0) {
            Note n = Notelist.notes.get(0);
            String content = n.content;
            editText.setText(content);
        }
    }

    void maybeSave() {
        EditText editText = (EditText) findViewById(R.id.EditText);
        String yourText = editText.getText().toString();

        if (yourText != null && !yourText.isEmpty()) {
            save(yourText);
        }
    }

    void save(String yourText) {
        try {
            File file = new File(getApplicationContext().getFilesDir(), "anteckning.txt");
            FileOutputStream fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            String firstPartOfContent = yourText.substring(0, 10);
            firstPartOfContent = firstPartOfContent.replace('\n', ' ');
            dos.writeUTF(firstPartOfContent);
            dos.writeUTF(yourText);
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //String firstPartOfContent = content.substring(0, 10);

}

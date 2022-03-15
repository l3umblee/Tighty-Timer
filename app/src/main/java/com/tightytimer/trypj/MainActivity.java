package com.tightytimer.trypj;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    public Button send;
    public EditText setcnt, resttime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setcnt = (EditText)findViewById(R.id.editset);
        resttime = (EditText)findViewById(R.id.editrest);
        send = (Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                int sets = 0, rests = 0;
                if((setcnt.getText().toString().equals("") || setcnt.getText().toString() == null)||(resttime.getText().toString().equals("") || resttime.getText().toString() == null)) {
                    sets = 10;
                    rests = 30;
                }
                else {
                    sets = Integer.parseInt(setcnt.getText().toString());
                    rests = Integer.parseInt(resttime.getText().toString());
                }
                i.putExtra("sets", sets);
                i.putExtra("rests", rests);

                startActivity(i);
            }
        });
    }
}

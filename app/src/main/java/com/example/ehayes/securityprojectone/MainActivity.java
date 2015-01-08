package com.example.ehayes.securityprojectone;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    DBAdapter db;
    EditText editText;
    Button button;
    String[] projects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBAdapter(getApplicationContext());

        editText = (EditText) findViewById(R.id.editText);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArray();
                Toast.makeText(getApplicationContext(),editText.getText().toString() + " added",Toast.LENGTH_SHORT).show();
                editText.setText("");

            }
        });
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,addArray());

        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    public String[] addArray(){

        projects = new String[100];
        for(int i = 0;i < 100;i++)
        {
            projects[i] = "Stuart Scott";
        }
        return projects;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int listIndex = position + 1;
        Toast.makeText(getApplicationContext(),listIndex + " Position was called",Toast.LENGTH_SHORT).show();

    }
}

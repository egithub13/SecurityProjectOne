package com.example.ehayes.securityprojectone;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class MainActivity extends Activity  {

    DBAdapter myDb;
    EditText editText;
    Time today = new Time(Time.getCurrentTimezone());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        openDb();
        populateListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    private void openDb(){
        myDb = new DBAdapter(this);
        myDb.open();
    }

    public void onClickAddTask(View v){
        today.setToNow();
        String timeStamp = today.format("%Y-%m-%d %H:%M:%S");
        if(!TextUtils.isEmpty(editText.getText().toString())){
            myDb.insertRow(editText.getText().toString(),timeStamp);
        }
        populateListView();
        editText.setText("");
    }

    private void populateListView(){
        Cursor cursor = myDb.getAllRows();
        String[]fromFieldNames = new String[]{DBAdapter.KEY_ROWID,DBAdapter.KEY_TASK};
        int[]toViewIds = new int[]{R.id.textViewItemNumber,R.id.textViewItemTask};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.item_layout,cursor,
                fromFieldNames,toViewIds,0);
        ListView myList = (ListView) findViewById(R.id.listViewTasks);
        myList.setAdapter(myCursorAdapter);
    }


}

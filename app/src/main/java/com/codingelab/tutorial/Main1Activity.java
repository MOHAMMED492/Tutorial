package com.codingelab.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class Main1Activity extends Activity  {
  private Button onInsert,onSearch;

  EditText edit_name;
  EditText edit_phone;
  EditText edit_email;
  private Button next;
  private Button onGet;

  ProgressBar pb;

  ArrayList<User> usersPhp;
  ArrayList<User> usersSqlite ;


  SQLiteDatabase sqLiteDatabase;
  DBHelper  db;
  Cursor cursor;

  private Syn syn;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (android.os.Build.VERSION.SDK_INT > 9) {
      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
      StrictMode.setThreadPolicy(policy);
    }
    // initilizing propreties
    this.onInsert=(Button)findViewById(R.id.onInsert);
    onSearch=(Button)findViewById(R.id.onSearch);
    edit_name=(EditText)findViewById(R.id.edit_name);
    edit_phone=(EditText)findViewById(R.id.edit_phone);
    edit_email=(EditText)findViewById(R.id.edit_email);
      this.next=(Button)findViewById(R.id.next);
    this.onGet=(Button)findViewById(R.id.onGet);

    pb = (ProgressBar) findViewById(R.id.pb);

    this.next.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
              startActivity(intent);
          }
      });

    this.syn=new Syn();
    // preparing listener (onAction)
    this.onInsert.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String msg=syn.doInBackground("insert",edit_name.getText().toString(),edit_phone.getText().toString(),edit_email.getText().toString());
        Toast.makeText(getBaseContext(),msg,Toast.LENGTH_SHORT).show();
      }
    });


    this.onSearch.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(Main1Activity.this, SearchPhpActivity.class);
        startActivity(i);
      }
    });



      this.onGet.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              Intent i = new Intent(Main1Activity.this, GetUsersActivity.class);
              startActivity(i);

          }
      });


  }



  boolean containName(List<User> list, User userphp) {

    for(int i=0;i<list.size();i++) {
      if (list.get(i).getName().equals(userphp.getName()) && list.get(i).getEmail().equals(userphp.getEmail())&& list.get(i).getPhone().equals(userphp.getPhone())){
        return true;
      }
    }

    return false;
  }



}

package com.jasonstanl3y.helloworld;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        //insert toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.calcTitle);


    }

    public void ToastIt(String message) {

        Toast.makeText(getApplicationContext(), getString(R.string.landscape), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        switch (item.getItemId()) {
            case R.id.menuConversion:
                intent = new Intent(getApplicationContext(), ConversionActivity.class);
                intent.putExtra("RESULT", MainActivity.edtResult.getText().toString());

                //PUT DATA in the intent
                //   intent.putExtra("RESULT", edtResult.getText().toString());
                startActivity(intent);
                break;

            case R.id.menuCalc:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;

            case R.id.menuHistory:
                intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
                break;

            case R.id.menuAbout:
                intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);


            default:
                return true;

        }
       return true;
    }

}

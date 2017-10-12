package com.android.tryczson.bitcoin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class PersonalActivity extends AppCompatActivity {

    String type;
    private TextView txtCoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        type = getIntent().getStringExtra("type");
        txtCoin = (TextView) findViewById(R.id.coin);

        if (type.equals("btc"))
            txtCoin.setText("0 BTC");
        else  if (type.equals("ltc"))
            txtCoin.setText("0 LTC");
        else  if (type.equals("eth"))
            txtCoin.setText("0 ETH");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.personal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        noinspection SimplifiableIfStatement
        if (id == R.id.qrcode) {
            return true;
        } else if (id == R.id.send) {
            return true;
        } else

            if (id == android.R.id.home) {
            this.finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}

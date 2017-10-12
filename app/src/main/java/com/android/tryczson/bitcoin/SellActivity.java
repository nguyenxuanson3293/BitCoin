package com.android.tryczson.bitcoin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SellActivity extends AppCompatActivity {
    private EditText edtBitCoin;
    private TextView txtFee, txtTotal;
    private String type;
    private TextView txtTitle, txtBuy, txtUnit;
    private float price;
    private TextView txtPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            type = getIntent().getStringExtra("type");
        } catch (Exception e) {

        }
        txtTitle = (TextView) findViewById(R.id.title) ;
        txtBuy = (TextView) findViewById(R.id.buycoin);
        txtUnit = (TextView) findViewById(R.id.unit);
        edtBitCoin = (EditText) findViewById(R.id.edtBitCoin);
        txtFee = (TextView) findViewById(R.id.txtFee);
        txtTotal = (TextView) findViewById(R.id.txtTotal);
        txtPrice = (TextView) findViewById(R.id.price);

        if (type.equals("btc")) {
            txtTitle.setText("Sell BitCoin");
            txtBuy.setText("Sell BitCoin");
            txtBuy.setText("BTC");
            txtPrice.setText("$4040 per coin");
            price = 4064f;
        }
        else if (type.equals("eth")) {
            txtTitle.setText("Sell Ethereum");
            txtBuy.setText("Sell Ethereum");
            txtUnit.setText("ETH");
            txtPrice.setText("$284 per coin");
            price = 284.6f;
        }
        else if (type.equals("ltc")) {
            txtTitle.setText("Sell Litecoin");
            txtBuy.setText("Sell Litecoin");
            txtUnit.setText("LTC");
            txtPrice.setText("$52 per coin");
            price = 51.66f;
        }

        edtBitCoin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                edtBitCoin.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        float a = Float.parseFloat(charSequence.toString());
                        float b =  (a*price)/ 10;
                        float c = a*price - b ;
                        txtFee.setText("$" + b);
                        txtTotal.setText("$" + c);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
package com.nadershamma.apps.tipcalculatorapp;

// The Activity Base class

import android.support.v7.app.AppCompatActivity;

// Used for saving state information
import android.os.Bundle;

//Android UI Classes
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

//Android UI event handler
import android.text.Editable;

// Android UI event listener
import android.text.TextWatcher;
import android.widget.SeekBar.OnSeekBarChangeListener;

// Number formatting
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double billAmount = 0.0;
    private double percent = 0.15;
    private EditText amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the GUI text widgets
        amountTextView = findViewById(R.id.amountEditText);
        percentTextView = findViewById(R.id.percentTextView);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);
        tipTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));

        amountTextView.addTextChangedListener(amountEditTextWatcher);
        SeekBar percentSeekBar = findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);
    }

    private void calculate() {
        percentTextView.setText(percentFormat.format(percent));

        double tip = billAmount * percent;
        double total = billAmount + tip;

        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));
    }

    private void reset() {
        tipTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));
    }

    private final OnSeekBarChangeListener seekBarListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            percent = progress / 100.0;
            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try {
                billAmount = Double.parseDouble(charSequence.toString());
                calculate();
            }
            catch (NumberFormatException e) {
                amountTextView.getText().clear();
                billAmount = 0.0;
                reset();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
}

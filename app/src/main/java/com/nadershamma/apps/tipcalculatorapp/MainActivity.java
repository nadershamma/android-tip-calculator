package com.nadershamma.apps.tipcalculatorapp;

// The Activity Base class

import android.support.v7.app.AppCompatActivity;

// Used for saving state information
import android.os.Bundle;

//Android UI Classes
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;

// Android UI event listener
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.view.View.OnClickListener;
import android.widget.Toast;

// Number formatting
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double billAmount = 0.0;
    private double percent = 0.15;
    private TextView amountTextView;
    private StringBuilder userAmountInput = new StringBuilder();
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private ViewGroup mainView;
    private ArrayList<Button> buttons = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainView = findViewById(R.id.mainLayout);

        //Get the GUI text widgets
        amountTextView = findViewById(R.id.amountTextView);
        amountTextView.setText("Enter Amount");
        percentTextView = findViewById(R.id.percentTextView);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);
        tipTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));

        SeekBar percentSeekBar = findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);

        for (int i = 0; i < mainView.getChildCount(); i++) {
            if (mainView.getChildAt(i) instanceof Button) {
                buttons.add((Button) mainView.getChildAt(i));
            }
        }

        for (Button button : buttons) {
            button.setOnClickListener(buttonPressed);
        }
    }

    private void calculateBill() {
        percentTextView.setText(percentFormat.format(percent));

        double tip = billAmount * percent;
        double total = billAmount + tip;

        tipTextView.setText(currencyFormat.format(tip).toString());
        totalTextView.setText(currencyFormat.format(total).toString());
    }

    private void resetBill() {
        tipTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));
    }

    private void updateInputView(String amount) {
        try {
            userAmountInput.append(amount);
            if (userAmountInput.toString().matches("(\\d*)")) {
                amountTextView.setText(userAmountInput.toString());
                billAmount = Double.parseDouble(userAmountInput.toString());
                calculateBill();
            } else if (userAmountInput.toString().matches("(\\d*)(\\.?)")) {
                amountTextView.setText(userAmountInput.toString());
                billAmount = Double.parseDouble(userAmountInput.toString().replace(".", "").trim());
                calculateBill();
            } else if (userAmountInput.toString().matches("(\\d*)(\\.?)(\\d{0,2})")) {
                amountTextView.setText(userAmountInput.toString());
                billAmount = Double.parseDouble(userAmountInput.toString());
                calculateBill();
            } else {
                Toast.makeText(getApplicationContext(), "Invalid Amount", Toast.LENGTH_SHORT).show();
                deleteInputView();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteInputView() {
        userAmountInput.deleteCharAt(userAmountInput.length() - 1);
        amountTextView.setText(userAmountInput.toString());
        if (userAmountInput.toString().matches("(\\d*)(\\.?)(\\d{0,2})")) {
            billAmount = Double.parseDouble(userAmountInput.toString());
            calculateBill();
        }
    }

    private void clearInputView() {
        userAmountInput.delete(0, userAmountInput.length());
        amountTextView.setText("Enter Amount");
        resetBill();
    }

    private final OnClickListener buttonPressed = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Button b = (Button) view;
            if (b.getText().toString().equalsIgnoreCase("DEL")) {
                if (userAmountInput.length() > 1) {
                    deleteInputView();
                } else {
                    clearInputView();
                }
            } else if (b.getText().toString().equalsIgnoreCase("CLR")) {
                clearInputView();
            } else {
                updateInputView(b.getText().toString());
            }
        }
    };

    private final OnSeekBarChangeListener seekBarListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            percent = progress / 100.0;
            calculateBill();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}

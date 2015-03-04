package tipcalculator.lau.com.tipcalculator;

import android.app.Activity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView.OnEditorActionListener;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.SharedPreferences; //To save values / onPause
import android.content.SharedPreferences.Editor; //To save value
import android.view.inputmethod.EditorInfo; //To edit soft key input

import java.text.NumberFormat;


public class TipCalculatorActivity extends Activity
implements OnEditorActionListener, OnClickListener {

    //Private variables for widgets
    private EditText billAmountEditText;
    private TextView percentTextView;
    private Button percentUpButton;
    private Button percentDownButton;
    private TextView tipTextView;
    private TextView totalTextView;

    //Define SharedPreferences object
    private SharedPreferences savedValues;

    //define as instance variable for tip percent
    private String billAmountString;
    private float tipPercent = .15f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        //References for widgets
        billAmountEditText = (EditText) findViewById(R.id.billAmountEditText);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        percentUpButton = (Button) findViewById(R.id.percentUpButton);
        percentDownButton = (Button) findViewById(R.id.percentDownButton);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);

        //Setting listeners
        billAmountEditText.setOnEditorActionListener((OnEditorActionListener) this);
        percentDownButton.setOnClickListener((OnClickListener) this);
        percentUpButton.setOnClickListener((OnClickListener) this);

        //Get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    @Override
    public void onPause() {
        //Save the instance variables
        Editor editor = savedValues.edit();
        editor.putString("billAmountString", billAmountString);
        editor.putFloat("tipPercent", tipPercent);
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        //Get instance variables
        billAmountString = savedValues.getString("billAmountString", "");
        tipPercent = savedValues.getFloat("tipPercent", 0.15f);

        //Set the bill amount on its widget
        billAmountEditText.setText(billAmountString);

        //Calculate and display
        calculateAndDisplay();
    }

        public void calculateAndDisplay() {

        //Get bill amount
        billAmountString = billAmountEditText.getText().toString();
        float billAmount;
        if (billAmountString.equals("")) {
            billAmount = 0;
        } else {
            billAmount = Float.parseFloat(billAmountString);
        }

        //Calculate tip and total amount
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        //Display the result with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent));

    }

    @Override
    public boolean onEditorAction(TextView v, int actionID, KeyEvent event) {
        //Allows usage of both soft and hard keys to input
        if (actionID == EditorInfo.IME_ACTION_DONE ||
                actionID == EditorInfo.IME_ACTION_UNSPECIFIED) {

            calculateAndDisplay();
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.percentDownButton:
                tipPercent = tipPercent - .01f;
                calculateAndDisplay();
                break;

            case R.id.percentUpButton:
                tipPercent = tipPercent + .01f;
                calculateAndDisplay();
                break;
        }
    }

}

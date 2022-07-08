package com.assignment.converter;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText inputText, outputText;
    private boolean inputStatus = false, outputStatus = false;
    private Spinner spinner;
    private TextView inputUnit, outputUnit;
    private final int CELSIUS_TO_KELVIN = 1;
    private final int MILES_TO_INCH = 0;
    private final int KILOGRAM_TO_LB = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = findViewById(R.id.input_text);
        outputText = findViewById(R.id.output_text);
        spinner = (Spinner) findViewById(R.id.spinner);
        inputUnit =  findViewById(R.id.input_unit);
        outputUnit =  findViewById(R.id.output_unit);
        setSpinner();
        spinnerListener();

        inputText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    inputStatus = true;
                } else {
                    inputStatus = false;
                }
            }
        });
        outputText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    outputStatus = true;
                } else {
                    outputStatus = false;
                }
            }
        });
        inputText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (inputStatus)
                    if (!s.toString().equals(""))
                        outputText.setText(connvertData(Double.parseDouble(s.toString())));
            }
        });
        outputText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (outputStatus)
                    if (!s.toString().equals(""))
                        inputText.setText(revertConversion(Double.parseDouble(s.toString())));
            }
        });
    }

    private void spinnerListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case MILES_TO_INCH:
                        inputText.setText("");
                        outputText.setText("");
                        inputUnit.setText("Mi");
                        outputUnit.setText("In");
                        break;
                    case KILOGRAM_TO_LB:
                        inputText.setText("");
                        outputText.setText("");
                        inputUnit.setText("Kg");
                        outputUnit.setText("Lb");
                        break;
                    case CELSIUS_TO_KELVIN:
                        inputText.setText("");
                        outputText.setText("");
                        inputUnit.setText("C");
                        outputUnit.setText("K");
                        break;



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private String revertConversion(double parseDouble) {
        switch (spinner.getSelectedItemPosition()) {
            case MILES_TO_INCH:
                return roundFunction(convertInchToMiles(parseDouble)).toString();

            case CELSIUS_TO_KELVIN:
                return roundFunction(convertKelvinToCelsius(parseDouble)).toString();
            case KILOGRAM_TO_LB:
                return roundFunction(convertLbtoKilogram(parseDouble)).toString();
            default:
                return "";
        }

    }

    private String connvertData(double parseDouble) {
        switch (spinner.getSelectedItemPosition()) {
            case MILES_TO_INCH:
                return roundFunction(convertMilesToInch(parseDouble)).toString();
            case CELSIUS_TO_KELVIN:
                return roundFunction(convertCelsiusToKelvin(parseDouble)).toString();
            case KILOGRAM_TO_LB:
                return roundFunction(convertKilogramToLb(parseDouble)).toString();
            default:
                return "";
        }

    }

    private void setSpinner() {

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.conversion_data, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    private Double convertInchToMiles(double parseDouble) {
        return parseDouble/63360;
    }

    private Double convertMilesToInch(double parseDouble) {
        return  parseDouble*63360;
    }

    private Double convertKelvinToCelsius(double parseDouble) {
        return  parseDouble - 273.15;
    }

    private Double convertKilogramToLb(double parseDouble) {
        return  parseDouble*2.2;
    }

    private Double convertCelsiusToKelvin(double parseDouble) {
        return  parseDouble + 273.15;
    }

    private Double convertLbtoKilogram(double parseDouble) {
        return  parseDouble/2.2;
    }
    private Double roundFunction(double parseDouble) {
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format(parseDouble));
    }

}
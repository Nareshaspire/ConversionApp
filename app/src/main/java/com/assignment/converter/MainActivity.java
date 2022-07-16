// Name: Naresh kamanaboyana
//Student ID :A00257027
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
    private EditText inputText, outputText; //Input Text fields
    private boolean inputStatus = false, outputStatus = false; //checking for status
    private Spinner spinner; // to select the conversion spinner is being used
    private TextView inputUnit, outputUnit;// texview units to view 
    private final int CELSIUS_TO_KELVIN = 1;//unit conversions
    private final int MILES_TO_INCH = 0;
    private final int KILOGRAM_TO_LB = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//creating a component to perform tasks
        inputText = findViewById(R.id.input_text);
        outputText = findViewById(R.id.output_text);
        spinner = (Spinner) findViewById(R.id.spinner);
        inputUnit =  findViewById(R.id.input_unit);
        outputUnit =  findViewById(R.id.output_unit);
        setSpinner();//spinner setup
        spinnerListener();//spinner event listener

        inputText.setOnFocusChangeListener(new View.OnFocusChangeListener() { //onfocus listner for input text
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    inputStatus = true;
                } else {
                    inputStatus = false;
                }
            }
        });
        outputText.setOnFocusChangeListener(new View.OnFocusChangeListener() { //on focus event listener for output
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    outputStatus = true;
                } else {
                    outputStatus = false;
                }
            }
        });
        //text changed event listener for input text
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
        //text changed listener for output text
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
//spinner event listener
    private void spinnerListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case MILES_TO_INCH: //conversion for miles to inches
                        inputText.setText("");
                        outputText.setText("");
                        inputUnit.setText("Mi");
                        outputUnit.setText("In");
                        break;
                    case KILOGRAM_TO_LB: //conversion for kilogram to lbs
                        inputText.setText("");
                        outputText.setText("");
                        inputUnit.setText("Kg");
                        outputUnit.setText("Lb");
                        break;
                    case CELSIUS_TO_KELVIN: //conversion fot celcius to kelvin
                        inputText.setText("");
                        outputText.setText("");
                        inputUnit.setText("C");
                        outputUnit.setText("K");
                        break;
                }
            }
            @Override
            //if user doesnt select anything
            public void onNothingSelected(AdapterView<?> adapterView) { 

            }
        });
    }
//revert convesrion
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
//different conversions been used selected data type as double
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
//setup of spinner for the selection of conversions
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

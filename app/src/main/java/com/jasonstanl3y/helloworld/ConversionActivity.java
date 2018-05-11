package com.jasonstanl3y.helloworld;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;



public class ConversionActivity extends BaseActivity {

    String resultStr = "";
    EditText edtConversionResult;
    ListView listView;
    EditText edtResultFrom;
    TextView txtFromConvert;
    TextView txtToConvert;
    String[] items = new String[40];
    Double fromNumber, toNumber;


    Double[] conversionRates = {
            2.54, //Inches to Centimeters
            0.393701, //Centimeters to Inches
            0.1, //Milimeters to Centimeters
            3.28084, //Meters to Feet
            0.333333, //Feet to Yards
            0.9144, //Yards to Meters
            0.333333, //Feet to Yards
            0.000189394, //Feet to Miles
            5280.0, //Miles to Feet
            1.60934, //Miles to Kilometers
            0.621371, //Kilometers to Miles
            0.0, //Fahrenheit to Celsius
            0.0, //Celsius to Fahrenheit
            2.0, //Pints to Cups
            2.0, //Quarts to Pints
            4.0, //Gallons to Quarts
            0.0625, //Cups to Gallons
            2.20462, //Kilograms to Pounds
            0.453592, //Pounds to Kilograms
            0.0625, //Ounces to Pounds
            33.814, //Liters to Ounces
            0.333333, //Teaspoon to Tablespoon
            2000.0, //Tons to Pounds
            0.25, //Pecks to Bushels
            0.0416667, //Hours to Days
            0.142857, //Days to Weeks
            0.230137, //Weeks to Months
            0.0833334, //Months to Years
            10.0, //Decades to Years
            100.0, //Centuries to Years
            0.3048, //FPS to MPS
            3.28084, //Meter per Second to FPS
            35.3147, //Cubic Meter to Cubic Foot
            0.0610237, //Cubic centimeter to Cubic inch
            145038.0, //Gigapascal to Pound-force per square inch
            100.0, //Millibar to Pascal
            3.38639, //InHg(inch or mercury) to kPa(kilopascal)
            0.0166667, //Seconds to Minutes
            0.0166667, //Minutes to Hours
            0.0 //Kelvin to Celsius







    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

              items[0] = getString(R.string.inchesToCentimeters);    //items[position].split(/ to /)
              items[1] = getString(R.string.centimetersToInches);
              items[2] = getString(R.string.milimetersToCentimeters);
              items[3] = getString(R.string.metersToFeet);
              items[4] = getString(R.string.feetToYards);
              items[5] = getString(R.string.yardsToMeters);
              items[6] = getString(R.string.yardsToFeet);
              items[7] = getString(R.string.feetToMiles);
              items[8] = getString(R.string.milesToFeet);
              items[9] = getString(R.string.milesToKilometers);
              items[10] = getString(R.string.kilometersToMiles);

              items[11] = getString(R.string.fahrenheitToCelsius);
              items[12] = getString(R.string.celsiusToFahrenheit);


              items[13] = getString(R.string.pintsToCups);
              items[14] = getString(R.string.quartsToPints);
              items[15] = getString(R.string.gallonsToQuarts);
              items[16] = getString(R.string.cupsToGallons);
              items[17] = getString(R.string.kilogramsToPounds);
              items[18] = getString(R.string.poundsToKilograms);
              items[19] = getString(R.string.ouncesToPounds);
              items[20] = getString(R.string.litersToOunces);
              items[21] = getString(R.string.teaspoonsToTablespoon);
              items[22] = getString(R.string.tonsToPounds);
              items[23] = getString(R.string.pecksToBushels);
              items[24] = getString(R.string.hoursToDays);
              items[25] = getString(R.string.daysToWeeks);
              items[26] = getString(R.string.weeksToMonths);
              items[27] = getString(R.string.monthsToYears);
              items[28] = getString(R.string.decadesToYears);
              items[29] = getString(R.string.centuriesToYears);
              items[30] = getString(R.string.fpsToMps);
              items[31] = getString(R.string.mpsToFps);
              items[32] = getString(R.string.cubicMeterToCubicFoot);
              items[33] = getString(R.string.cubicCentimeterToCubicInch);
              items[34] = getString(R.string.gigapascalToPSI);
              items[35] = getString(R.string.millibarToPascal);
              items[36] = getString(R.string.inHgTokPa);
              items[37] = getString(R.string.secondsToMinutes);
              items[38] = getString(R.string.minutesToHours);
              items[39] = getString(R.string.kelvinToCelsius);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        setTitle(R.string.conversions);

        edtConversionResult = findViewById( R.id.edtResultTo);
        edtResultFrom = findViewById( R.id.edtResultFrom);
        txtFromConvert = findViewById(R.id.txtFromConvert);
        txtToConvert = findViewById(R.id.txtToConvert);


        //Get The Intent data
        Intent intent = getIntent();
        resultStr = intent.getStringExtra("RESULT");
        Log.i("CALC", "ResultStr " + resultStr);
        edtConversionResult.setText(resultStr);
        ToastIt(getString(R.string.runningOnCreate));

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, items);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?>parent, View view, int position, long id) {
                performCalculation(position);
            }
        });


    }

    private void performCalculation(int position) {
        ToastIt("You clicked at: " + items[position]);
        //Double fromNumber, centimeters;


        if (!edtResultFrom.getText().toString().isEmpty()) {


            if (position == 11) { //Fahrenheit to Celsius
                fromNumber = Double.parseDouble(edtResultFrom.getText().toString());
                toNumber = (fromNumber - 32) * .5556;
                edtConversionResult.setText(Double.toString((toNumber)));

                String arr[] = items[11].split(getString(R.string.toSplit), 2);
                txtFromConvert.setText(arr[0].toString());
                txtToConvert.setText(arr[1].toString());


            } else if (position == 12) { //Celsius to Fahrenheit
                fromNumber = Double.parseDouble(edtResultFrom.getText().toString());
                toNumber = fromNumber * 1.8 + 32;
                edtConversionResult.setText(Double.toString((toNumber)));
                String arr[] = items[11].split(getString(R.string.toSplit), 2);
                txtFromConvert.setText(arr[0].toString());
                txtToConvert.setText(arr[1].toString());

            } else if (position == 39){
                fromNumber = Double.parseDouble(edtResultFrom.getText().toString());
                toNumber = fromNumber - 273.15;
                edtConversionResult.setText(Double.toString((toNumber)));
                String arr[] = items[39].split(getString(R.string.toSplit), 2);
                txtFromConvert.setText(arr[0].toString());
                txtToConvert.setText(arr[1].toString());

            }else {

                fromNumber = Double.parseDouble(edtResultFrom.getText().toString());
                toNumber = conversionRates[position] * fromNumber;
                edtConversionResult.setText(Double.toString(toNumber));
                String arr[] = items[position].split(getString(R.string.toSplit), 2);
                txtFromConvert.setText(arr[0].toString());
                txtToConvert.setText(arr[1].toString());


            }
        }



    }

    public void calcButtonOnClick(View v) {
        //Switch to other activity - calc
        // INTENT   intension - Intend to do something.
        Intent intent = new Intent(this, MainActivity.class);

        //PUT DATA in the intent
        startActivity(intent);
    }
}

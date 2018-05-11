package com.jasonstanl3y.helloworld;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends BaseActivity {


    Button btn1;
   public static EditText edtResult;
    TextView viewResult;
    Button btnPlus, btnMinus, btnMultiply, btnDivide, btnEquals, btnBack;
    boolean usedDecimalpoint = false;
    int pendingOperation;


    double lastNumber;
    double currentValue;

    boolean operationDidHappen = false;

    boolean portrait = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.calcTitle));


        //Get the current screen orientation (Landscape or Portrait)
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int orientation = display.getRotation(); //Surface.ROTATION_90 or Surface.ROTATION_270
        portrait = (orientation == Surface.ROTATION_0 || orientation == Surface.ROTATION_180);// ? true : false;
        Log.d("CALC", getString(R.string.orientation) + portrait);




        btn1 = (Button) findViewById(R.id.btn1);
        edtResult = findViewById(R.id.edtResultFrom);
        viewResult = findViewById(R.id.viewResult);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnEquals = (Button) findViewById(R.id.btnEquals);

        //Delete/backspace button image
//        if(!portrait) {
//            btnBack = (Button) findViewById(R.id.btnBack);
//            btnBack.setText("\u232b");
//        }

        //Disallow soft keyboard
        edtResult.setShowSoftInputOnFocus((false));
        edtResult.setInputType(InputType.TYPE_NULL);
        edtResult.setFocusable(false);
        edtResult.setText("");


    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("CALC", "OrientationChange: ");

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ToastIt(getString(R.string.landscape));
            portrait = false;
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            ToastIt(getString(R.string.portrait));
            portrait = true;
        }
    }


    //Handles all Operations
    public void btnOperationOnClick(View v) {

        int operation = ((Button) v).getId();
        int btnPlusID = btnPlus.getId();
        int btnMinusID = btnMinus.getId();
        int btnMultiplyID = btnMultiply.getId();
        int btnDivideID = btnDivide.getId();


        String resultText = edtResult.getText().toString();
        if (resultText.equals("")) {
            return;
        }

        usedDecimalpoint = false;


        if (pendingOperation != 0) {

            switch (pendingOperation) {

                case R.id.btnPlus:

                    currentValue = currentValue + Double.parseDouble(edtResult.getText().toString());
                    operationDidHappen = true;
                    edtResult.setText("");
                    viewResult.setText(Double.toString(currentValue));

                    pendingOperation = operation;
                    break;
                case R.id.btnMinus:

                    currentValue = currentValue - Double.parseDouble(edtResult.getText().toString());
                    operationDidHappen = true;

                    edtResult.setText("");
                    viewResult.setText(Double.toString(currentValue));
                    pendingOperation = operation;
                    break;
                case R.id.btnMultiply:

                    currentValue = Double.parseDouble(edtResult.getText().toString()) * Double.parseDouble(viewResult.getText().toString());
                    operationDidHappen = true;
                    edtResult.setText("");

                    viewResult.setText(Double.toString(currentValue));

                    operationDidHappen = true;
                    pendingOperation = operation;

                    break;

                case R.id.btnDivide:

                    currentValue = Double.parseDouble(viewResult.getText().toString()) / Double.parseDouble(edtResult.getText().toString());

                    edtResult.setText("");

                    viewResult.setText(Double.toString(currentValue));

                    operationDidHappen = true;

                    pendingOperation = operation;

                default:
                    break;


            }

        } else {
            if (operationDidHappen == false) {
                lastNumber = Double.parseDouble(edtResult.getText().toString());
            }

            switch (operation) {

                case R.id.btnPlus:
                    currentValue = lastNumber + currentValue;


                    viewResult.setText(Double.toString(currentValue));
                    operationDidHappen = true;
                    pendingOperation = btnPlusID;


                    lastNumber = 0;

                    break;
                case R.id.btnMinus:

                    currentValue = lastNumber - currentValue;
                    viewResult.setText(Double.toString(currentValue));
                    operationDidHappen = true;
                    pendingOperation = btnMinusID;

                    lastNumber = 0;

                    break;
                case R.id.btnMultiply:

                    currentValue = lastNumber * Double.parseDouble(edtResult.getText().toString());

                    viewResult.setText(Double.toString(lastNumber));
                    ;


                    operationDidHappen = true;
                    pendingOperation = btnMultiplyID;

                    lastNumber = 0;
                    break;

                case R.id.btnDivide:

                    currentValue = lastNumber / Double.parseDouble(edtResult.getText().toString());

                    viewResult.setText(Double.toString(lastNumber));


                    operationDidHappen = true;
                    pendingOperation = btnDivideID;

                    lastNumber = 0;


                default:
                    break;


            }
//            edtResult.setText(Double.toString(currentValue));

        }
    }


    //Handles all Numbers and Decimal point
    public void btnNumberOnClick(View v) {


        String newButton = ((Button) v).getText().toString();
        String currentResult = edtResult.getText().toString();
        if (newButton.equals(".")) {
            if (!usedDecimalpoint) {
                edtResult.setText(currentResult + ".");
                usedDecimalpoint = true;
            }
        } else if (operationDidHappen) {
            edtResult.setText(newButton);
            operationDidHappen = false;

        } else {
            edtResult.setText(currentResult + newButton);


        }

    }


    public void btnEqualsOnClick(View v) {
        //Assume that I have a pendingOperation
        //Log.i("CALC", "EQUALS ON CLICK");


        int operation = pendingOperation;

        String resultText = edtResult.getText().toString();
        if (resultText.equals("")) {
            return;
        }

        if (operation != 0) {

            switch (operation) {

                case R.id.btnPlus:


                    currentValue = Double.parseDouble(viewResult.getText().toString()) + Double.parseDouble(edtResult.getText().toString());

                    viewResult.setText(Double.toString(currentValue));

                    operationDidHappen = true;

                    break;

                case R.id.btnMinus:

                    currentValue = Double.parseDouble(viewResult.getText().toString()) - Double.parseDouble(edtResult.getText().toString());

                    viewResult.setText(Double.toString(currentValue));


                    operationDidHappen = true;

                    break;

                case R.id.btnMultiply:

                    currentValue = Double.parseDouble(viewResult.getText().toString()) * Double.parseDouble(edtResult.getText().toString());


                    viewResult.setText(Double.toString(currentValue));


                    operationDidHappen = true;


                    break;

                case R.id.btnDivide:

                    currentValue = Double.parseDouble(viewResult.getText().toString()) / Double.parseDouble(edtResult.getText().toString());


                    viewResult.setText(Double.toString(currentValue));


                    operationDidHappen = true;

                default:
                    break;
            }
            viewResult.setText(Double.toString(currentValue));
            edtResult.setText(Double.toString(currentValue));


        }
        pendingOperation = 0;

    }

    public void btnClearOnClick(View v) {
        edtResult.setText("");
        usedDecimalpoint = false;
        viewResult.setText("");
        pendingOperation = 0;
        lastNumber = 0;
        currentValue = 0;

    }

    public void btnBackSpace(View v) {
        String textInBox = edtResult.getText().toString();
        if (textInBox.length() > 0) {
            //Remove last character
            String newText = textInBox.substring(0, textInBox.length() - 1);
            // Update edit text
            edtResult.setText(newText);

        } else {
            return;
        }

    }

    public void operationTanOnClick(View v) {
        if (!edtResult.getText().toString().equals("")) {
            double value = Math.tan(Double.parseDouble(edtResult.getText().toString()));
            viewResult.setText(Double.toString(value));
            edtResult.setText(Double.toString(value));
        } else {
            return;
        }
        usedDecimalpoint = true;
    }

    public void getSqrRootOnClick(View v) {
        if (!edtResult.getText().toString().equals("")) {
            double value = Math.sqrt(Double.parseDouble(edtResult.getText().toString()));
            viewResult.setText(Double.toString(value));
        } else {
            return;
        }
        usedDecimalpoint = true;
    }

    public void btnSquaredOnClick(View v) {
        if (!edtResult.getText().toString().equals("")) {
            double value = Math.pow(Double.parseDouble(edtResult.getText().toString()), 2);
            viewResult.setText(Double.toString(value));
        } else {
            return;
        }
        usedDecimalpoint = true;
    }

    public void btnPercent(View v) {
        if (!edtResult.getText().toString().equals("")) {
            double amount = Double.parseDouble(edtResult.getText().toString());
            double res = (amount / 100.0f);
            viewResult.setText(Double.toString(res));
            edtResult.setText(Double.toString(res));
        } else {
            return;

        }

    }

    public void btnNegAndPos(View v) {

        if (!edtResult.getText().toString().equals("")) {
            double amount = Double.parseDouble(edtResult.getText().toString());
            double res = (amount * -1);
            edtResult.setText(Double.toString(res));
            viewResult.setText(Double.toString(res));
        } else {
            return;
        }
    }

    public void operationSinOnClick(View v) {


        if (!edtResult.getText().toString().equals("")) {
            double value = Math.sin(Double.parseDouble(edtResult.getText().toString()));
            edtResult.setText(Double.toString(value));
            viewResult.setText(Double.toString(value));
        } else {
            return;
        }

        usedDecimalpoint = true;
    }

    public void operationCosOnClick(View v) {


        if (!edtResult.getText().toString().equals("")) {
            double value = Math.cos(Double.parseDouble(edtResult.getText().toString()));
            edtResult.setText(Double.toString(value));
            viewResult.setText(Double.toString(value));
        } else {
            return;
        }

        usedDecimalpoint = true;
    }

    public void btnRand(View v) {

        edtResult.setText(Double.toString(Math.random()));
        viewResult.setText(Double.toString(Math.random()));
        usedDecimalpoint = true;
    }

    public void btnPi(View v) {

        double value = Math.PI;
        edtResult.setText(Double.toString(value));
        viewResult.setText(Double.toString(value));

        usedDecimalpoint = true;
    }

    public void btnConversionOnClick(View v) {
        //Switch to other activity - calc
        // INTENT   intension - Intend to do something.
        Intent intent = new Intent(this, ConversionActivity.class);

        //PUT DATA in the intent
         intent.putExtra("RESULT", edtResult.getText().toString());
        startActivity(intent);
    }

}

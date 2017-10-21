/*
 * Copyright (C) 2013 Code Here Now - A subsidiary of Mobs & Geeks
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file 
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.codeherenow.sicalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class SICalculatorActivity extends Activity implements View.OnClickListener{

	private Button calculateButton;

    private TextView resultText;
    private TextView yearText;
    private EditText amountText;
    private EditText rateText;

    private SeekBar seekBar;

    int amount;
    int progressChangedValue = 0;
    double rate;
    double result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sicalculator);

        calculateButton = (Button) findViewById(R.id.calculate);

        resultText = (TextView) findViewById(R.id.result);
        yearText = (TextView) findViewById(R.id.year);

        amountText = (EditText) findViewById(R.id.amount);
        rateText = (EditText) findViewById(R.id.rate);

        calculateButton.setOnClickListener(this);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar){

            }

            public void onStopTrackingTouch(SeekBar seekBar){
                yearText.setText(String.format("%d Year(s)", progressChangedValue));
            }
        });

	}

    @Override
    public void onClick(View view){
        calculate();
    }

    private void calculate(){
        amount = Integer.parseInt(amountText.getText().toString());
        rate = Double.parseDouble(rateText.getText().toString());
        result = amount * (rate/100) * progressChangedValue;
        resultText.setText("The interest for $"+amount+ " at a rate of "+rate+"% for "+
                progressChangedValue+" year(s) is $"+String.format("%.2f",result));
    }

}

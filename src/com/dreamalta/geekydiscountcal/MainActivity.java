package com.dreamalta.geekydiscountcal;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import com.dreamalta.geekydiscountcal.R.id;
import com.dreamalta.geekydiscountcal.R.layout;
import com.dreamalta.geekydiscountcal.R.menu;

import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends Activity implements View.OnClickListener, OnTouchListener { //View.OnClickListener for button clicking

	
	
	//char [][] num = new char[20][14];    //2 Dimensional array: 20 numbers of up to 13 digits
	int nCounter = 0;		//number counter
	int dCounter = 0;		//digit counter
	int rdCounter = 0;		//restore dCounter	//temporary storage for dCounter
	int dotCounter = 0;		//keep tracks that decimal numbers can only have one dot //0 means no dot yet while 1 means there is dot
	int rdotCounter = 0;	//restore dotCounter	//temporary storage for dotCounter
	int strWarn = 0;		//prevent deletion or assigning operator on string warning //if value is 1 then prevent
	int disV = 0,
		taxV = 0,
		tipV = 0;	//dis, tax, tip variable	(determine if the button is clicked)
	Double disVal = 0.0,
		   taxVal = 0.0,
		   tipVal = 0.0;		//dis, tax, tip, val	(store the value entered by the user)
	int dttDotCounter = 0;		//dis, tax, tip dotCounter
	int rDttDotCounter = 0;		//restore dttDotCounter
	int dttDCounter = 0;		//dis, taxk, tip digit counter
	int rDttDCounter = 0;		//restor dttDCounter

		
	TextView display, include, tvDis, tvTax, tvTip, youSave, taxAndTip, total;
	Button bDot, b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, qu, eq, div, mul, sub, add, dis, tax, tip;
	View swipe;
	
	float x1,x2;		//used for swipe detection
    float y1, y2;		//used for swip detection
	

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		
		display = (TextView) findViewById(R.id.textView1);
		tvDis = (TextView) findViewById(R.id.tvDis);
		tvTax = (TextView) findViewById(R.id.tvTax);
		tvTip = (TextView) findViewById(R.id.tvTip);
		youSave = (TextView) findViewById(R.id.youSave);
		taxAndTip = (TextView) findViewById(R.id.taxAndTip);
		total = (TextView) findViewById(R.id.total);
		
		
		bDot = (Button) findViewById(R.id.bDot);
		b0 = (Button) findViewById(R.id.b0);
		b1 = (Button) findViewById(R.id.b1);
		b2 = (Button) findViewById(R.id.b2);
		b3 = (Button) findViewById(R.id.b3);
		b4 = (Button) findViewById(R.id.b4);
		b5 = (Button) findViewById(R.id.b5);
		b6 = (Button) findViewById(R.id.b6);
		b7 = (Button) findViewById(R.id.b7);
		b8 = (Button) findViewById(R.id.b8);
		b9 = (Button) findViewById(R.id.b9);
		qu = (Button) findViewById(R.id.qu);
		eq = (Button) findViewById(R.id.eq);
		div = (Button) findViewById(R.id.divide);
		mul = (Button) findViewById(R.id.multiply);
		sub = (Button) findViewById(R.id.subtract);
		add = (Button) findViewById(R.id.add);
		dis = (Button) findViewById(R.id.dis);
		tax = (Button) findViewById(R.id.tax);
		tip = (Button) findViewById(R.id.tip);

		swipe = (View) findViewById(R.id.view1);
		
		bDot.setOnClickListener(this);
		b0.setOnClickListener(this);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		b6.setOnClickListener(this);
		b7.setOnClickListener(this);
		b8.setOnClickListener(this);
		b9.setOnClickListener(this);
		qu.setOnClickListener(this);
		eq.setOnClickListener(this);
		div.setOnClickListener(this);
		mul.setOnClickListener(this);
		sub.setOnClickListener(this);
		add.setOnClickListener(this);
		dis.setOnClickListener(this);
		tax.setOnClickListener(this);
		tip.setOnClickListener(this);
		
		swipe.setOnTouchListener(this);
	}
	
	@Override
	  public void onResume() {
	    super.onResume();
	    
	  }
	  
	
	@Override
	public void onClick(View v) {			//button click
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.bDot:
			/******Apply to discount, tax, tip**********/
			if(disV == 1 || taxV == 1 || tipV == 1){
				if(dttDCounter < 4 && dttDotCounter == 0)
				{
				if(disV == 1)
					tvDis.append(bDot.getText());
				if(taxV == 1)
					tvTax.append(bDot.getText());
				if(tipV == 1)
					tvTip.append(bDot.getText());
				dttDCounter++;
				dttDotCounter = 1;
				}
			}
			else{
			/*********Apply to main display***************/
			if(dCounter > 13)	{		//if it exceeds maximum digits warn user
			display.setText("<num>too_large!&");
			strWarn = 1;
			}
			else if(dotCounter == 0 && strWarn == 0)		//keep tracks that decimal numbers can only have one dot
			{
			display.append(bDot.getText());
			dCounter++;
			dotCounter = 1;
			}
			}
			break;
		case R.id.b0:
			/******Apply to discount, tax, tip**********/
			if(disV == 1 || taxV == 1 || tipV == 1){
				if(dttDCounter < 4)
				{
				if(disV == 1)
					tvDis.append(b0.getText());
				if(taxV == 1)
					tvTax.append(b0.getText());
				if(tipV == 1)
					tvTip.append(b0.getText());
				dttDCounter++;
				}
			}
			else{
				/*********Apply to main display***************/
			if(dCounter > 13)			//if it exceeds maximum digits warn user
			{
			display.setText("<num>too_large!&");
			strWarn = 1;
			}
			else if(strWarn == 0)
			{
			display.append(b0.getText());
			dCounter++;
			}
			}
			break;
		case R.id.b1:
			/******Apply to discount, tax, tip**********/
			if(disV == 1 || taxV == 1 || tipV == 1){
				if(dttDCounter < 4)
				{
				if(disV == 1)
					tvDis.append(b1.getText());
				if(taxV == 1)
					tvTax.append(b1.getText());
				if(tipV == 1)
					tvTip.append(b1.getText());
				dttDCounter++;
				}
			}
			else{
				/*********Apply to main display***************/
			if(dCounter > 13)	{	//if it exceeds maximum digits warn user
				display.setText("<num>too_large!&");
				strWarn = 1;
			}
			else if(strWarn == 0)
			{
			display.append(b1.getText());
			dCounter++;
			}
			}
			break;
		case R.id.b2:
			/******Apply to discount, tax, tip**********/
			if(disV == 1 || taxV == 1 || tipV == 1){
				if(dttDCounter < 4)
				{
				if(disV == 1)
					tvDis.append(b2.getText());
				if(taxV == 1)
					tvTax.append(b2.getText());
				if(tipV == 1)
					tvTip.append(b2.getText());
				dttDCounter++;
				}
			}
			else{
				/*********Apply to main display***************/
			if(dCounter > 13)	{	//if it exceeds maximum digits warn user
				display.setText("<num>too_large!&");
				strWarn = 1;
			}
			else if(strWarn == 0)
			{
			display.append(b2.getText());
			dCounter++;
			}
			}
			break;
		case R.id.b3:
			/******Apply to discount, tax, tip**********/
			if(disV == 1 || taxV == 1 || tipV == 1){
				if(dttDCounter < 4)
				{
				if(disV == 1)
					tvDis.append(b3.getText());
				if(taxV == 1)
					tvTax.append(b3.getText());
				if(tipV == 1)
					tvTip.append(b3.getText());
				dttDCounter++;
				}
			}
			else{
				/*********Apply to main display***************/
			if(dCounter > 13)	{	//if it exceeds maximum digits warn user
				display.setText("<num>too_large!&");
				strWarn = 1;
			}
			else if(strWarn == 0)
			{
			display.append(b3.getText());
			dCounter++;
			}
			}
			break;
		case R.id.b4:
			/******Apply to discount, tax, tip**********/
			if(disV == 1 || taxV == 1 || tipV == 1){
				if(dttDCounter < 4)
				{
				if(disV == 1)
					tvDis.append(b4.getText());
				if(taxV == 1)
					tvTax.append(b4.getText());
				if(tipV == 1)
					tvTip.append(b4.getText());
				dttDCounter++;
				}
			}
			else{
				/*********Apply to main display***************/
			if(dCounter > 13)	{	//if it exceeds maximum digits warn user
				display.setText("<num>too_large!&");
				strWarn = 1;
			}
			else if(strWarn == 0)
			{
			display.append(b4.getText());
			dCounter++;
			}
			}
			break;
		case R.id.b5:
			/******Apply to discount, tax, tip**********/
			if(disV == 1 || taxV == 1 || tipV == 1){
				if(dttDCounter < 4)
				{
				if(disV == 1)
					tvDis.append(b5.getText());
				if(taxV == 1)
					tvTax.append(b5.getText());
				if(tipV == 1)
					tvTip.append(b5.getText());
				dttDCounter++;
				}
			}
			else{
				/*********Apply to main display***************/
			if(dCounter > 13)	{	//if it exceeds maximum digits warn user
				display.setText("<num>too_large!&");
				strWarn = 1;
			}
			else if(strWarn == 0)
			{
			display.append(b5.getText());
			dCounter++;
			}
			}
			break;
		case R.id.b6:
			/******Apply to discount, tax, tip**********/
			if(disV == 1 || taxV == 1 || tipV == 1){
				if(dttDCounter < 4)
				{
				if(disV == 1)
					tvDis.append(b6.getText());
				if(taxV == 1)
					tvTax.append(b6.getText());
				if(tipV == 1)
					tvTip.append(b6.getText());
				dttDCounter++;
				}
			}
			else{
				/*********Apply to main display***************/
			if(dCounter > 13)	{	//if it exceeds maximum digits warn user
				display.setText("<num>too_large!&");
				strWarn = 1;
			}
			else if(strWarn == 0)
			{
			display.append(b6.getText());
			dCounter++;
			}
			}
			break;
		case R.id.b7:
			/******Apply to discount, tax, tip**********/
			if(disV == 1 || taxV == 1 || tipV == 1){
				if(dttDCounter < 4)
				{
				if(disV == 1)
					tvDis.append(b7.getText());
				if(taxV == 1)
					tvTax.append(b7.getText());
				if(tipV == 1)
					tvTip.append(b7.getText());
				dttDCounter++;
				}
			}
			else{
				/*********Apply to main display***************/
			if(dCounter > 13)	{	//if it exceeds maximum digits warn user
				display.setText("<num>too_large!&");
				strWarn = 1;
			}
			else if(strWarn == 0)
			{
			display.append(b7.getText());
			dCounter++;
			}
			}
			break;
		case R.id.b8:
			/******Apply to discount, tax, tip**********/
			if(disV == 1 || taxV == 1 || tipV == 1){
				if(dttDCounter < 4)
				{
				if(disV == 1)
					tvDis.append(b8.getText());
				if(taxV == 1)
					tvTax.append(b8.getText());
				if(tipV == 1)
					tvTip.append(b8.getText());
				dttDCounter++;
				}
			}
			else{
				/*********Apply to main display***************/
			if(dCounter > 13)	{	//if it exceeds maximum digits warn user
				display.setText("<num>too_large!&");
				strWarn = 1;
			}
			else if(strWarn == 0)
			{
			display.append(b8.getText());
			dCounter++;
			}
			}
			break;
		case R.id.b9:   
			/******Apply to discount, tax, tip**********/
			if(disV == 1 || taxV == 1 || tipV == 1){
				if(dttDCounter < 4)
				{
				if(disV == 1)
					tvDis.append(b9.getText());
				if(taxV == 1)
					tvTax.append(b9.getText());
				if(tipV == 1)
					tvTip.append(b9.getText());
				dttDCounter++;
				}
			}
			else{
				/*********Apply to main display***************/
			if(dCounter > 13)	{	//if it exceeds maximum digits warn user
				display.setText("<num>too_large!&");
				strWarn = 1;
			}
			else if(strWarn == 0)
			{
			display.append(b9.getText());
			dCounter++;
			}
			}
			break;
		case R.id.qu:
			youSave.setText("[SWIPE].instruction:");
			taxAndTip.setText("[SWIPE].right to clear && [SWIPE].left to delete");
			total.setText("Developed by DreamAlta");
			break;
		case R.id.eq:
			
			/******Apply to discount, tax, tip**********/
			if(disV == 1 || taxV == 1 || tipV == 1){
				String dttNum = "";
				if(disV == 1){
					dttNum = tvDis.getText().toString();
					if(dttNum.isEmpty() || dttNum.equals("."))	{	
						tvDis.setBackgroundColor(Color.TRANSPARENT);	//if empty or dot then remove display color
						disVal = 0.0;
					}
					else{
						disVal = Double.parseDouble(dttNum);		//get the value of discount
						tvDis.setBackgroundColor(Color.GREEN);
					}
					disV = 0;	//discount amount is now applied
				}
				else if(taxV == 1){
					dttNum = tvTax.getText().toString();
					if(dttNum.isEmpty() || dttNum.equals(".")){
						tvTax.setBackgroundColor(Color.TRANSPARENT);	//if empty or dot then remove display color
						taxVal = 0.0;
					}
					else{
						taxVal = Double.parseDouble(dttNum);		//get the value of tax
						tvTax.setBackgroundColor(Color.GREEN);
					}
					
					taxV = 0;	//tax amount is now applied
				}
				else if(tipV == 1){
					dttNum = tvTip.getText().toString();
					if(dttNum.isEmpty() || dttNum.equals(".")){
						tvTip.setBackgroundColor(Color.TRANSPARENT);	//if empty or dot then remove display color
						tipVal = 0.0;
					}
					else{
						tipVal = Double.parseDouble(dttNum);		//get the value of tip
						tvTip.setBackgroundColor(Color.GREEN);
					}
					
					tipV = 0;	//tip amount is now applied
				}
				dttDCounter = 0;	
				dttDotCounter = 0;
			}
			else{/******************Apply to main display***********/
			
			String disNum = display.getText().toString(); //get display numbers and operators
			if(disNum.isEmpty())		//if there's nothing to calculate then break out of this button
				break;
			
			int i = disNum.length();
			if(strWarn == 0 && 			//calculates display numbers and operators but does not work when display is string text
					disNum.charAt(i-1)!='.' && disNum.charAt(i-1)!='/' && disNum.charAt(i-1)!='*' && disNum.charAt(i-1)!='-' && disNum.charAt(i-1)!='+')	//does not calculate if last char is .,/,*,- or +		
       	    {
       		int j;		//char place on display text
       		int k = 0;	//where the first digit of the next number starts
       		double [] getNum = new double[10]; 	//store number in order of seen
       		int nc = 0;		//number counter
       		char [] operator = new char[10];	//store operator in order of seen
       		int oc= 0;		//operator counter
       		double ans;		//answer from operation
       		
       		for(j = 0; j < i; j++)
       		{	
       			

       			if(disNum.charAt(disNum.length()-(disNum.length()-j))=='/'){	//if char is / operator
       				getNum[nc] = Double.parseDouble(disNum.substring(k,j));		//store starting point digit to ending point digit of a number
       				k = j + 1;		//the starting of the next number. +1 to exclude the operator sign
       				operator[oc]='/';	//store the operator sign
       				nc++;		//number count increment
       				oc++;		//operator count increment        
       			}
       			else if(disNum.charAt(disNum.length()-(disNum.length()-j))=='*'){	//if char is * operator
       				getNum[nc] = Double.parseDouble(disNum.substring(k,j));		//store starting point digit to ending point digit of a number
       				k = j + 1;		//the starting of the next number. +1 to exclude the operator sign
       				operator[oc]='*';	//store the operator sign
       				nc++;		//number count increment
       				oc++;		//operator count increment    
       			}
       			else if(disNum.charAt(disNum.length()-(disNum.length()-j))=='-'){	//if char is - operator
       				getNum[nc] = Double.parseDouble(disNum.substring(k,j));		//store starting point digit to ending point digit of a number
       				k = j + 1;		//the starting of the next number. +1 to exclude the operator sign
       				operator[oc]='-';	//store the operator sign
       				nc++;		//number count increment
       				oc++;		//operator count increment    
       			}
       			else if(disNum.charAt(disNum.length()-(disNum.length()-j))=='+'){	//if char is + operator
       				getNum[nc] = Double.parseDouble(disNum.substring(k,j));		//store starting point digit to ending point digit of a number
       				k = j + 1;		//the starting of the next number. +1 to exclude the operator sign
       				operator[oc]='+';	//store the operator sign
       				nc++;		//number count increment
       				oc++;		//operator count increment    
       			}
       			}
       		
       			getNum[nc] = Double.parseDouble(disNum.substring(k, disNum.length()));		//get the very last number		
       			ans = getNum[0];	//assign the first number as answer
       			for(int x = 0; x < oc; x++){	
       				switch(operator[x]){
       				case '/':
       					ans = ans/getNum[x+1];
       					if(getNum[x+1] == 0)		//if divide by zero the results is string Infinity
       					{
       						strWarn = 1;		//prevent deletion and applying operator on inifinity  //must press clear
       					}
       					break;
       				case '*':
       					ans = ans*getNum[x+1];
       					break;
       				case '-':
       					ans = ans-getNum[x+1];
       					break;
       				case '+':
       					ans = ans+getNum[x+1];
       					break;
       				
       				}	
       			}
       			
       			youSave.setText("SAVE>>" + String.format("%.2f", (ans * (disVal/100))));		//display savings from discount
       			taxAndTip.setText("TAX>>" + String.format("%.2f", (ans * (taxVal/100))) + "   TIP>>" + String.format("%.2f", (ans * (tipVal/100))));	//display the tax and tip amount
       			total.setText("TOTAL>>" + String.format("%.2f", (ans - (ans * (disVal/100)) + (ans * (taxVal/100)) + (ans * (tipVal/100)))));		//display total
       			
       			/******main display************/
       			display.setText(String.format("%.2f", ans));		//round ans to 2 decimal places	
       			String getAns = display.getText().toString();
       			dCounter = getAns.length();		//set up the new number of digits based on the answer
       			dotCounter = 1;		//since result will have dot already
       	    }	
			}
			break;
		case R.id.divide:
			String opTempD = display.getText().toString();
			if(!opTempD.isEmpty() && strWarn == 0 && opTempD.charAt(opTempD.length()-1)!='.' && disV == 0 && taxV == 0 && tipV == 0)		//if display text is not empty and there's no string warning and it's not ending with . AND dis, tax, tip button are not activated
			{
			if(!(opTempD.charAt(opTempD.length()-1)=='/') && !(opTempD.charAt(opTempD.length()-1)=='*') && !(opTempD.charAt(opTempD.length()-1)=='-') && !(opTempD.charAt(opTempD.length()-1)=='+')) {	//make sure operator won't be duplicated
				
				//nCounter = nCounter + 1;		//move on to next number
				display.append("/");
				strWarn = 0;
				rdotCounter = dotCounter;
				dotCounter = 0;
				rdCounter = dCounter;
				dCounter = dCounter + 1;
			}
			}
			break;
		case R.id.multiply:
			String opTempM = display.getText().toString();
			if(!opTempM.isEmpty() && strWarn == 0 && opTempM.charAt(opTempM.length()-1)!='.' && disV == 0 && taxV == 0 && tipV == 0)		//if display text is not empty and there's no string warning and it's not ending with . AND dis, tax, tip button are not activated
			{
			if(!(opTempM.charAt(opTempM.length()-1)=='/') && !(opTempM.charAt(opTempM.length()-1)=='*') && !(opTempM.charAt(opTempM.length()-1)=='-') && !(opTempM.charAt(opTempM.length()-1)=='+')) {	//make sure operator won't be duplicated
				
				//nCounter = nCounter + 1;		//move on to next number
				display.append("*");
				strWarn = 0;
				rdotCounter = dotCounter;
				dotCounter = 0;
				rdCounter = dCounter;
				dCounter = dCounter + 1;
			}
			}
			break;
		case R.id.subtract:
			String opTempS = display.getText().toString();
			if(!opTempS.isEmpty() && strWarn == 0 && opTempS.charAt(opTempS.length()-1)!='.' && disV == 0 && taxV == 0 && tipV == 0)		//if display text is not empty and there's no string warning and it's not ending with . AND dis, tax, tip button are not activated
			{
			if(!(opTempS.charAt(opTempS.length()-1)=='/') && !(opTempS.charAt(opTempS.length()-1)=='*') && !(opTempS.charAt(opTempS.length()-1)=='-') && !(opTempS.charAt(opTempS.length()-1)=='+')) {	//make sure operator won't be duplicated
				
				//nCounter = nCounter + 1;		//move on to next number
				display.append("-");
				strWarn = 0;
				rdotCounter = dotCounter;
				dotCounter = 0;
				rdCounter = dCounter;
				dCounter = dCounter + 1;
			}
			}
			break;
		case R.id.add:
			String opTempA = display.getText().toString();
			if(!opTempA.isEmpty() && strWarn == 0 && opTempA.charAt(opTempA.length()-1)!='.' && disV == 0 && taxV == 0 && tipV == 0)		//if display text is not empty and there's no string warning and it's not ending with . AND dis, tax, tip button are not activated
			{
			if(!(opTempA.charAt(opTempA.length()-1)=='/') && !(opTempA.charAt(opTempA.length()-1)=='*') && !(opTempA.charAt(opTempA.length()-1)=='-') && !(opTempA.charAt(opTempA.length()-1)=='+')) {	//make sure operator won't be duplicated
				
				//nCounter = nCounter + 1;		//move on to next number
				display.append("+");
				strWarn = 0;
				rdotCounter = dotCounter;
				dotCounter = 0;
				rdCounter = dCounter;
				dCounter = dCounter + 1;
			}
			}
			break;
		case R.id.dis:
			if(disV == 0 && taxV == 0 && tipV ==0){		//make sure dis, tax, and tip is not currently being set up
			disV = 1;
			tvDis.setText("");		//start empty
			tvDis.setBackgroundColor(Color.RED);
			}
			break;
		case R.id.tax:
			if(disV == 0 && taxV == 0 && tipV ==0){		//make sure dis, tax, and tip is not currently being set up
			taxV = 1;
			tvTax.setText("");		//start empty
			tvTax.setBackgroundColor(Color.RED);
			}
			break;
		case R.id.tip:
			if(disV == 0 && taxV == 0 && tipV ==0){		//make sure dis, tax, and tip is not currently being set up
			tipV = 1;
			tvTip.setText("");		//start empty
			tvTip.setBackgroundColor(Color.RED);	
			}
			break;
			}
	}
	
	
	
	
	
	//************obtained online but modified for this project (for swipe) //works only on the entire layout view but not on buttons or other
	// onTouchEvent () method gets called when User performs any touch event on screen 
	// Method to handle touch event like left to right swap and right to left swap
	 public boolean onTouch(View v, MotionEvent touchevent)
	 {
		      switch (touchevent.getAction())
	              {
	                     // when user first touches the screen we get x and y coordinate
	                      case MotionEvent.ACTION_DOWN: 
	                      {
	                          x1 = touchevent.getX();
	                          y1 = touchevent.getY();
	                          break;
	                     }
	                      case MotionEvent.ACTION_UP: 
	                      {
	                          x2 = touchevent.getX();
	                          y2 = touchevent.getY(); 
	
	                          //if left to right sweep event on screen	//clear display
	                          if (x1 < x2) 
	                          {
	                        	  /******Apply to discount, tax, tip**********/
	                  			if(disV == 1 || taxV == 1 || tipV == 1){
	                  				if(disV == 1)
	                					tvDis.setText("");
	                				if(taxV == 1)
	                					tvTax.setText("");
	                				if(tipV == 1)
	                					tvTip.setText("");
	                				dttDCounter = 0;
	                				dttDotCounter = 0;
	                  			}
	                  			else{ 
	                  			/********Apply to main display**********/
	                        	display.setText("");
	                        	youSave.setText("");
	                        	taxAndTip.setText("");
	                        	total.setText("");
	                  			strWarn = 0;		//warning (num too large) will be cleared
	                  			dotCounter = 0;
	                  			dCounter = 0;
	                  			//nCounter = 0;
	                  			} 
	                           }
	                         
	                          // if right to left sweep event on screen		//delete last character
	                          if (x1 > x2)
	                          {
	                        	/******Apply to discount, tax, tip**********/
		                  		if(disV == 1 || taxV == 1 || tipV == 1){
		                  			String dttTemp = "";
		                  			if(disV == 1)
		                  				dttTemp = tvDis.getText().toString();
		                			if(taxV == 1)
		                				dttTemp = tvTax.getText().toString();
		                			if(tipV == 1)
		                				dttTemp = tvTip.getText().toString();
		                			
		                			if(!dttTemp.isEmpty())
		                			{
		                				if(dttTemp.charAt(dttTemp.length()-1)=='.')	
		                					dttDotCounter = 0;
		                				
		                				dttTemp = dttTemp.substring(0, dttTemp.length()-1);
		                				if(disV == 1)
			                  				tvDis.setText(dttTemp);
			                			if(taxV == 1)
			                				tvTax.setText(dttTemp);
			                			if(tipV == 1)
			                				tvTip.setText(dttTemp);
			                			
			                			dttDCounter = dttDCounter - 1;
		                				
		                			}
		                  		}
		                  		else{
		                  			/*****************Apply to main display**********/
	                        	String temp = display.getText().toString();
	                  			if(!temp.isEmpty() && strWarn == 0)				//check if empty and string warning (num too large) is currently on
	                  			{
	                  			if(temp.charAt(temp.length()-1)=='.')			//if the last character is . then dotCounter restart
	                  				dotCounter = 0;
	                  			if(temp.charAt(temp.length()-1)=='/' || temp.charAt(temp.length()-1)=='*' || temp.charAt(temp.length()-1)=='-' || temp.charAt(temp.length()-1)=='+')	//if the last char is math operator then go back to previous number
	                  			{
	                  				//nCounter = nCounter - 1;
	                  				dCounter = rdCounter;		//restore back the number of digits from previous number
	                  				dotCounter = rdotCounter;	//restore back dotCounter
	                  			}
	                  			temp = temp.substring(0, temp.length()-1);		//remove the last character
	                  			display.setText(temp);							//output text minus the last char
	                  			dCounter = dCounter - 1;						//reduce digit by 1
	                  			}
	                          }
	                          }
	                          /* if UP to Down sweep event on screen
	                          if (y1 < y2) 
	                          {
	                        	  display.setText("down");
	                          }
	                         */
	                          
	                          /*if Down to UP sweep event on screen
	                          if (y1 > y2)
	                          {
	                        		
	                          }
	                          */
	                          
	                          break;
	                      }
	              }
	              return true;
	 }


	 
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}


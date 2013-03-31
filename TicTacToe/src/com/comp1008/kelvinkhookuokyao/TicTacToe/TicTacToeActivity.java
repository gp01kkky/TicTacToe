package com.comp1008.kelvinkhookuokyao.TicTacToe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.util.Log;

/**
 * TicTacToeActivity.java 
 * @author Kelvin Khoo 12/02/2013
 * Updated on 24/02/2013
 */

public class TicTacToeActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	int gameMode = 0;
    	// here is the added try catch block
    	//try
    	//{
    	Bundle mode = getIntent().getExtras();
    	if(mode !=null)
    	{
    		gameMode = mode.getInt("gameMode");
    	}
        super.onCreate(savedInstanceState);
       
        
     // Set full screen view, no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// setContentView(R.layout.main);
		DrawView dv = new DrawView(this, gameMode);
		setContentView(dv);
		dv.requestFocus();
		
    	}
    	/*catch (Exception e)
    	{
    		// this is the line of code that sends a real error message to the log
    		Log.e("ERROR", "ERROR IN CODE: " + e.toString());
     
    		// this is the line that prints out the location in
    		// the code where the error occurred.
    		e.printStackTrace();
    	}*/


    }
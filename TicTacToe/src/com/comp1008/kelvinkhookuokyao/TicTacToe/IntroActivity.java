package com.comp1008.kelvinkhookuokyao.TicTacToe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/**
 * IntroActivity.java 
 * @author Kelvin Khoo 12/02/2013
 * Updated on 24/02/2013
 */

public class IntroActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.intro_screen, menu);
        return true;
    }
    
    public void onClickP1vsP2 (View Button){
    	Intent intent = new Intent();
    	intent.setClass(this, TicTacToeActivity.class);
    	intent.putExtra("gameMode", 0);
    	startActivity(intent);
    }
    
    public void onClickP1vsCom (View Button){
    	Intent intent = new Intent();
    	intent.setClass(this,TicTacToeActivity.class);
    	intent.putExtra("gameMode", 1);
    	startActivity(intent);
    }
    
    
}

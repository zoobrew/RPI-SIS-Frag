package com.zoobrew.rpi.sis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends Activity{
	public final static String MESSAGE_KEY = "com.zoobrew.rpi.sis.LOGIN";
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    
    
    //Called when the user presses the login button
    public void LoginUser(View view) {
    	//login
    	
    	Intent intent = new Intent(this, MainActivity.class);
    	EditText username = (EditText) findViewById(R.id.edit_username);
    	String message = username.getText().toString();
    	intent.putExtra(MESSAGE_KEY, message);
    	startActivity(intent);
    	
    }

}

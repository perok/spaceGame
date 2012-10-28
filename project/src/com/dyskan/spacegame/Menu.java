package com.dyskan.spacegame;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class Menu extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }
    
    public void btn_StartGame_onClick(View view){
    	this.startActivity(new Intent(this, MainActivity.class));
    }
}

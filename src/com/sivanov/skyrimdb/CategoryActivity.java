package com.sivanov.skyrimdb;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class CategoryActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
        
        View show_perks_btn = findViewById(R.id.show_perks_btn);
        show_perks_btn.setOnClickListener(new View.OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		
	    }
	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.categories, menu);
        return true;
    }
}

package com.sivanov.skyrimdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.sivanov.skyrimdb.db.PerksProvider;

public class CategoryActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        View show_perks_btn = findViewById(R.id.show_perks_btn);
        show_perks_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DataDisplayActivity.class);
                intent.putExtra("provider", PerksProvider.CONTENT_URI);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.categories, menu);
        return true;
    }
}

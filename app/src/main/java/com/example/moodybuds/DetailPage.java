package com.example.moodybuds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailPage extends AppCompatActivity {

    Button updateButton;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        updateButton = findViewById(R.id.updateButton);
        context = this;
        // on click
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMain = new Intent(context, MainScreen.class);
                startActivity(toMain);
            }
        });
    }


}
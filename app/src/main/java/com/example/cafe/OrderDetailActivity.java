package com.example.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    private final static String DRINK = "drink";
    private final static String USER_NAME = "userName";
    private final static String ADDITIVES = "additives";
    private final static String DRINK_TYPE = "drinkType";

    TextView textViewUserName;
    TextView textViewDrink;
    TextView textViewDrinkType;
    TextView textViewAdditives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initViews();
        showOrderDetail();
    }

    private void showOrderDetail() {
        String userName = getIntent().getStringExtra(USER_NAME);
        String drink = getIntent().getStringExtra(DRINK);
        String additives = getIntent().getStringExtra(ADDITIVES);
        String drinkType = getIntent().getStringExtra(DRINK_TYPE);
        textViewUserName.setText(userName);
        textViewDrink.setText(drink);
        if (additives.length() != 2)
            textViewAdditives.setText(additives.substring(1, additives.length()-1));
        else
            textViewAdditives.setText(getString(R.string.empty_additives));
        textViewDrinkType.setText(drinkType);
    }

    private void initViews() {
        textViewUserName = findViewById(R.id.textViewName);
        textViewDrink = findViewById(R.id.textViewDrink);
        textViewDrinkType = findViewById(R.id.textViewDrinkType);
        textViewAdditives = findViewById(R.id.textViewAdditives);
    }

    public static Intent newIntent(Context context,
                                   String userName,
                                   String drink,
                                   String additives,
                                   String drinkType) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(DRINK, drink);
        intent.putExtra(USER_NAME, userName);
        intent.putExtra(ADDITIVES, additives);
        intent.putExtra(DRINK_TYPE, drinkType);
        return intent;
    }
}
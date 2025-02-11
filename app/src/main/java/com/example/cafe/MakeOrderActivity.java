package com.example.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "userName";

    TextView header;
    TextView textViewAdditives;
    TextView chooseDrinkType;

    RadioGroup radioGroup;
    RadioButton radioButtonTea;
    RadioButton radioButtonCoffee;

    CheckBox sugar;
    CheckBox milk;
    CheckBox lemon;

    Spinner tea;
    Spinner coffee;

    Button makeOrder;

    private String drink;
    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        initViews();

        setUpUserName();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radioButtonTea.getId()) {
                    onUserChoseTea();
                } else if (checkedId == radioButtonCoffee.getId()) {
                    onUserChoseCoffee();
                }
            }
        });
        makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserMadeOrder();
            }
        });
    }

    private void onUserMadeOrder() {
        ArrayList<String> additives = new ArrayList<>();
        if (sugar.isChecked()) {
            additives.add(sugar.getText().toString());
        }
        if (milk.isChecked()) {
            additives.add(milk.getText().toString());
        }
        if (radioButtonTea.isChecked() && lemon.isChecked()) {
            additives.add(lemon.getText().toString());
        }
        String drinkType = "";
        if (radioButtonTea.isChecked()) {
            drinkType = tea.getSelectedItem().toString();
        } else if (radioButtonCoffee.isChecked()) {
            drinkType = coffee.getSelectedItem().toString();
        }
        launchNextScreen(userName, drink, additives.toString(), drinkType);
    }
    private void onUserChoseTea() {
        drink = getString(R.string.radio_button_tea);
        String additivesLabel = getString(R.string.additives, drink);
        textViewAdditives.setText(additivesLabel);
        showAllView();
        lemon.setVisibility(TextView.VISIBLE);
        tea.setVisibility(TextView.VISIBLE);
        coffee.setVisibility(TextView.INVISIBLE);
    }

    private void onUserChoseCoffee() {
        drink = getString(R.string.radio_button_coffee);
        String additivesLabel = getString(R.string.additives, drink);
        textViewAdditives.setText(additivesLabel);
        showAllView();
        lemon.setVisibility(TextView.INVISIBLE);
        tea.setVisibility(TextView.INVISIBLE);
        coffee.setVisibility(TextView.VISIBLE);
    }

    private void showAllView() {
        textViewAdditives.setVisibility(TextView.VISIBLE);
        chooseDrinkType.setVisibility(TextView.VISIBLE);
        sugar.setVisibility(TextView.VISIBLE);
        milk.setVisibility(TextView.VISIBLE);
        makeOrder.setVisibility(TextView.VISIBLE);
    }
    private void setUpUserName() {
        userName = getIntent().getStringExtra(EXTRA_USER_NAME);
        String greetings = getString(R.string.greetings, userName);
        header.setText(greetings);
    }

    private void initViews() {
        header = findViewById(R.id.textViewGreetings);
        textViewAdditives = findViewById(R.id.textViewAdditives);
        chooseDrinkType = findViewById(R.id.textViewDrinkType);

        radioGroup = findViewById(R.id.radioGroupDrinks);
        radioButtonTea = findViewById(R.id.radioButtonTea);
        radioButtonCoffee = findViewById(R.id.radioButtonCoffee);

        sugar = findViewById(R.id.checkboxSugar);
        milk = findViewById(R.id.checkboxMilk);
        lemon = findViewById(R.id.checkboxLemon);

        tea = findViewById(R.id.spinnerTea);
        coffee = findViewById(R.id.spinnerCoffee);

        makeOrder = findViewById(R.id.buttonMakeOrder);

    }

    private void launchNextScreen(String userName,
                                  String drink,
                                  String additives,
                                  String drinkType) {
        Intent intent = OrderDetailActivity.newIntent(this, userName, drink, additives, drinkType);
        startActivity(intent);
    }
    public static Intent newIntent(Context context, String userName) {
        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        return intent;
    }
}
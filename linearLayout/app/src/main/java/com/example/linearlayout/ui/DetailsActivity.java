package com.example.linearlayout.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.linearlayout.R;
import com.example.linearlayout.business.FoodBusiness;
import com.example.linearlayout.constants.FoodConstants;
import com.example.linearlayout.entities.FoodEntity;

public class DetailsActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Elementos de interface
        this.mViewHolder.mTextName = findViewById(R.id.text_name);
        this.mViewHolder.mTextCalories = findViewById(R.id.text_calories);
        this.mViewHolder.mTextQuantity = findViewById(R.id.text_quantity);
        this.mViewHolder.mTextUnit = findViewById(R.id.text_unit);
        this.mViewHolder.mTextDescription = findViewById(R.id.text_description);

        this.getData();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getData() {

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            // Obtém ID passado por parâmetro e busca detalhes do alimento
            int id = bundle.getInt(FoodConstants.FOOD_ID);
            FoodEntity food = new FoodBusiness().get(id);

            // Preenche os dados para visualização
            this.mViewHolder.mTextName.setText(food.getName());
            this.mViewHolder.mTextQuantity.setText(String.valueOf(food.getQuantity()));
            this.mViewHolder.mTextUnit.setText(food.getUnit());

            String strCalories = String.format("%s %s", food.getCalories(), this.getString(R.string.calorias));
            this.mViewHolder.mTextCalories.setText(strCalories);

            this.mViewHolder.mTextDescription.setText(food.getDescription());
        }

    }

    private static class ViewHolder {
        TextView mTextName;
        TextView mTextCalories;
        TextView mTextQuantity;
        TextView mTextUnit;
        TextView mTextDescription;
    }
}




















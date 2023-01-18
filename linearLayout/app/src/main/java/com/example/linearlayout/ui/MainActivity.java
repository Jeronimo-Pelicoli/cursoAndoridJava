package com.example.linearlayout.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.linearlayout.R;
import com.example.linearlayout.ui.adapter.FoodAdapter;
import com.example.linearlayout.business.FoodBusiness;
import com.example.linearlayout.constants.FoodConstants;
import com.example.linearlayout.entities.FoodEntity;
import com.example.linearlayout.listener.OnListClick;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private FoodBusiness mFoodBusiness = new FoodBusiness();
    private OnListClick mListener;
    private int mFilter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mListener = new OnListClick() {
            @Override
            public void onClick(int id) {
                // Logica de navegação

                Bundle bundle = new Bundle();
                bundle.putInt(FoodConstants.FOOD_ID, id);

                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        };

        // Obter a recycler view
        this.mViewHolder.mRecyclerFood = findViewById(R.id.recycler_food);

        // Definir um layout
        this.mViewHolder.mRecyclerFood.setLayoutManager(new LinearLayoutManager(this));

        this.listFood();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.filter_low: {
                this.mFilter = FoodConstants.FILTER.CAL_LOW;
                break;
            }
            case R.id.filter_medium: {
                this.mFilter = FoodConstants.FILTER.CAL_MEDIUM;
                break;
            }
            case R.id.filter_high: {
                this.mFilter = FoodConstants.FILTER.CAL_HIGH;
                break;
            }
        }

        this.listFood();
        return super.onOptionsItemSelected(item);
    }

    private void listFood() {
        List<FoodEntity> list = this.mFoodBusiness.getList(this.mFilter);

        //  Definir um adapter
        this.mViewHolder.mRecyclerFood.setAdapter(new FoodAdapter(list, this.mListener));
    }

    private static class ViewHolder {
        RecyclerView mRecyclerFood;
    }
}

















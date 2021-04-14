package com.example.mulitchoicequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Toolbar;

import com.example.mulitchoicequiz.Adapter.CategoryAdapter;
import com.example.mulitchoicequiz.Common.SpaceDecoration;
import com.example.mulitchoicequiz.Models.DBHelper;

public class MainActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recycler_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Quiz");
        setSupportActionBar(toolbar);

        recycler_category = (RecyclerView) findViewById(R.id.recycler_category);
        recycler_category.setLayoutManager((new GridLayoutManager(this,2)));

        CategoryAdapter adapter= new CategoryAdapter(MainActivity.this, DBHelper.getInstance(this).getAllCategories());
        int spaceInPixel = 4;
        recycler_category.addItemDecoration(new SpaceDecoration(spaceInPixel));
        recycler_category.setAdapter(adapter);
    }
}
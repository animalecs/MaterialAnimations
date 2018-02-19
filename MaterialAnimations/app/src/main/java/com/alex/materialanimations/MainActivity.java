package com.alex.materialanimations;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.alex.materialanimations.adapters.ColorsAdapter;
import com.alex.materialanimations.models.Color;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    List<Color> colors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);

        colors.add(new Color("Red", ContextCompat.getColorStateList(this, R.color.red)));
        colors.add(new Color("Yellow", ContextCompat.getColorStateList(this, R.color.yellow)));
        colors.add(new Color("Blue", ContextCompat.getColorStateList(this, R.color.blue)));
        colors.add(new Color("Orange", ContextCompat.getColorStateList(this, R.color.orange)));
        colors.add(new Color("Green", ContextCompat.getColorStateList(this, R.color.green)));

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new ColorsAdapter(colors));
    }
}

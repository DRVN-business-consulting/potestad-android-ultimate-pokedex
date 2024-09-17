package com.example.potestadultimatepokedex;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.potestadultimatepokedex.adapter.MyAdapter;
import com.example.potestadultimatepokedex.model.dto.response.PokemonModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView myRecyclerView;
    MyAdapter myAdapter;

    List<PokemonModel> pokemonList = List.of(
            new PokemonModel("JigglyPuff", R.drawable.jiggly, 1),
            new PokemonModel("Nidorina", R.drawable.jiggly, 2),
            new PokemonModel ("Meowth", R.drawable.jiggly, 3)
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myRecyclerView = findViewById(R.id.pokemonRecyclerView);
        myAdapter = new MyAdapter(pokemonList, this);

        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setAdapter(myAdapter);
    }
}
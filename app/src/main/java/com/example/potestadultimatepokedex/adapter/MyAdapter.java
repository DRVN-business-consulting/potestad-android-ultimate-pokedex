package com.example.potestadultimatepokedex.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.potestadultimatepokedex.PokemonDetailsActivity;
import com.example.potestadultimatepokedex.model.dto.response.PokemonModel;
import com.example.potestadultimatepokedex.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<PokemonModel> pokemonList;
    private Context context;

    public MyAdapter(List<PokemonModel> pokemonList, Context context) {
        this.pokemonList = pokemonList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_pokemon, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PokemonModel pokemon = pokemonList.get(position);
        holder.bind(pokemon);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), PokemonDetailsActivity.class);
            intent.putExtra("id", pokemon.getPokemonId());
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPokemon;
        TextView tvPokemon;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivPokemon = itemView.findViewById(R.id.ivPokemon);
            tvPokemon = itemView.findViewById(R.id.tvPokemon);
        }

        public void bind(PokemonModel pokemon) {
            ivPokemon.setImageResource(pokemon.getImageResId());
            tvPokemon.setText(pokemon.getName());
        }
    }
}


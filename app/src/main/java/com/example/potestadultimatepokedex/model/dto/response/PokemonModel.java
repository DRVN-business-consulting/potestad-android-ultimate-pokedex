package com.example.potestadultimatepokedex.model.dto.response;

public class PokemonModel {
    private String name;
    private int imageResId, pokemonId;


    public PokemonModel(String name, int imageResId, int pokemonId) {
        this.name = name;
        this.imageResId = imageResId;
        this.pokemonId = pokemonId;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;

    }
//    public int getPokeType() {
//        return imageResId;
//    }
//
//    public void setPokeType(int pokemonType) {
//        this.pokemonType = pokemonType;
//    }
}
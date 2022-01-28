package com.ynov.food.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ynov.food.databinding.RowLayoutRestaurantBinding;


class RestaurantHolder extends RecyclerView.ViewHolder {
    RowLayoutRestaurantBinding binding;

    public RestaurantHolder(@NonNull RowLayoutRestaurantBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}

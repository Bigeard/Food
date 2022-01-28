package com.ynov.food.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavOptions;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ynov.food.R;
import com.ynov.food.bo.Restaurant;
import com.ynov.food.databinding.RowLayoutRestaurantBinding;
import com.ynov.food.fragment.ListRestaurantFragmentDirections;

import java.util.ArrayList;


public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantHolder> {
        ArrayList<Restaurant> restaurantArrayList;

        public RestaurantAdapter() {
            restaurantArrayList = new ArrayList<>();
        }

        public void addRestaurant(Restaurant r){
            restaurantArrayList.add(r);
            notifyItemInserted(restaurantArrayList.size()-1);
        }


        public void setRestaurantArrayList(ArrayList<Restaurant> restaurantArrayList) {
            this.restaurantArrayList = restaurantArrayList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RowLayoutRestaurantBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.row_layout_restaurant,
                    parent,
                    false
            );
            return new RestaurantHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull RestaurantHolder holder, int position) {
            Restaurant rest = restaurantArrayList.get(position);
            ListRestaurantFragmentDirections.Liste2Detail action =
                    ListRestaurantFragmentDirections.liste2Detail(rest);
            holder.itemView.setOnClickListener(
                    (view)-> Navigation.findNavController(holder.itemView)
                            .navigate(
                                    R.id.detailRestaurantFragment,
                                    new Bundle().putParcelable("dddd", rest),
                                    new NavOptions.Builder().setPopUpTo(R.id.listRestaurantFragment, true).build())
            );
            holder.binding.setRestaurant(rest);
        }

        @Override
        public int getItemCount() {
            return restaurantArrayList.size();
        }
    }

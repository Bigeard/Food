package com.ynov.food.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ynov.food.bo.Restaurant;

import java.util.ArrayList;


public class ListRestaurantFragmentViewModel extends ViewModel {
    MutableLiveData<ArrayList<Restaurant>> arrayListRestaurant;

    public MutableLiveData<ArrayList<Restaurant>> getArrayListRestaurant(){
        if(arrayListRestaurant == null){
            this.arrayListRestaurant = new MutableLiveData<>(new ArrayList<>());
        }
        return this.arrayListRestaurant;
    }

}

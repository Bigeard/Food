package com.ynov.food.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ynov.food.R;
import com.ynov.food.adapter.RestaurantAdapter;
import com.ynov.food.bo.Restaurant;
import com.ynov.food.viewmodel.ListRestaurantFragmentViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ListRestaurantFragment extends Fragment {
    OkHttpClient client;
    private static final String TAG = "ListeRestaurantFragment";
/*    private SharedPreferences sp;
    private String token;*/
    private RestaurantAdapter adapter;
    private RecyclerView rv;
    private ListRestaurantFragmentViewModel vm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = new OkHttpClient();
/*        sp = getContext().getSharedPreferences(getString(R.string.spConfigName), MODE_PRIVATE);
        token = sp.getString(getString(R.string.keyJwt),"jkhkh");*/
        vm = new ViewModelProvider(this).get(ListRestaurantFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_restaurant, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Initialiser notre Toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(view.findViewById(R.id.toolbar));
        setHasOptionsMenu(true);
        //J'initialise ma recyclerview avec un RestaurantAdapter
        initializeRestaurants();
        Observer<ArrayList<Restaurant>> observerList = restaurants -> {
            adapter.setRestaurantArrayList(restaurants);
            rv.scrollToPosition(adapter.getItemCount()-1);
        };
        vm.getArrayListRestaurant().observe(getViewLifecycleOwner(),observerList);
        if(vm.getArrayListRestaurant().getValue().isEmpty()){
            fetchRestaurants();
        }

        super.onViewCreated(view, savedInstanceState);
    }

    public void fetchRestaurants(){
        ArrayList<Restaurant> alRests = new Gson().fromJson(
                "[{\"name\":\"restoducoin\",\"info\":\"bonresto...\"},{\"name\":\"Routedelafain\",\"info\":\"pascher\"}]",
                new TypeToken<ArrayList<Restaurant>>(){}.getType()
        );
        vm.getArrayListRestaurant().postValue(alRests);

        /*Request requestMsg = new Request.Builder()
                .url("https://flutter-learning.mooo.com/restaurants")
                .header("Authorization", "Bearer "+ token)
                .build();
        client.newCall(requestMsg).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure: " + "récupération rests:" + e.getRestaurant());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                //Récupérer les restaurants en ArrayList<Restaurant>
                if(response.code() == 200){
                    ArrayList<Restaurant> alMsgs = new Gson().fromJson(
                            response.body().string(),
                            new TypeToken<ArrayList<Restaurant>>(){}.getType()
                    );
                    vm.getArrayListRestaurant().postValue(alMsgs);
                }else{
                    Log.e(TAG, "onResponse: " + "authentification incorrecte" );
                }
            }
        });*/
    }

    private void initializeRestaurants(){
        rv = getView().findViewById(R.id.recyclerView);
        adapter = new RestaurantAdapter();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list_restaurant,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_refresh){
            fetchRestaurants();
        }
        return super.onOptionsItemSelected(item);
    }
}
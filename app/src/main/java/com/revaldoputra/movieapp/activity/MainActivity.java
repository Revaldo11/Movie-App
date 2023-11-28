package com.revaldoputra.movieapp.activity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.revaldoputra.movieapp.R;
import com.revaldoputra.movieapp.adapter.MainAdapter;
import com.revaldoputra.movieapp.model.MovieModel;
import com.revaldoputra.movieapp.retrofit.Constant;
import com.revaldoputra.movieapp.retrofit.MovieService;
import com.revaldoputra.movieapp.retrofit.RetrofitInstance;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    private final MovieService service = RetrofitInstance.getUrl().create(MovieService.class);
    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;
    private MainAdapter adapter;
    private List<MovieModel.Result> movies = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        setupRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMoviePopular();
    }
    private void setupView() {
        recyclerView = findViewById(R.id.list_movies);
        progressBar = findViewById(R.id.progress_bar);
    }

    private void setupRecyclerView() {
       adapter = new MainAdapter(movies, this);
       layoutManager = new GridLayoutManager(this, 2);
       recyclerView.setLayoutManager(layoutManager);
       recyclerView.setAdapter(adapter);
    }

    private void getMoviePopular(){

        showLoading(true);

        Call<MovieModel> call = service.getPopularMovie(
                Constant.API_KEY,
                Constant.LANGUAGE,
                "1"
        );
        Log.d(TAG, "getMoviePopular: "+call.request().url());
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                showLoading(false);
                if (response.isSuccessful()){
                    MovieModel movieModel = response.body();
                    assert movieModel != null;
                    showMovie(movieModel);
                    Log.d(TAG, "onResponse: "+movieModel.getResults().toString());
                }else {
                    showLoading(false);
                    MovieModel movieModel = response.body();
                    assert movieModel != null;
                    List<MovieModel.Result> results = movieModel.getResults();
                    Log.d(TAG, "onResponse: "+results.toString());
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                showLoading(false);
                Log.d("MainActivity", "onFailure: "+t.getMessage());
            }
        });

    }


    private void showLoading(Boolean state){
        if(state){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void showMovie(MovieModel movieModel){
        movies = movieModel.getResults();
        adapter.setData(movies);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
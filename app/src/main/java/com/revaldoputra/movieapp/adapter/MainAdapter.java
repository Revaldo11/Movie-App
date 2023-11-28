package com.revaldoputra.movieapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.revaldoputra.movieapp.model.MovieModel;
import com.revaldoputra.movieapp.R;
import com.revaldoputra.movieapp.retrofit.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    String TAG = "MainAdapter";
   private List<MovieModel.Result> results;
   private Context context;

   public MainAdapter(List<MovieModel.Result> results, Context context){
       this.results = results;
       this.context = context;
   }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie, parent,
                        false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        MovieModel.Result result = results.get(position);

        holder.text_title.setText(result.getTitle());
        Picasso.get()
                .load(Constant.IMAGE_POSTER + result.getPoster_path())
                .into(holder.image_poster);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_poster;
        TextView text_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_poster = itemView.findViewById(R.id.image_poster);
            text_title = itemView.findViewById(R.id.text_title);
        }
    }

    public void setData(List<MovieModel.Result> newResults){
        results.clear();
        results.addAll(newResults);
        notifyDataSetChanged();
    }
}

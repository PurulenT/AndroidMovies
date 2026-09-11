package com.example.androidmovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder> {

    List<Trailer> trailers = new ArrayList<>();
    private OnImageClickListener onImageClickListener;

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    @NonNull
    @Override
    public TrailersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.trailer_item,
                parent,
                false);
        return new TrailersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersViewHolder holder, int position) {
        Trailer trailer = trailers.get(position);
        holder.textViewTrailerName.setText(trailer.getName());

        holder.imageViewPlayTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onImageClickListener != null) {
                    onImageClickListener.onImageClick(trailer);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    static class TrailersViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTrailerName;
        public ImageView imageViewPlayTrailer;

        public TrailersViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            textViewTrailerName = itemView.findViewById(R.id.textViewTrailerName);
            imageViewPlayTrailer = itemView.findViewById(R.id.imageViewPlayTrailer);
        }
    }

    interface OnImageClickListener {
        void onImageClick(Trailer trailer);
    }
}

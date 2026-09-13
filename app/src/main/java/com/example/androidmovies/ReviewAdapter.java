package com.example.androidmovies;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{

    private List<Review> reviewList = new ArrayList<>();

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false
        );
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.textViewUsername.setText(review.getAuthor());
        holder.textViewDateReview.setText(review.getCreatedAt());
        holder.textViewTitle.setText(review.getTitle());
        holder.textViewComment.setText(review.getReview());
        int backgroundId;
        if(review.getType().equals("Позитивный")){
            backgroundId = android.R.color.holo_green_dark;
        }
        else if (review.getType().equals("Негативный")){
            backgroundId = android.R.color.holo_red_dark;
        }
        else{
            backgroundId = android.R.color.holo_orange_dark;
        }
        Drawable color = ContextCompat.getDrawable(holder.itemView.getContext(), backgroundId);
        holder.itemView.setBackground(color);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder{
        TextView textViewUsername;
        TextView textViewDateReview;
        TextView textViewTitle;
        TextView textViewComment;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews();
        }

        void initViews(){
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewDateReview = itemView.findViewById(R.id.textViewDateReview);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewComment = itemView.findViewById(R.id.textViewComment);
        }
    }
}

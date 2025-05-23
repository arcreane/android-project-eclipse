package com.example.redalert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.redalert.models.NewsArticle;

import java.util.List;

public class HealthArticleAdapter extends RecyclerView.Adapter<HealthArticleAdapter.ArticleViewHolder> {

    private final Context context;
    private final List<NewsArticle> articles;

    public HealthArticleAdapter(Context context, List<NewsArticle> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.article_item, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        NewsArticle article = articles.get(position);
        holder.title.setText(article.title);
        holder.description.setText(article.description);
        Glide.with(context).load(article.urlToImage).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, description;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.articleImage);
            title = itemView.findViewById(R.id.articleTitle);
            description = itemView.findViewById(R.id.articleDescription);
        }
    }
}

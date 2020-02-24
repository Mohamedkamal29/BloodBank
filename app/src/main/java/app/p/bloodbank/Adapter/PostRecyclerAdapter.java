package app.p.bloodbank.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.p.bloodbank.R;
import app.p.bloodbank.data.model.Post.Datum;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {

    List<Datum> posts;

    public PostRecyclerAdapter() {
        posts = new ArrayList<>();
    }

    public void addPosts(List<Datum> postsToAdd) {
        posts.addAll(postsToAdd);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.ivPost)
        ImageView ivPost;
        @BindView(R.id.ivFav)
        ImageView ivFav;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        private Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void bind(Datum datum) {
            if (datum.isIsFavourite()){
                ivFav.setImageResource(R.drawable.ic_favorite);
            }
            else {
                ivFav.setImageResource(R.drawable.ic_favorite_border);
            }
            tvTitle.setText(datum.getTitle());
            Glide.with(context).load(datum.getThumbnailFullPath()).into(ivPost);
        }
    }
}

package com.project.myanimelist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GetListAdapter extends RecyclerView.Adapter<GetListAdapter.VH> {
    ArrayList<Anime> animeList = new ArrayList<>();

    public GetListAdapter(ArrayList<Anime> animeList) {
        this.animeList = animeList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(animeList.get(position));
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView judul, rating;
        Context context = itemView.getContext();

        public VH(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.tv_judul);
            imgPhoto = itemView.findViewById(R.id.img_cover);
            rating = itemView.findViewById(R.id.tv_rating);
        }

        void bind(final Anime data){
            Picasso.get()
                    .load(data.getPhoto())
                    .placeholder(R.drawable.ic_baseline_broken_image_24)
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .into(imgPhoto);
            judul.setText(data.getJudul());
            rating.setText(data.getRating());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra(DetailActivity.EXTRA_DATA, data);
                    context.startActivity(i);
                }
            });
        }
    }
}

package com.betterlife.pronunciationjourney.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.betterlife.pronunciationjourney.R;
import com.betterlife.pronunciationjourney.model.PopularCollocationObject;

import java.util.List;

/**
 * Created by admin on 3/7/2018.
 */

public class PopularCollocationAdapter extends RecyclerView.Adapter<PopularCollocationAdapter.ViewHolder>{
    private List<PopularCollocationObject> collocationList;
    private Context context;
    private static OnItemClick onItemClick;

    public PopularCollocationAdapter(List<PopularCollocationObject> collocationList, Context context) {
        this.collocationList = collocationList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_collocation, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = collocationList.get(position).getTitle();
        int progress = collocationList.get(position).getProgress();

        holder.txtGroup1.setText(title);
        holder.txtGroup2.setText(title);
        holder.txtProgress.setText(String.format("%s/12", progress));

        holder.progressBar.setProgress(progress);
    }



    @Override
    public int getItemCount() {
        return collocationList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtGroup1, txtGroup2, txtProgress;
        private ProgressBar progressBar;
        private ImageView imgCollo;
        ViewHolder(View itemView) {
            super(itemView);

            txtGroup1 = itemView.findViewById(R.id.txtEng);
            txtGroup2 = itemView.findViewById(R.id.txtViet);
            txtProgress = itemView.findViewById(R.id.textProgress);
            progressBar = itemView.findViewById(R.id.progressColo);
            imgCollo = itemView.findViewById(R.id.imgCollo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClick.onClickListener(getAdapterPosition(), view);
        }
    }

    public void onItemClickListener(OnItemClick onItemClick) {
        PopularCollocationAdapter.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void onClickListener(int position, View view);
    }
}

package com.betterlife.pronunciationjourney.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.betterlife.pronunciationjourney.R;
import com.betterlife.pronunciationjourney.model.PopularWordObject;

import java.util.List;

/**
 * Created by HauDo on 2/27/2018.
 */

public class PopularWordAdapter extends RecyclerView.Adapter<PopularWordAdapter.DataViewHolder> {

    private List<PopularWordObject> wordObjectList;
    private Context context;
    private static OnItemClick onItemClick;

    public PopularWordAdapter(List<PopularWordObject> wordObjectList, Context context) {
        this.wordObjectList = wordObjectList;
        this.context = context;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_word, parent, false);
        return new DataViewHolder(item);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        String title = wordObjectList.get(position).getTitle();
        int progress = wordObjectList.get(position).getProgress();

        holder.txtGroup1.setText(String.format("Group %s", title));
        holder.txtGroup2.setText(String.format("Nhóm từ %s", title));
        holder.txtProgress.setText(String.format("%s/12", progress));

        holder.progressBar.setProgress(progress);
    }

    @Override
    public int getItemCount() {
        return wordObjectList.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtGroup1, txtGroup2, txtProgress;
        private ProgressBar progressBar;

        DataViewHolder(View itemView) {
            super(itemView);

            txtGroup1 = itemView.findViewById(R.id.txtGroup1);
            txtGroup2 = itemView.findViewById(R.id.txtGroup2);
            txtProgress = itemView.findViewById(R.id.txtProgress);
            progressBar = itemView.findViewById(R.id.progress);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClick.onClickListener(getAdapterPosition(), view);
        }
    }

    public void onItemClickListener(OnItemClick onItemClick) {
        PopularWordAdapter.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void onClickListener(int position, View view);
    }
}

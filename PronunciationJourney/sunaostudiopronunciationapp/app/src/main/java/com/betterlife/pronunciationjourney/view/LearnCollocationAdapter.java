package com.betterlife.pronunciationjourney.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.betterlife.pronunciationjourney.R;
import com.betterlife.pronunciationjourney.model.PopularCollocationObject;

import java.util.List;

/**
 * Created by admin on 3/11/2018.
 */

public class LearnCollocationAdapter extends RecyclerView.Adapter<LearnCollocationAdapter.ViewHolder> {
    private List<PopularCollocationObject> collocationList;
    private Context context;
    private OnItemClick onItemClick;

    public LearnCollocationAdapter(List<PopularCollocationObject> collocationList, Context context) {
        this.collocationList = collocationList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learn_collocation, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = collocationList.get(position).getTitle();
        holder.txtGroup1.setText(title);
        holder.txtGroup2.setText(title);
    }



    @Override
    public int getItemCount() {
        return collocationList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtGroup1, txtGroup2;
        private ImageView imgAudio, imgStar;

        ViewHolder(View itemView) {
            super(itemView);

            txtGroup1 = itemView.findViewById(R.id.txtEngTopic);
            txtGroup2 = itemView.findViewById(R.id.txtVietTopic);
            imgAudio = itemView.findViewById(R.id.imgAudio);
            imgStar = itemView.findViewById(R.id.imgStar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClick.onClickListener(getAdapterPosition(), view);
        }
    }

    public void setClickListener(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void onClickListener(int position, View view);
    }
}

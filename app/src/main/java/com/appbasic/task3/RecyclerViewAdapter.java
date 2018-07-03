package com.appbasic.task3;

/**
 * Created by 2159 on 03-01-2018.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<GetDataAdapter> getDataAdapter1;
    MainActivity mainActivity;

    public RecyclerViewAdapter(List<GetDataAdapter> getDataAdapter1, MainActivity mainActivity) {
        this.getDataAdapter1 = getDataAdapter1;
        this.mainActivity = mainActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        v.getLayoutParams().width=MainActivity.width;
        v.getLayoutParams().height=MainActivity.height/5;
        RecyclerViewAdapter.ViewHolder3 vh3 = new ViewHolder3(v);
        return vh3;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final RecyclerViewAdapter.ViewHolder3 holder1 = new RecyclerViewAdapter.ViewHolder3(holder.itemView);
        GetDataAdapter getDataAdapter22 = getDataAdapter1.get(position);
        holder1.idtext.setText("Id:              "+getDataAdapter22.get_id());
        holder1.categoryNametext.setText("Category:             " + getDataAdapter22.getCategoryName());
        holder1.captiontext.setText("Caption:            " + getDataAdapter22.getCaption());
        holder1.orientationtext.setText("Orientation:           " + getDataAdapter22.getOrientation());
        holder1.platformtext.setText("Platform:               " + getDataAdapter22.getPlatform());




        Log.d("hhhhh",";;;"+getDataAdapter22.getBitmap());
        Glide.with(mainActivity)
                .load(getDataAdapter22.getBitmap())
                .placeholder(R.drawable.ic_stub)
                .error(R.drawable.ic_stub)
                .into(holder1.image);

    }

    @Override
    public int getItemCount() {
        return getDataAdapter1.size();
    }

    class ViewHolder3 extends RecyclerView.ViewHolder {
        TextView idtext, categoryNametext, captiontext, orientationtext, platformtext;
        ImageView image;
        public ViewHolder3(View itemView) {
            super(itemView);
            idtext = (TextView) itemView.findViewById(R.id.idtext);
            image=(ImageView)itemView.findViewById(R.id.image);
            categoryNametext = (TextView) itemView.findViewById(R.id.categoryNametext);
            captiontext = (TextView) itemView.findViewById(R.id.captiontext);
            orientationtext = (TextView) itemView.findViewById(R.id.orientationtext);
            platformtext = (TextView) itemView.findViewById(R.id.platformtext);
        }
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}

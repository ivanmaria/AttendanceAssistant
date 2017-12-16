package com.github.ivanmaria.attendanceassistant;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
public class CustomAdapter extends BaseAdapter{
    private static LayoutInflater inflater = null;
    String [] subList;
    String [] hours;
    Context context;
    int [] percent;
    int count;

    public CustomAdapter(MainActivity mainActivity, int getcount, String[] subList1, String[] hours1, int[] percent1) {
        // TODO Auto-generated constructor stub
        subList=subList1;
        hours=hours1;
        context=mainActivity;
        percent=percent1;
        count = getcount;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return count;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.subject_stats, null);
        holder.subName = rowView.findViewById(R.id.sub);
        holder.subHours = rowView.findViewById(R.id.total);
        holder.subPercent = rowView.findViewById(R.id.percent);
        holder.pb = rowView.findViewById(R.id.progressBar);

        holder.subName.setText(subList[position]);
        holder.subHours.setText(hours[position]);
        holder.subPercent.setText(percent[position]+"%");
        holder.pb.setProgress(percent[position]);
        if(percent[position]<75)
            holder.pb.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        else
            holder.pb.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);

        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(percent[position]<75)
                    Toast.makeText(context, "Please attend regularly.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context, "Keep it up!", Toast.LENGTH_SHORT).show();

            }
        });
        return rowView;
    }

    public class Holder {
        TextView subName;
        TextView subHours;
        TextView subPercent;
        ProgressBar pb;
    }

}
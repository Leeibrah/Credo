package com.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.credit.Sales;
import com.credit.Sales.Cards;
import com.credit.R;


public class CardsAdapter extends ArrayAdapter<Cards>{

    Context context; 
    int layoutResourceId;    
    Cards data[] = null;
    
    public CardsAdapter(Context context, int layoutResourceId, Cards[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CardHolder holder = null;
        
        if(row == null)
        {

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new CardHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            
            row.setTag(holder);
        }
        else
        {
            holder = (CardHolder)row.getTag();
        }
        
        Cards cards = data[position];
        holder.txtTitle.setText(cards.title);
        holder.imgIcon.setImageResource(cards.icon);
        
        return row;
    }
    
    static class CardHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
    
    

}

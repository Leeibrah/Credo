package com.credit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dash.DashBoardListActivity;
import com.utils.CardsAdapter;


public class Sales extends DashBoardListActivity implements OnClickListener{
	ListView listView;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sales);
		
		
		final Cards card_data[] = new Cards[]
		        {
		            new Cards(R.drawable.n1000, "1000"),
		            new Cards(R.drawable.n500, "500"),
		            new Cards(R.drawable.n250, "250"),
		            new Cards(R.drawable.n100, "100"),
		            new Cards(R.drawable.n50, "50"),
		            new Cards(R.drawable.n50, "20"),
		            new Cards(R.drawable.n10, "10"),
		            new Cards(R.drawable.n5, "5")
		           
		        };
		        
		        CardsAdapter adapter = new CardsAdapter(this, 
		                R.layout.listview_item_row, card_data);
		        
		        
		        listView = getListView();
		         
		        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
		        listView.addHeaderView(header);
		        
		       
		        listView.setAdapter(adapter);
		        listView.setClickable(true);
		        listView.setTextFilterEnabled(true);
		        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
						// TODO Auto-generated method stub
//						Toast.makeText(getApplicationContext(), position, Toast.LENGTH_SHORT).show();
						if(position == 1){
							Toast.makeText(getApplicationContext(), "0", Toast.LENGTH_SHORT).show();
							intent = new Intent(getApplicationContext(), Sold.class);
							int valuedata = 1000;
							intent.putExtra("card_value", valuedata);
							startActivity(intent);
						}else if(position == 2){
							intent = new Intent(getApplicationContext(), Sold.class);
							int valuedata = 500;
							intent.putExtra("card_value", valuedata);
							startActivity(intent);
						}else if(position == 3){
							intent = new Intent(getApplicationContext(), Sold.class);
							int valuedata = 250;
							intent.putExtra("card_value", valuedata);
							startActivity(intent);
						}else if(position == 4){
							intent = new Intent(getApplicationContext(), Sold.class);
							int valuedata = 100;
							intent.putExtra("card_value", valuedata);
							startActivity(intent);				
						}else if(position == 5){
							intent = new Intent(getApplicationContext(), Sold.class);
							int valuedata = 50;
							intent.putExtra("card_value", valuedata);
							startActivity(intent);				
						}else if(position == 6){
							intent = new Intent(getApplicationContext(), Sold.class);
							int valuedata = 20;
							intent.putExtra("card_value", valuedata);
							startActivity(intent);				
						}else if(position == 7){
							intent = new Intent(getApplicationContext(), Sold.class);
							int valuedata = 10;
							intent.putExtra("card_value", valuedata);
							startActivity(intent);				
						}else if(position == 8){
							intent = new Intent(getApplicationContext(), Sold.class);
							int valuedata = 5;
							intent.putExtra("card_value", valuedata);
							startActivity(intent);				
						}
					}				
				});     
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	public class Cards{
    	public int icon;
        public String title;
        
        public Cards(){
            super();
        }
        
        public Cards(int icon, String title) {
            super();
            this.icon = icon;
            this.title = title;
        }
    }
}

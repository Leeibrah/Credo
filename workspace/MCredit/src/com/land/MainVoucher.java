package com.land;

import com.credit.MapLocation;
import com.credit.PhoneCall;
import com.credit.Sales;
import com.credit.Summary;
import com.credit.Support;
import com.dash.DashBoardActivity;
import com.credit.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.Manifest.*;

public class MainVoucher extends DashBoardActivity implements OnClickListener{
	Button sales, summary, support, call, login;
	ImageView location_btn;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        sales = (Button) findViewById(R.id.home_btn_feature1);
        summary = (Button) findViewById(R.id.home_btn_feature2);
        support = (Button) findViewById(R.id.home_btn_feature3);
        call = (Button) findViewById(R.id.home_btn_feature4);
        login = (Button) findViewById(R.id.login_btn);
        
        location_btn = (ImageView) findViewById(R.id.location_btn);
        sales.setOnClickListener(this);
        summary.setOnClickListener(this);
        support.setOnClickListener(this);
        call.setOnClickListener(this);
        login.setOnClickListener(this);
        location_btn.setOnClickListener(this);
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int pressed = v.getId();
		
		switch(pressed){
			case R.id.home_btn_feature1:
				startActivity(new Intent(getApplicationContext(), Sales.class));
				break;
			case R.id.home_btn_feature2:
				startActivity(new Intent(getApplicationContext(), Summary.class));
				break;
			case R.id.home_btn_feature3:
				startActivity(new Intent(getApplicationContext(), Support.class));
				break;
			case R.id.home_btn_feature4:
				Intent call = new Intent (Intent.ACTION_DIAL);
        		call.setData(Uri.parse("tel: 0714019079"));
        		startActivity(call);
//				startActivity(new Intent(getApplicationContext(), PhoneCall.class));
				break;
			case R.id.location_btn:
				startActivity(new Intent(getApplicationContext(), MapLocation.class));
				break;
			case R.id.login_btn:
				startActivity(new Intent(getApplicationContext(), Login.class));
				break;
		}
		
	}
}
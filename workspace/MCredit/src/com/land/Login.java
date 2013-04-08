package com.land;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.credit.R;
import com.dash.DashBoardActivity;



public class Login extends DashBoardActivity implements OnClickListener {
	Button ok, exit;
	TextView result;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		ok = (Button) findViewById(R.id.btn_login);
		ok.setOnClickListener(this);
		exit = (Button)findViewById(R.id.btn_cancel);
		exit.setOnClickListener(this);

		result = (TextView) findViewById(R.id.lbl_result);

	}
	public void check() {
		EditText uname = (EditText) findViewById(R.id.fill_name);
		String username = uname.getText().toString();

		EditText pword = (EditText) findViewById(R.id.fill_pass);
		String password = pword.getText().toString();
		
		if (username.equalsIgnoreCase("karis") && password.equalsIgnoreCase("karis")) {
//			Log.w("SENCIDE", "TRUE");
			Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
		}
	}
	public void postLoginData() {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();

		/* login.php returns true if username and password is equal to saranga */
		HttpPost httppost = new HttpPost("http://credo.wafanyikazi.org/login.php");

		try {
			// Add username and password
			EditText uname = (EditText) findViewById(R.id.fill_name);
			String username = uname.getText().toString();

			EditText pword = (EditText) findViewById(R.id.fill_pass);
			String password = pword.getText().toString();

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("username", username));
			nameValuePairs.add(new BasicNameValuePair("password", password));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			Log.w("SENCIDE", "Execute HTTP Post Request");
			HttpResponse response = httpclient.execute(httppost);

			String str = inputStreamToString(response.getEntity().getContent())
					.toString();
			Log.d("SENCIDE", str);
			
			if(username.equals("") || password.equals("")){
				Toast.makeText(getApplicationContext(), "Please fill in username and password", Toast.LENGTH_SHORT).show();
			}else{
				if (str.equals("1")) {
					Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
					result.setText("Login Successful");
					uname.setText("");
					pword.setText("");
				} else {
					Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
					result.setText("Login Failed");
				} 	//end of nested else
			} //end of first else 

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private StringBuilder inputStreamToString(InputStream is) {
		String line = "";
		StringBuilder total = new StringBuilder();
		// Wrap a BufferedReader around the InputStream
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		// Read response until the end
		try {
			while ((line = rd.readLine()) != null) {
				total.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String d = String.valueOf(total);
		Log.d("Returned", d);
		// Return full string
		return total;
	}

	public void alertDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to exit from this application?")
		       .setCancelable(false)
		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		           @Override
				public void onClick(DialogInterface dialog, int id) {
		        	// cancel the application
		        	   Login.this.finish();
		           }
		       })
		       .setNegativeButton("No", new DialogInterface.OnClickListener() {
		           @Override
				public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.btn_login) {
			postLoginData();

		}if(view.getId() == R.id.btn_cancel){
			alertDialog();
		}
	}

}

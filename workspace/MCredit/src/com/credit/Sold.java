package com.credit;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dash.DashBoardActivity;
import com.land.MainVoucher;


public class Sold extends DashBoardActivity implements OnClickListener {
	Bundle recordedData;
	Button submit_btn, exit_button;
	TextView card_quantity;
	ProgressDialog pDialog;

	// String input_url =
	// "http://10.0.2.2/credo/transaction.php?cardValue="+newCardValue+"&cardQuantity=90";
	String readInputStreamData, dataFromPhp, newCardValue, newCardQuantity;

	int cardvalue, cardquantity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sold);

		// initialize buttons
		submit_btn = (Button) findViewById(R.id.submit_button);
		submit_btn.setOnClickListener(this);
		
		exit_button = (Button) findViewById(R.id.exit_button);
		exit_button.setOnClickListener(this);

		card_quantity = (TextView) findViewById(R.id.quantity);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int pressed = v.getId();

		switch (pressed) {
		case R.id.home_btn:
			startActivity(new Intent(getApplicationContext(), MainVoucher.class));
			break;
		case R.id.submit_button:

			// Locate loc = new Locate();
			// loc.execute();

			if (card_quantity.getText().toString().equals("")) {
				Toast.makeText(getApplicationContext(),	"Please fill in card value", Toast.LENGTH_LONG).show();
			} else {
				
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Are you sure you want to push?")
				       .setCancelable(false)
				       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				           @Override
						public void onClick(DialogInterface dialog, int id) {
				        	// start data insert
								insertData();

								// show data insertion and clear the textview
								Toast.makeText(getApplicationContext(),	"Data was inserted to Database", Toast.LENGTH_LONG).show();
								card_quantity.setText("");
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
			break;
		case R.id.exit_button:
			Toast.makeText(getApplicationContext(),	"Exit Clicked", Toast.LENGTH_LONG).show();

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Are you sure you want to exit?")
			       .setCancelable(false)
			       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			           @Override
					public void onClick(DialogInterface dialog, int id) {
			                Sold.this.finish();
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
			break;
		}
	}

	@SuppressWarnings("unused")
	private class Locate extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Sold.this);
			pDialog.setMessage("Uploading Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			insertData();
			return null;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();
			Toast.makeText(getApplicationContext(), "Inserted",
					Toast.LENGTH_SHORT).show();
		}

	}

	private void insertData() {

		// TODO Auto-generated method stub

		// get data from the previous activity and assign to
		recordedData = getIntent().getExtras();
		cardvalue = recordedData.getInt("card_value");
		newCardQuantity = card_quantity.getText().toString();

		// convert values to string for printing
		newCardValue = String.valueOf(cardvalue);
		cardquantity = Integer.valueOf(newCardQuantity);

		// define the url
		String input_url = "http://credo.wafanyikazi.org/transaction.php?CardValue="+ cardvalue +"&CardQuantity=" +cardquantity;

		// create new httpclient and postheader
		HttpClient httpClient = new DefaultHttpClient();
		// HttpPost httpPost = new HttpPost(input_url);
		HttpGet httpGet = new HttpGet(input_url);

		// ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
		// param.add(new BasicNameValuePair("userID", "1"));
		// param.add(new BasicNameValuePair("cardValue", "1000"));
		// param.add(new BasicNameValuePair("cardQuantity",
		// card_quantity.getText().toString()));

		try {
			// httpPost.setEntity(new UrlEncodedFormEntity(param));
			// httpGet.setEntity(new UrlEncodedFormEntity(param));
			HttpResponse httpRespose = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpRespose.getEntity();
			InputStream in = httpEntity.getContent();
			BufferedReader read = new BufferedReader(new InputStreamReader(in));

			while ((readInputStreamData = read.readLine()) != null) {
				dataFromPhp += readInputStreamData;
			}

			Log.d("PHP Value", dataFromPhp);
			Log.d("Card Value", newCardValue);
			Log.d("Card Quantity", newCardQuantity);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

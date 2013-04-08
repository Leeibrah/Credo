package com.land;

import com.credit.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class SplashScreen extends Activity{
	

	   private boolean mActive = true;
	   private int mSplashTime = 500; // display time in ms
	   private Button btn;
	   /** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      
	      // hide the app title and also the notification bar
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
	      
	      setContentView(R.layout.splash);
//	      btn = (Button) findViewById(R.id.button1);
//	      Animation anim = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.animation);
//				btn.startAnimation(anim);

	      // thread for displaying the splash screen
	      Thread splashThread = new Thread() {
	         @Override
	         public void run() {
	            try {
	               int waited = 0;
	               while(mActive && (waited < mSplashTime)) {
	            	 
	                  sleep(100);
	                  if(mActive) {
	                     waited += 100;
	                  }
	               }
	            } catch(InterruptedException e) {
	               // do nothing
	            } finally {
	               Intent i = new Intent(getApplicationContext(), MainVoucher.class);
	               startActivity(i);
	               finish();
	            }
	         }
	      };
	      splashThread.start();
	   }
	   
	   @Override
	   public boolean onTouchEvent(MotionEvent event) {
	      if (event.getAction() == MotionEvent.ACTION_DOWN) {
	         mActive = false;
	      }
	      return true;
	   }
}

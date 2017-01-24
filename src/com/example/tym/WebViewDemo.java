package com.example.tym;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
// import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewDemo extends Activity {

	private WebView webView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
 	        setContentView(R.layout.layout_webview);
 
 	        
		String url =getIntent().getStringExtra("url"); //get url that is passed from the listview
 
		webView = (WebView)findViewById(R.id.wvDisplay);
		WebSettings webSetting= webView.getSettings(); //create new settings for webView
		webSetting.setJavaScriptEnabled(true); // enabled javascript
		webSetting.setSupportZoom(true); // enables zoom
		webSetting.setMinimumFontSize(10); 
		webSetting.setAllowFileAccess(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setDisplayZoomControls(false);
		webView.setWebViewClient(new WebViewClient()); //set up webviewclient, this set in order not to open the default browser when link is clicked
 
		webView.loadUrl(url);//load the web page
		
 
	}
	
	// reload button
	// not implemented
    
	// the Back button
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
 
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
	        webView.goBack();
	        return true;
	    }
 
	    return super.onKeyDown(keyCode, event);
	}
}

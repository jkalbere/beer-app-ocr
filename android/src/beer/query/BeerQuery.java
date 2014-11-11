package beer.query;


import java.util.LinkedHashMap;
import java.util.Map;

import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.api.GoogleAPI;

import com.google.api.translate.Translate;

import edu.sfsu.cs.orange.ocr.R;
import edu.sfsu.cs.orange.ocr.language.TranslatorGoogle;

public class BeerQuery {

	private Map<String,String> beerMap;
	
	public BeerQuery(){
	}
	
	 
	public void asyncBeerFetch(String s, final AQuery aq){
			  s.replaceAll("[^A-Za-z0-9 ]", "");
		      s.replace(" ", "+");
		      String url = "https://www.google.com/search?q=" + s + "+site%3Abeeradvocate.com";             		      
		      aq.ajax(url, String.class, new AjaxCallback<String>() {

		          @Override
		          public void callback(String url, String html, AjaxStatus status) {
		        	  	  int index = html.indexOf("Rating:");
		        	  	  if(index >= 0){
		        	  		  String rating = html.substring(index + 8, index + 10);     
		        	  		  String beerName = url.substring(url.indexOf("?q=") + 3, url.indexOf("+site") );
		        	  	  
		        	  	  
		        	  		  CharSequence text = aq.id(R.id.ocr_result_text_view).getText();
		        	  		  
		        	  		  if(!text.equals("")){
		        	  			  text = text + "\n";
		        	  		  }
		        	  	  
		        	  		  text = text + beerName + ": " + rating;
		        	  	  
		        	  		  aq.id(R.id.ocr_result_text_view).text(text);    
		        	  	  }
		          }	     
		  });
}		
}
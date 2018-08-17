package com.jl.app;

import android.os.AsyncTask;
import com.jl.app.utils.HttpProtocol;

/**
 * an AsyncTask implementation
 */

public class HttpTask extends AsyncTask<String,Void,String>{
	
	private HttpResponseHandler httpResponseHandler;
	
	public HttpTask(HttpResponseHandler httpResponseHandler){
		super();
		this.httpResponseHandler = httpResponseHandler;
	}
    
	@Override
	protected String doInBackground(String...url){
		return HttpProtocol.request(url[0]);
	}
	
	@Override
	protected void onPostExecute(String response){
		httpResponseHandler.handle(response);
	}
	
	public void executeParallel(String...url){
		executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,url);
	}
}

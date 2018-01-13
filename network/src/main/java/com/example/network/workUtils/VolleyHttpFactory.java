package com.example.network.workUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.network.framework.FailCallback;
import com.example.network.framework.NetworkFactory;
import com.example.network.framework.SuccessfulCallback;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class VolleyHttpFactory extends NetworkFactory {

    private static VolleyHttpFactory volleySingleton;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mContext;
	
    
    
    private VolleyHttpFactory(Context context) {

        mRequestQueue = getRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue,
        new ImageLoader.ImageCache(){
            private final LruCache<String,Bitmap> cache = new LruCache<String ,Bitmap>(20);
            @Override
            public Bitmap getBitmap(String url){
                return cache.get(url);
            }
            @Override
            public void putBitmap(String url,Bitmap bitmap){
                cache.put(url,bitmap);
            }
        });
        
        
    }
    
    
    public static synchronized VolleyHttpFactory getInstance(Context context){
           mContext=context;
        if(volleySingleton == null){
            volleySingleton = new VolleyHttpFactory(context);
        }
        return volleySingleton;
    }
    
	@Override
	public void start() {
		
		if(getMethod()==METHOD_GET){
			Log.i("VolleyHttp",getUrl()+"?"+getGetParams(getParams()));
			StringRequest stringRequest = new StringRequest(getUrl()+"?"+getGetParams(getParams()), new Listener<String>() {

				@Override
				public void onResponse(String arg0) {
					try {
						getSuccessfulCallback().success(arg0);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError arg0) {
					
					getFailCallback().fail(arg0.getMessage());
					
				}
			});
			
	   VolleyHttpFactory.getInstance(mContext.getApplicationContext()).addToRequestQueue(stringRequest);
	
	   
		}else if(getMethod()==METHOD_POST){
			
			
			StringRequest stringRequest=new StringRequest(Method.POST, getUrl(), new Listener<String>() {

				@Override
				public void onResponse(String arg0) {

					try {
						getSuccessfulCallback().success(arg0);
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError arg0) {
					
					getFailCallback().fail(arg0.getMessage());
				
				}
			}){
				
				@Override
				protected Map<String, String> getParams() throws AuthFailureError {
				
					return VolleyHttpFactory.this.getParams();

				}
			};
			
			VolleyHttpFactory.getInstance(mContext.getApplicationContext()).addToRequestQueue(stringRequest);
		}
		
		
	}
	
	
	
	 @Override
	public void start(int method, String url, final HashMap<String, String> params, final SuccessfulCallback successCall,
			final FailCallback failsCall) {
	
		 
		 if(method==METHOD_GET){
				StringRequest stringRequest = new StringRequest(url+"?"+getGetParams(params), new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						try {
							successCall.success(arg0);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						
						failsCall.fail(arg0.getMessage());
						
					}
				});
				
		   VolleyHttpFactory.getInstance(mContext.getApplicationContext()).addToRequestQueue(stringRequest);
		
		   
			}else if(method==METHOD_POST){
				
				
				StringRequest stringRequest=new StringRequest(Method.POST, url, new Listener<String>() {

					@Override
					public void onResponse(String arg0) {

						try {
							successCall.success(arg0);
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						
						failsCall.fail(arg0.getMessage());

					
					}
				}){
					
					@Override
					protected Map<String, String> getParams() throws AuthFailureError {
					
						return params;

					}
				};
				
		VolleyHttpFactory.getInstance(mContext.getApplicationContext()).addToRequestQueue(stringRequest);
			}
		 
	}


	public RequestQueue getRequestQueue(){
	        if(mRequestQueue == null){
	            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
	        }
	        return mRequestQueue;
	    }
	
	 
	 public <T> void addToRequestQueue(Request<T> req){
	        getRequestQueue().add(req);
	    }
	   
	 
	 public ImageLoader getImageLoader() {
	        return mImageLoader;
}


	public void getImageBitmapByUrl(String url, final BitmapListener listener){


		ImageRequest request=new ImageRequest(url, new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap arg0) {
				listener.success(arg0);

			}
		}, 0, 0, Config.ARGB_8888,new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				listener.fail(arg0.getMessage());
			}
		});

		VolleyHttpFactory.getInstance(mContext.getApplicationContext()).addToRequestQueue(request);

	}

	public void setImageBitmapBackground(String url,final View view){


		ImageRequest request=new ImageRequest(url, new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap arg0) {
				Drawable drawable =new BitmapDrawable(arg0);
				view.setBackgroundDrawable(drawable);

			}
		}, 0, 0, Config.ARGB_8888,new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {

			}
		});

		VolleyHttpFactory.getInstance(mContext.getApplicationContext()).addToRequestQueue(request);

	}
	public void setImageByImageLoader(String url,final ImageView view){

		ImageLoader loader=getInstance(view.getContext()).getImageLoader();
		loader.get(url, loader.getImageListener(view, 0, 0), 0, 0);

	}



	public 	interface BitmapListener{
		public void success(Bitmap b);
		public void fail(String error);


	}

	 
}

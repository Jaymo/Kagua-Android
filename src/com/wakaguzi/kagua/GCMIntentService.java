package com.wakaguzi.kagua;

import static com.wakaguzi.kagua.KaguaHome.SENDER_ID;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	private static final String TAG = "KAGUA GCMIntentService";
    
 
	
    public GCMIntentService() {
        super(SENDER_ID);
    }

    
    @Override
    protected void onRegistered(Context context, String registrationId) {
    	
    	 Log.i(TAG, registrationId);
    }

    
    @Override
    protected void onUnregistered(Context context, String registrationId) {
    	
    	Log.i(TAG, registrationId);
    }

    
    @Override
    protected void onMessage(Context context, Intent intent) {
    	 
         
    }
    
    
    @Override
    protected void onDeletedMessages(Context context, int total) {
        
    }

    
    @Override
    public void onError(Context context, String errorId) {
    	 Log.i(TAG, "Received error: " + errorId);
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
    	Log.i(TAG, "Received recoverable error: " + errorId);
    	 return super.onRecoverableError(context, errorId);
       
    }

    

}

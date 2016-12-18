package com.errorstation.sportstv;

/**
 * Created by Rubayet on 18-Dec-16.
 */


import android.util.Log;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class FirebaseInstanceIDService extends FirebaseInstanceIdService {


  @Override
  public void onTokenRefresh() {

    String token = FirebaseInstanceId.getInstance().getToken();

    registerToken(token);
  }

  private void registerToken(String token) {

    Log.d("registerToken: ",token);
    FirebaseCrash.log("registerToken: "+token);

  }
}
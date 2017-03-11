package com.example.ravikant.gcm;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by ravikant on 30/11/16.
 */

public class MyService extends Service {
    private Context context;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public MyService(Context context){
        this.context = context;
    }


}

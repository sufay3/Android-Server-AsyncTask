package com.jl.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * service for a long-lasting computing process which is executed in the background.
 */
public class ComputingService extends Service {
    @Override
    public void onCreate() {
        Log.d("onCreate", "creating service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new ComputingThread().start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

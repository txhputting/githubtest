package yaoshun.com.AndroidTest;

import android.app.Service;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import java.io.FileDescriptor;
import java.util.ArrayList;
import java.util.List;

public class testService extends Service {
    private static final String TAG = "testService";
    public testService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
//        FileDescriptor.out
        Log.d(TAG, "onBind: ");
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new DeleteRunnable()).start();
        return super.onStartCommand(intent, flags, startId);
    }

    private class DeleteRunnable implements Runnable {
        @Override
        public void run() {
            while(true){
                Log.d(TAG, "onStartCommand: ");
                WifiManager wifiManager = getSystemService(WifiManager.class);
                Log.d(TAG, "testService onCreate: " +  wifiManager.getConnectionInfo().getSSID());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }
}
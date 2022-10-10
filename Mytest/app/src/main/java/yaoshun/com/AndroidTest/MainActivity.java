package yaoshun.com.AndroidTest;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.net.VpnService;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import com.cvte.tv.api.aidl.EntityInputSource;
import com.cvte.tv.api.aidl.ITvApiManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
//import com.mstar.android.media.MMediaPlayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements ServiceConnection {
    Intent it1;
    Intent it2;
    Intent it3;
    Intent it4;
    TextView resultView;
    private WifiManager wifiManager;
    private List<ScanResult> mWifiList;
    private ITvApiManager iTvApiManager;

    private static final String TAG = "MainActivity";
    MyBroadcastReceiver intentServiceBroadcastReciver;

    private View.OnClickListener button1OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (iTvApiManager != null) {
                Log.i(TAG, "getSourceList() 000000");
                try {
                    Log.i(TAG, "getSourceList() 1111111");
                    List<EntityInputSource> entityInputSources = iTvApiManager.getTVApiSystemInputSource().eventSystemInputSourceGetList();
                    for (EntityInputSource tmp : entityInputSources){
                        Log.i(TAG, "sourceList add   " + tmp.name);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
//            startActivityForResult(it1, 0x001);
//            startWifiScaning();
//            System.load("/system/lib/libbinder_ndk_jni.so");
//            System.loadLibrary("mmedia_jni");
//            WifiManager wifiManager = getSystemService(WifiManager.class);
//            Log.d(TAG, "onClick: " +  wifiManager.getConnectionInfo().getSSID());
        }
    };


    private BroadcastReceiver mWifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String wifiInfo = null;
            List<String> wifiInfoList = new ArrayList<String>();
            Log.d(TAG, " mWifiReceiver onReceive: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            mWifiList = wifiManager.getScanResults();
            if (mWifiList != null) {
                for (ScanResult result : mWifiList) {
                    if (result.SSID == null || result.SSID.length() == 0
                            || result.capabilities.contains("[IBSS]")) {
                        continue;
                    }
                    String mSSID = result.SSID;
                    String mCapabilities = result.capabilities;
                    String mLevel = String.valueOf(result.level);
                    wifiInfo = mSSID + ";" + mCapabilities + ";" + mLevel;
                    Log.d(TAG, "onReceive: result = " + result.SSID + " time = " + result.timestamp);
                    wifiInfoList.add(wifiInfo);
                }
            }
        }
    };

    public void startWifiScaning() {
        enableWifi();
        registerWifiReceiver();
        wifiManager.startScan();
    }


    public void SourceDataProvider() {
        Intent intent = new Intent("com.cvte.tv.api.TV_API_SERVICE");
        intent.setPackage("com.cvte.tv.api.impl");
        this.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }


    public boolean enableWifi() {
        if (!getWifiManager()) {
            return false;
        }
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        return true;
    }

    private boolean getWifiManager() {
        if (wifiManager == null) {
            wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            return wifiManager != null;
        }
        return true;
    }

    private void registerWifiReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        this.registerReceiver(mWifiReceiver, filter);
    }

    private View.OnClickListener button2OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            startActivityForResult(it2, 0x002);
            startActivityForResult(it3, 0x002);
//            startActivityForResult(it4, 0x002);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: xxxxxx");
        super.onCreate(savedInstanceState);
        SourceDataProvider();
        setContentView(R.layout.activity_main);
        Button butto1 = findViewById(R.id.button1);
        butto1.setFilterTouchesWhenObscured(false);
        Button butto2 = findViewById(R.id.button2);
        butto2.setFilterTouchesWhenObscured(false);

        findViewById(R.id.button1).setOnClickListener(button1OnClickListener);
        findViewById(R.id.button2).setOnClickListener(button2OnClickListener);
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unBindService();
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService();
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStopService();
            }
        });

        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartService();
            }
        });
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntentService();
            }
        });

        intentServiceBroadcastReciver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("filefinish");
        registerReceiver(intentServiceBroadcastReciver, filter);
        resultView = findViewById(R.id.result);
        it1 = new Intent(this, TipsActivity.class);
        it1.putExtra("language", "English");

        it2 = new Intent(this, WelcomeActivity.class);
        Bundle bd = new Bundle();
        bd.putString("name", "yaoshun");
        bd.putInt("Age", 25);
        bd.putString("Tips", "Hi bro");
        it2.putExtras(bd);

        it3 = new Intent(this, calculatorActivity.class);
        it4 = new Intent(this, NdkdemoAcitivity.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("yaoshun", "startActivityForResult: 11111");
        String result = "Fail";
        if (requestCode == 0x002){
            result = "0x002 Result ";
        }else if(requestCode == 0x001){
            result = "0x001 Result ";
        }
        if(data != null){
            result += data.getStringExtra("result");
        }
        resultView.setText(result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: xxxxxx");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: xxxxxxxx");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: xxxxxxx");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: xxxxxx");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: xxxxxxx");
        super.onDestroy();
        //即使之前停止了服务，再次停止服务也是不会报错的
        stop();
        //解除广播
        unregisterReceiver(intentServiceBroadcastReciver);
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: ");
        super.onRestart();
    }
//region Service
    private void onStartService(){
        Log.d(TAG, "onStartService: ");
        startService(new Intent(this, testService.class));
    }

    private void onStopService(){
        Log.d(TAG, "onStopService: ");
        stopService(new Intent(this, testService.class));
    }

    private void bindService(){
        Log.d(TAG, "bindService: ");
        bindService(new Intent(this, testService.class), conn, flags);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };

    private int flags = Service.BIND_AUTO_CREATE;

    private  void unBindService(){
        Log.d(TAG, "unBindService: ");
        try{
            unbindService(conn);
        } catch (Exception e){
            Log.d(TAG, "unBindService: " + e);
        }
    }

    private  void startIntentService(){
        startService(new Intent(this, MyIntentService.class));
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
//        Log.e(TAG, "yaoshun onServiceConnected");
//        for (int i = 0 ; i < 5; i++){
//            if(iTvApiManager == null) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Log.d(TAG, "iTvApiManager == null");
//                iTvApiManager = ITvApiManager.Stub.asInterface(service);
//            }else{
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Log.d(TAG, "iTvApiManager != null");
//            }
//            onResume();
//        }
        Log.e(TAG, " onServiceConnected");
        for (int i = 0 ; i < 5; i++){
            if(iTvApiManager == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "iTvApiManager == null");
                iTvApiManager = ITvApiManager.Stub.asInterface(service);
            }else{
                Log.d(TAG, "iTvApiManager != null");
            }
            onResume();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.e(TAG, "onServiceDisconnected");
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //对接收到的广播进行处理，intent里面包含数据
            String result = intent.getStringExtra("file");
            resultView.setText(result);
            //停止服务,它的子线程也会停止
            stop();
        }
    }

    public void stop() {
        stopService(new Intent(MainActivity.this, MyIntentService.class));
    }
//endregion
}
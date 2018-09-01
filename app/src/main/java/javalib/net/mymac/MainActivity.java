package javalib.net.mymac;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView macText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        macText = findViewById(R.id.mac_text);

        IntentFilter filter = new IntentFilter(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.hasExtra(WifiP2pManager.EXTRA_WIFI_P2P_DEVICE)) {
                    WifiP2pDevice device = (WifiP2pDevice) intent.getExtras().get(WifiP2pManager.EXTRA_WIFI_P2P_DEVICE);
                    macText.setText(device.deviceAddress);
                } else {
                    macText.setText("unknown");
                }
            }
        }, filter);
    }

}

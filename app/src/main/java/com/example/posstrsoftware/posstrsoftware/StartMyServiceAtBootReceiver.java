package com.example.posstrsoftware.posstrsoftware;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.example.posstrsoftware.posstrsoftware.activity.MainActivity;


/**
 * Created by Wasabi on 1/5/2017.
 */
public class StartMyServiceAtBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}

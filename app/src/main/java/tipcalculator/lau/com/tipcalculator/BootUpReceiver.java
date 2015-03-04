package tipcalculator.lau.com.tipcalculator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by 89580 on 3/4/2015.
 */
public class BootUpReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Boot completed broadcast.", Toast.LENGTH_LONG).show();
    }
}

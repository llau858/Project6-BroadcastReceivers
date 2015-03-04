package tipcalculator.lau.com.tipcalculator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by 89580 on 3/4/2015.
 */
public class NetworkModeReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connMgr =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connMgr.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected)
        {
            Toast.makeText(context, "Connected.", Toast.LENGTH_LONG).show();
        }

        else
        {
            Toast.makeText(context, "Not connected.", Toast.LENGTH_LONG).show();
        }
    }
}

package pl.pwsztar.ppsm_lab_8.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class NetworkUtils {
  @RequiresApi(api = Build.VERSION_CODES.R)
  public static boolean isNetworkAvailable( Context context ) {
    ConnectivityManager cm =
      (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

    return activeNetwork != null &&
      activeNetwork.isConnectedOrConnecting();
  }

  @RequiresApi(api = Build.VERSION_CODES.R)
  public static void buildNetworkAlert(Context context ) {
    if( !isNetworkAvailable(context)) {
      new AlertDialog.Builder(context)
        .setTitle("Brak sieci")
        .setMessage("Aby aplikacja działała poprawnie należy przywrócić połączenie z internetem")
        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
        })
        .setNegativeButton(android.R.string.no, null)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show();

      new android.os.Handler().postDelayed(
        () -> buildNetworkAlert(context), 15000);
    }
  }
}

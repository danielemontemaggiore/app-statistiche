package eu.wegest.statistiche.listener;

import android.content.Context;
import android.content.Intent;

import com.onesignal.OneSignal;
import eu.wegest.statistiche.WebViewAppApplication;
import eu.wegest.statistiche.activity.MainActivity;
import eu.wegest.statistiche.utility.Logcat;

import org.json.JSONObject;


public class OneSignalNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler
{
	@Override
	public void notificationOpened(String message, JSONObject additionalData, boolean isActive)
	{
		try
		{
			Context context = WebViewAppApplication.getContext();
			String url = null;

			// get url
			if(additionalData != null)
			{
				Logcat.d("additionalData = " + additionalData.toString());
				Logcat.d("isActive = " + isActive);

				if(additionalData.has("url")) url = additionalData.getString("url");
				else if(additionalData.has("URL")) url = additionalData.getString("URL");
				else if(additionalData.has("launchURL")) url = additionalData.getString("launchURL");
			}

			// start activity
			Intent intent;
			if(url == null) intent = MainActivity.newIntent(context);
			else intent = MainActivity.newIntent(context, url);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
	}
}

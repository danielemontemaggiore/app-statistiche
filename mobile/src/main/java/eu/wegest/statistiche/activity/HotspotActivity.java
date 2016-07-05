package eu.wegest.statistiche.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

import eu.wegest.statistiche.R;

public class HotspotActivity extends AppCompatActivity {

    Button buttonAttiva;
    TextView info,info2, tpass,tpassword,tnome,tnomerete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspot);

        buttonAttiva = (Button) findViewById(R.id.button2);

        info = (TextView) findViewById(R.id.info);
        info2 = (TextView) findViewById(R.id.info2);
        tnome = (TextView) findViewById(R.id.TNome);
        tnomerete = (TextView) findViewById(R.id.TNomeRete);
        tpass = (TextView) findViewById(R.id.TPass);
        tpassword = (TextView) findViewById(R.id.TPassword);

        info.setText("Per attivare l'Hotspot, è necessario essere connessi ad Internet.");
        info.setText("Se è già connesso ad internet, clicchi su 'Attiva' per attivare l'Hotspot. ");

        buttonAttiva.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Context context = getApplicationContext();
                WifiManager wifiManager = (WifiManager) context .getSystemService(Context.WIFI_SERVICE);

                //setMobileDataState(true);
                WifiAPController wifiAPController  = new WifiAPController();
                wifiAPController.wifiToggle("reteProva", "12345678", wifiManager, context);
                info2.setText("Il suo Hotspot personale è stato attivato.");
                tnome.setText("Nome Rete :");
                tnomerete.setText("La Parrucchieria");
                tpass.setText("Password :");
                tpassword.setText("1234");
            }
        });



    }

    public void setMobileDataState(boolean mobileDataEnabled)
    {
        try
        {
            TelephonyManager telephonyService = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            Method setMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("setDataEnabled", boolean.class);

            if (null != setMobileDataEnabledMethod)
            {
                setMobileDataEnabledMethod.invoke(telephonyService, mobileDataEnabled);
            }
        }
        catch (Exception ex)
        {
            Log.e("ERROR", "Error setting mobile data state", ex);
        }
    }
}

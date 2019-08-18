package com.example.eicon_dod;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyService extends Service {

    WindowManager wm;
    View mView;
    String bad_word;
    Boolean is_first = true;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        bad_word = "man up";


        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                /*ViewGroup.LayoutParams.MATCH_PARENT*/300,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        |WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        |WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.LEFT | Gravity.TOP;
        mView = inflate.inflate(R.layout.view_in_service, null);

        final TextView textView = (TextView) mView.findViewById(R.id.textView);
        textView.setText(" \""+bad_word+"\" " + "is a bad word! ");
        final Button bt =  mView.findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_first){
                    textView.setText("Telling someone to “man up” means what you're actually saying is that “being a man” means being “strong”, fearless and confident. You’re saying that men should not show and feel (perfectly normal) emotions. You’re in fact discouraging a sense of positive masculinity and declaring that women are instead weak, over-emotional, scared and un-daring!");
                    is_first = false;
                    bt.setText("Okay! I'll Change");
                    bt.setTextColor(getResources().getColor(android.R.color.black));
                }else{
                    stopService(new Intent(getApplicationContext(), MyService.class));
                    bt.setTextColor(getResources().getColor(android.R.color.white));
                }

            }
        });
        wm.addView(mView, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(wm != null) {
            if(mView != null) {
                wm.removeView(mView);
                mView = null;
            }
            wm = null;
        }
    }
}
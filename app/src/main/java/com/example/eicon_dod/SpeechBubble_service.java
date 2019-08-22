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
import android.widget.TextView;

import static com.example.eicon_dod.customkeyboard.SoftKeyboard.isOkay;
import static com.example.eicon_dod.customkeyboard.SoftKeyboard.meaning_badword;
import static com.example.eicon_dod.customkeyboard.SoftKeyboard.word_bad;

public class SpeechBubble_service extends Service {

    WindowManager wm;
    View mView;
    String bad_word;
    String badWord_meaning;
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

        if (isOkay) {
            bad_word = word_bad;
            badWord_meaning = meaning_badword;
        }

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                /*ViewGroup.LayoutParams.MATCH_PARENT*/300,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                2003,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        //여기
        params.gravity = Gravity.LEFT | Gravity.CENTER;

        params.y = 20;
        mView = inflate.inflate(R.layout.view_in_service, null);

        final TextView textView = mView.findViewById(R.id.textView);
        textView.setText(" \"" + bad_word + "\" " + "is a bad word! ");
        final Button bt = mView.findViewById(R.id.bt);
        bt.setOnClickListener(view -> {
            if (is_first) {
                textView.setText(badWord_meaning);
                is_first = false;
                bt.setText("Okay! I'll Change");
                bt.setTextColor(getResources().getColor(android.R.color.black));
            } else {
                stopService(new Intent(getApplicationContext(), SpeechBubble_service.class));
                bt.setTextColor(getResources().getColor(android.R.color.white));
            }
        });
        wm.addView(mView, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (wm != null) {
            if (mView != null) {
                wm.removeView(mView);
                mView = null;
            }
            wm = null;
        }
    }
}
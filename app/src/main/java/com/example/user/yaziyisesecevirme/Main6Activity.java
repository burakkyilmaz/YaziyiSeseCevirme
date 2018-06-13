package com.example.user.yaziyisesecevirme;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

public class Main6Activity extends AppCompatActivity implements  TextToSpeech.OnInitListener, View.OnClickListener {private static final String TAG = Main6Activity.class.getSimpleName();
    private static final String DATA_PATH = Environment.getExternalStorageDirectory().toString() + "/Tess";
    private TextView textView2;

    private Uri outputFileDir;
    EditText giris;
    Button button_temizle,button_konus;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView2 = (TextView) this.findViewById(R.id.textView2);
        giris = (EditText) this.findViewById(R.id.giris);
        final Activity activity = this;



        button_temizle = (Button) findViewById(R.id.button_temizle);
        button_konus = (Button) findViewById(R.id.button_konus);
        button_konus.setOnClickListener(this);
        button_temizle.setOnClickListener(this);
        tts = new TextToSpeech(this, this);
    }



    @Override
    public void onInit(int status) {
        if(status==TextToSpeech.SUCCESS) {
            Locale turkish = tts.getLanguage();
            int sonuc = tts.setLanguage(turkish);
            if (sonuc == TextToSpeech.LANG_MISSING_DATA || sonuc == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "BU DİLDE KULLANILAMIYOR");

            } else {

            }
        }
        else
        {
            Log.e("TTS","BAŞARISIZ");

        }

    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_temizle:
                giris.setText("");
                break;
            case R.id.button_konus:
                String metin=giris.getText().toString();
                if(metin.isEmpty())
                {
                    Toast.makeText(Main6Activity.this,"metin boş",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    tts.speak(metin,TextToSpeech.QUEUE_FLUSH,null);
                    for (int i = 0; i < metin.length(); i++) {
                        if (metin.toLowerCase().equals("burak")) {
                            String url = "http://www.appkutuphanesi.com";
                            Intent ii = new Intent(Intent.ACTION_VIEW);
                            ii.setData(Uri.parse(url));
                            startActivity(ii);
                            break;
                        }
                    }
                }
        }
    }
}

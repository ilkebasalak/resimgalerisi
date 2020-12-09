package com.example.yucel.resimgalerisi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;

public class ayrintilar extends Activity {
    private TextView baslik,dosyaYolu,boyut,tarih;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayrintilar);


        baslik= (TextView) findViewById(R.id.baslik);
        dosyaYolu= (TextView) findViewById(R.id.dosyaYolu);
        boyut= (TextView) findViewById(R.id.boyut);
        tarih= (TextView) findViewById(R.id.tarih);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width/1.2),(int)(height/2));



        Intent intent=getIntent();
        int position=intent.getExtras().getInt("indis");
        ImageAdapter imageAdapter=new ImageAdapter(this);


        dosyaYolu.setText(getString(imageAdapter.images[position]));
        baslik.setText("image"+position+".jpg");



        Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(),imageAdapter.images[position]);
        Bitmap bitmap = bitmapOrg;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageInByte = stream.toByteArray();
        long length = (imageInByte.length);

        boyut.setText((length/1024)+"kb");





            File file = new File(getResources().getString(imageAdapter.images[position]));
            SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
            tarih.setText(sdf.format(file.lastModified()));





    }
}

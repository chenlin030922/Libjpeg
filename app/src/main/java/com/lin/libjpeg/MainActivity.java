package com.lin.libjpeg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI("da"));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aaa);
        String result = getSaveLocation() + "/compress1.png";
        long time = System.currentTimeMillis();
        int qu = 40;
        nativeCompressBitmap(bitmap, qu, result);
        Log.e("C_TAG", "NAtive" + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        compressByDefault(bitmap,qu);
        Log.e("C_TAG", "Java" + (System.currentTimeMillis() - time));

    }

    private void compressByDefault(Bitmap bitmap,int quality) {
        File file = new File(getSaveLocation() + "/compress2.png");
        if (file.exists()) {
            try {
                file.delete();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            OutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private String getSaveLocation() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI(String name);

    public native int nativeCompressBitmap(Bitmap bitmap, int quality, String destFile);
}

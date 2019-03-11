package com.lin.libjpeg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.youzi);
        String result = getSaveLocation();
        nativeCompressBitmap(bitmap, 0,result );
    }

    private String getSaveLocation(){
        return Environment.getExternalStorageDirectory() + "/tttes.png";
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI(String name);

    public native int nativeCompressBitmap(Bitmap bitmap, int quality, String destFile);
}

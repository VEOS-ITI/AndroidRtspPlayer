package com.ahmet.lowlatencyrtspplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.alexvas.rtsp.widget.RtspSurfaceView;

public class MainActivity extends AppCompatActivity {
    RtspSurfaceView surfaceView;
    Button btnCam1, btnCam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = findViewById(R.id.surfaceView);
        btnCam1 = findViewById(R.id.btnCam1);
        btnCam2 = findViewById(R.id.btnCam2);

        // Camera URIs
        Uri uri1 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=1&stream=0.sdp?");
        Uri uri2 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=2&stream=0.sdp?");

        // Start with Camera 1
        surfaceView.init(uri1, "", "");
        surfaceView.start(true, true);

        btnCam1.setOnClickListener(v -> switchCamera(uri1, "Camera 1"));
        btnCam2.setOnClickListener(v -> switchCamera(uri2, "Camera 2"));
    }

    private void switchCamera(Uri uri, String name) {
        if (surfaceView.isStarted()) {
            surfaceView.stop();
        }
        surfaceView.init(uri, "", "");
        surfaceView.start(true, true);
        Toast.makeText(this, name + " Started", Toast.LENGTH_SHORT).show();
    }
}

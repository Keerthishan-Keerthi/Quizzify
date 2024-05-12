package com.example.qizzify;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class endActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        findViewById(R.id.image_back).setOnClickListener(
                a-> finish()

        );
        VideoView videoView = findViewById(R.id.videoView);

        // Set the path to the video file
        String path = "android.resource://" + getPackageName() + "/" + R.raw.vid;
        videoView.setVideoURI(Uri.parse(path));

        // Start the video
        videoView.start();
    }
}
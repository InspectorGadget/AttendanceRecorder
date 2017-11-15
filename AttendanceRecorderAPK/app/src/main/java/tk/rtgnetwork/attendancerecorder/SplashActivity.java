package tk.rtgnetwork.attendancerecorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class SplashActivity extends AppCompatActivity {

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        bar = findViewById(R.id.bar);

        bar.setProgress(0);
        bar.setMax(100);

        final Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        bar.setProgress(i);
                        sleep(20);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                }
            }
        };
        thread.start();

    }

}



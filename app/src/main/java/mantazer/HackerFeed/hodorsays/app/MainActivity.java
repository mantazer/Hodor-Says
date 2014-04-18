package mantazer.HackerFeed.hodorsays.app;

import android.app.Activity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.Math;

public class MainActivity extends Activity {

    MediaPlayer myPlayer;
    Button hodorButton;

    String[] mp3Names = {"hodor1", "hodor2", "hodor3", "hodor4", "hodor5", "hodor6", "hodor7",
                            "hodor8"};

    int NUM_SOUNDS = 8;

    int[] resIDArray = new int[NUM_SOUNDS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < mp3Names.length; i++) {
            resIDArray[i] = getResources().getIdentifier(mp3Names[i], "raw", getPackageName());
        }

        hodorButton = (Button) findViewById(R.id.hodorButton);

        hodorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randomID = (int) (Math.random() * NUM_SOUNDS);
                myPlayer = MediaPlayer.create(MainActivity.this, resIDArray[randomID]);
                myPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        myPlayer.start();
                    }
                });

//                myPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mediaPlayer) {
//                        myPlayer.reset();
//                        myPlayer.release();
//                    }
//                });

            }
        });

    }
}

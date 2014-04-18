package mantazer.HackerFeed.hodorsays.app;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.Math;

public class MainActivity extends Activity {

    int[] hodorSounds = {R.raw.hodor1, R.raw.hodor2, R.raw.hodor3, R.raw.hodor4, R.raw.hodor5,
                         R.raw.hodor6, R.raw.hodor7, R.raw.hodor8};

    int NUM_SOUNDS = hodorSounds.length;

    int[] hodorSoundIDs = new int[NUM_SOUNDS];

    AudioManager hodorManager;
    SoundPool hodorPool;
    Button hodorButton;
    ImageView hodorImage;

    float currentVolume;
    float maxVolume;
    float volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        hodorPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        for (int i = 0; i < hodorSounds.length; i++) {
            hodorSoundIDs[i] = hodorPool.load(this, hodorSounds[i], 1);
        }

        hodorButton = (Button) findViewById(R.id.hodorButton);
        hodorImage = (ImageView) findViewById(R.id.hodorImage);

        hodorButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    hodorImage.setImageResource(R.drawable.hodorface);
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    hodorImage.setImageResource(R.drawable.hodorfaceopen);
                    sayHodor();
                    return true;
                }

                return false;
            }
        });

    }

    public void sayHodor() {
        hodorManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        currentVolume = hodorManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = hodorManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = currentVolume / maxVolume;

        int randomIndex = (int) (Math.random() * NUM_SOUNDS);
        hodorPool.play(hodorSoundIDs[randomIndex], volume, volume, 1, 0, 1f);
    }

}

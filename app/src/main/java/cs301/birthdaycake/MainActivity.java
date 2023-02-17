package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        CakeView view = findViewById(R.id.cakeview);
        CakeController control = new CakeController(view);

        Button blowOut = findViewById(R.id.blowOut);
        blowOut.setOnClickListener(control);

        CompoundButton candle = findViewById(R.id.candleSwitch);
        candle.setOnCheckedChangeListener(control);

        SeekBar candlesNum = findViewById(R.id.seekBar);
        candlesNum.setOnSeekBarChangeListener(control);

        view.setOnTouchListener(control);
    }
    public void goodbye(View button) {
        Log.i("button", "Goodbye");
        finishAffinity();


    }
}

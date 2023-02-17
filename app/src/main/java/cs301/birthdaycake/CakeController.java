package cs301.birthdaycake;

import android.view.MotionEvent;
import android.view.View;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, View.OnTouchListener  {

    private CakeView view;

    private CakeModel model;

    public CakeController(CakeView v){
        view = v;

        model = view.dataGetter();
    }

    public void onClick(View v){
        Log.d("worked","wow");
        model.candlesLit = false;
        view.invalidate();

    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        model.hasCandles = b;
        view.invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        model.numCandles = i;
        view.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        model.balloonX = motionEvent.getX();
        model.balloonY = motionEvent.getY();
        model.balloonExists = true;
        this.view.invalidate();
        Log.d("click","worked");
        return false;
    }
}

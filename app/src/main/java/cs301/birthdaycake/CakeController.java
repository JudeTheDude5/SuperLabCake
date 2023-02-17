package cs301.birthdaycake;

import android.view.View;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener  {

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
    public boolean onTouch(View v, MotionEvent event) {
        model.x = event.getX();
        model.y = event.getY();
        model.touched = true;
        Log.d("worked","wow");
        view.invalidate();
        return false;
    }
}

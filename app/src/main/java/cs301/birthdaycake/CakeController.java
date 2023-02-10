package cs301.birthdaycake;

public class CakeController {

    private CakeView view;

    private CakeModel model;

    public CakeController(CakeView v){
        view = v;

        model = view.dataGetter();
    }

}

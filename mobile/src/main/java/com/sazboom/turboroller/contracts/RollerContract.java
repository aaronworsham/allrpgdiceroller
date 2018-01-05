package com.sazboom.turboroller.contracts;

import com.sazboom.turboroller.presenters.RollPresenter;

/**
 * Created by aaronworsham on 1/3/18.
 */

public interface RollerContract {
    interface View extends BaseView<RollPresenter>{

        void setPresenter(RollPresenter presenter);

    }

    interface Presenter extends BasePresenter{

    }

    interface DicePoolView extends View{
        void setPoolDescription(String str);
        void setPoolRoll(String str);
    }
}

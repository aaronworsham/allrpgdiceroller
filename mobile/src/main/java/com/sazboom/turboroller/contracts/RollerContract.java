package com.sazboom.turboroller.contracts;

import com.sazboom.turboroller.presenters.DndRollPresenter;

/**
 * Created by aaronworsham on 1/3/18.
 */

public interface RollerContract {
    interface View extends BaseView<Presenter>{
        void setPoolDescription(String str);

        void setPoolRoll(String str);

        void setPoolResults(String str);

        void setToast(String str);

    }

    interface Presenter extends BasePresenter{

        void addDiceToPool(int dieFace);


        void setResults();

        void clearPool();
    }

    interface SystemFragment{

    }

    interface DndPresenter extends Presenter{
        void incrementBonus();

        void decrementBonus();
    }


}

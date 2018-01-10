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


    }

    interface Presenter extends BasePresenter{
        void addDiceToPool(int dieFace);

        void addPosDicePoolBonus(int bonus);

        void addNegDicePoolBonus(int bonus);

        void rollPool();

        void clearDiceAndPool();
    }

    interface SystemFragment{

    }


}

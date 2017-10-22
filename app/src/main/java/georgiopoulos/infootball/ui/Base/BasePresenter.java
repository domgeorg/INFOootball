/**
 * Presenter’s lifecycle:
 *
 * void onCreate(Bundle savedState) - is called on every presenter’s creation.
 *
 * void onDestroy() - is called when a View becomes destroyed not because of configuration change.
 *
 * void onSave(Bundle state) - is called during View’s onSaveInstanceState to persist Presenter’s state as well.
 *
 * void onTakeView(ViewType view) - is called during Activity’s or Fragment’s onResume(),
 * or during android.view.View#onAttachedToWindow().
 *
 * void onDropView() - is called during Activity’s onDestroy() or Fragment’s onDestroyView(),
 * or during android.view.View#onDetachedFromWindow().
 */
package georgiopoulos.infootball.ui.Base;

import android.os.Bundle;

import icepick.Icepick;
import nucleus.presenter.RxPresenter;

public class BasePresenter<ViewType> extends RxPresenter<ViewType> {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        Icepick.restoreInstanceState(this, savedState);
    }

    @Override
    protected void onSave(Bundle state) {
        super.onSave(state);
        Icepick.saveInstanceState(this, state);
    }
}
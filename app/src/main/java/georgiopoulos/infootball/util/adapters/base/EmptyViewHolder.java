package georgiopoulos.infootball.util.adapters.base;

import android.view.View;

import georgiopoulos.infootball.util.adapters.base.BaseViewHolder;

public class EmptyViewHolder extends BaseViewHolder<Object>{

    public EmptyViewHolder(View view) {
        super(view);
    }

    @Override
    public void bind(Object item) {

    }
}

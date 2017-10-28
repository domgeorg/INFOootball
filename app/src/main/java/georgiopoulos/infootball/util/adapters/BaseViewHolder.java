package georgiopoulos.infootball.util.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import georgiopoulos.infootball.util.Injector;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View view) {
        super(view);
        ((Injector)view.getContext().getApplicationContext()).inject(this);
    }

    public abstract void bind(T item);
}

package georgiopoulos.infootball.util.adapters.base;

import android.view.ViewGroup;

import georgiopoulos.infootball.util.adapters.base.BaseViewHolder;

public interface ViewHolderType<T>{
    boolean isOfItem(Object item);
    BaseViewHolder<T> create(ViewGroup parent);
}

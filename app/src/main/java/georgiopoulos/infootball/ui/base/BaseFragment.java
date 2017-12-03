/*
  Copyright 2017 georgiopoulos kyriakos

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package georgiopoulos.infootball.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.util.adapters.base.SimpleListAdapter;
import georgiopoulos.infootball.util.injection.Injector;
import nucleus.factory.PresenterFactory;
import nucleus.presenter.Presenter;
import nucleus.view.NucleusSupportFragment;

public abstract class BaseFragment<T,P extends Presenter>
        extends NucleusSupportFragment<P>
        implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.loading_content_error_fragment_card_error)
    CardView cardViewError;
    @BindView(R.id.loading_content_error_fragment_card_error_text_view)
    TextView textViewError;
    @BindView(R.id.loading_content_error_fragment_card_error_image)
    ImageView imageViewError;
    @BindView(R.id.loading_content_error_fragment_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayoutLoading;
    @BindView(R.id.loading_content_error_fragment_recycler_view)
    RecyclerView recyclerViewContent;

    private Unbinder unbinder;
    private SimpleListAdapter<T> adapter;

    /**
     * Base-Fragment Lifecycle
     */

    @Override
    public void onCreate(Bundle bundle){
        //Inject presenter
        final PresenterFactory<P> superFactory = super.getPresenterFactory();
        setPresenterFactory(superFactory == null? null :(PresenterFactory<P>)() -> {
            P presenter = superFactory.createPresenter();
            ((Injector)getActivity().getApplication()).inject(presenter);
            return presenter;
        });
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle bundle){
        return inflater.inflate(R.layout.fragment_base,container,false);
    }

    @Override
    public void onViewCreated(View view,@Nullable Bundle bundle){
        super.onViewCreated(view,bundle);
        unbinder = ButterKnife.bind(this,view);
        //Prepare view for general purpose content
        cardViewError.setVisibility(View.GONE);
        enableSwipeRefreshLayout(false);
        setRecyclerViewContent(new LinearLayoutManager(getActivity()),false);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * <p>
     * <b>Loading - Content - Error</b>
     *
     */

    /**
     * <p>
     * Loading
     */

    @Override
    public void onRefresh(){
        cardViewError.setVisibility(View.GONE);
        swipeRefreshLayoutLoading.setRefreshing(false);
        adapter.clear();
        adapter.showProgress();
    }

    /**
     * <p>
     * Content
     */
    public void displayList(@Nullable List<T> list,int animation){
        adapter.hideProgress();
        adapter.set(list);
        runLayoutAnimation(animation);
    }

    /**
     * <p>
     * Error
     */
    @SuppressWarnings("deprecation")
    public void displayError(int errorMessage,int drawable){
        adapter.hideProgress();
        cardViewError.setVisibility(View.VISIBLE);
        imageViewError.setImageDrawable(getResources().getDrawable(drawable));
        textViewError.setText(errorMessage);
    }

    public void onNetworkError(Throwable throwable){
        throwable.printStackTrace();
        displayError(R.string.network_error_message,R.drawable.ic_error_network);
    }

    public abstract void onItemClick(T t);

    /**
     * <p>
     * View
     */
    public final void enableSwipeRefreshLayout(boolean enabled){
        if(enabled){
            swipeRefreshLayoutLoading.setOnRefreshListener(this);
            swipeRefreshLayoutLoading.setEnabled(true);
        }else swipeRefreshLayoutLoading.setEnabled(false);
    }

    public final void setRecyclerViewContent(RecyclerView.LayoutManager layoutManager,boolean fixedSize){
        recyclerViewContent.setLayoutManager(layoutManager);
        recyclerViewContent.setHasFixedSize(fixedSize);
        recyclerViewContent.setAdapter(adapter);
    }

    @NonNull
    public SimpleListAdapter<T> getAdapter(){
        return adapter;
    }

    public void setAdapter(@NonNull SimpleListAdapter<T> adapter){
        this.adapter = adapter;
    }

    private final void runLayoutAnimation(int animation){
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getActivity(),animation);
        recyclerViewContent.setLayoutAnimation(controller);
        recyclerViewContent.scheduleLayoutAnimation();
    }

    public boolean isNullOrEmpty(List<T> list){
        return (list == null || list.isEmpty());
    }
}

/**
 * Copyright 2017 georgiopoulos kyriakos
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package georgiopoulos.infootball.ui.Base;

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

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.util.RecyclerViewHeadline;
import georgiopoulos.infootball.util.adapters.SimpleListAdapter;
import georgiopoulos.infootball.util.injection.Injector;
import icepick.Icepick;
import nucleus.factory.PresenterFactory;
import nucleus.presenter.Presenter;
import nucleus.view.NucleusSupportFragment;

public abstract class LoadingContentErrorFragment<T,P extends Presenter> extends
                                                                         NucleusSupportFragment<P> implements SwipeRefreshLayout.OnRefreshListener{

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
    @BindView(R.id.loading_content_error_fragment_recycler_view_headline)
    RecyclerViewHeadline recyclerViewHeadlineContent;

    private Unbinder unbinder;
    private SimpleListAdapter<T> adapter;

    //LoadingContentErrorFragment Lifecycle --------------------------------------------------------
    @Override
    public void onCreate(Bundle bundle){

        //Inject presenter
        final PresenterFactory<P> superFactory = super.getPresenterFactory();
        setPresenterFactory(superFactory == null? null : (PresenterFactory<P>)() -> {
            P presenter = superFactory.createPresenter();
            ((Injector)getActivity().getApplication()).inject(presenter); return presenter;
        });

        super.onCreate(bundle); Icepick.restoreInstanceState(this,bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle bundle){
        return inflater.inflate(R.layout.fragment_loading_content_error,container,false);
    }

    @Override
    public void onViewCreated(View view,@Nullable Bundle bundle){
        super.onViewCreated(view,bundle); unbinder = ButterKnife.bind(this,view);

        //Prepare view for general purpose content
        cardViewError.setVisibility(View.GONE); enableRecyclerViewHeadline(false);
        enableSwipeRefreshLayout(false);
        setRecyclerViewContent(new LinearLayoutManager(getActivity()),false);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle); Icepick.saveInstanceState(this,bundle);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView(); unbinder.unbind();
    }


    // Loading - Content - Error -------------------------------------------------------------------

    //Loading
    @Override
    public void onRefresh(){
        swipeRefreshLayoutLoading.setRefreshing(false); adapter.showProgress();
    }

    //Content
    public void onResults(@Nullable List<T> items,String errorMessage,int errorDrawable,int animation){
        adapter.hideProgress(); if(items != null){
            if(items.isEmpty()) onError(errorMessage,errorDrawable);
            else{
                runLayoutAnimation(animation); adapter.set(items);
            }
        }
    }

    //Error
    private void onError(String message,int drawable){
        adapter.hideProgress(); cardViewError.setVisibility(View.VISIBLE);
        Picasso.with(getActivity()).load(drawable).into(imageViewError);
        textViewError.setText(message);
    }

    public void onNetworkError(Throwable throwable){
        adapter.hideProgress(); onError(throwable.getMessage(),R.drawable.ic_network_error);
    }

    public abstract void onItemClick(T t);


    //View -----------------------------------------------------------------------------------------

    @NonNull
    public SimpleListAdapter<T> getAdapter(){
        return adapter;
    }

    public void setAdapter(@NonNull SimpleListAdapter<T> adapter){
        this.adapter = adapter;
    }

    private void runLayoutAnimation(int animation){
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation
                                                                            (getActivity(),
                                                                             animation);
        recyclerViewContent.setLayoutAnimation(controller);
        recyclerViewContent.scheduleLayoutAnimation();
    }

    public void enableRecyclerViewHeadline(boolean enabled){
        if(enabled) recyclerViewHeadlineContent.setVisibility(View.VISIBLE);
        else recyclerViewHeadlineContent.setVisibility(View.GONE);
    }

    public void enableSwipeRefreshLayout(boolean enabled){
        if(enabled){
            swipeRefreshLayoutLoading.setOnRefreshListener(this);
            swipeRefreshLayoutLoading.setEnabled(true);
        }else swipeRefreshLayoutLoading.setEnabled(false);
    }

    public void setRecyclerViewContent(RecyclerView.LayoutManager layoutManager,boolean fixedSize){
        recyclerViewContent.setLayoutManager(layoutManager);
        recyclerViewContent.setHasFixedSize(fixedSize); recyclerViewContent.setAdapter(adapter);
    }
}

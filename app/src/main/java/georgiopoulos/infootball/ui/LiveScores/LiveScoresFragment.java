/**
 *  Copyright 2017 georgiopoulos kyriakos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package georgiopoulos.infootball.ui.LiveScores;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.BindView;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.livescores.LiveScores;
import georgiopoulos.infootball.data.remote.dto.livescores.Match;
import georgiopoulos.infootball.ui.Base.BaseFragment;
import georgiopoulos.infootball.util.adapters.LiveScoresViewHolder;
import georgiopoulos.infootball.util.adapters.SimpleListAdapter;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LiveScoresPresenter.class)
public class LiveScoresFragment extends BaseFragment<LiveScoresPresenter> implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.swipe_refresh_layout_activity_live_scores) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.swipe_refresh_layout_recycler_view_activity_live_scores) RecyclerView recyclerView;
    private SimpleListAdapter<Match> adapter;

    @Override public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle bundle){
        return inflater.inflate(R.layout.layout_swipe_refresh_activity_live_scores,container,false);
    }

    @Override public void onViewCreated(View view, Bundle bundle){
        super.onViewCreated(view,bundle);
        adapter = new SimpleListAdapter<>(R.layout.view_loading,new ClassViewHolderType<>(Match.class,R.layout.card_live_scores,v -> new LiveScoresViewHolder<>(v,this::onItemClick)));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();
    }

    @Override public void onRefresh(){
        swipeRefreshLayout.setRefreshing(false);
        adapter.showProgress();
        getPresenter().request();
    }

    protected void onLiveScores(LiveScores liveScores){
        adapter.hideProgress();
        List<Match> matches=liveScores.getTeams().getMatch();
        if (matches.isEmpty())
            newsFlash("Server does not provide info about live matches",recyclerView);
        else {
            getPresenter().requestLiveMatchesPersistence(matches);
            runLayoutAnimation(recyclerView);
            adapter.set(matches);
        }
    }

    protected void onNetworkError(Throwable throwable){
        adapter.hideProgress();
        newsFlash(throwable.getMessage(),recyclerView);
    }

    private void onItemClick(Match match){}

}

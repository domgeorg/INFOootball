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
package georgiopoulos.infootball.ui.Team;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.Player;
import georgiopoulos.infootball.data.remote.dto.TeamPlayers;
import georgiopoulos.infootball.ui.Base.BaseFragment;
import georgiopoulos.infootball.util.adapters.PlayerViewHolder;
import georgiopoulos.infootball.util.adapters.SimpleListAdapter;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(TeamRosterPresenter.class)
public class TeamRosterFragment extends BaseFragment<TeamRosterPresenter>{

    @BindView(R.id.league_recycler_view) RecyclerView recyclerView;
    private SimpleListAdapter<Player> adapter;

    public static TeamRosterFragment create(String teamId){
        Bundle bundle = new Bundle();
        bundle.putString("teamId", teamId);
        TeamRosterFragment fragment = new TeamRosterFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(R.layout.view_recycler,container,false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        getPresenter().request(getArguments().getString("teamId"));
        adapter = new SimpleListAdapter<>(R.layout.view_loading,new ClassViewHolderType<>(Player.class,R.layout.card_player,v -> new PlayerViewHolder<>(v,this::onItemClick)));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.showProgress();
    }

    void onPlayers(TeamPlayers teamPlayers){
        adapter.hideProgress();
        if (teamPlayers.getPlayer()==null) newsFlash("Server does not provide info about players",recyclerView);
        else {
            runLayoutAnimation(recyclerView,R.anim.layout_animation_from_bottom);
            adapter.set(teamPlayers.getPlayer());}
    }

    void onNetworkError(Throwable throwable){
        newsFlash(throwable.getMessage(),recyclerView);
    }

    private void onItemClick(Player player){
        newsFlash(player.getStrPlayer(),recyclerView);}
}

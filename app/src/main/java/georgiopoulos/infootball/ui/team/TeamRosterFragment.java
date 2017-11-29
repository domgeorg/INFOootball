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
package georgiopoulos.infootball.ui.team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.team.Player;
import georgiopoulos.infootball.ui.base.BaseFragment;
import georgiopoulos.infootball.util.adapters.PlayerViewHolder;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import georgiopoulos.infootball.util.adapters.base.SimpleListAdapter;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(TeamRosterPresenter.class)
public class TeamRosterFragment
        extends BaseFragment<Player,TeamRosterPresenter>{

    public static TeamRosterFragment create(String teamId){
        Bundle bundle = new Bundle();
        bundle.putString("teamId", teamId);
        TeamRosterFragment fragment = new TeamRosterFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view,Bundle bundle){
        setAdapter(new SimpleListAdapter<>(R.layout.progress_bar,new ClassViewHolderType<>(Player.class,R.layout.card_player,v -> new PlayerViewHolder<>(v,this::onItemClick))));
        super.onViewCreated(view,bundle);
        if(bundle==null){
            onRefresh();
            getPresenter().request(getArguments().getString("teamId"));
        }
    }

    protected void onPlayers(@Nullable List<Player> list){
        if(isNullOrEmpty(list)) displayError( R.string.team_roster_fragment_error_message,R.drawable.ic_error_fragment_team);
        else displayList(list,R.anim.layout_animation_from_right);
    }

    @Override
    public void onItemClick(Player player){}

}

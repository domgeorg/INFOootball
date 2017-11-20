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
package georgiopoulos.infootball.ui.Team;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindDrawable;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.local.LocalData;
import georgiopoulos.infootball.data.remote.dto.team.Player;
import georgiopoulos.infootball.data.remote.dto.team.TeamPlayers;
import georgiopoulos.infootball.ui.Base.CoreActivityFacet;
import georgiopoulos.infootball.ui.Base.LoadingContentErrorFragment;
import georgiopoulos.infootball.util.adapters.PlayerViewHolder;
import georgiopoulos.infootball.util.adapters.SimpleListAdapter;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import georgiopoulos.infootball.util.injection.Injector;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(TeamRosterPresenter.class)
public class TeamRosterFragment
        extends LoadingContentErrorFragment<Player,TeamRosterPresenter>{

    @Inject
    CoreActivityFacet activityFacet;
    @Inject
    LocalData localData;

    @BindDrawable(R.drawable.ic_arrow_primary_color_24dp) Drawable primaryColorArrow;
    @BindDrawable(R.drawable.ic_arrow_white_24dp) Drawable whiteColorArrow;

    public static TeamRosterFragment create(String teamId){
        Bundle bundle = new Bundle();
        bundle.putString("teamId", teamId);
        TeamRosterFragment fragment = new TeamRosterFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle){
        ((Injector)getActivity().getApplicationContext()).inject(this);
        super.onCreate(bundle);


    }

    @Override
    public void onViewCreated(View view,Bundle bundle){
        setAdapter(new SimpleListAdapter<>(R.layout.view_loading,new ClassViewHolderType<>(Player.class,R.layout.card_player,v -> new PlayerViewHolder<>(v,this::onItemClick))));
        super.onViewCreated(view,bundle); this.onRefresh();
    }

    @Override
    public void onRefresh(){
        super.onRefresh();
        getPresenter().request(getArguments().getString("teamId"));
    }

    void onPlayers(TeamPlayers teamPlayers){
        onResults(teamPlayers.getPlayer(),getString(R.string.team_roster_fragment_error_message),
                  R.drawable.ic_error_team_roster_fragment,R.anim.layout_animation_from_bottom);
    }

    @Override
    public void onItemClick(Player player){}
}

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
package georgiopoulos.infootball.ui.league.leagueTable;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.leagueTable.Table;
import georgiopoulos.infootball.data.remote.dto.teamsDetails.TeamsDetails;
import georgiopoulos.infootball.ui.base.BaseFragment;
import georgiopoulos.infootball.ui.team.TeamActivity;
import georgiopoulos.infootball.util.adapters.LeagueTableTeamViewHolder;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import georgiopoulos.infootball.util.adapters.base.SimpleListAdapter;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LeagueTablePresenter.class)
public class LeagueTableFragment extends BaseFragment<Table,LeagueTablePresenter>{

    public static LeagueTableFragment create(String leagueId){
        Bundle bundle = new Bundle();
        bundle.putString("leagueId", leagueId);
        LeagueTableFragment fragment = new LeagueTableFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view,Bundle bundle){
        setAdapter(new SimpleListAdapter<>(R.layout.progress_bar,new ClassViewHolderType<>(Table.class,R.layout.card_league_table_team,v -> new LeagueTableTeamViewHolder<>(v,this::onItemClick))));
        super.onViewCreated(view,bundle);
        if(bundle==null){
            onRefresh();
            getPresenter().requestTeams(getArguments().getString("leagueId"));
        }
    }

    protected void onTeams(@Nullable TeamsDetails details){getPresenter().requestLeagueTable();}

    protected void onTable(@Nullable List<Table> list){
        if(isNullOrEmpty(list)) displayError(R.string.league_table_fragment_error_message,R.drawable.ic_error_fragment_league_table);
        else displayList(list,R.anim.layout_animation_fall_down);
    }

    @Override
    public void onItemClick(Table team){
        startActivity(new Intent(getActivity(),TeamActivity.class)
                              .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                              .putExtra("teamId",team.getTeamid())
                              .putExtra("teamName",team.getName()));
    }

}

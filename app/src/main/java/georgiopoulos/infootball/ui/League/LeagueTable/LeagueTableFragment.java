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
package georgiopoulos.infootball.ui.League.LeagueTable;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.league.LeagueTable;
import georgiopoulos.infootball.data.remote.dto.league.Table;
import georgiopoulos.infootball.ui.Base.LoadingContentErrorFragment;
import georgiopoulos.infootball.ui.Team.TeamRosterActivity;
import georgiopoulos.infootball.util.adapters.LeagueTableTeamViewHolder;
import georgiopoulos.infootball.util.adapters.SimpleListAdapter;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LeagueTablePresenter.class)
public class LeagueTableFragment extends LoadingContentErrorFragment<Table,LeagueTablePresenter>{

    public static LeagueTableFragment create(String leagueId){
        Bundle bundle = new Bundle();
        bundle.putString("leagueId", leagueId);
        LeagueTableFragment fragment = new LeagueTableFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        setAdapter(new SimpleListAdapter<>(R.layout.view_loading,new ClassViewHolderType<>(Table.class,R.layout.card_league_table,v -> new LeagueTableTeamViewHolder<>(v,this::onItemClick))));
        super.onViewCreated(view, savedInstanceState); this.onRefresh();
    }

    @Override
    public void onRefresh(){
        super.onRefresh(); getPresenter().request(getArguments().getString("leagueId"));
    }

    protected void onTable(@Nullable LeagueTable leagueTable){
        onResults(leagueTable.getTable(),getString(R.string.league_table_fragment_error_message),
                  R.drawable.ic_error_league_table_fragment,R.anim.layout_animation_fall_down);
    }

    @Override
    public void onItemClick(Table team){
       startActivity(new Intent(getActivity(),TeamRosterActivity.class).putExtra("teamId",team.getTeamid()).putExtra("team", team.getName()));
    }

}

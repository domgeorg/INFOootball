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
package georgiopoulos.infootball.ui.main.leagues;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.leagues.Country;
import georgiopoulos.infootball.ui.base.BaseFragment;
import georgiopoulos.infootball.ui.league.LeagueActivity;
import georgiopoulos.infootball.util.adapters.LeagueViewHolder;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import georgiopoulos.infootball.util.adapters.base.SimpleListAdapter;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LeaguesPresenter.class)
public class LeaguesFragment extends BaseFragment<Country,LeaguesPresenter>{

    @Override
    public void onViewCreated(View view,Bundle bundle){
        setAdapter(new SimpleListAdapter<>(R.layout.progress_bar,new ClassViewHolderType<>(Country.class,R.layout.card_league,v -> new LeagueViewHolder<>(v,this::onItemClick))));
        super.onViewCreated(view,bundle);
        if(bundle==null)this.onRefresh();
    }

    @Override
    public void onRefresh(){
        super.onRefresh();
        getPresenter().requestLeagues();
    }

    protected void onLeagues(@Nullable List<Country> list){
        if(isNullOrEmpty(list)) displayError(R.string.leagues_fragment_error_message,R.drawable.ic_error_fragment_leagues);
        else displayList(list,R.anim.layout_animation_from_bottom);
    }

    @Override
    public void onItemClick(Country country){
        startActivity(new Intent(getActivity(),LeagueActivity.class)
                              .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                              .putExtra("leagueId",country.getIdLeague())
                              .putExtra("leagueName",country.getStrLeague()));
    }
}

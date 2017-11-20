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
package georgiopoulos.infootball.ui.SoccerLeagues;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.soccerLeagues.Country;
import georgiopoulos.infootball.data.remote.dto.soccerLeagues.Leagues;
import georgiopoulos.infootball.ui.Base.LoadingContentErrorFragment;
import georgiopoulos.infootball.ui.League.LeagueActivity;
import georgiopoulos.infootball.util.adapters.SimpleListAdapter;
import georgiopoulos.infootball.util.adapters.SoccerLeagueViewHolder;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LeaguesPresenter.class)
public class LeaguesFragment extends LoadingContentErrorFragment<Country,LeaguesPresenter>{

    @Override
    public void onViewCreated(View view,Bundle bundle){
        setAdapter(new SimpleListAdapter<>(R.layout.view_loading,new ClassViewHolderType<>(Country.class,R.layout.card_soccer_league,v -> new SoccerLeagueViewHolder<>(v,this::onItemClick))));
        super.onViewCreated(view,bundle); onRefresh();
    }

    protected void onLeagues(Leagues leagues){
        onResults(leagues.getCountrys(),getString(R.string.leagues_fragment_error_message),R.drawable.ic_error_leagues_fragment,R.anim.layout_animation_from_right);
    }

    @Override
    public void onItemClick(Country country){
        startActivity(new Intent(getActivity(),LeagueActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).putExtra("leagueId",country.getIdLeague()));
    }
}

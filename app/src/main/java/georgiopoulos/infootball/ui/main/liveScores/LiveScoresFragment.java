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
package georgiopoulos.infootball.ui.main.liveScores;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.livescores.Match;
import georgiopoulos.infootball.data.remote.dto.team.TeamsDetails;
import georgiopoulos.infootball.ui.base.BaseFragment;
import georgiopoulos.infootball.util.adapters.LiveScoresViewHolder;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import georgiopoulos.infootball.util.adapters.base.SimpleListAdapter;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LiveScoresPresenter.class)
public class LiveScoresFragment extends BaseFragment<Match,LiveScoresPresenter>{

    @Override
    public void onViewCreated(View view,Bundle bundle){
        setAdapter(new SimpleListAdapter<>(R.layout.progress_bar,new ClassViewHolderType<>(Match.class,R.layout.card_live_scores,v -> new LiveScoresViewHolder<>(v,this::onItemClick))));
        super.onViewCreated(view,bundle);
        enableSwipeRefreshLayout(true);
        if(bundle==null) onRefresh();
    }

    @Override
    public void onRefresh(){
        super.onRefresh();
        getPresenter().request();
    }

    protected void onLiveScores(@Nullable List<Match> list){
        if(isNullOrEmpty(list)) displayError(R.string.live_scores_fragment_error_message,R.drawable.ic_error_fragment_live_scores);
        else displayList(list,R.anim.layout_animation_from_bottom);
    }

    protected void onTeamDetails(@Nullable TeamsDetails teamsDetails){}

    @Override
    public void onItemClick(Match match){}

}

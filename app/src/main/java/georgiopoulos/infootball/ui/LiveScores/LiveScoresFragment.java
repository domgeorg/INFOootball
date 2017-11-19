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
package georgiopoulos.infootball.ui.LiveScores;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.livescores.LiveScores;
import georgiopoulos.infootball.data.remote.dto.livescores.Match;
import georgiopoulos.infootball.ui.Base.LoadingContentErrorFragment;
import georgiopoulos.infootball.util.adapters.LiveScoresViewHolder;
import georgiopoulos.infootball.util.adapters.SimpleListAdapter;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LiveScoresPresenter.class)
public class LiveScoresFragment extends LoadingContentErrorFragment<Match,LiveScoresPresenter>{

    @Override
    public void onViewCreated(View view,Bundle bundle){
        setAdapter(new SimpleListAdapter<>(R.layout.view_loading,new ClassViewHolderType<>(Match.class,R.layout.card_live_scores,v -> new LiveScoresViewHolder<>(v,this::onItemClick))));
        super.onViewCreated(view,bundle); enableSwipeRefreshLayout(true); this.onRefresh();
    }

    @Override
    public void onRefresh(){
        super.onRefresh();
        getPresenter().request();
    }

    protected void onLiveScores(@Nullable LiveScores liveScores){
        List<Match> matches=liveScores.getTeams().getMatch();
        if(! matches.isEmpty()) getPresenter().requestLiveMatchesPersistence(matches);
        onResults(matches,getString(R.string.live_Scores_fragment_error_message),R.drawable
                                                                                         .ic_error_live_scores_fragment,R.anim.layout_animation_from_bottom);

    }

    @Override
    public void onItemClick(Match match){}

}

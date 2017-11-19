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
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import georgiopoulos.infootball.data.local.LocalData;
import georgiopoulos.infootball.data.remote.api.ServerAPI;
import georgiopoulos.infootball.data.remote.dto.livescores.Match;
import georgiopoulos.infootball.ui.Base.BasePresenter;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class LiveScoresPresenter extends BasePresenter<LiveScoresFragment>{

    private static final int REQUEST_LIVE_SCORES = 1;
    @Inject ServerAPI api;
    @Inject LocalData localData;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        restartableLatestCache(REQUEST_LIVE_SCORES,() -> api.getLiveScores().subscribeOn
                                                                                     (Schedulers
                                                                                              .io()).observeOn(mainThread()),LiveScoresFragment::onLiveScores,LiveScoresFragment::onNetworkError);
    }

    public void request(){
        start(REQUEST_LIVE_SCORES);
    }

    public void requestLiveMatchesPersistence(@NonNull List<Match> liveMatches){
        for(Match match:liveMatches){
            teamDetailsPersistence(match.getAwayTeamId());
            teamDetailsPersistence(match.getHomeTeamId());
        }
    }

    private void teamDetailsPersistence(String teamId){
        if(! (teamId.isEmpty() || teamId == null))
            api.getTeamDetails(teamId).subscribeOn(Schedulers.immediate()).map(teamsDetails -> localData.writeTeamDetailsToRealm(teamsDetails));

    }
}

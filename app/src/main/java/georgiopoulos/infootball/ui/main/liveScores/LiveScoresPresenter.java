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

import java.util.List;

import javax.inject.Inject;

import georgiopoulos.infootball.data.local.LocalData;
import georgiopoulos.infootball.data.remote.api.ServerAPI;
import georgiopoulos.infootball.data.remote.dto.livescores.LiveScores;
import georgiopoulos.infootball.data.remote.dto.livescores.Match;
import georgiopoulos.infootball.data.remote.dto.livescores.Teams;
import georgiopoulos.infootball.ui.base.BasePresenter;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class LiveScoresPresenter extends BasePresenter<LiveScoresFragment>{

    private static final int REQUEST_LIVE_SCORES = 1;
    private static final int REQUEST_PERSISTENCE = 2;
    @Inject ServerAPI api;
    @Inject LocalData localData;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);

        restartableLatestCache(
                REQUEST_LIVE_SCORES,
                () -> api.getLiveScores()
                              .subscribeOn(Schedulers.io())
                              .map(LiveScores::getTeams)
                              .map(Teams::getMatch)
                              .doOnNext(this::requestPersistence)
                              .subscribeOn(Schedulers.computation())
                              .observeOn(mainThread()),
                LiveScoresFragment::onLiveScores,
                LiveScoresFragment::onNetworkError);
        start(REQUEST_LIVE_SCORES);
    }

    protected void request(){start(REQUEST_LIVE_SCORES);}

    protected void requestPersistence(List<Match> matches){
        if(! (matches.isEmpty() || matches == null))
        for(Match match:matches){
            teamDetailsPersistence(match.getHomeTeamId());
            teamDetailsPersistence(match.getAwayTeamId());
        }
    }

    private void teamDetailsPersistence(String teamId){
        if(! (teamId.isEmpty() || teamId == null))
            restartableLatestCache(REQUEST_PERSISTENCE,
                                   () -> api.getTeamDetails(teamId)
                                                  .subscribeOn(Schedulers.immediate())
                                                  .map(teamsDetails -> localData.writeTeamDetailsToRealm(teamsDetails))
                                                  .observeOn(mainThread())
                     ,LiveScoresFragment::onTeamDetails);
        start(REQUEST_PERSISTENCE);
    }

}

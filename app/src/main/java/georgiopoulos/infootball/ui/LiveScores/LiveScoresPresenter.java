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
package georgiopoulos.infootball.ui.LiveScores;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import georgiopoulos.infootball.data.local.LocalData;
import georgiopoulos.infootball.data.remote.api.ServerAPI;
import georgiopoulos.infootball.data.remote.dto.livescores.LiveScores;
import georgiopoulos.infootball.data.remote.dto.livescores.Match;
import georgiopoulos.infootball.ui.Base.BasePresenter;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class LiveScoresPresenter extends BasePresenter<LiveScoresFragment>{

    private static final int REQUEST_LIVE_SCORES = 1;
    @Inject ServerAPI api;
    @Inject LocalData localData;

    public void request(){
        restartableLatestCache(REQUEST_LIVE_SCORES,
                               ()-> api.getLiveScores()
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(mainThread()),
                               LiveScoresFragment::onLiveScores,
                               LiveScoresFragment::onNetworkError
                              );
        start(REQUEST_LIVE_SCORES);
    }

    public void requestLiveMatchesPersistence(@NonNull List<Match> liveMatches){
        for(Match match:liveMatches){
            teamDetailsPersistence(match.getAwayTeamId());
            teamDetailsPersistence(match.getHomeTeamId());
        }
    }

    private void teamDetailsPersistence(String teamId){
        if(!(teamId.isEmpty() || teamId==null)){
            api.getTeamDetails(teamId)
                    .subscribeOn(Schedulers.immediate())
                    .map(teamsDetails -> localData.writeTeamDetailsToRealm(teamsDetails));
        }
    }
}

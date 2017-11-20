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
package georgiopoulos.infootball.ui.League.LatestEvents;

import android.os.Bundle;

import javax.inject.Inject;

import georgiopoulos.infootball.data.local.LocalData;
import georgiopoulos.infootball.data.remote.api.ServerAPI;
import georgiopoulos.infootball.ui.Base.BasePresenter;
import icepick.State;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class LatestEventsPresenter extends BasePresenter<LatestEventsFragment>{

    private static final int REQUEST_LATEST_EVENTS = 2;
    private static final int REQUEST_TEAMS = 1;
    @Inject ServerAPI api;
    @Inject LocalData localData;
    @State String leagueId;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);

        restartableLatestCache
                (REQUEST_TEAMS,
                 ()->api.getLeagueTeams(leagueId)
                             .subscribeOn(Schedulers.io())
                             .observeOn(Schedulers.computation())
                             .map(teamsDetails -> localData.writeTeamDetailsToRealm(teamsDetails))
                             .observeOn(mainThread()),
                 LatestEventsFragment::onTeams,
                 LatestEventsFragment::onNetworkError);

        restartableLatestCache
                (REQUEST_LATEST_EVENTS,
                 () -> api.getLatestEvents(leagueId)
                               .subscribeOn(Schedulers.io())
                               .observeOn(Schedulers.computation())
                               .map(events -> localData.writeRoundToRealm(events))
                               .observeOn(mainThread()),
                 LatestEventsFragment::onEvents,
                 LatestEventsFragment::onNetworkError);
    }

    protected void requestTeams(String leagueId){
        this.leagueId = leagueId;
        start(REQUEST_TEAMS);
    }

    protected void requestEvents(){
        start(REQUEST_LATEST_EVENTS);
    }

}

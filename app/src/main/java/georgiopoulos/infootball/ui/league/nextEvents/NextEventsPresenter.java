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
package georgiopoulos.infootball.ui.league.nextEvents;

import android.os.Bundle;

import javax.inject.Inject;

import georgiopoulos.infootball.data.local.LocalData;
import georgiopoulos.infootball.data.remote.api.ServerAPI;
import georgiopoulos.infootball.data.remote.dto.events.Events;
import georgiopoulos.infootball.ui.base.BasePresenter;
import icepick.State;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class NextEventsPresenter extends BasePresenter<NextEventsFragment>{

    private static final int REQUEST_NEXT_EVENTS = 1;
    @Inject ServerAPI api;
    @Inject LocalData localData;
    @State String leagueId;
    @State String round;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        restartableLatestCache(
                REQUEST_NEXT_EVENTS,
                 () -> api.getNextEvents(leagueId,round)
                               .subscribeOn(Schedulers.io())
                               .map(Events::getEvents)
                               .observeOn(mainThread()),
                 NextEventsFragment::onEvents,
                 NextEventsFragment::onNetworkError);
    }

    public void request(String leagueId){
        round=localData.getRoundFromRealm(leagueId);
        this.leagueId = leagueId;
        start(REQUEST_NEXT_EVENTS);
    }

}

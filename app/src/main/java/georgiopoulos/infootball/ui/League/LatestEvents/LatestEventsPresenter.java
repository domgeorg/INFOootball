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
package georgiopoulos.infootball.ui.League.LatestEvents;

import android.os.Bundle;

import javax.inject.Inject;

import georgiopoulos.infootball.data.remote.api.ServerAPI;
import georgiopoulos.infootball.ui.Base.BasePresenter;
import icepick.State;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class LatestEventsPresenter extends BasePresenter<LatestEventsFragment>{

    private static final int REQUEST_LATEST_EVENTS = 1;
    @Inject ServerAPI api;
    @State String leagueId;

    @Override public void onCreate(Bundle savedState){
        super.onCreate(savedState);

        restartableLatestCache(REQUEST_LATEST_EVENTS,
                               () -> api.getLatestEvents(leagueId)
                                             .subscribeOn(Schedulers.io())
                                             .observeOn(mainThread()),
                               LatestEventsFragment::onEvents,
                               LatestEventsFragment::onNetworkError);
    }

    public void request(String leagueId){
        this.leagueId = leagueId;
        start(REQUEST_LATEST_EVENTS);
    }
}
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
package georgiopoulos.infootball.ui.Team;

import android.os.Bundle;

import javax.inject.Inject;

import georgiopoulos.infootball.data.remote.api.ServerAPI;
import georgiopoulos.infootball.ui.Base.BasePresenter;
import icepick.State;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class TeamRosterPresenter extends BasePresenter<TeamRosterFragment>{

    private static final int REQUEST_PLAYERS = 1;
    @Inject ServerAPI api;
    @State String teamId;

    @Override public void onCreate(Bundle savedState){
        super.onCreate(savedState);

        restartableLatestCache(REQUEST_PLAYERS,
                               () -> api.getTeamPlayers(teamId)
                                             .subscribeOn(Schedulers.io())
                                             .observeOn(mainThread()),
                               TeamRosterFragment::onPlayers,
                               TeamRosterFragment::onNetworkError);
    }

    public void request(String teamId){
        this.teamId=teamId;
        start(REQUEST_PLAYERS);
    }
}

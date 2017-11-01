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
 *
 * DeliverLatestCache keeps the latest onNext value and emits it each time a new view gets attached.
 * If a new onNext value appears while a view is attached, it will be delivered immediately.
 */
package georgiopoulos.infootball.ui.League.LeagueTable;

import android.os.Bundle;

import javax.inject.Inject;

import georgiopoulos.infootball.data.remote.api.ServerAPI;
import georgiopoulos.infootball.ui.Base.BasePresenter;
import icepick.State;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class LeagueTablePresenter extends BasePresenter<LeagueTableFragment>{

    private static final int REQUEST_LEAGUE_TABLE = 1;
    @Inject ServerAPI api;
    @State String leagueId;
    @State String season="1718";

    public void request(String leagueId){

        this.leagueId = leagueId;

        restartableLatestCache(REQUEST_LEAGUE_TABLE,
                               () -> api.getLeagueTable(leagueId,season)
                                             .subscribeOn(Schedulers.io())
                                             .observeOn(mainThread()),
                               LeagueTableFragment::onTable,
                               LeagueTableFragment::onNetworkError);

        start(REQUEST_LEAGUE_TABLE);
    }
}

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
package georgiopoulos.infootball.ui.league.leagueTable;

import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import georgiopoulos.infootball.data.local.LocalData;
import georgiopoulos.infootball.data.remote.api.ServerAPI;
import georgiopoulos.infootball.data.remote.dto.league.LeagueTable;
import georgiopoulos.infootball.data.remote.dto.league.Table;
import georgiopoulos.infootball.ui.base.BasePresenter;
import icepick.State;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class LeagueTablePresenter
        extends BasePresenter<LeagueTableFragment>{

    private static final int REQUEST_TEAMS = 1;
    private static final int REQUEST_LEAGUE_TABLE = 2;
    @Inject ServerAPI api;
    @Inject LocalData localData;
    @State String leagueId;
    @State String season="1718";

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);

        restartableLatestCache(
                REQUEST_TEAMS,
                () -> api.getLeagueTeams(leagueId)
                             .subscribeOn(Schedulers.io())
                             .observeOn(Schedulers.computation())
                             .filter(teamsDetails -> teamsDetails!=null)
                             .map(teamsDetails -> localData.writeTeamDetailsToRealm(teamsDetails))
                             .observeOn(mainThread()),
                LeagueTableFragment::onTeams,
                LeagueTableFragment::onNetworkError);


        restartableLatestCache(
                REQUEST_LEAGUE_TABLE,
                () -> api.getLeagueTable(leagueId,season)
                              .subscribeOn(Schedulers.io())
                              .map(LeagueTable::getTable)
                              .map(this::persistRound)
                              .subscribeOn(Schedulers.immediate())
                              .observeOn(mainThread()),
                LeagueTableFragment::onTable,
                LeagueTableFragment::onNetworkError);
    }

    protected void requestLeagueTable(){
        start(REQUEST_LEAGUE_TABLE);
    }

    protected void requestTeams(String leagueId){
        this.leagueId = leagueId;
        start(REQUEST_TEAMS);
    }

    private List<Table> persistRound(List<Table> list){
        localData.writeRoundToRealm(leagueId,list.get(0).getPlayed());
        return list;
    }
}

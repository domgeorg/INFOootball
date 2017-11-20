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
package georgiopoulos.infootball.application;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Component;
import georgiopoulos.infootball.data.local.LocalData;
import georgiopoulos.infootball.data.local.LocalDataModule;
import georgiopoulos.infootball.data.remote.api.NetworkModule;
import georgiopoulos.infootball.data.remote.api.ServerAPI;
import georgiopoulos.infootball.data.remote.dto.league.Event;
import georgiopoulos.infootball.data.remote.dto.league.Table;
import georgiopoulos.infootball.data.remote.dto.livescores.Match;
import georgiopoulos.infootball.data.remote.dto.soccerLeagues.Country;
import georgiopoulos.infootball.data.remote.dto.team.Player;
import georgiopoulos.infootball.ui.Base.ActivityModule;
import georgiopoulos.infootball.ui.Base.CoreActivityFacet;
import georgiopoulos.infootball.ui.League.LatestEvents.LatestEventsPresenter;
import georgiopoulos.infootball.ui.League.LeagueActivity;
import georgiopoulos.infootball.ui.League.LeagueTable.LeagueTablePresenter;
import georgiopoulos.infootball.ui.League.NextEvents.NextEventsPresenter;
import georgiopoulos.infootball.ui.LiveScores.LiveScoresPresenter;
import georgiopoulos.infootball.ui.SoccerLeagues.LeaguesPresenter;
import georgiopoulos.infootball.ui.Team.TeamRosterActivity;
import georgiopoulos.infootball.ui.Team.TeamRosterFragment;
import georgiopoulos.infootball.ui.Team.TeamRosterPresenter;
import georgiopoulos.infootball.util.adapters.EventViewHolder;
import georgiopoulos.infootball.util.adapters.LeagueTableTeamViewHolder;
import georgiopoulos.infootball.util.adapters.LiveScoresViewHolder;
import georgiopoulos.infootball.util.adapters.NextEventViewHolder;
import georgiopoulos.infootball.util.adapters.PlayerViewHolder;
import georgiopoulos.infootball.util.adapters.SoccerLeagueViewHolder;
import georgiopoulos.infootball.util.adapters.base.EmptyViewHolder;
import io.realm.Realm;

@Singleton
@Component(modules = {AppModule.class,LocalDataModule.class,NetworkModule.class,ActivityModule.class})
public interface AppComponent{

    Resources resources();
    Realm realm();
    ServerAPI api();
    Context appContext();
    LocalData localData();
    CoreActivityFacet facet();

    //Presenters
    void inject(LeagueTablePresenter leagueTablePresenter);

    void inject(LeaguesPresenter leaguesPresenter);
    void inject(LatestEventsPresenter latestEventsPresenter);
    void inject(NextEventsPresenter nextEventsPresenter);
    void inject(TeamRosterPresenter teamRosterPresenter);
    void inject(LiveScoresPresenter liveScoresPresenter);

    //ViewHolders
    void inject(EventViewHolder<Event> EventViewHolder);
    void inject(NextEventViewHolder<Event> nextEventViewHolder);
    void inject(EmptyViewHolder emptyViewHolder);
    void inject(SoccerLeagueViewHolder<Country> soccerLeagueViewHolder);
    void inject(LeagueTableTeamViewHolder<Table> leagueTableTeamViewHolder);
    void inject(PlayerViewHolder<Player> playerPlayerViewHolder);
    void inject(LiveScoresViewHolder<Match> liveScoresLiveScoresViewHolder);

    //Activities
    void inject(TeamRosterActivity teamRosterActivity);
    void inject(LeagueActivity leagueActivity);


    void inject(TeamRosterFragment teamRosterFragment);
}

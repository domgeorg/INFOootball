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
package georgiopoulos.infootball.application;

import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Component;
import georgiopoulos.infootball.data.local.LocalData;
import georgiopoulos.infootball.data.local.LocalDataModule;
import georgiopoulos.infootball.data.remote.api.NetworkModule;
import georgiopoulos.infootball.data.remote.api.NewsAPI;
import georgiopoulos.infootball.data.remote.api.ServerAPI;
import georgiopoulos.infootball.data.remote.dto.events.Event;
import georgiopoulos.infootball.data.remote.dto.leagueTable.Table;
import georgiopoulos.infootball.data.remote.dto.leagues.Country;
import georgiopoulos.infootball.data.remote.dto.liveScores.Match;
import georgiopoulos.infootball.data.remote.dto.news.Article;
import georgiopoulos.infootball.data.remote.dto.teamPlayers.Player;
import georgiopoulos.infootball.ui.league.latestEvents.LatestEventsPresenter;
import georgiopoulos.infootball.ui.league.leagueTable.LeagueTablePresenter;
import georgiopoulos.infootball.ui.league.nextEvents.NextEventsPresenter;
import georgiopoulos.infootball.ui.main.leagues.LeaguesPresenter;
import georgiopoulos.infootball.ui.main.liveScores.LiveScoresPresenter;
import georgiopoulos.infootball.ui.main.news.NewsPresenter;
import georgiopoulos.infootball.ui.team.TeamRosterPresenter;
import georgiopoulos.infootball.util.adapters.EmptyViewHolder;
import georgiopoulos.infootball.util.adapters.EventViewHolder;
import georgiopoulos.infootball.util.adapters.LeagueTableTeamViewHolder;
import georgiopoulos.infootball.util.adapters.LeagueViewHolder;
import georgiopoulos.infootball.util.adapters.LiveScoresViewHolder;
import georgiopoulos.infootball.util.adapters.NewsViewHolder;
import georgiopoulos.infootball.util.adapters.NextEventViewHolder;
import georgiopoulos.infootball.util.adapters.PlayerViewHolder;
import io.realm.Realm;

@Singleton
@Component(modules = {AppModule.class,LocalDataModule.class,NetworkModule.class})
public interface AppComponent{

    Resources resources();
    Realm realm();
    ServerAPI api();
    NewsAPI news();
    LocalData localData();

    //Presenters
    void inject(LeagueTablePresenter leagueTablePresenter);
    void inject(LeaguesPresenter leaguesPresenter);
    void inject(LatestEventsPresenter latestEventsPresenter);
    void inject(NextEventsPresenter nextEventsPresenter);
    void inject(TeamRosterPresenter teamRosterPresenter);
    void inject(LiveScoresPresenter liveScoresPresenter);
    void inject(NewsPresenter newsPresenter);

    //ViewHolders
    void inject(EmptyViewHolder emptyViewHolder);
    void inject(EventViewHolder<Event> EventViewHolder);
    void inject(NextEventViewHolder<Event> nextEventViewHolder);
    void inject(LeagueViewHolder<Country> leagueViewHolder);
    void inject(LeagueTableTeamViewHolder<Table> leagueTableTeamViewHolder);
    void inject(PlayerViewHolder<Player> playerPlayerViewHolder);
    void inject(LiveScoresViewHolder<Match> liveScoresLiveScoresViewHolder);
    void inject(NewsViewHolder<Article> newsViewHolder);
}

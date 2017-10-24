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
package georgiopoulos.infootball.data.remote.api;

import javax.inject.Singleton;

import dagger.Component;
import georgiopoulos.infootball.ui.League.LatestEvents.LatestEventsPresenter;
import georgiopoulos.infootball.ui.League.LeaguePresenter;
import georgiopoulos.infootball.ui.League.LeagueTable.LeagueTablePresenter;
import georgiopoulos.infootball.ui.SoccerLeagues.SoccerLeaguesPresenter;

@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent{
    void inject(LeagueTablePresenter leagueTablePresenter);
    void inject(SoccerLeaguesPresenter soccerLeaguesPresenter);
    void inject(LeaguePresenter leaguePresenter);
    void inject(LatestEventsPresenter latestEventsPresenter);
}

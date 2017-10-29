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

import georgiopoulos.infootball.data.remote.dto.Events;
import georgiopoulos.infootball.data.remote.dto.LeagueTable;
import georgiopoulos.infootball.data.remote.dto.Leagues;
import georgiopoulos.infootball.data.remote.dto.TeamPlayers;
import georgiopoulos.infootball.data.remote.dto.TeamsDetails;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ServerAPI{

    @GET("search_all_leagues.php?s=soccer")
    Observable<Leagues>getLeagues();

    @GET("lookup_all_teams.php")
    Observable<TeamsDetails>getLeagueTeams(@Query("id") String id);

    @GET("lookuptable.php")
    Observable<LeagueTable>getLeagueTable(@Query("l") String leagueId, @Query("s") String season);

    @GET("eventspastleague.php")
    Observable<Events>getLatestEvents(@Query("id") String leagueId);

    @GET("eventsnextleague.php")
    Observable<Events>getNextEvents(@Query("id") String leagueId, @Query("r") String round);

    @GET("lookup_all_players.php")
    Observable<TeamPlayers>getTeamPlayers(@Query("id") String teamId);
}

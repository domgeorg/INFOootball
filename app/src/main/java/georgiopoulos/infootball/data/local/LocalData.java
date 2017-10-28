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
package georgiopoulos.infootball.data.local;

import georgiopoulos.infootball.data.remote.dto.Leagues;
import georgiopoulos.infootball.data.remote.dto.TeamsDetails;
import io.realm.Realm;

public interface LocalData{

    String getJerseyUrl(String idTeam);
    String getBadgeUrl(String idTeam);
    String getStadium(String idTeam);
    Boolean findTeamInRealm(Realm realm,String idTeam);
    TeamsDetails writeTeamDetailsToRealm(TeamsDetails teamsDetails);
    String getRoundFromRealm(String leagueId);
    Boolean findLeagueInRealm(Realm realm, String leagueId);
    Leagues writeLeaguesToRealm(Leagues leagues);

}

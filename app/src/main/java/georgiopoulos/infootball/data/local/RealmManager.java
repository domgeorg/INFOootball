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

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;
import javax.inject.Provider;

import georgiopoulos.infootball.data.local.realmObjects.LeagueRealm;
import georgiopoulos.infootball.data.local.realmObjects.LeagueRoundRealm;
import georgiopoulos.infootball.data.local.realmObjects.TeamRealm;
import georgiopoulos.infootball.data.remote.dto.Country;
import georgiopoulos.infootball.data.remote.dto.Event;
import georgiopoulos.infootball.data.remote.dto.Events;
import georgiopoulos.infootball.data.remote.dto.Leagues;
import georgiopoulos.infootball.data.remote.dto.Team;
import georgiopoulos.infootball.data.remote.dto.TeamsDetails;
import io.realm.Realm;
@SuppressLint("NewApi") // try-with-resources is back-ported by retrolambda
public class RealmManager implements LocalData{

    private final Provider<Realm> realmProvider;

    @Inject
    public RealmManager(Provider<Realm> realmProvider) {
        this.realmProvider = realmProvider;
    }

    @NonNull
    public String getJerseyUrl(String idTeam){
        try(Realm realm = realmProvider.get()){
            TeamRealm teamRealm = realm.where(TeamRealm.class).equalTo("idTeam",idTeam).findFirst();
            if(teamRealm!=null){
                if(teamRealm.getStrTeamJersey() != null && ! teamRealm.getStrTeamJersey().isEmpty())
                    return teamRealm.getStrTeamJersey();
                else if(teamRealm.getStrTeamBadge() != null && ! teamRealm.getStrTeamBadge().isEmpty())
                    return teamRealm.getStrTeamBadge();
                else if(teamRealm.getStrTeamLogo() != null && ! teamRealm.getStrTeamLogo().isEmpty())
                    return teamRealm.getStrTeamLogo();
            }
        } return "https://fm-view.net/forum/uploads/monthly_2017_09/NO-BRAND-73.png.cf70c74cd066c59d9ce14992f0bdedfc.png";
    }

    @NonNull
    public String getBadgeUrl(String idTeam){
        try(Realm realm = realmProvider.get()){
            TeamRealm teamRealm = realm.where(TeamRealm.class).equalTo("idTeam",idTeam).findFirst();
            if(teamRealm!=null){
                if(teamRealm.getStrTeamBadge() != null && ! teamRealm.getStrTeamBadge().isEmpty())
                    return teamRealm.getStrTeamBadge();
                else if(teamRealm.getStrTeamLogo() != null && ! teamRealm.getStrTeamLogo().isEmpty())
                    return teamRealm.getStrTeamLogo();
                else if(teamRealm.getStrTeamJersey() != null && ! teamRealm.getStrTeamJersey().isEmpty())
                    return teamRealm.getStrTeamJersey();
            }return "http://www.thesportsdb.com/images/team-icon.png";
        }
    }

    @NonNull
    public String getStadium(String idTeam){
        try(Realm realm = realmProvider.get()){
            TeamRealm teamRealm = realm.where(TeamRealm.class).equalTo("idTeam",idTeam).findFirst();
            if (teamRealm!=null && teamRealm.getStrStadium()!=null)return teamRealm.getStrStadium();
            else return " ";
        }
    }

    @NonNull
    public Boolean findTeamInRealm(Realm realm,String idTeam){
        return (realm.where(TeamRealm.class).equalTo("idTeam",idTeam).findFirst()!=null);
    }

    @NonNull
    public TeamsDetails writeTeamDetailsToRealm(TeamsDetails teamsDetails){
        try(Realm realm = realmProvider.get()){
            realm.executeTransaction(t -> {
                for(Team team : teamsDetails.getTeams())
                    if(! findTeamInRealm(realm,team.getIdTeam()))
                        t.insertOrUpdate(new TeamRealm(team.getIdTeam(),team.getStrTeam(),team.getStrLeague(),team.getIdLeague(),team.getStrManager(),team.getStrStadium(),team.getStrStadiumThumb(),team.getStrStadiumDescription(),team.getStrStadiumLocation(),team.getStrWebsite(),team.getStrDescriptionEN(),team.getStrTeamBadge(),team.getStrTeamJersey(),team.getStrTeamLogo()));});
        }return (teamsDetails);
    }

    @NonNull
    public String getRoundFromRealm(String leagueId){
        String round="-1";
        try(Realm realm = realmProvider.get()){
            if(realm.where(LeagueRoundRealm.class).equalTo("leagueId",leagueId).findFirst().getRound() != null)
                round = realm.where(LeagueRoundRealm.class).equalTo("leagueId",leagueId).findFirst().getRound();
            int r = Integer.valueOf(round)+1;
            return String.valueOf(r);
        }
    }

    @NonNull
    public Boolean findLeagueInRealm(Realm realm,String leagueId){
        return (realm.where(LeagueRealm.class).equalTo("idLeague",leagueId).findFirst()!=null);
    }

    @NonNull
    public Leagues writeLeaguesToRealm(Leagues leagues){
        try(Realm realm = realmProvider.get()){
            realm.executeTransaction(t -> {
                for(Country league : leagues.getCountrys())
                    if(! findLeagueInRealm(realm,league.getIdLeague()))
                        t.insertOrUpdate(new LeagueRealm(league.getIdLeague(),league.getStrLeague(),league.getStrCountry(),league.getStrWebsite(),league.getStrFacebook(),league.getStrTwitter(),league.getStrYoutube(),league.getStrRSS(),league.getStrDescriptionEN(),
                                                         league.getStrBanner(),league.getStrBadge(),league.getStrLogo(),league.getStrPoster(),league.getStrTrophy()));});
        }return (leagues);
    }

    @Nullable
    public Events writeRoundToRealm(Events events){
        if (events!=null){
            try(Realm realm = Realm.getDefaultInstance()){
                realm.executeTransaction(t -> {
                    Event event = events.getEvents().get(1);
                    t.insertOrUpdate(new LeagueRoundRealm(event.getIdLeague(),event.getIntRound()));
                });
            }}
        return (events);
    }

    @Override
    @NonNull
    public String getLeagueBadgeUrl(String leagueName){
        try(Realm realm = realmProvider.get()){
            LeagueRealm leagueRealm = realm.where(LeagueRealm.class).equalTo("strLeague",leagueName).findFirst();
            if(leagueRealm!=null){
                if(leagueRealm.getStrBadge()!=null && !leagueRealm.getStrBadge().isEmpty()) return leagueRealm.getStrBadge();
                else if(leagueRealm.getStrLogo() != null && ! leagueRealm.getStrLogo().isEmpty()) return leagueRealm.getStrLogo();
                else if(leagueRealm.getStrTrophy() != null && ! leagueRealm.getStrTrophy().isEmpty()) return leagueRealm.getStrTrophy();
            }return "https://www.flaticon.com/free-icon/champion_344213";
        }
    }
}

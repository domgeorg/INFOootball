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
package georgiopoulos.infootball.data.local;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Provider;

import georgiopoulos.infootball.data.local.realmObjects.LeagueRealm;
import georgiopoulos.infootball.data.local.realmObjects.LeagueRoundRealm;
import georgiopoulos.infootball.data.local.realmObjects.TeamRealm;
import georgiopoulos.infootball.data.remote.dto.league.Team;
import georgiopoulos.infootball.data.remote.dto.soccerLeagues.Country;
import georgiopoulos.infootball.data.remote.dto.soccerLeagues.Leagues;
import georgiopoulos.infootball.data.remote.dto.team.TeamsDetails;
import io.realm.Realm;

@SuppressLint("NewApi")
public class RealmManager implements LocalData{

    private final Provider<Realm> realmProvider;

    @Inject
    public RealmManager(Provider<Realm> realmProvider){
        this.realmProvider = realmProvider;
    }

    //Read

    @Override
    @NonNull
    public String getJerseyUrl(String idTeam){
        try(Realm realm = realmProvider.get()){
            TeamRealm teamRealm = realm.where(TeamRealm.class).equalTo("idTeam",idTeam).findFirst();
            if(teamRealm != null){
                if(teamRealm.getStrTeamJersey() != null && ! teamRealm.getStrTeamJersey().isEmpty())
                    return teamRealm.getStrTeamJersey();
                else if(teamRealm.getStrTeamBadge() != null && ! teamRealm.getStrTeamBadge().isEmpty())

                    return teamRealm.getStrTeamBadge();
                else if(teamRealm.getStrTeamLogo() != null && ! teamRealm.getStrTeamLogo()
                                                                        .isEmpty())
                    return teamRealm.getStrTeamLogo();
            }
        }
        return "https://fm-view.net/forum/uploads/monthly_2017_09/NO-BRAND-73.png.cf70c74cd066c59d9ce14992f0bdedfc.png";
    }

    @Override
    @NonNull
    public String getBadgeUrl(String idTeam){
        try(Realm realm = realmProvider.get()){
            TeamRealm teamRealm = realm.where(TeamRealm.class).equalTo("idTeam",idTeam).findFirst();
            if(teamRealm != null){
                if(teamRealm.getStrTeamBadge() != null && ! teamRealm.getStrTeamBadge().isEmpty())
                    return teamRealm.getStrTeamBadge();
                else if(teamRealm.getStrTeamLogo() != null && ! teamRealm.getStrTeamLogo().isEmpty())
                    return teamRealm.getStrTeamLogo();
                else if(teamRealm.getStrTeamJersey() != null && ! teamRealm.getStrTeamJersey().isEmpty())
                    return teamRealm.getStrTeamJersey();
            }
            return "http://www.wunapps.com/2015/lg/padres_lg/images/lg-team-badge-08.png";
        }
    }

    @Override
    @NonNull
    public String getStadium(String idTeam){
        try(Realm realm = realmProvider.get()){
            TeamRealm teamRealm = realm.where(TeamRealm.class).equalTo("idTeam",idTeam).findFirst();
            if(teamRealm != null && teamRealm.getStrStadium() != null) return teamRealm.getStrStadium();
            else return " ";
        }
    }

    @Override
    @NonNull
    public Boolean findTeamInRealm(Realm realm,String idTeam){
        return (realm.where(TeamRealm.class).equalTo("idTeam",idTeam).findFirst() != null);
    }


    @Override
    @NonNull
    public String getRoundFromRealm(String leagueId){
        int round=0;
        try(Realm realm = realmProvider.get()){
            LeagueRoundRealm leagueRoundRealm = realm.where(LeagueRoundRealm.class).equalTo("leagueId",leagueId).findFirst();
            if(leagueRoundRealm != null) round=leagueRoundRealm.getRound()+1;
        }
        return String.valueOf(round);
    }

    @Override
    @NonNull
    public Boolean findLeagueInRealm(Realm realm,String leagueId){
        return (realm.where(LeagueRealm.class).equalTo("idLeague",leagueId).findFirst() != null);
    }


    //Write

    @Override
    @NonNull
    public Leagues writeLeaguesToRealm(Leagues leagues){
        try(Realm realm = realmProvider.get()){
            realm.executeTransaction(t -> {
                for(Country league : leagues.getCountrys())
                    if(! findLeagueInRealm(realm,league.getIdLeague()))
                        t.insertOrUpdate(new LeagueRealm(league.getIdLeague(),league.getStrLeague(),league.getStrCountry(),league.getStrWebsite(),league.getStrFacebook(),league.getStrTwitter(),league.getStrYoutube(),league.getStrRSS(),league.getStrDescriptionEN(),league.getStrBanner(),league.getStrBadge(),league.getStrLogo(),league.getStrPoster(),league.getStrTrophy()));
            });
        }
        return (leagues);
    }

    @Override
    public void writeRoundToRealm(String leagueId,int round){
        try(Realm realm = Realm.getDefaultInstance()){
            realm.executeTransaction(t -> {
                t.insertOrUpdate(new LeagueRoundRealm(leagueId,round));
            });
        }
    }

    @Override
    @NonNull
    public TeamsDetails writeTeamDetailsToRealm(TeamsDetails teamsDetails){
        try(Realm realm = realmProvider.get()){
            realm.executeTransaction(t -> {
                for(Team team : teamsDetails.getTeams())
                    if(! findTeamInRealm(realm,team.getIdTeam()))
                        t.insertOrUpdate(new TeamRealm(team.getIdTeam(),team.getStrTeam(),team.getStrLeague(),team.getIdLeague(),team.getStrManager(),team.getStrStadium(),team.getStrStadiumThumb(),team.getStrStadiumDescription(),team.getStrStadiumLocation(),team.getStrWebsite(),team.getStrDescriptionEN(),team.getStrTeamBadge(),team.getStrTeamJersey(),team.getStrTeamLogo()));
            });
        }
        return (teamsDetails);
    }
}
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

import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.annotations.PrimaryKey;

public class TeamRealm extends RealmObject{

    @PrimaryKey private String idTeam;
    private String strTeam;
    private String strLeague;
    private String strManager;
    private String strStadium;
    private String strStadiumThumb;
    private String strStadiumDescription;
    private String strStadiumLocation;
    private String strWebsite;
    private String strDescriptionEN;
    private String strTeamBadge;
    private String strTeamJersey;
    private String strTeamLogo;



    /**
     * No args constructor for use in serialization
     *
     */
    public TeamRealm() {
    }

    /**
     *
     * @param strDescriptionEN
     * @param strManager
     * @param strStadiumLocation
     * @param strTeam
     * @param strStadiumDescription
     * @param idTeam
     * @param strStadiumThumb
     * @param strTeamLogo
     * @param strWebsite
     * @param idLeague
     * @param strLeague
     * @param strStadium
     * @param strTeamBadge
     * @param strTeamJersey
     */
    public TeamRealm(String idTeam,  String strTeam, String strLeague, String idLeague, String strManager, String strStadium, String strStadiumThumb, String strStadiumDescription, String strStadiumLocation,String strWebsite,String strDescriptionEN, String strTeamBadge, String strTeamJersey, String strTeamLogo) {
        super();
        this.idTeam = idTeam;
        this.strTeam = strTeam;
        this.strLeague = strLeague;
        this.strManager = strManager;
        this.strStadium = strStadium;
        this.strStadiumThumb = strStadiumThumb;
        this.strStadiumDescription = strStadiumDescription;
        this.strStadiumLocation = strStadiumLocation;
        this.strWebsite = strWebsite;
        this.strDescriptionEN = strDescriptionEN;
        this.strTeamBadge = strTeamBadge;
        this.strTeamJersey = strTeamJersey;
        this.strTeamLogo = strTeamLogo;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getStrTeam() {
        return strTeam;
    }

    public void setStrTeam(String strTeam) {
        this.strTeam = strTeam;
    }

    public String getStrLeague() {
        return strLeague;
    }

    public void setStrLeague(String strLeague) {
        this.strLeague = strLeague;
    }

    public String getStrManager() {
        return strManager;
    }

    public void setStrManager(String strManager) {
        this.strManager = strManager;
    }

    public String getStrStadium() {
        return strStadium;
    }

    public void setStrStadium(String strStadium) {
        this.strStadium = strStadium;
    }

    public String getStrStadiumThumb() {
        return strStadiumThumb;
    }

    public void setStrStadiumThumb(String strStadiumThumb) {
        this.strStadiumThumb = strStadiumThumb;
    }

    public String getStrStadiumDescription() {
        return strStadiumDescription;
    }

    public void setStrStadiumDescription(String strStadiumDescription) {
        this.strStadiumDescription = strStadiumDescription;
    }

    public String getStrStadiumLocation() {
        return strStadiumLocation;
    }

    public void setStrStadiumLocation(String strStadiumLocation) {
        this.strStadiumLocation = strStadiumLocation;
    }
    public String getStrWebsite() {
        return strWebsite;
    }

    public void setStrWebsite(String strWebsite) {
        this.strWebsite = strWebsite;
    }

    public String getStrDescriptionEN() {
        return strDescriptionEN;
    }

    public void setStrDescriptionEN(String strDescriptionEN) {
        this.strDescriptionEN = strDescriptionEN;
    }

    public String getStrTeamBadge() {
        return strTeamBadge;
    }

    public void setStrTeamBadge(String strTeamBadge) {
        this.strTeamBadge = strTeamBadge;
    }

    public String getStrTeamJersey() {
        return strTeamJersey;
    }

    public void setStrTeamJersey(String strTeamJersey) {
        this.strTeamJersey = strTeamJersey;
    }

    public String getStrTeamLogo() {
        return strTeamLogo;
    }

    public void setStrTeamLogo(String strTeamLogo) {
        this.strTeamLogo = strTeamLogo;
    }

}


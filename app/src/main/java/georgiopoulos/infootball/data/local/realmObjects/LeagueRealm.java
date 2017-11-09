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
package georgiopoulos.infootball.data.local.realmObjects;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class LeagueRealm extends RealmObject{

    @PrimaryKey
    private String idLeague;
    @Index
    private String strLeague;
    private String strCountry;
    private String strWebsite;
    private String strFacebook;
    private String strTwitter;
    private String strYoutube;
    private String strRSS;
    private String strDescriptionEN;
    private String strBanner;
    private String strBadge;
    private String strLogo;
    private String strPoster;
    private String strTrophy;

    /**
     * No args constructor for use in serialization
     *
     */
    public LeagueRealm() {
    }

    /**
     *
     * @param strDescriptionEN
     * @param strTwitter
     * @param strWebsite
     * @param strBadge
     * @param idLeague
     * @param strLeague
     * @param strLogo
     * @param strCountry
     * @param strRSS
     * @param strBanner
     * @param strTrophy
     * @param strYoutube
     * @param strFacebook
     * @param strPoster
     */
    public LeagueRealm(String idLeague,String strLeague, String strCountry, String strWebsite, String strFacebook, String strTwitter, String strYoutube, String strRSS, String strDescriptionEN,
                   String strBanner, String strBadge, String strLogo, String strPoster, String strTrophy){
        super();
        this.idLeague = idLeague;
        this.strLeague = strLeague;
        this.strCountry = strCountry;
        this.strBadge = strBadge;
        this.strBanner = strBanner;
        this.strLogo = strLogo;
        this.strPoster = strPoster;
        this.strTrophy = strTrophy;
        this.strWebsite = strWebsite;
        this.strFacebook = strFacebook;
        this.strTwitter = strTwitter;
        this.strYoutube = strYoutube;
        this.strRSS = strRSS;
        this.strDescriptionEN = strDescriptionEN;
    }

    public String getIdLeague() {
        return idLeague;
    }

    public void setIdLeague(String idLeague) {
        this.idLeague = idLeague;
    }

    public String getStrLeague() {
        return strLeague;
    }

    public void setStrLeague(String strLeague) {
        this.strLeague = strLeague;
    }

    public String getStrCountry() {
        return strCountry;
    }

    public void setStrCountry(String strCountry) {
        this.strCountry = strCountry;
    }

    public String getStrWebsite() {
        return strWebsite;
    }

    public void setStrWebsite(String strWebsite) {
        this.strWebsite = strWebsite;
    }

    public String getStrFacebook() {
        return strFacebook;
    }

    public void setStrFacebook(String strFacebook) {
        this.strFacebook = strFacebook;
    }

    public String getStrTwitter() {
        return strTwitter;
    }

    public void setStrTwitter(String strTwitter) {
        this.strTwitter = strTwitter;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public String getStrRSS() {
        return strRSS;
    }

    public void setStrRSS(String strRSS) {
        this.strRSS = strRSS;
    }

    public String getStrDescriptionEN() {
        return strDescriptionEN;
    }

    public void setStrDescriptionEN(String strDescriptionEN) {
        this.strDescriptionEN = strDescriptionEN;
    }

    public String getStrBanner() {
        return strBanner;
    }

    public void setStrBanner(String strBanner) {
        this.strBanner = strBanner;
    }

    public String getStrBadge() {
        return strBadge;
    }

    public void setStrBadge(String strBadge) {
        this.strBadge = strBadge;
    }

    public String getStrLogo() {
        return strLogo;
    }

    public void setStrLogo(String strLogo) {
        this.strLogo = strLogo;
    }

    public String getStrPoster() {
        return strPoster;
    }

    public void setStrPoster(String strPoster) {
        this.strPoster = strPoster;
    }

    public String getStrTrophy() {
        return strTrophy;
    }

    public void setStrTrophy(String strTrophy) {
        this.strTrophy = strTrophy;
    }

}

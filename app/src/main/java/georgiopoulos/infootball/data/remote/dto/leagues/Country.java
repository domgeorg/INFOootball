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
package georgiopoulos.infootball.data.remote.dto.leagues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("idLeague")
    @Expose
    private String idLeague;
    @SerializedName("idSoccerXML")
    @Expose
    private String idSoccerXML;
    @SerializedName("strSport")
    @Expose
    private String strSport;
    @SerializedName("strLeague")
    @Expose
    private String strLeague;
    @SerializedName("strLeagueAlternate")
    @Expose
    private String strLeagueAlternate;
    @SerializedName("intFormedYear")
    @Expose
    private String intFormedYear;
    @SerializedName("dateFirstEvent")
    @Expose
    private String dateFirstEvent;
    @SerializedName("strGender")
    @Expose
    private String strGender;
    @SerializedName("strCountry")
    @Expose
    private String strCountry;
    @SerializedName("strWebsite")
    @Expose
    private String strWebsite;
    @SerializedName("strFacebook")
    @Expose
    private String strFacebook;
    @SerializedName("strTwitter")
    @Expose
    private String strTwitter;
    @SerializedName("strYoutube")
    @Expose
    private String strYoutube;
    @SerializedName("strRSS")
    @Expose
    private String strRSS;
    @SerializedName("strDescriptionEN")
    @Expose
    private String strDescriptionEN;
    @SerializedName("strDescriptionDE")
    @Expose
    private String strDescriptionDE;
    @SerializedName("strDescriptionFR")
    @Expose
    private String strDescriptionFR;
    @SerializedName("strDescriptionIT")
    @Expose
    private String strDescriptionIT;
    @SerializedName("strDescriptionCN")
    @Expose
    private String strDescriptionCN;
    @SerializedName("strDescriptionJP")
    @Expose
    private String strDescriptionJP;
    @SerializedName("strDescriptionRU")
    @Expose
    private String strDescriptionRU;
    @SerializedName("strDescriptionES")
    @Expose
    private String strDescriptionES;
    @SerializedName("strDescriptionPT")
    @Expose
    private String strDescriptionPT;
    @SerializedName("strDescriptionSE")
    @Expose
    private String strDescriptionSE;
    @SerializedName("strDescriptionNL")
    @Expose
    private String strDescriptionNL;
    @SerializedName("strDescriptionHU")
    @Expose
    private String strDescriptionHU;
    @SerializedName("strDescriptionNO")
    @Expose
    private String strDescriptionNO;
    @SerializedName("strDescriptionPL")
    @Expose
    private String strDescriptionPL;
    @SerializedName("strFanart1")
    @Expose
    private String strFanart1;
    @SerializedName("strFanart2")
    @Expose
    private String strFanart2;
    @SerializedName("strFanart3")
    @Expose
    private String strFanart3;
    @SerializedName("strFanart4")
    @Expose
    private String strFanart4;
    @SerializedName("strBanner")
    @Expose
    private String strBanner;
    @SerializedName("strBadge")
    @Expose
    private String strBadge;
    @SerializedName("strLogo")
    @Expose
    private String strLogo;
    @SerializedName("strPoster")
    @Expose
    private String strPoster;
    @SerializedName("strTrophy")
    @Expose
    private String strTrophy;
    @SerializedName("strNaming")
    @Expose
    private String strNaming;
    @SerializedName("strLocked")
    @Expose
    private String strLocked;

    /**
     * No args constructor for use in serialization
     *
     */
    public Country() {
    }

    /**
     *
     * @param strLocked
     * @param dateFirstEvent
     * @param strDescriptionEN
     * @param strDescriptionRU
     * @param strTwitter
     * @param strWebsite
     * @param strDescriptionPL
     * @param strDescriptionCN
     * @param strBadge
     * @param strDescriptionNO
     * @param idLeague
     * @param strDescriptionES
     * @param strDescriptionPT
     * @param strDescriptionNL
     * @param strDescriptionSE
     * @param idSoccerXML
     * @param strLeague
     * @param strDescriptionIT
     * @param strLeagueAlternate
     * @param strLogo
     * @param intFormedYear
     * @param strSport
     * @param strCountry
     * @param strRSS
     * @param strBanner
     * @param strTrophy
     * @param strDescriptionJP
     * @param strYoutube
     * @param strDescriptionHU
     * @param strFacebook
     * @param strGender
     * @param strNaming
     * @param strDescriptionDE
     * @param strFanart4
     * @param strFanart3
     * @param strPoster
     * @param strFanart2
     * @param strDescriptionFR
     * @param strFanart1
     */
    public Country(String idLeague, String idSoccerXML, String strSport, String strLeague, String strLeagueAlternate, String intFormedYear, String dateFirstEvent, String strGender, String strCountry, String strWebsite, String strFacebook, String strTwitter, String strYoutube, String strRSS, String strDescriptionEN, String strDescriptionDE, String strDescriptionFR, String strDescriptionIT, String strDescriptionCN, String strDescriptionJP, String strDescriptionRU, String strDescriptionES, String strDescriptionPT, String strDescriptionSE, String strDescriptionNL, String strDescriptionHU, String strDescriptionNO, String strDescriptionPL, String strFanart1, String strFanart2, String strFanart3, String strFanart4, String strBanner, String strBadge, String strLogo, String strPoster, String strTrophy, String strNaming, String strLocked) {
        super();
        this.idLeague = idLeague;
        this.idSoccerXML = idSoccerXML;
        this.strSport = strSport;
        this.strLeague = strLeague;
        this.strLeagueAlternate = strLeagueAlternate;
        this.intFormedYear = intFormedYear;
        this.dateFirstEvent = dateFirstEvent;
        this.strGender = strGender;
        this.strCountry = strCountry;
        this.strWebsite = strWebsite;
        this.strFacebook = strFacebook;
        this.strTwitter = strTwitter;
        this.strYoutube = strYoutube;
        this.strRSS = strRSS;
        this.strDescriptionEN = strDescriptionEN;
        this.strDescriptionDE = strDescriptionDE;
        this.strDescriptionFR = strDescriptionFR;
        this.strDescriptionIT = strDescriptionIT;
        this.strDescriptionCN = strDescriptionCN;
        this.strDescriptionJP = strDescriptionJP;
        this.strDescriptionRU = strDescriptionRU;
        this.strDescriptionES = strDescriptionES;
        this.strDescriptionPT = strDescriptionPT;
        this.strDescriptionSE = strDescriptionSE;
        this.strDescriptionNL = strDescriptionNL;
        this.strDescriptionHU = strDescriptionHU;
        this.strDescriptionNO = strDescriptionNO;
        this.strDescriptionPL = strDescriptionPL;
        this.strFanart1 = strFanart1;
        this.strFanart2 = strFanart2;
        this.strFanart3 = strFanart3;
        this.strFanart4 = strFanart4;
        this.strBanner = strBanner;
        this.strBadge = strBadge;
        this.strLogo = strLogo;
        this.strPoster = strPoster;
        this.strTrophy = strTrophy;
        this.strNaming = strNaming;
        this.strLocked = strLocked;
    }

    public String getIdLeague() {
        return idLeague;
    }

    public void setIdLeague(String idLeague) {
        this.idLeague = idLeague;
    }

    public String getIdSoccerXML() {
        return idSoccerXML;
    }

    public void setIdSoccerXML(String idSoccerXML) {
        this.idSoccerXML = idSoccerXML;
    }

    public String getStrSport() {
        return strSport;
    }

    public void setStrSport(String strSport) {
        this.strSport = strSport;
    }

    public String getStrLeague() {
        return strLeague;
    }

    public void setStrLeague(String strLeague) {
        this.strLeague = strLeague;
    }

    public String getStrLeagueAlternate() {
        return strLeagueAlternate;
    }

    public void setStrLeagueAlternate(String strLeagueAlternate) {
        this.strLeagueAlternate = strLeagueAlternate;
    }

    public String getIntFormedYear() {
        return intFormedYear;
    }

    public void setIntFormedYear(String intFormedYear) {
        this.intFormedYear = intFormedYear;
    }

    public String getDateFirstEvent() {
        return dateFirstEvent;
    }

    public void setDateFirstEvent(String dateFirstEvent) {
        this.dateFirstEvent = dateFirstEvent;
    }

    public String getStrGender() {
        return strGender;
    }

    public void setStrGender(String strGender) {
        this.strGender = strGender;
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

    public String getStrDescriptionDE() {
        return strDescriptionDE;
    }

    public void setStrDescriptionDE(String strDescriptionDE) {
        this.strDescriptionDE = strDescriptionDE;
    }

    public String getStrDescriptionFR() {
        return strDescriptionFR;
    }

    public void setStrDescriptionFR(String strDescriptionFR) {
        this.strDescriptionFR = strDescriptionFR;
    }

    public String getStrDescriptionIT() {
        return strDescriptionIT;
    }

    public void setStrDescriptionIT(String strDescriptionIT) {
        this.strDescriptionIT = strDescriptionIT;
    }

    public String getStrDescriptionCN() {
        return strDescriptionCN;
    }

    public void setStrDescriptionCN(String strDescriptionCN) {
        this.strDescriptionCN = strDescriptionCN;
    }

    public String getStrDescriptionJP() {
        return strDescriptionJP;
    }

    public void setStrDescriptionJP(String strDescriptionJP) {
        this.strDescriptionJP = strDescriptionJP;
    }

    public String getStrDescriptionRU() {
        return strDescriptionRU;
    }

    public void setStrDescriptionRU(String strDescriptionRU) {
        this.strDescriptionRU = strDescriptionRU;
    }

    public String getStrDescriptionES() {
        return strDescriptionES;
    }

    public void setStrDescriptionES(String strDescriptionES) {
        this.strDescriptionES = strDescriptionES;
    }

    public String getStrDescriptionPT() {
        return strDescriptionPT;
    }

    public void setStrDescriptionPT(String strDescriptionPT) {
        this.strDescriptionPT = strDescriptionPT;
    }

    public String getStrDescriptionSE() {
        return strDescriptionSE;
    }

    public void setStrDescriptionSE(String strDescriptionSE) {
        this.strDescriptionSE = strDescriptionSE;
    }

    public String getStrDescriptionNL() {
        return strDescriptionNL;
    }

    public void setStrDescriptionNL(String strDescriptionNL) {
        this.strDescriptionNL = strDescriptionNL;
    }

    public String getStrDescriptionHU() {
        return strDescriptionHU;
    }

    public void setStrDescriptionHU(String strDescriptionHU) {
        this.strDescriptionHU = strDescriptionHU;
    }

    public String getStrDescriptionNO() {
        return strDescriptionNO;
    }

    public void setStrDescriptionNO(String strDescriptionNO) {
        this.strDescriptionNO = strDescriptionNO;
    }

    public String getStrDescriptionPL() {
        return strDescriptionPL;
    }

    public void setStrDescriptionPL(String strDescriptionPL) {
        this.strDescriptionPL = strDescriptionPL;
    }

    public String getStrFanart1() {
        return strFanart1;
    }

    public void setStrFanart1(String strFanart1) {
        this.strFanart1 = strFanart1;
    }

    public String getStrFanart2() {
        return strFanart2;
    }

    public void setStrFanart2(String strFanart2) {
        this.strFanart2 = strFanart2;
    }

    public String getStrFanart3() {
        return strFanart3;
    }

    public void setStrFanart3(String strFanart3) {
        this.strFanart3 = strFanart3;
    }

    public String getStrFanart4() {
        return strFanart4;
    }

    public void setStrFanart4(String strFanart4) {
        this.strFanart4 = strFanart4;
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

    public String getStrNaming() {
        return strNaming;
    }

    public void setStrNaming(String strNaming) {
        this.strNaming = strNaming;
    }

    public String getStrLocked() {
        return strLocked;
    }

    public void setStrLocked(String strLocked) {
        this.strLocked = strLocked;
    }

}

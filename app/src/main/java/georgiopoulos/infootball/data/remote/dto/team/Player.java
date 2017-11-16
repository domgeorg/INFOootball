package georgiopoulos.infootball.data.remote.dto.team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Player{

    @SerializedName("idPlayer")
    @Expose
    private String idPlayer;
    @SerializedName("idTeam")
    @Expose
    private String idTeam;
    @SerializedName("idSoccerXML")
    @Expose
    private String idSoccerXML;
    @SerializedName("idPlayerManager")
    @Expose
    private String idPlayerManager;
    @SerializedName("strNationality")
    @Expose
    private String strNationality;
    @SerializedName("strPlayer")
    @Expose
    private String strPlayer;
    @SerializedName("strTeam")
    @Expose
    private String strTeam;
    @SerializedName("strSport")
    @Expose
    private String strSport;
    @SerializedName("intSoccerXMLTeamID")
    @Expose
    private String intSoccerXMLTeamID;
    @SerializedName("dateBorn")
    @Expose
    private String dateBorn;
    @SerializedName("dateSigned")
    @Expose
    private String dateSigned;
    @SerializedName("strSigning")
    @Expose
    private String strSigning;
    @SerializedName("strWage")
    @Expose
    private String strWage;
    @SerializedName("strBirthLocation")
    @Expose
    private String strBirthLocation;
    @SerializedName("strDescriptionEN")
    @Expose
    private String strDescriptionEN;
    @SerializedName("strDescriptionDE")
    @Expose
    private Object strDescriptionDE;
    @SerializedName("strDescriptionFR")
    @Expose
    private Object strDescriptionFR;
    @SerializedName("strDescriptionCN")
    @Expose
    private Object strDescriptionCN;
    @SerializedName("strDescriptionIT")
    @Expose
    private Object strDescriptionIT;
    @SerializedName("strDescriptionJP")
    @Expose
    private Object strDescriptionJP;
    @SerializedName("strDescriptionRU")
    @Expose
    private Object strDescriptionRU;
    @SerializedName("strDescriptionES")
    @Expose
    private String strDescriptionES;
    @SerializedName("strDescriptionPT")
    @Expose
    private Object strDescriptionPT;
    @SerializedName("strDescriptionSE")
    @Expose
    private Object strDescriptionSE;
    @SerializedName("strDescriptionNL")
    @Expose
    private Object strDescriptionNL;
    @SerializedName("strDescriptionHU")
    @Expose
    private Object strDescriptionHU;
    @SerializedName("strDescriptionNO")
    @Expose
    private Object strDescriptionNO;
    @SerializedName("strDescriptionIL")
    @Expose
    private Object strDescriptionIL;
    @SerializedName("strDescriptionPL")
    @Expose
    private Object strDescriptionPL;
    @SerializedName("strGender")
    @Expose
    private String strGender;
    @SerializedName("strPosition")
    @Expose
    private String strPosition;
    @SerializedName("strCollege")
    @Expose
    private Object strCollege;
    @SerializedName("strFacebook")
    @Expose
    private String strFacebook;
    @SerializedName("strWebsite")
    @Expose
    private String strWebsite;
    @SerializedName("strTwitter")
    @Expose
    private String strTwitter;
    @SerializedName("strInstagram")
    @Expose
    private String strInstagram;
    @SerializedName("strYoutube")
    @Expose
    private String strYoutube;
    @SerializedName("strHeight")
    @Expose
    private String strHeight;
    @SerializedName("strWeight")
    @Expose
    private String strWeight;
    @SerializedName("intLoved")
    @Expose
    private String intLoved;
    @SerializedName("strThumb")
    @Expose
    private String strThumb;
    @SerializedName("strCutout")
    @Expose
    private String strCutout;
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
    @SerializedName("strLocked")
    @Expose
    private String strLocked;

    /**
     * No args constructor for use in serialization
     */
    public Player(){
    }

    /**
     * @param strLocked
     * @param strDescriptionIL
     * @param strDescriptionEN
     * @param strDescriptionRU
     * @param strTwitter
     * @param strWebsite
     * @param idPlayer
     * @param strDescriptionPL
     * @param strDescriptionCN
     * @param strWeight
     * @param strBirthLocation
     * @param strDescriptionNO
     * @param strPlayer
     * @param strDescriptionES
     * @param strDescriptionPT
     * @param strDescriptionNL
     * @param strSigning
     * @param strThumb
     * @param strHeight
     * @param strDescriptionSE
     * @param dateBorn
     * @param strCutout
     * @param idSoccerXML
     * @param strDescriptionIT
     * @param intSoccerXMLTeamID
     * @param strTeam
     * @param idPlayerManager
     * @param strWage
     * @param strSport
     * @param strCollege
     * @param dateSigned
     * @param intLoved
     * @param strNationality
     * @param strDescriptionJP
     * @param idTeam
     * @param strYoutube
     * @param strFacebook
     * @param strDescriptionHU
     * @param strGender
     * @param strDescriptionDE
     * @param strFanart4
     * @param strFanart3
     * @param strFanart2
     * @param strInstagram
     * @param strDescriptionFR
     * @param strFanart1
     * @param strPosition
     */
    public Player(String idPlayer,String idTeam,String idSoccerXML,String idPlayerManager,String strNationality,String strPlayer,String strTeam,String strSport,String intSoccerXMLTeamID,String dateBorn,String dateSigned,String strSigning,String strWage,String strBirthLocation,String strDescriptionEN,Object strDescriptionDE,Object strDescriptionFR,Object strDescriptionCN,Object strDescriptionIT,Object strDescriptionJP,Object strDescriptionRU,String strDescriptionES,Object strDescriptionPT,Object strDescriptionSE,Object strDescriptionNL,Object strDescriptionHU,Object strDescriptionNO,Object strDescriptionIL,Object strDescriptionPL,String strGender,String strPosition,Object strCollege,String strFacebook,String strWebsite,String strTwitter,String strInstagram,String strYoutube,String strHeight,String strWeight,String intLoved,String strThumb,String strCutout,String strFanart1,String strFanart2,String strFanart3,String strFanart4,String strLocked){
        super(); this.idPlayer = idPlayer; this.idTeam = idTeam; this.idSoccerXML = idSoccerXML;
        this.idPlayerManager = idPlayerManager; this.strNationality = strNationality;
        this.strPlayer = strPlayer; this.strTeam = strTeam; this.strSport = strSport;
        this.intSoccerXMLTeamID = intSoccerXMLTeamID; this.dateBorn = dateBorn;
        this.dateSigned = dateSigned; this.strSigning = strSigning; this.strWage = strWage;
        this.strBirthLocation = strBirthLocation; this.strDescriptionEN = strDescriptionEN;
        this.strDescriptionDE = strDescriptionDE; this.strDescriptionFR = strDescriptionFR;
        this.strDescriptionCN = strDescriptionCN; this.strDescriptionIT = strDescriptionIT;
        this.strDescriptionJP = strDescriptionJP; this.strDescriptionRU = strDescriptionRU;
        this.strDescriptionES = strDescriptionES; this.strDescriptionPT = strDescriptionPT;
        this.strDescriptionSE = strDescriptionSE; this.strDescriptionNL = strDescriptionNL;
        this.strDescriptionHU = strDescriptionHU; this.strDescriptionNO = strDescriptionNO;
        this.strDescriptionIL = strDescriptionIL; this.strDescriptionPL = strDescriptionPL;
        this.strGender = strGender; this.strPosition = strPosition; this.strCollege = strCollege;
        this.strFacebook = strFacebook; this.strWebsite = strWebsite; this.strTwitter = strTwitter;
        this.strInstagram = strInstagram; this.strYoutube = strYoutube; this.strHeight = strHeight;
        this.strWeight = strWeight; this.intLoved = intLoved; this.strThumb = strThumb;
        this.strCutout = strCutout; this.strFanart1 = strFanart1; this.strFanart2 = strFanart2;
        this.strFanart3 = strFanart3; this.strFanart4 = strFanart4; this.strLocked = strLocked;
    }

    public String getIdPlayer(){
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer){
        this.idPlayer = idPlayer;
    }

    public String getIdTeam(){
        return idTeam;
    }

    public void setIdTeam(String idTeam){
        this.idTeam = idTeam;
    }

    public String getIdSoccerXML(){
        return idSoccerXML;
    }

    public void setIdSoccerXML(String idSoccerXML){
        this.idSoccerXML = idSoccerXML;
    }

    public String getIdPlayerManager(){
        return idPlayerManager;
    }

    public void setIdPlayerManager(String idPlayerManager){
        this.idPlayerManager = idPlayerManager;
    }

    public String getStrNationality(){
        return strNationality;
    }

    public void setStrNationality(String strNationality){
        this.strNationality = strNationality;
    }

    public String getStrPlayer(){
        return strPlayer;
    }

    public void setStrPlayer(String strPlayer){
        this.strPlayer = strPlayer;
    }

    public String getStrTeam(){
        return strTeam;
    }

    public void setStrTeam(String strTeam){
        this.strTeam = strTeam;
    }

    public String getStrSport(){
        return strSport;
    }

    public void setStrSport(String strSport){
        this.strSport = strSport;
    }

    public String getIntSoccerXMLTeamID(){
        return intSoccerXMLTeamID;
    }

    public void setIntSoccerXMLTeamID(String intSoccerXMLTeamID){
        this.intSoccerXMLTeamID = intSoccerXMLTeamID;
    }

    public String getDateBorn(){
        return dateBorn;
    }

    public void setDateBorn(String dateBorn){
        this.dateBorn = dateBorn;
    }

    public String getDateSigned(){
        return dateSigned;
    }

    public void setDateSigned(String dateSigned){
        this.dateSigned = dateSigned;
    }

    public String getStrSigning(){
        return strSigning;
    }

    public void setStrSigning(String strSigning){
        this.strSigning = strSigning;
    }

    public String getStrWage(){
        return strWage;
    }

    public void setStrWage(String strWage){
        this.strWage = strWage;
    }

    public String getStrBirthLocation(){
        return strBirthLocation;
    }

    public void setStrBirthLocation(String strBirthLocation){
        this.strBirthLocation = strBirthLocation;
    }

    public String getStrDescriptionEN(){
        return strDescriptionEN;
    }

    public void setStrDescriptionEN(String strDescriptionEN){
        this.strDescriptionEN = strDescriptionEN;
    }

    public Object getStrDescriptionDE(){
        return strDescriptionDE;
    }

    public void setStrDescriptionDE(Object strDescriptionDE){
        this.strDescriptionDE = strDescriptionDE;
    }

    public Object getStrDescriptionFR(){
        return strDescriptionFR;
    }

    public void setStrDescriptionFR(Object strDescriptionFR){
        this.strDescriptionFR = strDescriptionFR;
    }

    public Object getStrDescriptionCN(){
        return strDescriptionCN;
    }

    public void setStrDescriptionCN(Object strDescriptionCN){
        this.strDescriptionCN = strDescriptionCN;
    }

    public Object getStrDescriptionIT(){
        return strDescriptionIT;
    }

    public void setStrDescriptionIT(Object strDescriptionIT){
        this.strDescriptionIT = strDescriptionIT;
    }

    public Object getStrDescriptionJP(){
        return strDescriptionJP;
    }

    public void setStrDescriptionJP(Object strDescriptionJP){
        this.strDescriptionJP = strDescriptionJP;
    }

    public Object getStrDescriptionRU(){
        return strDescriptionRU;
    }

    public void setStrDescriptionRU(Object strDescriptionRU){
        this.strDescriptionRU = strDescriptionRU;
    }

    public String getStrDescriptionES(){
        return strDescriptionES;
    }

    public void setStrDescriptionES(String strDescriptionES){
        this.strDescriptionES = strDescriptionES;
    }

    public Object getStrDescriptionPT(){
        return strDescriptionPT;
    }

    public void setStrDescriptionPT(Object strDescriptionPT){
        this.strDescriptionPT = strDescriptionPT;
    }

    public Object getStrDescriptionSE(){
        return strDescriptionSE;
    }

    public void setStrDescriptionSE(Object strDescriptionSE){
        this.strDescriptionSE = strDescriptionSE;
    }

    public Object getStrDescriptionNL(){
        return strDescriptionNL;
    }

    public void setStrDescriptionNL(Object strDescriptionNL){
        this.strDescriptionNL = strDescriptionNL;
    }

    public Object getStrDescriptionHU(){
        return strDescriptionHU;
    }

    public void setStrDescriptionHU(Object strDescriptionHU){
        this.strDescriptionHU = strDescriptionHU;
    }

    public Object getStrDescriptionNO(){
        return strDescriptionNO;
    }

    public void setStrDescriptionNO(Object strDescriptionNO){
        this.strDescriptionNO = strDescriptionNO;
    }

    public Object getStrDescriptionIL(){
        return strDescriptionIL;
    }

    public void setStrDescriptionIL(Object strDescriptionIL){
        this.strDescriptionIL = strDescriptionIL;
    }

    public Object getStrDescriptionPL(){
        return strDescriptionPL;
    }

    public void setStrDescriptionPL(Object strDescriptionPL){
        this.strDescriptionPL = strDescriptionPL;
    }

    public String getStrGender(){
        return strGender;
    }

    public void setStrGender(String strGender){
        this.strGender = strGender;
    }

    public String getStrPosition(){
        return strPosition;
    }

    public void setStrPosition(String strPosition){
        this.strPosition = strPosition;
    }

    public Object getStrCollege(){
        return strCollege;
    }

    public void setStrCollege(Object strCollege){
        this.strCollege = strCollege;
    }

    public String getStrFacebook(){
        return strFacebook;
    }

    public void setStrFacebook(String strFacebook){
        this.strFacebook = strFacebook;
    }

    public String getStrWebsite(){
        return strWebsite;
    }

    public void setStrWebsite(String strWebsite){
        this.strWebsite = strWebsite;
    }

    public String getStrTwitter(){
        return strTwitter;
    }

    public void setStrTwitter(String strTwitter){
        this.strTwitter = strTwitter;
    }

    public String getStrInstagram(){
        return strInstagram;
    }

    public void setStrInstagram(String strInstagram){
        this.strInstagram = strInstagram;
    }

    public String getStrYoutube(){
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube){
        this.strYoutube = strYoutube;
    }

    public String getStrHeight(){
        return strHeight;
    }

    public void setStrHeight(String strHeight){
        this.strHeight = strHeight;
    }

    public String getStrWeight(){
        return strWeight;
    }

    public void setStrWeight(String strWeight){
        this.strWeight = strWeight;
    }

    public String getIntLoved(){
        return intLoved;
    }

    public void setIntLoved(String intLoved){
        this.intLoved = intLoved;
    }

    public String getStrThumb(){
        return strThumb;
    }

    public void setStrThumb(String strThumb){
        this.strThumb = strThumb;
    }

    public String getStrCutout(){
        return strCutout;
    }

    public void setStrCutout(String strCutout){
        this.strCutout = strCutout;
    }

    public String getStrFanart1(){
        return strFanart1;
    }

    public void setStrFanart1(String strFanart1){
        this.strFanart1 = strFanart1;
    }

    public String getStrFanart2(){
        return strFanart2;
    }

    public void setStrFanart2(String strFanart2){
        this.strFanart2 = strFanart2;
    }

    public String getStrFanart3(){
        return strFanart3;
    }

    public void setStrFanart3(String strFanart3){
        this.strFanart3 = strFanart3;
    }

    public String getStrFanart4(){
        return strFanart4;
    }

    public void setStrFanart4(String strFanart4){
        this.strFanart4 = strFanart4;
    }

    public String getStrLocked(){
        return strLocked;
    }

    public void setStrLocked(String strLocked){
        this.strLocked = strLocked;
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(strLocked).append(strDescriptionIL).append(strDescriptionEN).append(strDescriptionRU).append(strTwitter).append(strWebsite).append(idPlayer).append(strDescriptionPL).append(strDescriptionCN).append(strWeight).append(strBirthLocation).append(strDescriptionNO).append(strDescriptionPT).append(strDescriptionES).append(strPlayer).append(strDescriptionNL).append(strSigning).append(strThumb).append(strHeight).append(strDescriptionSE).append(dateBorn).append(strCutout).append(idSoccerXML).append(strDescriptionIT).append(intSoccerXMLTeamID).append(strTeam).append(idPlayerManager).append(strWage).append(strSport).append(strCollege).append(dateSigned).append(intLoved).append(strDescriptionJP).append(strNationality).append(idTeam).append(strYoutube).append(strFacebook).append(strDescriptionHU).append(strGender).append(strDescriptionDE).append(strFanart4).append(strFanart3).append(strFanart2).append(strInstagram).append(strDescriptionFR).append(strFanart1).append(strPosition).toHashCode();
    }

    @Override
    public boolean equals(Object other){
        if(other == this){
            return true;
        } if((other instanceof Player) == false){
            return false;
        } Player rhs = ((Player)other);
        return new EqualsBuilder().append(strLocked,rhs.strLocked).append(strDescriptionIL,rhs.strDescriptionIL).append(strDescriptionEN,rhs.strDescriptionEN).append(strDescriptionRU,rhs.strDescriptionRU).append(strTwitter,rhs.strTwitter).append(strWebsite,rhs.strWebsite).append(idPlayer,rhs.idPlayer).append(strDescriptionPL,rhs.strDescriptionPL).append(strDescriptionCN,rhs.strDescriptionCN).append(strWeight,rhs.strWeight).append(strBirthLocation,rhs.strBirthLocation).append(strDescriptionNO,rhs.strDescriptionNO).append(strDescriptionPT,rhs.strDescriptionPT).append(strDescriptionES,rhs.strDescriptionES).append(strPlayer,rhs.strPlayer).append(strDescriptionNL,rhs.strDescriptionNL).append(strSigning,rhs.strSigning).append(strThumb,rhs.strThumb).append(strHeight,rhs.strHeight).append(strDescriptionSE,rhs.strDescriptionSE).append(dateBorn,rhs.dateBorn).append(strCutout,rhs.strCutout).append(idSoccerXML,rhs.idSoccerXML).append(strDescriptionIT,rhs.strDescriptionIT).append(intSoccerXMLTeamID,rhs.intSoccerXMLTeamID).append(strTeam,rhs.strTeam).append(idPlayerManager,rhs.idPlayerManager).append(strWage,rhs.strWage).append(strSport,rhs.strSport).append(strCollege,rhs.strCollege).append(dateSigned,rhs.dateSigned).append(intLoved,rhs.intLoved).append(strDescriptionJP,rhs.strDescriptionJP).append(strNationality,rhs.strNationality).append(idTeam,rhs.idTeam).append(strYoutube,rhs.strYoutube).append(strFacebook,rhs.strFacebook).append(strDescriptionHU,rhs.strDescriptionHU).append(strGender,rhs.strGender).append(strDescriptionDE,rhs.strDescriptionDE).append(strFanart4,rhs.strFanart4).append(strFanart3,rhs.strFanart3).append(strFanart2,rhs.strFanart2).append(strInstagram,rhs.strInstagram).append(strDescriptionFR,rhs.strDescriptionFR).append(strFanart1,rhs.strFanart1).append(strPosition,rhs.strPosition).isEquals();
    }

}

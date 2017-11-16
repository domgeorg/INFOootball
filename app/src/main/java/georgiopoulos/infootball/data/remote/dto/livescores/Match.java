/**
 * Copyright 2017 georgiopoulos kyriakos
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package georgiopoulos.infootball.data.remote.dto.livescores;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Match{

    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("League")
    @Expose
    private String league;
    @SerializedName("Round")
    @Expose
    private String round;
    @SerializedName("HomeTeam")
    @Expose
    private String homeTeam;
    @SerializedName("HomeTeam_Id")
    @Expose
    private String homeTeamId;
    @SerializedName("AwayTeam")
    @Expose
    private String awayTeam;
    @SerializedName("AwayTeam_Id")
    @Expose
    private String awayTeamId;
    @SerializedName("Time")
    @Expose
    private String time;
    @SerializedName("HomeGoals")
    @Expose
    private String homeGoals;
    @SerializedName("AwayGoals")
    @Expose
    private String awayGoals;
    @SerializedName("Location")
    @Expose
    private String location;

    /**
     * No args constructor for use in serialization
     *
     */
    public Match(){
    }

    /**
     *
     * @param homeGoals
     * @param time
     * @param awayTeam
     * @param awayTeamId
     * @param location
     * @param awayGoals
     * @param homeTeamId
     * @param round
     * @param league
     * @param date
     * @param homeTeam
     */
    public Match(String date,String league,String round,String homeTeam,String homeTeamId,String awayTeam,String awayTeamId,String time,String homeGoals,String awayGoals,String location){
        super(); this.date = date; this.league = league; this.round = round;
        this.homeTeam = homeTeam; this.homeTeamId = homeTeamId; this.awayTeam = awayTeam;
        this.awayTeamId = awayTeamId; this.time = time; this.homeGoals = homeGoals;
        this.awayGoals = awayGoals; this.location = location;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getLeague(){
        return league;
    }

    public void setLeague(String league){
        this.league = league;
    }

    public String getRound(){
        return round;
    }

    public void setRound(String round){
        this.round = round;
    }

    public String getHomeTeam(){
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam){
        this.homeTeam = homeTeam;
    }

    public String getHomeTeamId(){
        return homeTeamId;
    }

    public void setHomeTeamId(String homeTeamId){
        this.homeTeamId = homeTeamId;
    }

    public String getAwayTeam(){
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam){
        this.awayTeam = awayTeam;
    }

    public String getAwayTeamId(){
        return awayTeamId;
    }

    public void setAwayTeamId(String awayTeamId){
        this.awayTeamId = awayTeamId;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getHomeGoals(){
        return homeGoals;
    }

    public void setHomeGoals(String homeGoals){
        this.homeGoals = homeGoals;
    }

    public String getAwayGoals(){
        return awayGoals;
    }

    public void setAwayGoals(String awayGoals){
        this.awayGoals = awayGoals;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this).append("date",date).append("league",league).append("round",round).append("homeTeam",homeTeam).append("homeTeamId",homeTeamId).append("awayTeam",awayTeam).append("awayTeamId",awayTeamId).append("time",time).append("homeGoals",homeGoals).append("awayGoals",awayGoals).append("location",location).toString();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(homeGoals).append(time).append(awayTeam).append(awayTeamId).append(location).append(awayGoals).append(homeTeamId).append(round).append(league).append(date).append(homeTeam).toHashCode();
    }

    @Override
    public boolean equals(Object other){
        if(other == this){
            return true;
        } if((other instanceof Match) == false){
            return false;
        } Match rhs = ((Match)other);
        return new EqualsBuilder().append(homeGoals,rhs.homeGoals).append(time,rhs.time).append(awayTeam,rhs.awayTeam).append(awayTeamId,rhs.awayTeamId).append(location,rhs.location).append(awayGoals,rhs.awayGoals).append(homeTeamId,rhs.homeTeamId).append(round,rhs.round).append(league,rhs.league).append(date,rhs.date).append(homeTeam,rhs.homeTeam).isEquals();
    }

}

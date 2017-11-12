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
package georgiopoulos.infootball.data.remote.dto.league;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("teamid")
    @Expose
    private String teamid;
    @SerializedName("played")
    @Expose
    private int played;
    @SerializedName("goalsfor")
    @Expose
    private int goalsfor;
    @SerializedName("goalsagainst")
    @Expose
    private int goalsagainst;
    @SerializedName("goalsdifference")
    @Expose
    private int goalsdifference;
    @SerializedName("win")
    @Expose
    private int win;
    @SerializedName("draw")
    @Expose
    private int draw;
    @SerializedName("loss")
    @Expose
    private int loss;
    @SerializedName("total")
    @Expose
    private int total;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Table() {
    }

    /**
     * 
     * @param total
     * @param draw
     * @param goalsfor
     * @param teamid
     * @param loss
     * @param played
     * @param name
     * @param win
     * @param goalsdifference
     * @param goalsagainst
     */
    public Table(String name, String teamid, int played, int goalsfor, int goalsagainst, int goalsdifference, int win, int draw, int loss, int total) {
        super();
        this.name = name;
        this.teamid = teamid;
        this.played = played;
        this.goalsfor = goalsfor;
        this.goalsagainst = goalsagainst;
        this.goalsdifference = goalsdifference;
        this.win = win;
        this.draw = draw;
        this.loss = loss;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getGoalsfor() {
        return goalsfor;
    }

    public void setGoalsfor(int goalsfor) {
        this.goalsfor = goalsfor;
    }

    public int getGoalsagainst() {
        return goalsagainst;
    }

    public void setGoalsagainst(int goalsagainst) {
        this.goalsagainst = goalsagainst;
    }

    public int getGoalsdifference() {
        return goalsdifference;
    }

    public void setGoalsdifference(int goalsdifference) {
        this.goalsdifference = goalsdifference;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}

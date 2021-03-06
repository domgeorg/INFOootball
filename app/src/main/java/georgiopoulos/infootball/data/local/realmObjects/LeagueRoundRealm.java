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
package georgiopoulos.infootball.data.local.realmObjects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LeagueRoundRealm extends RealmObject{

    @PrimaryKey
    private String leagueId;
    private int round;

    public LeagueRoundRealm(){

    }

    public LeagueRoundRealm(String leagueId, int round){
        this.leagueId=leagueId;
        this.round=round;
    }

    public void setLeagueId(String leagueId){
        this.leagueId=leagueId;
    }

    public String getLeagueId(){return this.leagueId;}

    public void setRound(int round){
        this.round = round;
    }

    public int getRound(){
        return round;
    }
}

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
package georgiopoulos.infootball.data.remote.dto.liveScores;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class LiveScores{

    @SerializedName("teams")
    @Expose
    private Teams teams;

    /**
     * No args constructor for use in serialization
     *
     */
    public LiveScores(){
    }

    /**
     *
     * @param teams
     */
    public LiveScores(Teams teams){
        super(); this.teams = teams;
    }

    public Teams getTeams(){
        return teams;
    }

    public void setTeams(Teams teams){
        this.teams = teams;
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this).append("teams",teams).toString();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(teams).toHashCode();
    }

    @Override
    public boolean equals(Object other){
        if(other == this){
            return true;
        } if((other instanceof LiveScores) == false){
            return false;
        } LiveScores rhs = ((LiveScores)other);
        return new EqualsBuilder().append(teams,rhs.teams).isEquals();
    }

}

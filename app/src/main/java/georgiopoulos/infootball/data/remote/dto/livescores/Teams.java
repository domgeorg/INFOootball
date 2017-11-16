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

import java.util.List;

public class Teams{

    @SerializedName("Match")
    @Expose
    private List<Match> match = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Teams(){
    }

    /**
     *
     * @param match
     */
    public Teams(List<Match> match){
        super(); this.match = match;
    }

    public List<Match> getMatch(){
        return match;
    }

    public void setMatch(List<Match> match){
        this.match = match;
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this).append("match",match).toString();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(match).toHashCode();
    }

    @Override
    public boolean equals(Object other){
        if(other == this){
            return true;
        } if((other instanceof Teams) == false){
            return false;
        } Teams rhs = ((Teams)other); return new EqualsBuilder().append(match,rhs.match).isEquals();
    }

}

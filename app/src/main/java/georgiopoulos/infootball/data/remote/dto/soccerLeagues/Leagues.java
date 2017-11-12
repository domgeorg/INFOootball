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
package georgiopoulos.infootball.data.remote.dto.soccerLeagues;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import georgiopoulos.infootball.data.remote.dto.soccerLeagues.Country;

public class Leagues {

    @SerializedName("countrys")
    @Expose
    private List<Country> countrys = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Leagues() {
    }

    /**
     * 
     * @param countrys
     */
    public Leagues(List<Country> countrys) {
        super();
        this.countrys = countrys;
    }

    public List<Country> getCountrys() {
        return countrys;
    }

    public void setCountrys(List<Country> countrys) {
        this.countrys = countrys;
    }

}

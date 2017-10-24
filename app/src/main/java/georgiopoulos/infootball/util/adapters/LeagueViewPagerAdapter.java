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
package georgiopoulos.infootball.util.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import georgiopoulos.infootball.ui.League.LatestEvents.LatestEventsFragment;

public class LeagueViewPagerAdapter extends FragmentPagerAdapter{

    private String leagueId;

    public LeagueViewPagerAdapter(FragmentManager fragmentManager, String leagueId){
        super(fragmentManager);
        this.leagueId=leagueId;
    }

    @Override public Fragment getItem(int position){
        switch(position){
            case 1: return LatestEventsFragment.create(leagueId);
            default: return LatestEventsFragment.create(leagueId);
        }
    }

    @Override public CharSequence getPageTitle(int position){
        switch(position){
            case 1: return "Last Events";
            default: return "Last Events";
        }
    }

    @Override public int getCount(){
        return 1;
    }
}

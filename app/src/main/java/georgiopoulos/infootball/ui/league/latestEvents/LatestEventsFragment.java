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
package georgiopoulos.infootball.ui.league.latestEvents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.events.Event;
import georgiopoulos.infootball.ui.base.BaseFragment;
import georgiopoulos.infootball.util.adapters.EventViewHolder;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import georgiopoulos.infootball.util.adapters.base.SimpleListAdapter;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LatestEventsPresenter.class)
public class LatestEventsFragment extends BaseFragment<Event,LatestEventsPresenter>{

    public static LatestEventsFragment create(String leagueId){
        Bundle bundle = new Bundle();
        bundle.putString("leagueId", leagueId);
        LatestEventsFragment fragment = new LatestEventsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view,Bundle bundle){
        setAdapter(new SimpleListAdapter<>(R.layout.progress_bar,new ClassViewHolderType<>(Event.class,R.layout.card_event,v -> new EventViewHolder<>(v,this::onItemClick))));
        super.onViewCreated(view,bundle);
        if(bundle==null){
            onRefresh();
            getPresenter().requestLatestEvents(getArguments().getString("leagueId"));
        }
    }

    protected void onEvents(@Nullable List<Event> list){
        if(isNullOrEmpty(list)) displayError(R.string.latest_events_fragment_error_message,R.drawable.ic_error_fragment_latest_events);
        else displayList(list,R.anim.layout_animation_fall_down);
    }

    @Override
    public void onItemClick(Event event){}
}

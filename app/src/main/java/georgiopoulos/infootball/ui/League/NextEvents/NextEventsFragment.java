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
package georgiopoulos.infootball.ui.League.NextEvents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.league.Event;
import georgiopoulos.infootball.data.remote.dto.league.Events;
import georgiopoulos.infootball.ui.Base.LoadingContentErrorFragment;
import georgiopoulos.infootball.util.adapters.NextEventViewHolder;
import georgiopoulos.infootball.util.adapters.SimpleListAdapter;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(NextEventsPresenter.class)
public class NextEventsFragment extends LoadingContentErrorFragment<Event,NextEventsPresenter>{

    protected String leagueId;

    public static NextEventsFragment create(String leagueId){
        Bundle bundle = new Bundle();
        bundle.putString("leagueId",leagueId);
        NextEventsFragment fragment = new NextEventsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view,Bundle bundle){
        leagueId = getArguments().getString("leagueId");
        setAdapter(new SimpleListAdapter<>(R.layout.view_loading,new ClassViewHolderType<>(Event.class,R.layout.card_next_events,v -> new NextEventViewHolder<>(v,this::onItemClick))));
        super.onViewCreated(view,bundle); this.onRefresh();
    }

    @Override
    public void onRefresh(){
        super.onRefresh(); getPresenter().request(leagueId);
    }

    protected void onEvents(@Nullable Events events){
        onResults(events.getEvents(),getString(R.string.next_events_fragment_error_message),R.drawable.ic_error_next_events_fragment,R.anim.layout_animation_from_right);
    }

    @Override
    public void onItemClick(Event event){}
}

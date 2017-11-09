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
package georgiopoulos.infootball.ui.League.NextEvents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.Event;
import georgiopoulos.infootball.data.remote.dto.Events;
import georgiopoulos.infootball.ui.Base.BaseFragment;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import georgiopoulos.infootball.util.adapters.NextEventViewHolder;
import georgiopoulos.infootball.util.adapters.SimpleListAdapter;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(NextEventsPresenter.class)
public class NextEventsFragment extends BaseFragment<NextEventsPresenter>{

    @BindView(R.id.next_event_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.recycler_view_header) RecyclerViewHeader recyclerViewHeader;
    @BindView(R.id.leagueTrophy) ImageView trophyImageView;
    private SimpleListAdapter<Event> adapter;

    public static NextEventsFragment create(String leagueId,String trophy){
        Bundle bundle = new Bundle();
        bundle.putString("leagueId",leagueId);
        bundle.putString("trophy",trophy);
        NextEventsFragment fragment = new NextEventsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(R.layout.view_recycler_next_events_with_header,container,false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        adapter = new SimpleListAdapter<>(R.layout.view_loading,new ClassViewHolderType<>(Event.class,R.layout.card_next_events,v -> new NextEventViewHolder<>(v,this::onItemClick)));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerViewHeader.attachTo(recyclerView);
        Picasso.with(getContext()).load(getArguments().getString("trophy")).into(trophyImageView);
        getPresenter().request(getArguments().getString("leagueId"));
        adapter.showProgress();
    }

    void onEvents(@Nullable Events events){
        adapter.hideProgress();
        if(events.getEvents() == null) newsFlash("Server does not provide info about next events",recyclerView);
        else{
            runLayoutAnimation(recyclerView);
            adapter.set(events.getEvents());
        }
    }

    void onNetworkError(Throwable throwable){
        adapter.hideProgress();
        newsFlash(throwable.getMessage(),recyclerView);
    }

    void onItemClick(Event event){
       newsFlash(event.getStrFilename(),recyclerView);}
}

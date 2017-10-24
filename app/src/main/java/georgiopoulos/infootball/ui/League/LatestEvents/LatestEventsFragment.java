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
package georgiopoulos.infootball.ui.League.LatestEvents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import butterknife.BindView;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.Event;
import georgiopoulos.infootball.data.remote.dto.Events;
import georgiopoulos.infootball.ui.Base.BaseFragment;
import georgiopoulos.infootball.util.adapters.ClassViewHolderType;
import georgiopoulos.infootball.util.adapters.LatestEventViewHolder;
import georgiopoulos.infootball.util.adapters.SimpleListAdapter;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LatestEventsPresenter.class)
public class LatestEventsFragment extends BaseFragment<LatestEventsPresenter>{

    @BindView(R.id.league_recycler_view) RecyclerView recyclerView;
    private SimpleListAdapter<Event> adapter;

    public static LatestEventsFragment create(String leagueId){
        Bundle bundle = new Bundle();
        bundle.putString("leagueId", leagueId);
        LatestEventsFragment fragment = new LatestEventsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override public void onCreate(Bundle savedState){
        super.onCreate(savedState);
        getPresenter().request(getArguments().getString("leagueId"));
    }

    @Override public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(R.layout.recycler_view,container,false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        adapter = new SimpleListAdapter<>(R.layout.loading_view, new ClassViewHolderType<>(Event.class,R.layout.latest_events_card,v -> new LatestEventViewHolder<>(v,this::onItemClick)));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.showProgress();
    }

    void onEvents(@Nullable Events events){
        adapter.hideProgress();
        if (events.getEvents()==null) new SuperToast(getActivity()).setText("Server does not provide info about latest events").setTextSize(R.dimen.toastTextSize).setTextColor(PaletteUtils.getSolidColor(PaletteUtils.WHITE)).setDuration(Style.DURATION_SHORT).setFrame(Style.FRAME_STANDARD).setColor(getResources().getColor(R.color.colorAccent)).setAnimations(Style.ANIMATIONS_SCALE).show();
        else adapter.set(events.getEvents());
    }

    void onNetworkError(Throwable throwable){
        new SuperToast(getActivity()).setText(throwable.getMessage()).setTextSize(R.dimen.toastTextSize).setTextColor(PaletteUtils.getSolidColor(PaletteUtils.WHITE)).setDuration(Style.DURATION_SHORT).setFrame(Style.FRAME_STANDARD).setColor(getResources().getColor(R.color.colorAccent)).setAnimations(Style.ANIMATIONS_SCALE).show();
    }

    void onItemClick(Event event){
        new SuperToast(getActivity()).setText(event.getStrFilename()).setTextSize(R.dimen.toastTextSize).setTextColor(PaletteUtils.getSolidColor(PaletteUtils.WHITE)).setDuration(Style.DURATION_SHORT).setFrame(Style.FRAME_STANDARD).setColor(getResources().getColor(R.color.colorAccent)).setAnimations(Style.ANIMATIONS_SCALE).show();
    }
}

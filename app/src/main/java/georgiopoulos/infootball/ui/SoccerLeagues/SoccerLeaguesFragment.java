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
package georgiopoulos.infootball.ui.SoccerLeagues;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import georgiopoulos.infootball.data.remote.dto.Country;
import georgiopoulos.infootball.data.remote.dto.Leagues;
import georgiopoulos.infootball.ui.Base.BaseFragment;
import georgiopoulos.infootball.ui.LeagueTable.LeagueTablePresenter;
import georgiopoulos.infootball.util.adapters.ClassViewHolderType;
import georgiopoulos.infootball.util.adapters.SimpleListAdapter;
import georgiopoulos.infootball.util.adapters.SoccerLeagueViewHolder;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(SoccerLeaguesPresenter.class)
public class SoccerLeaguesFragment extends BaseFragment<SoccerLeaguesPresenter>{

    @BindView(R.id.recycler_view_soccer_leagues) RecyclerView recyclerView;
    private SimpleListAdapter<Country> adapter;

    @Override public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
         return inflater.inflate(R.layout.soccer_leagues_fragment, container,false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        adapter = new SimpleListAdapter<>(R.layout.loading_view, new ClassViewHolderType<>(Country.class,R.layout.soccer_league_card,v -> new SoccerLeagueViewHolder<>(v,this::onItemClick)));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.showProgress();
    }

    void onLeagues(Leagues leagues){
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.action_bar_title_soccer_leagues);
        adapter.hideProgress();
        adapter.set(leagues.getCountrys());
    }

    void onNetworkError(Throwable throwable){
        adapter.hideProgress();
        new SuperToast(getActivity()).setText(throwable.getMessage()).setTextSize(R.dimen.toastTextSize).setTextColor(PaletteUtils.getSolidColor(PaletteUtils.WHITE)).setDuration(Style.DURATION_SHORT).setFrame(Style.FRAME_STANDARD).setColor(getResources().getColor(R.color.colorAccent)).setAnimations(Style.ANIMATIONS_SCALE).show();
    }

    private void onItemClick(Country country) {
        //((MainActivity) getActivity()).push(ItemFragment.create(item.id, itemsName));
        new SuperToast(getActivity()).setText(country.getIdLeague()).setTextSize(R.dimen.toastTextSize).setTextColor(PaletteUtils.getSolidColor(PaletteUtils.WHITE)).setDuration(Style.DURATION_SHORT).setFrame(Style.FRAME_STANDARD).setColor(getResources().getColor(R.color.colorAccent)).setAnimations(Style.ANIMATIONS_SCALE).show();
    }

}

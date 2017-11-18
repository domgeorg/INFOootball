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

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.soccerLeagues.Country;
import georgiopoulos.infootball.data.remote.dto.soccerLeagues.Leagues;
import georgiopoulos.infootball.ui.Base.BaseActivity;
import georgiopoulos.infootball.ui.League.LeagueActivity;
import georgiopoulos.infootball.ui.LiveScores.LiveScoresActivity;
import georgiopoulos.infootball.util.adapters.SimpleListAdapter;
import georgiopoulos.infootball.util.adapters.SoccerLeagueViewHolder;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import icepick.State;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LeaguesPresenter.class)
public class SoccerLeaguesActivity extends BaseActivity<LeaguesPresenter> implements AppBarLayout
                                                                                             .OnOffsetChangedListener, FloatingActionButton.OnClickListener{

    @BindView(R.id.activity_soccer_leagues_coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.activity_soccer_leagues_app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.activity_soccer_leagues_header_image)
    ImageView headerImageView;
    @BindView(R.id.activity_soccer_leagues_toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_soccer_leagues_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.activity_soccer_leagues_floating_action_button)
    FloatingActionButton floatingActionButton;
    @State boolean isShow = false;
    private SimpleListAdapter<Country> adapter;
    private int scrollRange;

    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soccer_leagues);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null) getSupportActionBar().setTitle(R.string.action_bar_title_soccer_leagues);
        Picasso.with(this).load(R.drawable.grass).into(headerImageView);
        floatingActionButton.setOnClickListener(this);
        appBarLayout.addOnOffsetChangedListener(this);
        scrollRange = -1;
        appBarLayout.setExpanded(!isShow);
        adapter = new SimpleListAdapter<>(R.layout.view_loading,new ClassViewHolderType<>(Country.class,R.layout.card_soccer_league,v -> new SoccerLeagueViewHolder<>(v,this::onItemClick)));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.showProgress();
    }

    void onLeagues(Leagues leagues){
        adapter.hideProgress();
        if(leagues!=null){
            runLayoutAnimation(recyclerView,R.anim.layout_animation_from_right);
            adapter.set(leagues.getCountrys());
        }
        else newsFlash("Server does not provide data right now...try again later",coordinatorLayout);
    }

    void onNetworkError(Throwable throwable){
        adapter.hideProgress();
        newsFlash(throwable.getMessage(),coordinatorLayout);
    }

    private void onItemClick(Country country){
        startActivity(new Intent(this, LeagueActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).putExtra("leagueId",country.getIdLeague()).putExtra("leagueBadge",country.getStrBadge()).putExtra("league",country.getStrLeague()).putExtra("trophy",country.getStrTrophy()));
    }

    @Override public void onOffsetChanged(AppBarLayout appBarLayout,int verticalOffset){
        if (scrollRange == -1) scrollRange = appBarLayout.getTotalScrollRange();
        isShow=(Math.abs(scrollRange+verticalOffset)<10);
    }

    @Override public void onClick(View view){
        startActivity(new Intent(this, LiveScoresActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}

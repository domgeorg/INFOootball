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
package georgiopoulos.infootball.ui.League;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import eu.long1.spacetablayout.SpaceTabLayout;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.team.TeamsDetails;
import georgiopoulos.infootball.ui.Base.BaseActivity;
import georgiopoulos.infootball.ui.League.LatestEvents.LatestEventsFragment;
import georgiopoulos.infootball.ui.League.LeagueTable.LeagueTableFragment;
import georgiopoulos.infootball.ui.League.NextEvents.NextEventsFragment;
import icepick.State;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LeaguePresenter.class)
public class LeagueActivity extends BaseActivity<LeaguePresenter> implements AppBarLayout.OnOffsetChangedListener{


    @BindView(R.id.activity_league_coordinator_layout) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.activity_league_app_bar_layout) AppBarLayout appBarLayout;
    @BindView(R.id.activity_league_collapsing_toolbar_layout) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.activity_league_header_image) ImageView headerImageView;
    @BindView(R.id.activity_league_toolbar) Toolbar toolbar;
    @BindView(R.id.activity_league_view_pager) ViewPager viewPager;
    @BindView(R.id.activity_league_tab_layout) SpaceTabLayout tabLayout;
    @BindDrawable(R.drawable.ic_arrow_primary_color_24dp) Drawable primaryColorArrow;
    @BindDrawable(R.drawable.ic_arrow_white_24dp) Drawable whiteColorArrow;
    @BindColor(R.color.colorPrimary) int colorPrimary;
    @State String league;
    @State String trophy;
    @State boolean isShow = false;
    private int scrollRange;

    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league);
        ButterKnife.bind(this);

        league=getIntent().getStringExtra("league");
        trophy=getIntent().getStringExtra("trophy");

        setSupportActionBar(toolbar);
        appBarLayout.addOnOffsetChangedListener(this);
        if (getSupportActionBar()!=null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        scrollRange = -1;
        collapsingToolbarLayout.setTitle(" ");
        appBarLayout.setExpanded(!isShow);
        if(!isShow)getSupportActionBar().setHomeAsUpIndicator(primaryColorArrow);
        else getSupportActionBar().setHomeAsUpIndicator(whiteColorArrow);
        Picasso.with(this).load(getIntent().getStringExtra("leagueBadge")).into(headerImageView);

        getPresenter().request(getIntent().getStringExtra("leagueId"));

        tabLayout.setTabColor(colorPrimary);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(LatestEventsFragment.create(getIntent().getStringExtra("leagueId")));
        fragmentList.add(LeagueTableFragment.create(getIntent().getStringExtra("leagueId")));
        fragmentList.add(NextEventsFragment.create(getIntent().getStringExtra("leagueId"),trophy));
        tabLayout.initialize(viewPager,getSupportFragmentManager(),fragmentList,savedInstanceState);
    }

    void onTeams(TeamsDetails teamsDetails){}

    void onNetworkError(Throwable throwable){
        newsFlash(throwable.getMessage(),collapsingToolbarLayout);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_league_table,menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset){
        if (scrollRange == -1) scrollRange = appBarLayout.getTotalScrollRange();
        if (Math.abs(scrollRange+verticalOffset)<10){
            collapsingToolbarLayout.setTitle(league);
            getSupportActionBar().setHomeAsUpIndicator(whiteColorArrow);
            isShow=true;
        }else if (isShow){
            collapsingToolbarLayout.setTitle("");
            getSupportActionBar().setHomeAsUpIndicator(primaryColorArrow);
            isShow=false;
        }
    }

    @Override public void onSaveInstanceState(Bundle bundle){
        tabLayout.saveState(bundle);
        super.onSaveInstanceState(bundle);
    }

}

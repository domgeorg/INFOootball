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

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.long1.spacetablayout.SpaceTabLayout;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.TeamsDetails;
import georgiopoulos.infootball.ui.Base.BaseActivity;
import georgiopoulos.infootball.ui.League.LatestEvents.LatestEventsFragment;
import georgiopoulos.infootball.ui.League.LeagueTable.LeagueTableFragment;
import georgiopoulos.infootball.ui.League.NextEvents.NextEventsFragment;
import icepick.Icepick;
import icepick.State;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LeaguePresenter.class)
public class LeagueActivity extends BaseActivity<LeaguePresenter> implements AppBarLayout.OnOffsetChangedListener{

    private String callbackId;
    private String league;
    private String trophy;
    private boolean isShow = false;
    private int scrollRange = -1;
    @BindView(R.id.app_bar_league) AppBarLayout appBarLayout;
    @BindView(R.id.collapsing_toolbar_layout_league) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar_league) Toolbar toolbar;
    @BindView(R.id.header_image_league_logo) ImageView headerImageView;
    @BindView(R.id.view_pager_league) ViewPager viewPager;
    @BindView(R.id.tab_layout_league) SpaceTabLayout tabLayout;

    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.league_activity);
        ButterKnife.bind(this);

        league=getIntent().getStringExtra("league");
        trophy=getIntent().getStringExtra("trophy");

        setSupportActionBar(toolbar);
        appBarLayout.addOnOffsetChangedListener(this);
        if (getSupportActionBar()!=null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(" ");

        Picasso.with(this).load(getIntent().getStringExtra("leagueLogo")).into(headerImageView);

        getPresenter().request(getIntent().getStringExtra("leagueId"));

        tabLayout.setTabColor(getResources().getColor(R.color.colorPrimary));
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(LatestEventsFragment.create(getIntent().getStringExtra("leagueId")));
        fragmentList.add(LeagueTableFragment.create(getIntent().getStringExtra("leagueId")));
        fragmentList.add(NextEventsFragment.create(getIntent().getStringExtra("leagueId"),trophy));
        tabLayout.initialize(viewPager,getSupportFragmentManager(),fragmentList,savedInstanceState);
    }

    void onTeams(TeamsDetails teamsDetails){
        callbackId = teamsDetails.getTeams().get(0).getIdLeague();
    }

    void onNetworkError(Throwable throwable){
        new SuperToast(this).setText(throwable.getMessage()).setTextSize(R.dimen.toastTextSize).setTextColor(PaletteUtils.getSolidColor(PaletteUtils.WHITE)).setDuration(Style.DURATION_SHORT).setFrame(Style.FRAME_STANDARD).setColor(getResources().getColor(R.color.colorAccent)).setAnimations(Style.ANIMATIONS_SCALE).show();
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
            isShow=true;
        }else if (isShow){
            collapsingToolbarLayout.setTitle("");
            isShow=false;
        }
    }

    @Override public void onSaveInstanceState(Bundle bundle){
        tabLayout.saveState(bundle);
        super.onSaveInstanceState(bundle);
    }

}

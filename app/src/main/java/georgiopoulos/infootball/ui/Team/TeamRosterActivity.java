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
package georgiopoulos.infootball.ui.Team;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.local.LocalData;
import georgiopoulos.infootball.ui.Base.BaseActivity;
import georgiopoulos.infootball.util.injection.Injector;
import icepick.State;

public class TeamRosterActivity extends BaseActivity<TeamRosterPresenter> implements AppBarLayout
                                                                                             .OnOffsetChangedListener{

    @BindView(R.id.activity_team_roaster_app_bar_layout) AppBarLayout appBarLayout;
    @BindView(R.id.activity_team_roaster_collapsing_toolbar_layout) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.activity_team_roaster_team_badge) ImageView teamBadgeImageView;
    @BindView(R.id.activity_team_roaster_toolbar) Toolbar toolbar;
    @BindDrawable(R.drawable.ic_arrow_primary_color_24dp) Drawable primaryColorArrow;
    @BindDrawable(R.drawable.ic_arrow_white_24dp) Drawable whiteColorArrow;
    @Inject LocalData localData;
    @State String teamName;
    @State boolean  isShow = false;
    private int scrollRange;
    private String teamId;

    @Override public void onCreate(Bundle bundle){
        ((Injector)getApplication()).inject(this);
        super.onCreate(bundle);
        setContentView(R.layout.activity_team_roaster);
        ButterKnife.bind(this);

        teamId = getIntent().getStringExtra("teamId");
        teamName = getIntent().getStringExtra("team");

        setSupportActionBar(toolbar);
        appBarLayout.addOnOffsetChangedListener(this);
        if (getSupportActionBar()!=null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Picasso.with(this).load(localData.getBadgeUrl(teamId)).into(teamBadgeImageView);
        scrollRange = -1;
        collapsingToolbarLayout.setTitle(" ");
        appBarLayout.setExpanded(!isShow);
        if(!isShow)getSupportActionBar().setHomeAsUpIndicator(primaryColorArrow);
        else getSupportActionBar().setHomeAsUpIndicator(whiteColorArrow);

        if (bundle == null) getSupportFragmentManager().beginTransaction().replace(R.id.activity_team_roaster_fragment_container, TeamRosterFragment.create(teamId)).commit();
    }

    public void push(Fragment fragment){
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.activity_team_roaster_fragment_container, fragment).commit();
    }

    public void replace(Fragment fragment){
        getSupportFragmentManager().popBackStackImmediate(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_team_roaster_fragment_container, fragment).commit();
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
            collapsingToolbarLayout.setTitle(teamName);
            getSupportActionBar().setHomeAsUpIndicator(whiteColorArrow);
            isShow=true;
        }else if (isShow){
            collapsingToolbarLayout.setTitle(" ");
            getSupportActionBar().setHomeAsUpIndicator(primaryColorArrow);
            isShow=false;
        }
    }

}

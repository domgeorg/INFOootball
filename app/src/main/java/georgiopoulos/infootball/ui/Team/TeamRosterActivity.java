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

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.local.LocalData;
import georgiopoulos.infootball.data.remote.dto.Player;
import georgiopoulos.infootball.data.remote.dto.TeamPlayers;
import georgiopoulos.infootball.ui.Base.BaseActivity;
import georgiopoulos.infootball.util.injection.Injector;
import georgiopoulos.infootball.util.adapters.PlayerViewHolder;
import georgiopoulos.infootball.util.adapters.SimpleListAdapter;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(TeamRosterPresenter.class)
public class TeamRosterActivity extends BaseActivity<TeamRosterPresenter> implements AppBarLayout.OnOffsetChangedListener{

    private String teamId;
    private String teamName;
    @Inject LocalData localData;
    private SimpleListAdapter<Player> adapter;
    private boolean isShow = false;
    private int scrollRange = -1;
    @BindView(R.id.app_bar_team_details) AppBarLayout appBarLayout;
    @BindView(R.id.collapsing_toolbar_layout_team_details) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar_team) Toolbar toolbar;
    @BindView(R.id.team_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.teamBadge) ImageView teamBadgeImageView;

    @Override public void onCreate(Bundle bundle){
        ((Injector)getApplication()).inject(this);
        super.onCreate(bundle);
        setContentView(R.layout.team_roster_activity);
        ButterKnife.bind(this);
        teamId = getIntent().getStringExtra("teamId");
        teamName=getIntent().getStringExtra("team");
        setSupportActionBar(toolbar);
        appBarLayout.addOnOffsetChangedListener(this);
        if (getSupportActionBar()!=null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(" ");
        Picasso.with(this).load(localData.getBadgeUrl(teamId)).into(teamBadgeImageView);

        adapter = new SimpleListAdapter<>(R.layout.loading_view, new ClassViewHolderType<>(Player.class,R.layout.player_card,v -> new PlayerViewHolder<>(v,this::onItemClick)));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.showProgress();
        getPresenter().request(teamId);
    }

    void onPlayers(TeamPlayers teamPlayers){
        adapter.hideProgress();
        if (teamPlayers.getPlayer()==null) new SuperToast(this).setText("Server does not provide info about players").setTextSize(R.dimen.toastTextSize).setTextColor(PaletteUtils.getSolidColor(PaletteUtils.WHITE)).setDuration(Style.DURATION_SHORT).setFrame(Style.FRAME_STANDARD).setColor(getResources().getColor(R.color.colorAccent)).setAnimations(Style.ANIMATIONS_SCALE).show();
        else adapter.set(teamPlayers.getPlayer());
    }

    void onNetworkError(Throwable throwable){
        adapter.hideProgress();
        new SuperToast(this).setText(throwable.getMessage()).setTextSize(R.dimen.toastTextSize).setTextColor(PaletteUtils.getSolidColor(PaletteUtils.WHITE)).setDuration(Style.DURATION_SHORT).setFrame(Style.FRAME_STANDARD).setColor(getResources().getColor(R.color.colorAccent)).setAnimations(Style.ANIMATIONS_SCALE).show();
    }

    private void onItemClick(Player player){
        new SuperToast(this).setText(player.getStrPlayer()).setTextSize(R.dimen.toastTextSize).setTextColor(PaletteUtils.getSolidColor(PaletteUtils.WHITE)).setDuration(Style.DURATION_SHORT).setFrame(Style.FRAME_STANDARD).setColor(getResources().getColor(R.color.colorAccent)).setAnimations(Style.ANIMATIONS_SCALE).show();
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
            isShow=true;
        }else if (isShow){
            collapsingToolbarLayout.setTitle("");
            isShow=false;
        }
    }
}

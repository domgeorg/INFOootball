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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.Country;
import georgiopoulos.infootball.data.remote.dto.Leagues;
import georgiopoulos.infootball.ui.Base.BaseActivity;
import georgiopoulos.infootball.ui.League.LeagueActivity;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import georgiopoulos.infootball.util.adapters.SimpleListAdapter;
import georgiopoulos.infootball.util.adapters.SoccerLeagueViewHolder;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(SoccerLeaguesPresenter.class)
public class SoccerLeaguesActivity extends BaseActivity<SoccerLeaguesPresenter>{

    @BindView(R.id.main_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.header_image) ImageView headerImageView;
    private SimpleListAdapter<Country> adapter;

    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soccer_leagues_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null) getSupportActionBar().setTitle(R.string.action_bar_title_soccer_leagues);
        Picasso.with(this).load(R.drawable.grass).into(headerImageView);

        adapter = new SimpleListAdapter<>(R.layout.loading_view, new ClassViewHolderType<>(Country.class,R.layout.soccer_league_card,v -> new SoccerLeagueViewHolder<>(v,this::onItemClick)));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.showProgress();
    }

    void onLeagues(Leagues leagues){
        adapter.hideProgress();

        if(leagues!=null){
            runLayoutAnimation(recyclerView);
            adapter.set(leagues.getCountrys());
        }
        else toaster("Server does not provide data right now...try again later");
    }

    void onNetworkError(Throwable throwable){
        adapter.hideProgress();
        toaster(throwable.getMessage());
      }

    private void onItemClick(Country country){
        startActivity(new Intent(this, LeagueActivity.class).putExtra("leagueId",country.getIdLeague()).putExtra("leagueLogo",country.getStrLogo()).putExtra("league",country.getStrLeague()).putExtra("trophy",country.getStrTrophy()));
    }

    @Override public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_league_table,menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    private void runLayoutAnimation(final RecyclerView recyclerView){
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_right);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
    }

    private void toaster(String message){
        new SuperToast(this).setText(message).setTextSize(R.dimen.toastTextSize).setTextColor(PaletteUtils.getSolidColor(PaletteUtils.WHITE)).setDuration(Style.DURATION_SHORT).setFrame(Style.FRAME_STANDARD).setColor(getResources().getColor(R.color.colorAccent)).setAnimations(Style.ANIMATIONS_SCALE).show();

    }
}

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
package georgiopoulos.infootball.ui.LiveScores;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.ui.Base.BaseActivity;
import icepick.State;

public class LiveScoresActivity extends BaseActivity<LiveScoresPresenter> implements AppBarLayout.OnOffsetChangedListener{

    @BindView(R.id.activity_live_scores_app_bar_layout) AppBarLayout appBarLayout;
    @BindView(R.id.activity_live_scores_collapsing_toolbar_layout) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.activity_live_scores_header_image) ImageView imageView;
    @BindView(R.id.activity_live_scores_toolbar) Toolbar toolbar;
    @State boolean isShow = false;
    private int scrollRange;

    @Override public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_live_scores);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle(R.string.activity_live_scores_tool_bar_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Picasso.with(this).load(R.drawable.grass).into(imageView);

        appBarLayout.addOnOffsetChangedListener(this);
        scrollRange = -1;
        appBarLayout.setExpanded(!isShow);

        if (bundle == null) getSupportFragmentManager().beginTransaction().replace(R.id.activity_live_scores_fragment_container, new LiveScoresFragment()).commit();
    }

    public void push(Fragment fragment){
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.activity_live_scores_fragment_container, fragment).commit();
    }

    public void replace(Fragment fragment){
        getSupportFragmentManager().popBackStackImmediate(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_live_scores_fragment_container, fragment).commit();
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

    @Override public void onOffsetChanged(AppBarLayout appBarLayout,int verticalOffset){
        if (scrollRange == -1) scrollRange = appBarLayout.getTotalScrollRange();
        isShow=(Math.abs(scrollRange+verticalOffset)<10);
    }

}


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
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.ui.Base.BaseActivity;
import icepick.State;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LiveScoresPresenter.class)
public class LiveScoresActivity extends BaseActivity<LiveScoresPresenter> implements AppBarLayout.OnOffsetChangedListener, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.activity_live_scores_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.activity_live_scores_coordinator_layout) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.activity_live_scores_swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.activity_live_scores_collapsing_toolbar_layout) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.activity_live_scores_header_image) ImageView imageView;
    @BindView(R.id.activity_live_scores_toolbar) Toolbar toolbar;
    @BindView(R.id.activity_live_scores_app_bar_layout) AppBarLayout appBarLayout;
    @State boolean isShow = false;
    private int scrollRange;

    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_scores);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null) getSupportActionBar().setTitle(R.string.activity_live_scores_tool_bar_title);
        Picasso.with(this).load(R.drawable.grass).into(imageView);

        appBarLayout.addOnOffsetChangedListener(this);
        scrollRange = -1;
        appBarLayout.setExpanded(!isShow);

        swipeRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout,int verticalOffset){

    }

    @Override
    public void onRefresh(){

    }
}


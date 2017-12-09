/*
  Copyright 2017 georgiopoulos kyriakos

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package georgiopoulos.infootball.ui.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import devlight.io.library.ntb.NavigationTabBar;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.ui.main.leagues.LeaguesFragment;
import georgiopoulos.infootball.ui.main.liveScores.LiveScoresFragment;
import georgiopoulos.infootball.ui.main.liveScores.LiveScoresPresenter;
import georgiopoulos.infootball.ui.main.news.NewsFragment;
import georgiopoulos.infootball.util.adapters.base.NavigationPagerAdapter;
import nucleus.view.NucleusAppCompatActivity;

public class MainActivity
        extends NucleusAppCompatActivity<LiveScoresPresenter>{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.navigation_tab_bar)
    NavigationTabBar navigationTabBar;

    @BindString(R.string.app_name)
    String appName;
    @BindString(R.string.main_tab_one)
    String tabOneTitle;
    @BindString(R.string.main_tab_two)
    String tabTwoTitle;
    @BindString(R.string.main_tab_three)
    String tabThreeTitle;

    @BindDrawable(R.drawable.ic_main_tab_one)
    Drawable tabOneDrawable;
    @BindDrawable(R.drawable.ic_main_tab_two)
    Drawable tabTwoDrawable;
    @BindDrawable(R.drawable.ic_main_tab_three)
    Drawable tabThreeDrawable;

    @BindColor(R.color.colorAccent)
    int accentColor;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setTheme(R.style.AppTheme_NoActionBar_StatusBar);
        setContentView(R.layout.activity_tabs);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setToolbar(appName);

        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(new NavigationTabBar.Model.Builder(tabOneDrawable, accentColor)
                           .title(tabOneTitle)
                           .build());
        models.add(new NavigationTabBar.Model.Builder(tabTwoDrawable, accentColor)
                           .title(tabTwoTitle)
                           .build());
        models.add(new NavigationTabBar.Model.Builder(tabThreeDrawable, accentColor)
                           .title(tabThreeTitle)
                           .build());

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new LiveScoresFragment());
        fragmentList.add(new LeaguesFragment());
        fragmentList.add(new NewsFragment());
        viewPager.setAdapter(new NavigationPagerAdapter(getSupportFragmentManager(),fragmentList));

        setNavigationTabBar(models, viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_league_table,menu);
        return true;
    }

    private void setToolbar(String title){
        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle(title);
        }
    }

    private void setNavigationTabBar(ArrayList<NavigationTabBar.Model> models,ViewPager viewPager){
        navigationTabBar.setIsTitled(true);
        navigationTabBar.setIsSwiped(true);
        navigationTabBar.setTitleMode(NavigationTabBar.TitleMode.ACTIVE);
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 1);
        navigationTabBar.setBehaviorEnabled(true);
    }

}

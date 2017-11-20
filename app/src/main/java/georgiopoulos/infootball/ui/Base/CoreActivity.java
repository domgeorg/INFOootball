/**
 * Copyright 2017 georgiopoulos kyriakos
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package georgiopoulos.infootball.ui.Base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import georgiopoulos.infootball.R;
import icepick.Icepick;
import icepick.State;

public class CoreActivity
        extends AppCompatActivity
        implements AppBarLayout.OnOffsetChangedListener,
                   CoreActivityFacet{

    @BindView(R.id.core_activity_app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.core_activity_collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.core_activity_header_image)
    ImageView headerImageView;
    @BindView(R.id.core_activity_toolbar)
    Toolbar toolbar;
    @BindView(R.id.floating_action_button_core_activity)
    FloatingActionButton floatingActionButton;

    @BindColor(R.color.colorPrimaryDark)
    int expandedTitleTextColor;
    @BindColor(R.color.icons)
    int collapsedTitleTextColor;
    @BindString(R.string.app_name) @State
    String title;
    @State
    boolean collapsed = true;

    Drawable collapsedNavigationButtonIndicator;
    Drawable expandedNavigationButtonIndicator;

    private int scrollRange;

    @Inject
    public CoreActivity(){}

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        Icepick.restoreInstanceState(this,bundle);
        setContentView(R.layout.activity_core);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        Icepick.saveInstanceState(this,bundle);
    }

    @Override
    public void push(Fragment fragment){
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.activity_core_fragment_container,fragment).commit();
    }

    @Override
    public void replace(Fragment fragment){
        getSupportFragmentManager().popBackStackImmediate(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_core_fragment_container,fragment).commit();
    }

    @Override
    public void enableCollapsingToolbar(boolean enable){
        if(enable){
            scrollRange = -1;
            appBarLayout.addOnOffsetChangedListener(this);
        }
        else {
            appBarLayout.removeOnOffsetChangedListener(this);
            setCollapsingToolbar(enable);
        }
    }

    @Override
    public void setCollapsingToolbarTitle(String title){
        this.title=title;
    }

    @Override
    public void setCollapsingToolbar(boolean collapsed){
        this.collapsed=collapsed;
        appBarLayout.setExpanded(collapsed);
        setCollapsingToolbarTextTitleColor(collapsed);
        collapsingToolbarLayout.setTitle(title);
    }

    @Override
    public boolean isCollapsed(){
        return collapsed;
    }

    @Override
    public void setHeaderImageView(boolean isDrawable,@Nullable Drawable drawable,@Nullable String url){
        if(isDrawable) headerImageView.setImageDrawable(drawable);
        else Picasso.with(this).load(url).into(headerImageView);
    }

    @Override
    public void enableNavigationButton(boolean enable){
        ActionBar actionBar=getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(enable);
        }
    }

    @Override
    public void setCollapsedNavigationButtonIndicator(Drawable indicator){
        this.collapsedNavigationButtonIndicator=indicator;
    }

    @Override
    public void setExpandedNavigationButtonIndicator(Drawable indicator){
        this.expandedNavigationButtonIndicator=indicator;
    }

    public void enableFloatingActionButton(boolean enable, Drawable drawable){
        if(enable){
            floatingActionButton.setOnClickListener(this);
            floatingActionButton.setImageDrawable(drawable);
            floatingActionButton.show();
        }
        else floatingActionButton.hide();
    }

    @Override
    public void onClick(View view){}


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout,int verticalOffset){
        if (scrollRange == -1) scrollRange = appBarLayout.getTotalScrollRange();
        collapsed=!(Math.abs(scrollRange+verticalOffset)<10);
        setNavigationButton(collapsed);
        setCollapsingToolbarTextTitleColor(collapsed);
    }

    private void setCollapsingToolbarTextTitleColor(boolean collapsed){
        if(collapsed) collapsingToolbarLayout.setCollapsedTitleTextColor(collapsedTitleTextColor);
        else collapsingToolbarLayout.setCollapsedTitleTextColor(expandedTitleTextColor);
    }

    private void setNavigationButton(boolean collapsed){
        if(collapsed)  getSupportActionBar().setHomeAsUpIndicator(collapsedNavigationButtonIndicator);
        else  getSupportActionBar().setHomeAsUpIndicator(expandedNavigationButtonIndicator);
    }

}

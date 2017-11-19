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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.ui.SoccerLeagues.LeaguesFragment;
import icepick.Icepick;
import icepick.State;

public class CoreActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    @BindView(R.id.core_activity_app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.core_activity_collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.core_activity_header_image)
    ImageView headerImageView;
    @BindView(R.id.core_activity_toolbar)
    Toolbar toolbar;

    @State
    String title;
    @State
    boolean isShow = false;

    private int scrollRange;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle); Icepick.restoreInstanceState(this,bundle);
        setContentView(R.layout.activity_core); ButterKnife.bind(this); if(bundle == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_core_fragment_container,new LeaguesFragment()).commit();
        scrollRange = - 1;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle); Icepick.saveInstanceState(this,bundle);
    }

    public void push(Fragment fragment){
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.activity_core_fragment_container,fragment).commit();
    }

    public void replace(Fragment fragment){
        getSupportFragmentManager().popBackStackImmediate(null,FragmentManager
                                                                       .POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_core_fragment_container,fragment).commit();
    }

    public void setAppBarLayout(@NonNull String title,@NonNull boolean expanded,@NonNull boolean
                                                                                        displayHomeAsUpEnabled,@Nullable Drawable expandedIndicator,@Nullable Drawable collapsedIndicator){

        this.title = title;
    }

    public void setHeaderImageView(boolean isDrawable,@Nullable Drawable drawable,@Nullable
                                                                                          String
                                                                                          url){
        if(Objects.requireNonNull(isDrawable)) headerImageView.setImageDrawable(drawable);
        else Picasso.with(this).load(url).into(headerImageView);

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout,int verticalOffset){

    }
}

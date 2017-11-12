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
package georgiopoulos.infootball.util.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.soccerLeagues.Country;
import georgiopoulos.infootball.util.adapters.base.BaseViewHolder;
import rx.functions.Action1;

public class SoccerLeagueViewHolder<T extends Country> extends BaseViewHolder<T>{

    private T league;
    private View view;
    @BindView(R.id.leagueBadge) ImageView leagueBadgeImageView;
    @BindView(R.id.leagueName) TextView leagueNameTextView;
    @BindView(R.id.leagueCountry) TextView leagueCountryTextView;

    public SoccerLeagueViewHolder(View view,Action1<T> onClick){
        super(view);
        this.view=view;
        ButterKnife.bind(this,view);
        view.setOnClickListener(v -> onClick.call(league));
    }

    @Override public void bind(T item){
        this.league=item;
        leagueNameTextView.setText(league.getStrLeague());
        leagueCountryTextView.setText(league.getStrCountry());
        Picasso.with(view.getContext()).load(league.getStrBadge()).into(leagueBadgeImageView);
    }
}

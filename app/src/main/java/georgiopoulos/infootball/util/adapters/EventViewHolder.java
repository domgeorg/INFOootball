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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.local.LocalData;
import georgiopoulos.infootball.data.remote.dto.league.Event;
import georgiopoulos.infootball.util.adapters.base.BaseViewHolder;
import rx.functions.Action1;

public class EventViewHolder<T extends Event> extends BaseViewHolder<T>{

    @BindView(R.id.card_event_home_team_badge)
    ImageView homeTeamBadgeImageView;
    @BindView(R.id.card_event_away_team_badge)
    ImageView awayTeamBadgeImageView;
    @BindView(R.id.card_event_match_name)
    TextView matchNameTextView;
    @BindView(R.id.card_event_home_team_score)
    TextView homeTeamScoreTextView;
    @BindView(R.id.card_event_away_team_score)
    TextView awayTeamScoreTextView;
    @BindView(R.id.card_event_match_date)
    TextView matchDateTextView;

    @Inject LocalData localData;
    private T event;
    private View view;

    public EventViewHolder(View view,Action1<T> onClick){
        super(view);
        this.view=view;
        ButterKnife.bind(this,view);
        view.setOnClickListener(v -> onClick.call(event));
    }

    @Override
    public void bind(T item){
        this.event=item;
        Picasso.with(view.getContext()).load(localData.getBadgeUrl(event.getIdHomeTeam())).into(homeTeamBadgeImageView);
        Picasso.with(view.getContext()).load(localData.getBadgeUrl(event.getIdAwayTeam())).into(awayTeamBadgeImageView);
        matchNameTextView.setText(event.getStrEvent());
        homeTeamScoreTextView.setText(event.getIntHomeScore());
        awayTeamScoreTextView.setText(event.getIntAwayScore());
        matchDateTextView.setText(event.getStrDate());
    }
}

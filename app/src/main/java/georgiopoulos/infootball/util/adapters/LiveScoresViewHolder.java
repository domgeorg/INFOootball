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
import android.widget.TextView;

import com.santalu.diagonalimageview.DiagonalImageView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.local.LocalData;
import georgiopoulos.infootball.data.remote.dto.livescores.LiveScores;
import georgiopoulos.infootball.data.remote.dto.livescores.Match;
import georgiopoulos.infootball.util.adapters.base.BaseViewHolder;
import rx.functions.Action1;

public class LiveScoresViewHolder<T extends Match> extends BaseViewHolder<T>{

    @BindView(R.id.card_live_scores_league_badge_diagonal_image_view) DiagonalImageView imageView;
    @BindView(R.id.card_live_scores_league_name) TextView leagueNameTextView;
    @BindView(R.id.card_live_scores_match_name) TextView matchNameTextView;
    @BindView(R.id.card_live_scores_match_score) TextView matchScoreTextView;
    @BindView(R.id.card_live_scores_match_time) TextView matchTimeTextView;
    @BindView(R.id.card_live_scores_match_location) TextView matchLocationTextView;
    @BindColor(R.color.card_live_scores_red_match_time_text_color) int red;
    @BindColor(R.color.card_live_scores_yellow_match_time_text_color) int yellow;
    @BindColor(R.color.card_live_scores_green_match_time_text_color) int green;

    @Inject LocalData localData;
    private T match;
    private final View view;

    public LiveScoresViewHolder(View view,Action1<T> onClick){
        super(view);
        this.view=view;
        ButterKnife.bind(this,view);
        view.setOnClickListener(v -> onClick.call(match));
    }

    @Override public void bind(T item){
        match=item;
        leagueNameTextView.setText(match.getLeague());
        matchNameTextView.setText(match.getHomeTeam()+" vs "+match.getAwayTeam());
        matchScoreTextView.setText(match.getHomeGoals()+" - "+match.getAwayGoals());
        matchTimeTextView.setText(match.getTime());
        matchTimeTextView.setTextColor(matchTimeTextColor(match.getTime()));
        matchLocationTextView.setText(match.getLocation());
        Picasso.with(view.getContext()).load(localData.getLeagueBadgeUrl(match.getLeague()));
    }

    private int matchTimeTextColor(String time){
        switch(time){
            case "Not started": return yellow;
            case "Finished": return red;
            default: return green;
        }
    }

}

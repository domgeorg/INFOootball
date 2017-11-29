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
package georgiopoulos.infootball.util.adapters;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.santalu.diagonalimageview.DiagonalImageView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.local.LocalData;
import georgiopoulos.infootball.data.remote.dto.league.Table;
import georgiopoulos.infootball.util.adapters.base.BaseViewHolder;
import rx.functions.Action1;

public class LeagueTableTeamViewHolder<T extends Table>
        extends BaseViewHolder<T>{

    @BindView(R.id.card_league_table_team_badge)
    DiagonalImageView teamBadgeImageView;
    @BindView(R.id.card_league_table_team_jersey)
    DiagonalImageView teamJerseyImageView;
    @BindView(R.id.card_league_table_team_name)
    TextView teamNameTextView;
    @BindView(R.id.card_league_table_played)
    TextView playedTextView;
    @BindView(R.id.card_league_table_win)
    TextView winTextView;
    @BindView(R.id.card_league_table_draw)
    TextView drawTextView;
    @BindView(R.id.card_league_table_loss)
    TextView lossTextView;

    @Inject LocalData localData;
    private T team;
    private View view;

    public LeagueTableTeamViewHolder(View view, Action1<T> onClick){
        super(view);
        this.view=view;
        ButterKnife.bind(this,view);
        view.setOnClickListener(v -> onClick.call(team));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void bind(T item){
        this.team=item;
        teamNameTextView.setText(item.getName());
        playedTextView.setText(item.getPlayed()+"\nGames");
        winTextView.setText(item.getWin()+"\nWins");
        drawTextView.setText(item.getDraw()+"\nDraws");
        lossTextView.setText(item.getLoss()+"\nLosses");
        Picasso.with(view.getContext()).load(localData.getBadgeUrl(item.getTeamid())).into(teamBadgeImageView);
        Picasso.with(view.getContext()).load(localData.getJerseyUrl(item.getTeamid())).into(teamJerseyImageView);
    }
}

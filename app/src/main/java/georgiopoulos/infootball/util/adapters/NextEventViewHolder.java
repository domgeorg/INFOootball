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

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.local.LocalData;
import georgiopoulos.infootball.data.remote.dto.events.Event;
import georgiopoulos.infootball.util.adapters.base.BaseViewHolder;
import rx.functions.Action1;

public class NextEventViewHolder<T extends Event>
        extends BaseViewHolder<T>{

    @BindView(R.id.card_next_event_home_team_jersey)
    ImageView homeTeamImageView;
    @BindView(R.id.card_next_event_away_team_jersey)
    ImageView awayTeamImageView;
    @BindView(R.id.card_next_event_name)
    TextView eventTextView;
    @BindView(R.id.card_next_event_date)
    TextView dateTextView;
    @BindView(R.id.card_next_event_time)
    TextView timeTextView;
    @BindView(R.id.card_next_event_stadium)
    TextView stadiumTextView;

    @Inject LocalData localData;
    private T event;
    private View view;

    public NextEventViewHolder(View view,Action1<T> onClick){
        super(view);
        this.view=view;
        ButterKnife.bind(this,view);
        view.setOnClickListener(v -> onClick.call(event));
    }

    @Override public void bind(T item){
        this.event=item;
        Picasso.with(view.getContext()).load(localData.getJerseyUrl(event.getIdHomeTeam())).into(homeTeamImageView);
        Picasso.with(view.getContext()).load(localData.getJerseyUrl(event.getIdAwayTeam())).into(awayTeamImageView);
        eventTextView.setText(event.getStrEvent());
        dateTextView.setText(event.getStrDate());
        timeTextView.setText(getTime(event.getStrTime()));
        stadiumTextView.setText(localData.getStadium(event.getIdHomeTeam()));
    }

    private String getTime(String time){
        int index = time.indexOf("+");
        if (index != -1) return time.substring(0, index);
        else return time;
    }
}

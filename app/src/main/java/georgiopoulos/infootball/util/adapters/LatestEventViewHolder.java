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
import georgiopoulos.infootball.data.local.TeamRealm;
import georgiopoulos.infootball.data.remote.dto.Event;
import io.realm.Realm;
import rx.functions.Action1;

public class LatestEventViewHolder<T extends Event> extends BaseViewHolder<T>{

    private T event;
    private View view;
    @BindView(R.id.homeTeamBadge) ImageView homeTeamBadgeImageView;
    @BindView(R.id.awayTeamBadge) ImageView awayTeamBadgeImageView;
    @BindView(R.id.event) TextView eventTextView;
    @BindView(R.id.homeScore) TextView homeScoreTextView;
    @BindView(R.id.awayScore) TextView awayScoreTextView;
    @BindView(R.id.date) TextView dateTextView;

    public LatestEventViewHolder(View view,Action1<T> onClick){
        super(view);
        this.view=view;
        ButterKnife.bind(this,view);
        view.setOnClickListener(v -> onClick.call(event));
    }

    @Override
    public void bind(T item){
        this.event=item;
        Picasso.with(view.getContext()).load(getBadgeUrl(event.getIdHomeTeam())).into(homeTeamBadgeImageView);
        Picasso.with(view.getContext()).load(getBadgeUrl(event.getIdAwayTeam())).into(awayTeamBadgeImageView);
        eventTextView.setText(event.getStrEvent());
        homeScoreTextView.setText(event.getIntHomeScore());
        awayScoreTextView.setText(event.getIntAwayScore());
        dateTextView.setText(event.getStrDate());
    }

    private String getBadgeUrl(String idTeam){
        String badge="http://www.thesportsdb.com/images/team-icon.png";
        try(Realm realm = Realm.getDefaultInstance()){
            TeamRealm teamRealm = realm.where(TeamRealm.class).equalTo("idTeam",idTeam).findFirst();
            if (teamRealm!=null && teamRealm.getStrTeamBadge()!=null)badge=teamRealm.getStrTeamBadge();
        }
        return badge;
    }
}

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

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.local.LocalData;
import georgiopoulos.infootball.data.remote.dto.team.Player;
import georgiopoulos.infootball.util.adapters.base.BaseViewHolder;
import rx.functions.Action1;

public class PlayerViewHolder<T extends Player> extends BaseViewHolder<T>{

    @Inject LocalData localData;
    private T player;
    private View view;
    @BindView(R.id.card_player_cutout) ImageView playerCutoutImageView;
    @BindView(R.id.card_player_name) TextView playerNameTextView;
    @BindView(R.id.card_player_nationality) TextView playerNationalityTextView;
    @BindView(R.id.card_player_position) TextView playerPositionTextView;
    @BindView(R.id.card_player_height) TextView playerHeightTextView;
    @BindView(R.id.card_player_weight) TextView playerWeightTextView;
    @BindView(R.id.card_player_birthday) TextView playerDateBornTextView;
    @BindDrawable(R.drawable.ic_player) Drawable playerDrawable;

    public PlayerViewHolder(View view,Action1<T> onClick){
        super(view);
        this.view=view;
        ButterKnife.bind(this,view);
        view.setOnClickListener(v -> onClick.call(player));
    }

    @Override
    public void bind(T player){
        this.player=player;
        playerNameTextView.setText(player.getStrPlayer());
        playerNationalityTextView.setText("Nationality: "+ player.getStrNationality());
        playerPositionTextView.setText("Position: "+player.getStrPosition());
        playerHeightTextView.setText("Height: "+player.getStrHeight());
        playerWeightTextView.setText("Weight: "+ player.getStrWeight());
        playerDateBornTextView.setText("Birthday: "+ player.getDateBorn());
        //TODO: Make Realm Player
        String playerPhoto="https://image.flaticon.com/icons/png/128/166/166344.png";
        if(player.getStrCutout()!=null) playerPhoto=player.getStrCutout();
        else if (player.getStrThumb()!=null)playerPhoto=player.getStrThumb();
        Picasso.with(view.getContext()).load(playerPhoto).into(playerCutoutImageView);
    }
}

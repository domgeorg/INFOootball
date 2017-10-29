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
import georgiopoulos.infootball.data.remote.dto.Player;
import georgiopoulos.infootball.util.adapters.base.BaseViewHolder;
import rx.functions.Action1;

public class PlayerViewHolder<T extends Player> extends BaseViewHolder<T>{

    @Inject LocalData localData;
    private T player;
    private View view;
    @BindView(R.id.PlayerCutout) ImageView playerCutoutImageView;
    @BindView(R.id.playerName) TextView playerNameTextView;
    @BindView(R.id.playerNationality) TextView playerNationalityTextView;
    @BindView(R.id.playerPosition) TextView playerPositionTextView;
    @BindView(R.id.playerHeight) TextView playerHeightTextView;
    @BindView(R.id.playerWeight) TextView playerWeightTextView;
    @BindView(R.id.playerDateBorn) TextView playerDateBornTextView;

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
        String playerPhoto="http://freevector.co/wp-content/uploads/2013/02/22453-football-player-shape-simbolo-de-la-interfaz-de-ios-71.png";
        if(player.getStrCutout()!=null) playerPhoto=player.getStrCutout();
        else if (player.getStrThumb()!=null)playerPhoto=player.getStrThumb();
        Picasso.with(view.getContext()).load(playerPhoto).placeholder(R.drawable.player).into(playerCutoutImageView);
    }
}
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
package georgiopoulos.infootball.ui.LeagueTable;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import butterknife.BindView;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.LeagueTable;
import georgiopoulos.infootball.data.remote.dto.Table;
import georgiopoulos.infootball.ui.Base.BaseFragment;
import georgiopoulos.infootball.util.adapters.ClassViewHolderType;
import georgiopoulos.infootball.util.adapters.LeagueTableTeamViewHolder;
import georgiopoulos.infootball.util.adapters.SimpleListAdapter;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LeagueTablePresenter.class)
public class LeagueTableFragment extends BaseFragment<LeagueTablePresenter>{

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    private String leagueId="4328";
    private String season="1718";
    private SimpleListAdapter<Table> adapter;

    @Override public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        if (bundle == null){
            adapter.showProgress();
            getPresenter().request(leagueId,season);
        }
    }

    @Override public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_league_table,container,false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        adapter = new SimpleListAdapter<>(R.layout.loading_view, new ClassViewHolderType<>(Table.class,R.layout.league_table_card,v -> new LeagueTableTeamViewHolder<>(v, this::onItemClick)));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    void onTable(LeagueTable leagueTable){
        adapter.hideProgress();
        adapter.set(leagueTable.getTable());
    }

    void onNetworkError(Throwable throwable){
        adapter.hideProgress();
        new SuperToast(getActivity()).setText(throwable.getMessage()).setTextSize(R.dimen.toastTextSize).setTextColor(PaletteUtils.getSolidColor(PaletteUtils.WHITE)).setDuration(Style.DURATION_SHORT).setFrame(Style.FRAME_STANDARD).setColor(getResources().getColor(R.color.colorAccent)).setAnimations(Style.ANIMATIONS_SCALE).show();
    }

    private void onItemClick(Table team) {
        //((MainActivity) getActivity()).push(ItemFragment.create(item.id, itemsName));
        new SuperToast(getActivity()).setText("BOOM").setTextSize(R.dimen.toastTextSize).setTextColor(PaletteUtils.getSolidColor(PaletteUtils.WHITE)).setDuration(Style.DURATION_SHORT).setFrame(Style.FRAME_STANDARD).setColor(getResources().getColor(R.color.colorAccent)).setAnimations(Style.ANIMATIONS_SCALE).show();

    }
}

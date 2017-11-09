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
package georgiopoulos.infootball.ui.LiveScores;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.ui.Base.BaseFragment;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LiveScoresPresenter.class)
public class LiveScoresFragment extends BaseFragment<LiveScoresPresenter> implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.swipe_refresh_layout_activity_live_scores) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.swipe_refresh_layout_recycler_view_activity_live_scores) RecyclerView recyclerView;

    @Override
    public void onRefresh(){

    }
}

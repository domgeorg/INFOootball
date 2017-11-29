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
package georgiopoulos.infootball.ui.main.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.news.Article;
import georgiopoulos.infootball.ui.base.BaseFragment;
import georgiopoulos.infootball.util.adapters.NewsViewHolder;
import georgiopoulos.infootball.util.adapters.base.ClassViewHolderType;
import georgiopoulos.infootball.util.adapters.base.SimpleListAdapter;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(NewsPresenter.class)
public class NewsFragment extends BaseFragment<Article,NewsPresenter>{

    @Override
    public void onViewCreated(View view,Bundle bundle){
        setAdapter(new SimpleListAdapter<>(R.layout.progress_bar,new ClassViewHolderType<>(Article.class,R.layout.card_news,v -> new NewsViewHolder<>(v,this::onItemClick))));
        super.onViewCreated(view,bundle);
        enableSwipeRefreshLayout(true);
        if(bundle==null)this.onRefresh();
    }

    @Override
    public void onRefresh(){
        super.onRefresh();
        getPresenter().request();
    }

    protected void onArticles(@Nullable List<Article> list){
        if(isNullOrEmpty(list)) displayError(R.string.news_fragment_error_message,R.drawable.ic_error_fragment_news);
        else displayList(list,R.anim.layout_animation_from_bottom);
    }

    @Override
    public void onItemClick(@Nullable Article article){
        String articleUrl = article.getUrl();
        if(articleUrl!=null || !(articleUrl.isEmpty()))
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(articleUrl)));
    }
}

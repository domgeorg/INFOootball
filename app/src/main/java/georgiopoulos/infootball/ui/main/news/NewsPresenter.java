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

import javax.inject.Inject;

import georgiopoulos.infootball.data.remote.api.NewsAPI;
import georgiopoulos.infootball.data.remote.dto.news.News;
import georgiopoulos.infootball.ui.base.BasePresenter;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class NewsPresenter extends BasePresenter<NewsFragment>{

    private static final int REQUEST_NEWS = 1;
    @Inject
    NewsAPI news;

    protected void request(){

        restartableLatestCache(
                REQUEST_NEWS,
                () -> news.getNews()
                              .subscribeOn(Schedulers.io())
                              .map(News::getArticles)
                              .observeOn(mainThread()),
                NewsFragment::onArticles,
                NewsFragment::onNetworkError);

        start(REQUEST_NEWS);
    }

}

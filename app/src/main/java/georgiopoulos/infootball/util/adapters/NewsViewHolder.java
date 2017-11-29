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

import butterknife.BindView;
import butterknife.ButterKnife;
import georgiopoulos.infootball.R;
import georgiopoulos.infootball.data.remote.dto.news.Article;
import georgiopoulos.infootball.util.adapters.base.BaseViewHolder;
import rx.functions.Action1;

public class NewsViewHolder<T extends Article> extends BaseViewHolder<T>{

    private T article;
    private final View view;

    @BindView(R.id.news_image)
    ImageView newsImageView;
    @BindView(R.id.news_title)
    TextView titleTextView;
    @BindView(R.id.news_description)
    TextView descriptionTextView;

    public NewsViewHolder(View view,Action1<T> onClick){
        super(view);
        this.view=view;
        ButterKnife.bind(this,view);
        view.setOnClickListener(v -> onClick.call(article));
    }

    @Override
    public void bind(T item){
        article=item;
        Picasso.with(view.getContext()).load(article.getUrlToImage()).into(newsImageView);
        titleTextView.setText(article.getTitle());
        descriptionTextView.setText(article.getDescription());
    }
}
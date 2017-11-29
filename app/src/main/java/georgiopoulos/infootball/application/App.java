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
package georgiopoulos.infootball.application;

import android.app.Application;
import android.content.res.Resources;

import georgiopoulos.infootball.util.injection.ComponentReflectionInjector;
import georgiopoulos.infootball.util.injection.Injector;
import io.realm.Realm;

public class App extends Application implements Injector{

    private ComponentReflectionInjector<AppComponent> injector;
    private static App sInstance = null;
    private static AppComponent component = null;

    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(this);
        sInstance = this;
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        injector = new ComponentReflectionInjector<>(AppComponent.class,component);
    }

    @Override
    public void inject(Object target){
        injector.inject(target);
    }

    public static App getInstance() { return sInstance; }

    public static AppComponent getAppComponent() { return component; }

    public static Realm getRealm() { return component.realm(); }

    public static Resources getRes() { return sInstance.getResources();}
}

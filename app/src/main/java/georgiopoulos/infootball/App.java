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
package georgiopoulos.infootball;

import android.app.Application;

import georgiopoulos.infootball.data.remote.api.NetworkComponent;
import georgiopoulos.infootball.data.remote.api.DaggerNetworkComponent;
import georgiopoulos.infootball.data.remote.api.NetworkModule;
import georgiopoulos.infootball.util.ComponentReflectionInjector;
import georgiopoulos.infootball.util.Injector;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application implements Injector{

    private ComponentReflectionInjector<NetworkComponent> injector;

    @Override
    public void onCreate(){
        super.onCreate();

        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);

        NetworkComponent component = DaggerNetworkComponent.builder()
                                         .networkModule(new NetworkModule())
                                         .build();
        injector = new ComponentReflectionInjector<>(NetworkComponent.class,component);
    }

    @Override
    public void inject(Object target){
        injector.inject(target);
    }
}

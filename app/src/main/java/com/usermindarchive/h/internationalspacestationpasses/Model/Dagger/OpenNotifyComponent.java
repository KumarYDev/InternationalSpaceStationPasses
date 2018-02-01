package com.usermindarchive.h.internationalspacestationpasses.Model.Dagger;

import com.usermindarchive.h.internationalspacestationpasses.MainActivity;
import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyDataParser;
import com.usermindarchive.h.internationalspacestationpasses.Presenter.MainFragmentPresenter;
import com.usermindarchive.h.internationalspacestationpasses.View.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * OpenNotifyComponent Interface is the Dagger Component which is reposible for the collection of the Dagger Module classes
 * and list of class where Dagger is being Used
 */

@Singleton
// List of module classes that contain Dagger provide methods
@Component(modules = {OpenNotifyModule.class})
public interface OpenNotifyComponent {

//    Classes where Dagger is being Injected
    void inject(MainFragmentPresenter mainFragment);
    void inject(MainActivity mainActivity);
    void inject(OpenNotifyDataParser openNotifyDataParser);

}

package ir.mlcode.latifiarchitecturelibrary.daggers.utility;

import dagger.Module;
import dagger.Provides;
import ir.mlcode.latifiarchitecturelibrary.daggers.DaggerScope;
import ir.mlcode.latifiarchitecturelibrary.utility.ApplicationUtility;

@Module
public class UtilityModule {

    @Provides
    @DaggerScope
    public ApplicationUtility getApplicationUtility() {
        return new ApplicationUtility();
    }
}

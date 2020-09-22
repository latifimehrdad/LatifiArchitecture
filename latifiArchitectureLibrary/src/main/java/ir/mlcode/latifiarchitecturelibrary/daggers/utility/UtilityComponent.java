package ir.mlcode.latifiarchitecturelibrary.daggers.utility;


import dagger.Component;
import ir.mlcode.latifiarchitecturelibrary.daggers.DaggerScope;
import ir.mlcode.latifiarchitecturelibrary.utility.ApplicationUtility;

@DaggerScope
@Component(modules = UtilityModule.class)
public interface UtilityComponent {

    ApplicationUtility getApplicationUtility();
}

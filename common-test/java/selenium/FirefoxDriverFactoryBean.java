package todo.selenium;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.beans.factory.FactoryBean;

public class FirefoxDriverFactoryBean implements FactoryBean<FirefoxDriver> {

    @Override
    public FirefoxDriver getObject() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("brouser.startup.homepage_override.mstone",
                "ignore");
        profile.setPreference("network.proxy.type", 0);
        profile.setPreference("intl.accept_languages", "en-us,en");
        return new FirefoxDriver(profile);
    }

    @Override
    public Class<?> getObjectType() {
        return FirefoxDriver.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}

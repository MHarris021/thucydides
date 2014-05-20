package net.thucydides.core.reports.json.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.thucydides.core.model.Story;
import net.thucydides.core.model.TestOutcome;
import net.thucydides.core.model.features.ApplicationFeature;
import net.thucydides.core.reports.json.JSONFeatureMixin;
import net.thucydides.core.reports.json.JSONStoryMixin;
import net.thucydides.core.reports.json.JSONTestOutcomeMixin;

public class TestOutcomeModule extends SimpleModule {

    public TestOutcomeModule() {
        super("TestOutcomes", new Version(0,0,1,"RELEASE","net.thucydides.core","thucydides-core-json"));
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(TestOutcome.class, JSONTestOutcomeMixin.class);
        context.setMixInAnnotations(Story.class, JSONStoryMixin.class);
        context.setMixInAnnotations(ApplicationFeature.class, JSONFeatureMixin.class);

    }
}
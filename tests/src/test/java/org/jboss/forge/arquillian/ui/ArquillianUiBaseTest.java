package org.jboss.forge.arquillian.ui;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.forge.addon.projects.Project;
import org.jboss.forge.addon.projects.ProjectFactory;
import org.jboss.forge.addon.ui.command.UICommand;
import org.jboss.forge.addon.ui.controller.CommandController;
import org.jboss.forge.arquillian.AddonDependency;
import org.jboss.forge.arquillian.Dependencies;
import org.jboss.forge.arquillian.archive.ForgeArchive;
import org.jboss.forge.furnace.repositories.AddonDependencyEntry;
import org.jboss.forge.ui.test.UITestHarness;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public abstract class ArquillianUiBaseTest {

    @Deployment
    @Dependencies({
             @AddonDependency(name = "org.jboss.forge.addon:projects"),
             @AddonDependency(name = "org.jboss.forge.addon:ui-test-harness"),
             @AddonDependency(name = "org.jboss.forge.addon:maven"),
             @AddonDependency(name = "org.jboss.forge.arquillian:arquillian")
    })
    public static ForgeArchive getDeployment() {
        ForgeArchive archive = ShrinkWrap
            .create(ForgeArchive.class)
            .addBeansXML()
            .addAsAddonDependencies(AddonDependencyEntry.create("org.jboss.forge.furnace.container:cdi"),
                    AddonDependencyEntry.create("org.jboss.forge.addon:projects"),
                    AddonDependencyEntry.create("org.jboss.forge.arquillian:arquillian"),
                    AddonDependencyEntry.create("org.jboss.forge.addon:ui-test-harness"))
            .addClass(ArquillianUiBaseTest.class);
        return archive;
    }

    @Inject
    protected ProjectFactory projectFactory;

    @Inject
    protected UITestHarness testHarness;

    protected Project project;
    protected CommandController commandController;

    protected abstract Class<? extends UICommand> getCommandClass();

    @Before
    public void setup() throws Exception {
        project = projectFactory.createTempProject();
        commandController = testHarness.createCommandController(getCommandClass(), project.getRootDirectory());
        commandController.initialize();
    }

}

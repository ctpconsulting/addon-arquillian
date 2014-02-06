package org.jboss.forge.arquillian.ui;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.jboss.forge.addon.ui.command.UICommand;
import org.jboss.forge.addon.ui.result.Result;
import org.jboss.forge.arquillian.facet.ArquillianFacet;
import org.junit.Test;


public class ArquillianSetupWizardTest extends ArquillianUiBaseTest {

    @Override
    protected Class<? extends UICommand> getCommandClass() {
        return ArquillianSetupWizard.class;
    }

    @Test
    public void should_setup_arquillian_with_managed_jboss() throws Exception {
        // given
        commandController.setValueFor("arquillianVersion", "1.1.2.Final");
        commandController.setValueFor("container", "JBoss AS Managed 7.x");

        // when
        Result result = commandController.execute();

        // then
        assertNotNull(result);
        assertTrue(project.hasFacet(ArquillianFacet.class));
    }

}

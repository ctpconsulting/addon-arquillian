package org.jboss.forge.arquillian.ui;

import static org.jboss.forge.arquillian.ui.util.UiUtils.selectContainer;
import static org.jboss.forge.arquillian.ui.util.UiUtils.selectVersion;

import javax.inject.Inject;

import org.jboss.forge.addon.dependencies.Coordinate;
import org.jboss.forge.addon.projects.Project;
import org.jboss.forge.addon.ui.context.UIBuilder;
import org.jboss.forge.addon.ui.context.UIContext;
import org.jboss.forge.addon.ui.context.UIExecutionContext;
import org.jboss.forge.addon.ui.context.UINavigationContext;
import org.jboss.forge.addon.ui.input.UISelectOne;
import org.jboss.forge.addon.ui.metadata.WithAttributes;
import org.jboss.forge.addon.ui.result.NavigationResult;
import org.jboss.forge.addon.ui.result.Result;
import org.jboss.forge.addon.ui.util.Categories;
import org.jboss.forge.addon.ui.util.Metadata;
import org.jboss.forge.addon.ui.wizard.UIWizard;
import org.jboss.forge.arquillian.container.Container;
import org.jboss.forge.arquillian.container.ContainerDirectoryParser;

public class ArquillianSetupWizard extends AbstractArquillianCommand implements UIWizard {

    private static final String ARQUILLIAN_BOM = "org.jboss.arquillian:arquillian-bom:[1.0.4.Final,)";

    @Inject
    @WithAttributes(shortName = 'v', label = "Arquillian Version", required = true)
    private UISelectOne<Coordinate> arquillianVersion;

    @Inject
    @WithAttributes(shortName = 's', label = "Use Servlet Protocol?")
    private UISelectOne<Boolean> useServletProtocol;

    @Inject
    @WithAttributes(shortName = 'c', label = "Container", required = true)
    private UISelectOne<Container> container;

    @Inject
    private ContainerDirectoryParser containerParser;

    @Override
    public void initializeUI(UIBuilder builder) throws Exception {
        UIContext context = builder.getUIContext();
        Project project = getSelectedProject(context);
        selectVersion(arquillianVersion, project, ARQUILLIAN_BOM);
        selectContainer(container, containerParser.getContainers());
        builder.add(arquillianVersion)
            .add(useServletProtocol)
            .add(container);
    }

    @Override
    public Metadata getMetadata(UIContext context) {
        return Metadata
            .from(super.getMetadata(context), getClass())
            .name("Arquillian: Setup")
            .description("Setup Arquillian in your project")
            .category(Categories.create(super.getMetadata(context).getCategory().getName(), "Testing"));
    }

    @Override
    public boolean isEnabled(UIContext context) {
        return containsProject(context);
    }

    @Override
    public Result execute(UIExecutionContext context) throws Exception {
        return null;
    }

    @Override
    public NavigationResult next(UINavigationContext context) throws Exception {
        return null;
    }

}

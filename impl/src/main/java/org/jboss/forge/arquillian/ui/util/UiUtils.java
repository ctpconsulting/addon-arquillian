package org.jboss.forge.arquillian.ui.util;

import java.util.List;

import org.jboss.forge.addon.dependencies.Coordinate;
import org.jboss.forge.addon.projects.Project;
import org.jboss.forge.addon.projects.facets.DependencyFacet;
import org.jboss.forge.addon.ui.input.UISelectOne;
import org.jboss.forge.arquillian.container.Container;

public final class UiUtils {

    private UiUtils() {}

    public static void selectVersion(UISelectOne<Coordinate> coordinate, Project project, String dependency) {
        DependencyFacet dependencyFacet = project.getFacet(DependencyFacet.class);
        List<Coordinate> dependencies = dependencyFacet.resolveAvailableVersions(dependency);
        coordinate.setValueChoices(dependencies);
        coordinate.setItemLabelConverter(Converters.coordinates);
    }

    public static void selectContainer(UISelectOne<Container> container, List<Container> containers) {
        container.setValueChoices(containers);
        container.setItemLabelConverter(Converters.containers);
    }

}

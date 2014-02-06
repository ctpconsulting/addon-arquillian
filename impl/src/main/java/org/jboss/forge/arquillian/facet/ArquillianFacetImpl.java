package org.jboss.forge.arquillian.facet;

import org.jboss.forge.addon.dependencies.Coordinate;
import org.jboss.forge.addon.facets.AbstractFacet;
import org.jboss.forge.addon.projects.Project;
import org.jboss.forge.arquillian.container.Container;

public class ArquillianFacetImpl extends AbstractFacet<Project> implements ArquillianFacet {

    private Coordinate coordinate;

    @Override
    public boolean install() {
     // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isInstalled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Container getContainer(String profileName) {
        // TODO Auto-generated method stub
        return null;
    }

    public void withArquillianVersion(Coordinate value) {
        this.coordinate = value;
    }

}

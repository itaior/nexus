/*
 * Nexus: Maven Repository Manager
 * Copyright (C) 2008 Sonatype Inc.                                                                                                                          
 * 
 * This file is part of Nexus.                                                                                                                                  
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 */
package org.sonatype.nexus.rest;

import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.Router;

/**
 * Nexus REST content bridge.
 * 
 * @author cstamas
 */
public class ApplicationContentBridge
    extends ApplicationBridge
{

    /**
     * Constructor to enable usage in ServletRestletApplicationBridge.
     * 
     * @param context
     */
    public ApplicationContentBridge( Context context )
    {
        super( context );
    }

    /**
     * Creating restlet application root.
     */
    public Restlet createRoot()
    {
        configure();

        // instance filter, that injects proper Nexus instance into request attributes
        LocalNexusInstanceFilter nif = new LocalNexusInstanceFilter( getContext() );

        // simple anonymous guard
        NexusAuthenticationGuard nexusGuard = new NexusAuthenticationGuard( getContext() );

        // attaching it after nif
        nif.setNext( nexusGuard );

        BrowserSensingFilter browserFilter = new BrowserSensingFilter( getContext() );

        // next is filter
        nexusGuard.setNext( browserFilter );

        // creating _another_ router, that will be next isntance called after filtering
        Router router = new Router( getContext() );

        // setting the new router to be next instance
        browserFilter.setNext( router );

        // the content
        router.attach( "", ContentResourceHandler.class );

        // returning root
        return nif;
    }

}

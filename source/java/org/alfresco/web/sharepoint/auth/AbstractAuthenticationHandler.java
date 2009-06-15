/*
 * Copyright (C) 2005-2009 Alfresco Software Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.

 * As a special exception to the terms and conditions of version 2.0 of 
 * the GPL, you may redistribute this Program in connection with Free/Libre 
 * and Open Source Software ("FLOSS") applications as described in Alfresco's 
 * FLOSS exception.  You should have received a copy of the text describing 
 * the FLOSS exception, and it is also available here: 
 * http://www.alfresco.com/legal/licensing"
 */
package org.alfresco.web.sharepoint.auth;

import javax.servlet.http.HttpServletResponse;

import org.alfresco.repo.management.subsystems.ActivateableBean;
import org.alfresco.service.cmr.security.AuthenticationService;
import org.alfresco.service.cmr.security.PersonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Abstract implementation of web authentication.</p>
 * 
 * @author PavelYur
 *
 */
public abstract class AbstractAuthenticationHandler implements AuthenticationHandler, ActivateableBean
{
    protected Log logger = LogFactory.getLog(getClass());
    protected AuthenticationService authenticationService;
    protected PersonService personService;
    private boolean isActive = true;

    public void setAuthenticationService(AuthenticationService authenticationService)
    {
        this.authenticationService = authenticationService;
    }

    public void setPersonService(PersonService personService)
    {
        this.personService = personService;
    }
    
    public void setActive(boolean isActive)
    {
        this.isActive = isActive;
    }

    public boolean isActive()
    {
        return this.isActive;
    }

    /**
     * Returns the <i>value</i> of 'WWW-Authenticate' http header that determine what type of authentication to use by
     * client.
     * 
     * @return value
     */
    public abstract String getWWWAuthenticate();

    public void forceClientToPromptLogonDetails(HttpServletResponse response)
    {
        if (logger.isDebugEnabled())
            logger.debug("Force the client to prompt for logon details");

        response.setHeader(HEADER_WWW_AUTHENTICATE, getWWWAuthenticate());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
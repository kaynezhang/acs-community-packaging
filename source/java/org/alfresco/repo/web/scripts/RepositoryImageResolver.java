/*
 * Copyright (C) 2005-2007 Alfresco Software Limited.
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
 * FLOSS exception.  You should have recieved a copy of the text describing 
 * the FLOSS exception, and it is also available here: 
 * http://www.alfresco.com/legal/licensing"
 */
package org.alfresco.repo.web.scripts;

import javax.servlet.ServletContext;

import org.alfresco.service.cmr.repository.FileTypeImageSize;
import org.alfresco.service.cmr.repository.TemplateImageResolver;
import org.alfresco.web.ui.common.Utils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;


/**
 * Web Scripts Image Resolver
 * 
 * @author davidc
 */
public class RepositoryImageResolver
    implements ServletContextAware, InitializingBean
{
    private ServletContext servletContext;
    private TemplateImageResolver imageResolver;
    
    
    /* (non-Javadoc)
     * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
     */
    public void setServletContext(ServletContext context)
    {
        this.servletContext = context;
    }

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet()
        throws Exception
    {
        this.imageResolver = new TemplateImageResolver()
        {
            public String resolveImagePathForName(String filename, FileTypeImageSize size)
            {
                return Utils.getFileTypeImage(servletContext, filename, size);
            }  
        };        
    }

    /**
     * @return  image resolver
     */
    public TemplateImageResolver getImageResolver()
    {
        return this.imageResolver;
    }

}
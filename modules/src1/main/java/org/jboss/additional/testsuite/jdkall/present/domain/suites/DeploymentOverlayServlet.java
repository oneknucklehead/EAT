/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.additional.testsuite.jdkall.present.domain.suites;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.eap.additional.testsuite.annotations.EAT;

/**
 * @author Stuart Douglas
 */
@WebServlet(urlPatterns = "/*")
@EAT({"modules/testcases/jdkAll/WildflyRelease-13.0.0.Final/domain/src/main/java","modules/testcases/jdkAll/WildflyRelease-24.0.0.Final/domain/src/main/java","modules/testcases/jdkAll/Wildfly/domain/src/main/java","modules/testcases/jdkAll/ServerBeta/domain/src/main/java","modules/testcases/jdkAll/WildflyRelease-17.0.0.Final/domain/src/main/java","modules/testcases/jdkAll/WildflyRelease-20.0.0.Final/domain/src/main/java","modules/testcases/jdkAll/Eap72x/domain/src/main/java","modules/testcases/jdkAll/Eap72x-Proposed/domain/src/main/java","modules/testcases/jdkAll/Eap7/domain/src/main/java#7.3.0*7.3.9","modules/testcases/jdkAll/Eap71x-Proposed/domain/src/main/java","modules/testcases/jdkAll/Eap71x/domain/src/main/java"})
public class DeploymentOverlayServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        try {
            final String result = (String) new InitialContext().lookup("java:comp//env/simpleString");
            resp.getWriter().write(result);
            resp.getWriter().close();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}

/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
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
package org.jboss.additional.testsuite.jdkall.present.domain.management.cli;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jboss.as.test.integration.domain.management.util.DomainTestSupport;
import org.jboss.additional.testsuite.jdkall.present.domain.suites.CLITestSuite;
import org.jboss.as.test.integration.management.base.AbstractCliTestBase;
import org.jboss.eap.additional.testsuite.annotations.EAT;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dominik Pospisil <dpospisi@redhat.com>
 */
@EAT({"modules/testcases/jdkAll/WildflyRelease-13.0.0.Final/domain/src/main/java","modules/testcases/jdkAll/WildflyRelease-24.0.0.Final/domain/src/main/java","modules/testcases/jdkAll/Wildfly/domain/src/main/java","modules/testcases/jdkAll/ServerBeta/domain/src/main/java","modules/testcases/jdkAll/WildflyRelease-17.0.0.Final/domain/src/main/java","modules/testcases/jdkAll/WildflyRelease-20.0.0.Final/domain/src/main/java","modules/testcases/jdkAll/Eap72x/domain/src/main/java","modules/testcases/jdkAll/Eap72x-Proposed/domain/src/main/java","modules/testcases/jdkAll/Eap7/domain/src/main/java#7.3.0*7.3.9","modules/testcases/jdkAll/Eap71x-Proposed/domain/src/main/java","modules/testcases/jdkAll/Eap71x/domain/src/main/java"})
public class JmsTestCase extends AbstractCliTestBase {

    private static String profileName;

    @BeforeClass
    public static void before() throws Exception {
        CLITestSuite.initSuite();
        CLITestSuite.createSupport(JmsTestCase.class.getSimpleName());
        AbstractCliTestBase.initCLI(DomainTestSupport.masterAddress);
    }

    @AfterClass
    public static void after() throws Exception {
        AbstractCliTestBase.closeCLI();
        CLITestSuite.stopSupport();
    }

    @Before
    public void init() {
        profileName = CLITestSuite.serverProfiles.keySet().iterator().next();
    }

    @Test
    public void testAddRemoveJmsQueue() throws Exception {
        testAddJmsQueue();
        testRemoveJmsQueue();
    }

    @Test
    public void testAddRemoveJmsTopic() throws Exception {
        testAddJmsTopic();
        testRemoveJmsTopic();
    }

    private void testAddJmsQueue() throws Exception {

        // check the queue is not registered
        cli.sendLine("cd /profile=" + profileName + "/subsystem=messaging-activemq/server=default/jms-queue");
        cli.sendLine("ls");
        String ls = cli.readOutput();
        assertFalse(ls.contains("testJmsQueue"));

        // create queue
        cli.sendLine("/profile=" + profileName + "/subsystem=messaging-activemq/server=default/jms-queue=testJmsQueue:add(entries=[testJmsQueue])");

        // check it is listed
        cli.sendLine("cd /profile=" + profileName + "/subsystem=messaging-activemq/server=default/jms-queue");
        cli.sendLine("ls");
        ls = cli.readOutput();
        assertTrue(ls.contains("testJmsQueue"));
    }

    private void testRemoveJmsQueue() throws Exception {

        // create queue
        cli.sendLine("/profile=" + profileName + "/subsystem=messaging-activemq/server=default/jms-queue=testJmsQueue:remove()");

        // check it is listed
        cli.sendLine("cd /profile=" + profileName + "/subsystem=messaging-activemq/server=default/jms-queue");
        cli.sendLine("ls");
        String ls = cli.readOutput();
        assertFalse(ls.contains("testJmsQueue"));
    }

    private void testAddJmsTopic() throws Exception {

        // check the queue is not registered
        cli.sendLine("cd /profile=" + profileName + "/subsystem=messaging-activemq/server=default/jms-topic");
        cli.sendLine("ls");
        String ls = cli.readOutput();
        assertFalse(ls.contains("testJmsTopic"));

        // create topic
        cli.sendLine("/profile=" + profileName + "/subsystem=messaging-activemq/server=default/jms-topic=testJmsTopic:add(entries=[testJmsTopic])");

        // check it is listed
        cli.sendLine("cd /profile=" + profileName + "/subsystem=messaging-activemq/server=default/jms-topic");
        cli.sendLine("ls");
        ls = cli.readOutput();
        assertTrue(ls.contains("testJmsTopic"));
    }

    private void testRemoveJmsTopic() throws Exception {

        // create queue
        cli.sendLine("/profile=" + profileName + "/subsystem=messaging-activemq/server=default/jms-topic=testJmsTopic:remove()");

        // check it is listed
        cli.sendLine("cd /profile=" + profileName + "/subsystem=messaging-activemq/server=default/jms-topic");
        cli.sendLine("ls");
        String ls = cli.readOutput();
        assertFalse(ls.contains("testJmsTopic"));
    }
}

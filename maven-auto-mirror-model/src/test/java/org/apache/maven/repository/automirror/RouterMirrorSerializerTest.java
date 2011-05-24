package org.apache.maven.repository.automirror;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.StringWriter;

import junit.framework.TestCase;

public class RouterMirrorSerializerTest
    extends TestCase
{

    public void testSerializeOneMirror()
        throws Exception
    {
        final MirrorRoutingTable mirrorMap =
            new MirrorRoutingTable().addMirror( new MirrorRoute( "central", "http://repo1.maven.org/maven2",
                                                             "http://localhost:8081/nexus", 99, true ) );

        final StringWriter sw = new StringWriter();
        MirrorRouteSerializer.serialize( mirrorMap, sw );

        System.out.println( sw );
    }

    public void testSerializeToStringOneMirror()
        throws Exception
    {
        final MirrorRoutingTable mirrorMap =
            new MirrorRoutingTable().addMirror( new MirrorRoute( "central", "http://repo1.maven.org/maven2",
                                                             "http://localhost:8081/nexus", 99, true ) );

        System.out.println( MirrorRouteSerializer.serializeToString( mirrorMap ) );
    }

    public void testRoundTripOneMirror()
        throws Exception
    {
        final MirrorRoutingTable mirrorMap =
            new MirrorRoutingTable().addMirror( new MirrorRoute( "central", "http://repo1.maven.org/maven2",
                                                             "http://localhost:8081/nexus", 99, true ) );

        final String ser = MirrorRouteSerializer.serializeToString( mirrorMap );
        final MirrorRoutingTable result = MirrorRouteSerializer.deserialize( ser );

        assertEquals( mirrorMap, result );
        assertTrue( result.getMirror( "http://repo1.maven.org/maven2" ).isEnabled() );
    }

}

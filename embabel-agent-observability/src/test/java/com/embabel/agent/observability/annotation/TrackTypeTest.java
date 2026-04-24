/*
 * Copyright 2024-2026 Embabel Pty Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.embabel.agent.observability.annotation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TrackTypeTest {

    @Test
    @DisplayName("Should contain all defined enumeration values")
    void shouldContainAllEnumValues() {
        TrackType[] types = TrackType.values();
        
        assertNotNull(types);
        assertEquals(6, types.length);
        
        assertEquals(TrackType.CUSTOM, TrackType.valueOf("CUSTOM"));
        assertEquals(TrackType.PROCESSING, TrackType.valueOf("PROCESSING"));
        assertEquals(TrackType.VALIDATION, TrackType.valueOf("VALIDATION"));
        assertEquals(TrackType.TRANSFORMATION, TrackType.valueOf("TRANSFORMATION"));
        assertEquals(TrackType.EXTERNAL_CALL, TrackType.valueOf("EXTERNAL_CALL"));
        assertEquals(TrackType.COMPUTATION, TrackType.valueOf("COMPUTATION"));
    }
}

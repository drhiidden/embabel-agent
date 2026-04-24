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
package com.embabel.agent.observability.observation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObservationKeysTest {

    @Test
    @DisplayName("Should have private constructor to prevent instantiation")
    void hasPrivateConstructor() throws Exception {
        Constructor<ObservationKeys> constructor = ObservationKeys.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()),
            "Constructor must be private to prevent instantiation");
    }

    @Test
    @DisplayName("Should format agentKey correctly")
    void shouldFormatAgentKey() {
        assertEquals("agent:12345", ObservationKeys.agentKey("12345"));
    }

    @Test
    @DisplayName("Should format actionKey correctly")
    void shouldFormatActionKey() {
        assertEquals("action:12345:myAction", ObservationKeys.actionKey("12345", "myAction"));
    }

    @Test
    @DisplayName("Should format llmKey correctly")
    void shouldFormatLlmKey() {
        assertEquals("llm:12345:interaction-1", ObservationKeys.llmKey("12345", "interaction-1"));
    }

    @Test
    @DisplayName("Should format toolLoopKey correctly")
    void shouldFormatToolLoopKey() {
        assertEquals("tool-loop:12345:interaction-1", ObservationKeys.toolLoopKey("12345", "interaction-1"));
    }

    @Test
    @DisplayName("Should format toolKey correctly")
    void shouldFormatToolKey() {
        assertEquals("tool:12345:myTool", ObservationKeys.toolKey("12345", "myTool"));
    }

    @Test
    @DisplayName("Should format toolSpanName correctly")
    void shouldFormatToolSpanName() {
        assertEquals("tool:myTool", ObservationKeys.toolSpanName("myTool"));
    }

    @Test
    @DisplayName("Should format toolLoopSpanName correctly")
    void shouldFormatToolLoopSpanName() {
        // According to the logic in ObservationKeys, it ignores the interactionId
        assertEquals("tool-loop:", ObservationKeys.toolLoopSpanName("interaction-1"));
    }
}

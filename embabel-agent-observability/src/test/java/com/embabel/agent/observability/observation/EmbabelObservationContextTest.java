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

import static org.junit.jupiter.api.Assertions.*;

class EmbabelObservationContextTest {

    @Test
    @DisplayName("Should create root agent context correctly")
    void shouldCreateRootAgentContext() {
        // Arrange
        String runId = "root-run-123";
        String name = "rootAgent";

        // Act
        EmbabelObservationContext ctx = EmbabelObservationContext.rootAgent(runId, name);

        // Assert
        assertTrue(ctx.isRoot());
        assertEquals(runId, ctx.getRunId());
        assertEquals(name, ctx.getName());
        assertEquals(EmbabelObservationContext.EventType.AGENT_PROCESS, ctx.getEventType());
        assertNull(ctx.getParentRunId());
    }

    @Test
    @DisplayName("Should create sub agent context correctly")
    void shouldCreateSubAgentContext() {
        // Arrange
        String runId = "sub-run-123";
        String name = "subAgent";
        String parentId = "root-run-123";

        // Act
        EmbabelObservationContext ctx = EmbabelObservationContext.subAgent(runId, name, parentId);

        // Assert
        assertFalse(ctx.isRoot());
        assertEquals(runId, ctx.getRunId());
        assertEquals(name, ctx.getName());
        assertEquals(EmbabelObservationContext.EventType.AGENT_PROCESS, ctx.getEventType());
        assertEquals(parentId, ctx.getParentRunId());
    }

    @Test
    @DisplayName("Should create action context correctly")
    void shouldCreateActionContext() {
        // Arrange
        String runId = "run-123";
        String actionName = "myAction";

        // Act
        EmbabelObservationContext ctx = EmbabelObservationContext.action(runId, actionName);

        // Assert
        assertFalse(ctx.isRoot());
        assertEquals(runId, ctx.getRunId());
        assertEquals(actionName, ctx.getName());
        assertEquals(EmbabelObservationContext.EventType.ACTION, ctx.getEventType());
        assertNull(ctx.getParentRunId());
    }

    @Test
    @DisplayName("Should create goal context correctly")
    void shouldCreateGoalContext() {
        // Arrange & Act
        EmbabelObservationContext ctx = EmbabelObservationContext.goal("r1", "g1");

        // Assert
        assertEquals("r1", ctx.getRunId());
        assertEquals("g1", ctx.getName());
        assertEquals(EmbabelObservationContext.EventType.GOAL, ctx.getEventType());
    }

    @Test
    @DisplayName("Should create tool call context correctly")
    void shouldCreateToolCallContext() {
        // Arrange & Act
        EmbabelObservationContext ctx = EmbabelObservationContext.toolCall("r1", "t1");

        // Assert
        assertEquals("r1", ctx.getRunId());
        assertEquals("t1", ctx.getName());
        assertEquals(EmbabelObservationContext.EventType.TOOL_CALL, ctx.getEventType());
    }

    @Test
    @DisplayName("Should create planning context correctly")
    void shouldCreatePlanningContext() {
        // Arrange & Act
        EmbabelObservationContext ctx = EmbabelObservationContext.planning("r1", "p1");

        // Assert
        assertEquals("r1", ctx.getRunId());
        assertEquals("p1", ctx.getName());
        assertEquals(EmbabelObservationContext.EventType.PLANNING, ctx.getEventType());
    }

    @Test
    @DisplayName("Should create state transition context correctly")
    void shouldCreateStateTransitionContext() {
        // Arrange & Act
        EmbabelObservationContext ctx = EmbabelObservationContext.stateTransition("r1", "s1");

        // Assert
        assertEquals("r1", ctx.getRunId());
        assertEquals("s1", ctx.getName());
        assertEquals(EmbabelObservationContext.EventType.STATE_TRANSITION, ctx.getEventType());
    }

    @Test
    @DisplayName("Should create lifecycle context correctly")
    void shouldCreateLifecycleContext() {
        // Arrange & Act
        EmbabelObservationContext ctx = EmbabelObservationContext.lifecycle("r1", "l1");

        // Assert
        assertEquals("r1", ctx.getRunId());
        assertEquals("l1", ctx.getName());
        assertEquals(EmbabelObservationContext.EventType.LIFECYCLE, ctx.getEventType());
    }

    @Test
    @DisplayName("Should create LLM call context correctly")
    void shouldCreateLlmCallContext() {
        // Arrange & Act
        EmbabelObservationContext ctx = EmbabelObservationContext.llmCall("r1", "llm1");

        // Assert
        assertEquals("r1", ctx.getRunId());
        assertEquals("llm1", ctx.getName());
        assertEquals(EmbabelObservationContext.EventType.LLM_CALL, ctx.getEventType());
    }

    @Test
    @DisplayName("Should create tool loop context correctly")
    void shouldCreateToolLoopContext() {
        // Arrange & Act
        EmbabelObservationContext ctx = EmbabelObservationContext.toolLoop("r1", "tl1");

        // Assert
        assertEquals("r1", ctx.getRunId());
        assertEquals("tl1", ctx.getName());
        assertEquals(EmbabelObservationContext.EventType.TOOL_LOOP, ctx.getEventType());
    }

    @Test
    @DisplayName("Should create ranking context correctly")
    void shouldCreateRankingContext() {
        // Arrange & Act
        EmbabelObservationContext ctx = EmbabelObservationContext.ranking("rk1");

        // Assert
        assertEquals("", ctx.getRunId());
        assertEquals("rk1", ctx.getName());
        assertEquals(EmbabelObservationContext.EventType.RANKING, ctx.getEventType());
    }

    @Test
    @DisplayName("Should create dynamic agent creation context correctly")
    void shouldCreateDynamicAgentCreationContext() {
        // Arrange & Act
        EmbabelObservationContext ctx = EmbabelObservationContext.dynamicAgentCreation("da1");

        // Assert
        assertEquals("", ctx.getRunId());
        assertEquals("da1", ctx.getName());
        assertEquals(EmbabelObservationContext.EventType.DYNAMIC_AGENT_CREATION, ctx.getEventType());
    }

    @Test
    @DisplayName("Should create RAG context correctly")
    void shouldCreateRagContext() {
        // Arrange & Act
        EmbabelObservationContext ctx = EmbabelObservationContext.rag("r1", "rag1");

        // Assert
        assertEquals("r1", ctx.getRunId());
        assertEquals("rag1", ctx.getName());
        assertEquals(EmbabelObservationContext.EventType.RAG, ctx.getEventType());
    }

    @Test
    @DisplayName("Should create custom context correctly")
    void shouldCreateCustomContext() {
        // Arrange & Act
        EmbabelObservationContext ctx = EmbabelObservationContext.custom("r1", "c1");

        // Assert
        assertEquals("r1", ctx.getRunId());
        assertEquals("c1", ctx.getName());
        assertEquals(EmbabelObservationContext.EventType.CUSTOM, ctx.getEventType());
    }
}

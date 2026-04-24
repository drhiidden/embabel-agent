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
package com.embabel.agent.api.annotation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

class AchievesGoalTest {

    @AchievesGoal(description = "Test default goal")
    public void defaultValuesMethod() {}

    @AchievesGoal(
        description = "Custom goal",
        value = 1.5,
        tags = {"test", "tag"},
        examples = {"example1", "example2"},
        export = @Export(name = "customExport")
    )
    public void customValuesMethod() {}

    @Test
    @DisplayName("Should verify default values of AchievesGoal annotation")
    void shouldHaveDefaultValues() throws NoSuchMethodException {
        // Arrange
        Method method = this.getClass().getMethod("defaultValuesMethod");
        
        // Act
        AchievesGoal annotation = method.getAnnotation(AchievesGoal.class);

        // Assert
        assertNotNull(annotation, "Annotation should be present");
        assertEquals("Test default goal", annotation.description());
        assertEquals(0.0, annotation.value());
        assertArrayEquals(new String[]{}, annotation.tags());
        assertArrayEquals(new String[]{}, annotation.examples());
        
        Export export = annotation.export();
        assertNotNull(export);
        assertEquals("", export.name());
        assertFalse(export.remote());
        assertTrue(export.local());
        assertArrayEquals(new Class<?>[]{}, export.startingInputTypes());
    }

    @Test
    @DisplayName("Should retain custom values of AchievesGoal annotation")
    void shouldRetainCustomValues() throws NoSuchMethodException {
        // Arrange
        Method method = this.getClass().getMethod("customValuesMethod");
        
        // Act
        AchievesGoal annotation = method.getAnnotation(AchievesGoal.class);

        // Assert
        assertNotNull(annotation, "Annotation should be present");
        assertEquals("Custom goal", annotation.description());
        assertEquals(1.5, annotation.value());
        assertArrayEquals(new String[]{"test", "tag"}, annotation.tags());
        assertArrayEquals(new String[]{"example1", "example2"}, annotation.examples());
        
        Export export = annotation.export();
        assertNotNull(export);
        assertEquals("customExport", export.name());
    }
}


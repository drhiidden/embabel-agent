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
import static org.junit.jupiter.api.Assertions.*;

class ExportTest {

    @Export
    public void defaultExportMethod() {}

    @Export(
        name = "customName",
        remote = true,
        local = false,
        startingInputTypes = {String.class, Integer.class}
    )
    public void customExportMethod() {}

    @Test
    @DisplayName("Should verify default values of Export annotation")
    void shouldHaveDefaultValues() throws NoSuchMethodException {
        // Arrange
        java.lang.reflect.Method method = this.getClass().getMethod("defaultExportMethod");
        
        // Act
        Export annotation = method.getAnnotation(Export.class);

        // Assert
        assertNotNull(annotation, "Annotation should be present");
        assertEquals("", annotation.name());
        assertFalse(annotation.remote());
        assertTrue(annotation.local());
        assertArrayEquals(new Class<?>[]{}, annotation.startingInputTypes());
    }

    @Test
    @DisplayName("Should retain custom values of Export annotation")
    void shouldRetainCustomValues() throws NoSuchMethodException {
        // Arrange
        java.lang.reflect.Method method = this.getClass().getMethod("customExportMethod");
        
        // Act
        Export annotation = method.getAnnotation(Export.class);

        // Assert
        assertNotNull(annotation, "Annotation should be present");
        assertEquals("customName", annotation.name());
        assertTrue(annotation.remote());
        assertFalse(annotation.local());
        assertArrayEquals(new Class<?>[]{String.class, Integer.class}, annotation.startingInputTypes());
    }
}


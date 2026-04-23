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
    void shouldHaveDefaultValues() throws NoSuchMethodException {
        Export annotation = this.getClass().getMethod("defaultExportMethod").getAnnotation(Export.class);

        assertNotNull(annotation, "Annotation should be present");
        assertEquals("", annotation.name());
        assertFalse(annotation.remote());
        assertTrue(annotation.local());
        assertArrayEquals(new Class<?>[]{}, annotation.startingInputTypes());
    }

    @Test
    void shouldRetainCustomValues() throws NoSuchMethodException {
        Export annotation = this.getClass().getMethod("customExportMethod").getAnnotation(Export.class);

        assertNotNull(annotation, "Annotation should be present");
        assertEquals("customName", annotation.name());
        assertTrue(annotation.remote());
        assertFalse(annotation.local());
        assertArrayEquals(new Class<?>[]{String.class, Integer.class}, annotation.startingInputTypes());
    }
}

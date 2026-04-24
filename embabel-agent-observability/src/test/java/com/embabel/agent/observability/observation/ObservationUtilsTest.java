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

import com.embabel.plan.Plan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ObservationUtilsTest {

    @Test
    @DisplayName("Should truncate string when it exceeds maximum length")
    void shouldTruncateString() {
        // Arrange
        String input = "1234567890";
        
        // Act
        String result = ObservationUtils.truncate(input, 5);
        
        // Assert
        assertEquals("12345...", result);
    }

    @Test
    @DisplayName("Should not truncate string when it is below maximum length")
    void shouldNotTruncateShortString() {
        // Arrange
        String input = "12345";
        
        // Act
        String result = ObservationUtils.truncate(input, 10);
        
        // Assert
        assertEquals("12345", result);
    }

    @Test
    @DisplayName("Should return empty string when truncating null")
    void shouldHandleNullInTruncate() {
        // Arrange & Act
        String result = ObservationUtils.truncate(null, 10);
        
        // Assert
        assertEquals("", result);
    }

    @Test
    @DisplayName("Should return empty array representation for null plan")
    void shouldFormatNullPlan() {
        // Arrange & Act
        String result = ObservationUtils.formatPlanSteps(null);
        
        // Assert
        assertEquals("[]", result);
    }
}

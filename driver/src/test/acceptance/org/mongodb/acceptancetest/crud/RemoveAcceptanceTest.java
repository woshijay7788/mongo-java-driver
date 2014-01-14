/*
 * Copyright (c) 2008 - 2014 MongoDB, Inc. <http://10gen.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mongodb.acceptancetest.crud;

import org.junit.Test;
import org.mongodb.DatabaseTestCase;
import org.mongodb.Document;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This acceptance test demonstrates the use and expected behaviour of the remove() and removeOne() methods.
 *
 * @since 3.0
 */
public class RemoveAcceptanceTest extends DatabaseTestCase {
    @Test
    public void shouldRemoveOnlyOneMatchingDocumentWithRemoveOne() {
        // Given
        Document firstDocument = new Document("_id", 1).append("a", 1);
        collection.insert(firstDocument);
        Document secondDocument = new Document("_id", 2).append("a", 1);
        collection.insert(secondDocument);

        // When
        Document searchCriteria = new Document("a", 1);
        collection.find(searchCriteria).removeOne();

        // Then
        assertThat(collection.find(searchCriteria).count(), is(1L));
    }

    @Test
    public void shouldRemoveAllMatchingDocumentsWithRemove() {
        // Given
        Document firstDocument = new Document("_id", 1).append("a", 1);
        collection.insert(firstDocument);
        Document secondDocument = new Document("_id", 2).append("a", 1);
        collection.insert(secondDocument);

        // When
        Document searchCriteria = new Document("a", 1);
        collection.find(searchCriteria).remove();

        // Then
        assertThat(collection.find(searchCriteria).count(), is(0L));
    }

}

/*
 * Copyright (c) 2008 - 2014 10gen, Inc. <http://10gen.com>
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

package org.bson.codecs;

import org.bson.BSONReader;
import org.bson.BSONWriter;
import org.bson.types.Undefined;

/**
 * Allows reading and writing of the BSON Undefined type.  On encoding, it will write the correct type to the BSONWriter, but ignore the
 * value, and on decoding it will read the type off the BSONReader and return an Undefined type, which simply represents a placeholder for
 * the undefined value.
 * <p/>
 * The undefined type is deprecated (see the spec).
 *
 * @see <a href="http://bsonspec.org/spec.html">BSON Spec</a>
 * @see org.bson.BSONType#UNDEFINED
 * @since 3.0
 */
public class UndefinedCodec implements Codec<Undefined> {
    @Override
    public Undefined decode(final BSONReader reader) {
        reader.readUndefined();
        return new Undefined();
    }

    @Override
    public void encode(final BSONWriter bsonWriter, final Undefined value) {
        bsonWriter.writeUndefined();
    }

    @Override
    public Class<Undefined> getEncoderClass() {
        return Undefined.class;
    }
}
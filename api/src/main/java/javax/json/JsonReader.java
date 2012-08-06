/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2012 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package javax.json;

import org.glassfish.json.JsonReaderImpl;

import java.io.Closeable;
import java.io.InputStream;
import java.io.Reader;

/**
 * A JSON reader that reads a JSON object or array from an input source.
 * For example:
 *
 * <code>
 * <pre>
 * An empty JSON array can be created as follows:
 *
 * JsonReader jsonReader = new JsonReader(new StringReader("[]"));
 * JsonObject obj = jsonReader.readObject();
 * jsonReader.close();
 * </pre>
 * </code>
 *
 * @author Jitendra Kotamraju
 */
public class JsonReader implements /*Auto*/Closeable {

    private final JsonReaderImpl impl;

    /**
     * Creates a JSON reader from a character stream
     *
     * @param reader a reader from which JSON is to be read
     * @return a JSON reader
     */
    public JsonReader(Reader reader) {
        impl = new JsonReaderImpl(reader);
    }

    /**
     * Creates a JSON reader from a character stream
     *
     * @param reader a character stream from which JSON is to be read
     * @return a JSON reader
     */
    public JsonReader(Reader reader, JsonConfiguration config) {
        impl = new JsonReaderImpl(reader, config);
    }

    /**
     * Creates a JSON reader from a byte stream. The character encoding of
     * the stream is determined as per the
     * <a href="http://tools.ietf.org/rfc/rfc4627.txt">RFC</a>.
     *
     * @param in a byte stream from which JSON is to be read
     * @return a JSON reader
     */
    public JsonReader(InputStream in) {
        impl = new JsonReaderImpl(in);
    }

    /**
     * Creates a JSON reader from a byte stream. The bytes of the stream
     * are decoded to characters using the specified encoding.
     *
     * @param in a byte stream from which JSON is to be read
     * @param encoding the character encoding of the stream
     * @return a JSON reader
     */
    public JsonReader(InputStream in, String encoding) {
        impl = new JsonReaderImpl(in, encoding);
    }

    /**
     * Creates a JSON reader from a byte stream. The bytes of the stream
     * are decoded to characters using the specified encoding. The created
     * reader is configured with the specified configuration.
     *
     * @param in a byte stream from which JSON is to be read
     * @return a JSON reader
     */
    public JsonReader(InputStream in, String encoding, JsonConfiguration config) {
        impl = new JsonReaderImpl(in, encoding, config);
    }

    /**
     * Returns a JSON array or object that is represented in
     * the input source. This method needs to be called
     * only once for a reader instance.
     *
     * @return a Json object or array
     * @throws JsonException if a JSON object or array cannot
     *     be created due to i/o error or incorrect representation
     * @throws IllegalStateException if this method, readObject, readArray or
     *     close method is already called
     */
    public JsonStructure read() {
        return impl.read();
    }

    /**
     * Returns a JSON object that is represented in
     * the input source. This method needs to be called
     * only once for a reader instance.
     *
     * @return a Json object or array
     * @throws JsonException if a JSON object or array cannot
     *     be created due to i/o error or incorrect representation
     * @throws IllegalStateException if this method, readObject, readArray or
     *     close method is already called
     */
    public JsonObject readObject() {
        return impl.readObject();
    }

    /**
     * Returns a JSON array that is represented in
     * the input source. This method needs to be called
     * only once for a reader instance.
     *
     * @return a Json object or array
     * @throws JsonException if a JSON object or array cannot
     *     be created due to i/o error or incorrect representation
     * @throws IllegalStateException if this method, readObject, readArray or
     *     close method is already called
     */
    public JsonArray readArray() {
        return impl.readArray();
    }

    /**
     * Closes this reader and frees any resources associated with the
     * reader. This doesn't close the underlying input source.
     */
    @Override
    public void close() {
        impl.close();
    }

}
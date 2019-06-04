/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pinot.core.realtime.impl.fakestream;

import java.util.List;
import org.apache.pinot.core.realtime.stream.MessageBatch;


/**
 * MessageBatch implementation for the fake stream
 */
public class FakeStreamMessageBatch implements MessageBatch<byte[]> {
  private List<Integer> _messageOffsets;
  private List<byte[]> _messageBytes;

  FakeStreamMessageBatch(List<Integer> messageOffsets, List<byte[]> messageBytes) {
    _messageOffsets = messageOffsets;
    _messageBytes = messageBytes;
  }

  public int getMessageCount() {
    return _messageOffsets.size();
  }

  public byte[] getMessageAtIndex(int index) {
    return _messageBytes.get(index);
  }

  public int getMessageOffsetAtIndex(int index) {
    return _messageOffsets.get(index);
  }

  public int getMessageLengthAtIndex(int index) {
    return _messageBytes.get(index).length;
  }

  public long getNextStreamMessageOffsetAtIndex(int index) {
    return _messageOffsets.get(index) + 1;
  }
}

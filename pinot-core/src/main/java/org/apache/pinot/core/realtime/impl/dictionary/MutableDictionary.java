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
package org.apache.pinot.core.realtime.impl.dictionary;

import javax.annotation.Nonnull;
import org.apache.pinot.core.segment.index.readers.BaseDictionary;


public abstract class MutableDictionary extends BaseDictionary {
  @Override
  public String getStringValue(int dictId) {
    return get(dictId).toString();
  }

  public boolean isSorted() {
    return false;
  }

  public abstract void index(@Nonnull Object rawValue);

  public abstract boolean inRange(@Nonnull String lower, @Nonnull String upper, int dictIdToCompare,
      boolean includeLower, boolean includeUpper);

  @Nonnull
  public abstract Object getMinVal();

  @Nonnull
  public abstract Object getMaxVal();

  @Nonnull
  public abstract Object getSortedValues();

  public abstract int getAvgValueSize();

  public abstract boolean isEmpty();

  /**
   * Returns the comparison result of value for dict Id 1 and dict Id 2, i.e. {@code value1.compareTo(value2)}.
   */
  public abstract int compare(int dictId1, int dictId2);

  /**
   * Helper method to identify if given (Comparable) value is in provided range.
   *
   * @param lower Lower value of range
   * @param upper Upper value of range
   * @param includeLower Include lower value in range
   * @param includeUpper Include upper value in range
   * @param value Value to compare
   * @param <T> Extends Comparable
   * @return True if value in range, false otherwise.
   */
  protected <T extends Comparable<T>> boolean valueInRange(@Nonnull T lower, @Nonnull T upper, boolean includeLower,
      boolean includeUpper, T value) {
    if (includeLower) {
      if (value.compareTo(lower) < 0) {
        return false;
      }
    } else {
      if (value.compareTo(lower) <= 0) {
        return false;
      }
    }

    if (includeUpper) {
      if (value.compareTo(upper) > 0) {
        return false;
      }
    } else {
      if (value.compareTo(upper) >= 0) {
        return false;
      }
    }
    return true;
  }
}

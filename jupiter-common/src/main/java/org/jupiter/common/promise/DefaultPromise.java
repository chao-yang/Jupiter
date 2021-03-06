/*
 * Copyright (c) 2015 The Jupiter Project
 *
 * Licensed under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Copyright 2013 Ray Tsang
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

package org.jupiter.common.promise;

public class DefaultPromise<D, F> extends AbstractPromise<D, F> {

    @Override
    public Promise<D, F> resolve(D resolve) {
        synchronized (this) {
            if (!isPending()) {
                throw new IllegalStateException("already finished, could not be resolve again");
            }

            resolveResult = resolve;
            state = State.RESOLVED;

            triggerDone(resolve);
        }
        return this;
    }

    @Override
    public Promise<D, F> reject(F reject) {
        synchronized (this) {
            if (!isPending()) {
                throw new IllegalStateException("already finished, could not be reject again");
            }

            rejectResult = reject;
            state = State.REJECTED;

            triggerFail(reject);
        }
        return this;
    }

    @Override
    public Promise<D, F> promise() {
        return this;
    }
}

/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.instantexecution

import groovy.transform.CompileStatic
import org.gradle.integtests.fixtures.android.AndroidHome
import org.gradle.integtests.fixtures.versions.AndroidGradlePluginVersions
import org.gradle.internal.scan.config.fixtures.GradleEnterprisePluginSettingsFixture
import org.gradle.test.fixtures.file.TestFile

import static org.gradle.integtests.fixtures.versions.AndroidGradlePluginVersions.usingAgpVersion


/**
 * Base Android / Instant execution integration test.
 */
@CompileStatic
abstract class AbstractInstantExecutionAndroidIntegrationTest extends AbstractInstantExecutionIntegrationTest {

    private static final AndroidGradlePluginVersions AGP_VERSIONS = new AndroidGradlePluginVersions()

    protected static final List<String> TESTED_AGP_VERSIONS = AGP_VERSIONS.getLatestsFromMinorPlusNightly("4.0")

    def setup() {
        AndroidHome.assumeIsSet()
    }

    void copyRemoteProject(String remoteProject) {
        new TestFile(new File("build/$remoteProject")).copyTo(testDirectory)
        GradleEnterprisePluginSettingsFixture.applyEnterprisePlugin(settingsFile)
    }

    void usingAgpVersion(String agpVersion) {
        usingAgpVersion(executer, buildFile, agpVersion)
    }

    void usingAgpVersion(TestFile buildFile, String agpVersion) {
        usingAgpVersion(executer, buildFile, agpVersion)
    }
}

/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.macgyver.plugin.artifactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import io.macgyver.okrest.OkRestTarget;

public interface ArtifactoryClient {

	OkRestTarget getBaseTarget();
	GavcSearchBuilder gavcSearch();
	PropertySearchBuilder propertySearch();
	DateSearchBuilder dateSearch();
	
	File fetchArtifactToDir(String path, File target) throws IOException;
	File fetchArtifactToFile(String path, File out) throws IOException;
	File fetchArtifactToTempFile(String path) throws IOException;
	InputStream fetchArtifact(String path) throws IOException;
	void delete(String path) throws IOException;
}

/*
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
package com.facebook.presto.spark.classloader_interface;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toMap;

public class PrestoSparkConfiguration
{
    private final Map<String, String> configProperties;
    private final String pluginsDirectoryPath;
    private final Map<String, Map<String, String>> catalogProperties;

    public PrestoSparkConfiguration(
            Map<String, String> configProperties,
            String pluginsDirectoryPath,
            Map<String, Map<String, String>> catalogProperties)
    {
        this.configProperties = unmodifiableMap(new HashMap<>(requireNonNull(configProperties, "configProperties is null")));
        this.pluginsDirectoryPath = requireNonNull(pluginsDirectoryPath, "pluginsDirectoryPath is null");
        this.catalogProperties = unmodifiableMap(requireNonNull(catalogProperties, "catalogProperties is null").entrySet().stream()
                .collect(toMap(Map.Entry::getKey, entry -> unmodifiableMap(new HashMap<>(entry.getValue())))));
    }

    public Map<String, String> getConfigProperties()
    {
        return configProperties;
    }

    public String getPluginsDirectoryPath()
    {
        return pluginsDirectoryPath;
    }

    public Map<String, Map<String, String>> getCatalogProperties()
    {
        return catalogProperties;
    }
}

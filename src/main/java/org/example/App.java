package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature;
import com.google.cloudbuild.v1.Build;
import com.google.protobuf.util.JsonFormat;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    private final ObjectMapper readingYamlMapper = new ObjectMapper(new YAMLFactory());
    private final ObjectMapper jsonMapper = new ObjectMapper();

    public Build parseBuildYaml(String gcbYaml) throws IOException {
        Object buildObj = readingYamlMapper.readValue(gcbYaml, Object.class);
        Build build = parseBuildJson(jsonMapper.writeValueAsString(buildObj));
        build = build.toBuilder()
            .putAllSubstitutions(new LinkedHashMap<String, String>())
            .build();
        return build;
    }

    Build parseBuildJson(String gcbJson) throws IOException {
        Build.Builder builder = Build.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(gcbJson, builder);
        return builder.build();
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}

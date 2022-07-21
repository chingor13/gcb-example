package org.example;

import static org.junit.Assert.assertTrue;

import com.google.cloudbuild.v1.Build;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void shouldParseYaml() throws IOException {
        App app = new App();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("example.yaml");
        String gcbYaml = new BufferedReader(
            new InputStreamReader(inputStream, StandardCharsets.UTF_8))
            .lines()
            .collect(Collectors.joining("\n"));
        Build build = app.parseBuildYaml(gcbYaml);
        System.out.println(build);
    }
}

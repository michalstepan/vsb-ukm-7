package sample.plugin;

/**
 * Created by Michal on 20.03.2016.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @goal processSource
 * @phase process-sources
 */
public class ProcessSourceMojo extends AbstractMojo {


    /**
     * Any Object to print out.
     *
     * @parameter expression="${echo.path}"
     * default-value="C://"
     */
    private Object path;


    /**
     * @parameter
     */
    private String[] regexes;



    /**
     * @parameter default-value="${project}"
     * @required
     * @readonly
     */
    MavenProject project;


    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Processing sources...");
        List<RegexPattern> regexPatterns = new ArrayList<>();
        for (String regex : regexes) {
            regexPatterns.add(new RegexPattern(regex));
        }

        List<Path> allFiles = new ArrayList<>();

        for (Object root : project.getCompileSourceRoots()) {
            try {
                Files.walk(Paths.get(root.toString()))
                        .filter(Files::isRegularFile)
                        .forEach(allFiles::add);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        for (Path file : allFiles) {
            try {
                String oneFile = readFile(file, Charset.defaultCharset());
                for (RegexPattern regex : regexPatterns){
                    int actualOccurance = regex.getOcurrance();
                    regex.setOcurrance(actualOccurance + StringUtils.countMatches(oneFile, regex.getRegexName()));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (RegexPattern pattern : regexPatterns){
            getLog().info("regex: " + pattern.getRegexName() + " occurance: " + pattern.getOcurrance());
        }

        try {
            save(path.toString(), regexPatterns);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    static String readFile(Path path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(path);
        return new String(encoded, encoding);
    }

    public void save(String fileName, List<RegexPattern> patterns) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(fileName +"mvnPluginOutput.txt"));
        for (RegexPattern pattern : patterns)
            pw.println("regex: " + pattern.getRegexName() + " occurance: " + pattern.getOcurrance());
        pw.close();
    }

}

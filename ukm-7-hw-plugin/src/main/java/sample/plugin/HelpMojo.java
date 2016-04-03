package sample.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Created by Michal on 20.03.2016.
 */
/**
 * @goal help
 */
public class HelpMojo extends AbstractMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("use -memoryInfo for memoryInfo");
        getLog().info("use -processSource for processSource");
    }
}

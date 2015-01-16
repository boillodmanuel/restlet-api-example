package org.restlet.example.contact.api.resource;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.io.File;

public class AppReadmeResource extends ServerResource {

    private static File README;

    static {
        String legalNoticePath = ClassLoader
                .getSystemResource("org/restlet/example/contact/api/readme.txt")
                .getFile();
        README = new File(legalNoticePath);
        if (!README.exists()) {
            throw new RuntimeException("File " + README.getAbsolutePath() + " does not exist");
        }
    }

    public AppReadmeResource() {
        setName("Readme");
        setDescription("Readme resource");
    }

    @Get("txt")
    public File getReadme() {
        return README;
    }

}

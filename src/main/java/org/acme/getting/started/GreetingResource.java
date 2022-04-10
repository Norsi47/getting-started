package org.acme.getting.started;

import org.atmosphere.config.service.Get;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    
    @Inject
    GreetingService greetingService;
    
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/greeting/{name}")
    //made path param match the variable name
    public String greeting(@PathParam("name") String name)  {
        return greetingService.greeting(name);
        
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/testingRest")
    public String hello() {
        return GreetingService.print();
    }
}
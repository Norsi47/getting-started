//package practice.onye.stuff;
//
//import javax.inject.Inject;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import java.awt.*;
//
//
//
//@Path("/practice")
//public class StartingOut {
//
//    @Inject
//    StartingOutService startingOutService;
//
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    @Path("/firstOne")
//    public String firstOne() {
//        return "Beginning Practice";
//    }
//
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    @Path("/secondOne/{name}")
//
//    public String secondOne(@PathParam("name")String name) {
//        return startingOutService.randomExample(name);
//
//    }
//
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    @Path("/getTextBox")
//
//    public void webSiteName () {
//        startingOutService.add();
//
//    }
//
//
//}

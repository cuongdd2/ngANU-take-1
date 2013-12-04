package ngANU

import javax.ws.rs._
import javax.ws.rs.core.{MediaType, Response}
import javax.ws.rs.core.Response.Status

@Path("test")
class TestApi {

  @GET
  @Path("/{id : (\\d*)}")
  def idProvided:String = "ID Provided"

  @GET
  @Path("/{name}")
  def nameProvided:String = "Name Provided"

  @GET
  @Path("/byName/{name}")
  def byname:String = "ByName"

  @GET
  def noneProvided:String = "None Provided"

}

package ngANU

import javax.ws.rs._
import javax.ws.rs.core.{MediaType, Response}
import javax.ws.rs.core.Response.Status

import protocol.{GameDB, XmlRole}

@Path("char")
class CharApi {

  @GET
  @Path("/{id : (\\d*)}")
  @Produces(Array(MediaType.APPLICATION_XML))
  def byId(@PathParam("id") idParam:Int):String = {
    val role = Option(XmlRole.getRoleFromDB(idParam))
    role match {
      case Some(char) => new String(XmlRole.toXMLByteArray(char), "UTF-8")
      case None => notFound
    }
  }

  @GET
  @Path("/{name}")
  @Produces(Array(MediaType.APPLICATION_XML))
  def byName(@PathParam("name") nameParam:String):String = {
    val id = Option(GameDB.getRoleIdByName(nameParam))
    id match {
      case Some(id) => byId(id)
      case None => notFound
    }
  }

  def notFound = throw new WebApplicationException(Response.status(Status.NOT_FOUND).build)

}

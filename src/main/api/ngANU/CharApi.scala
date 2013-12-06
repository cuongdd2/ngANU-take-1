package ngANU

import javax.ws.rs._
import javax.ws.rs.core.{MediaType, Response}
import javax.ws.rs.core.Response.Status

import protocol.GameDB

@Path("char")
class CharApi {

  @GET
  @Path("/{id : (\\d*)}")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def getById(@PathParam("id") idParam:Int):model.CharBean = {
    val role = Option(GameDB.get(idParam))
    role match {
      case Some(char) => model.CharBean.map(char)
      case None => notFound
    }
  }

  @GET
  @Path("/{name}")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def getByName(@PathParam("name") nameParam:String):model.CharBean = {
    val id = Option(GameDB.getRoleIdByName(nameParam))
    id match {
      case Some(id) => getById(id)
      case None => notFound
    }
  }

  def notFound = throw new WebApplicationException(Response.status(Status.NOT_FOUND).build)
}

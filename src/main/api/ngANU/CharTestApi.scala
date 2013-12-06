package ngANU

import javax.ws.rs._
import javax.ws.rs.core.{MediaType, Response}
import javax.ws.rs.core.Response.Status

import protocol.{RoleBean, GameDB, XmlRole}

import org.modelmapper.ModelMapper
import ngANU.model.CharBean

@Path("c")
class CharTestApi {

  @GET
  @Path("/{id}")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def getById(@PathParam("id") idParam:Int):model.CharBean = {
    val role = Option(GameDB.get(idParam))
    role match {
      case Some(char) => {
        val bean = model.CharBean.map(char)
        bean
      }
      case None => notFound
    }
  }

  def notFound = throw new WebApplicationException(Response.status(Status.NOT_FOUND).build)
}

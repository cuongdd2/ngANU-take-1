package ngANU

import javax.ws.rs._
import javax.ws.rs.core.{MediaType, Response}
import javax.ws.rs.core.Response.Status

import protocol.{XmlRole, GameDB}

@Path("char")
class CharApi {

  @GET
  @Path("/{id}/xml")
  @Produces(Array(MediaType.APPLICATION_XML))
  def getCharXml(@PathParam("id") idParam:Int):String = {
    val role = Option(XmlRole.getRoleFromDB(idParam))
    role match {
      case Some(char) => new String(XmlRole.toXMLByteArray(char), "UTF-8")
      case None => StdResp.notFound
    }
  }

  @GET
  @Path("/{id : (\\d*)}")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def getById(@PathParam("id") idParam:Int):model.CharBean = {
    val role = Option(GameDB.get(idParam))
    role match {
      case Some(char) => model.CharBean.map(char)
      case None => StdResp.notFound
    }
  }

  @GET
  @Path("/{name}")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def getByName(@PathParam("name") nameParam:String):model.CharBean = {
    val id = Option(GameDB.getRoleIdByName(nameParam))
    id match {
      case Some(id) => getById(id)
      case None => StdResp.notFound
    }
  }

  @GET
  @Path("/info/{ids}")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def getCharInfo(@PathParam("ids") token:String):Array[model.CharInfo] = {
    token.split(",").map(id => model.CharInfo.fromRoleBean(GameDB.get(id.toInt)) )
  }
}

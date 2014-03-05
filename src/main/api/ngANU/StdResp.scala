package ngANU

import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status

object StdResp {
  def notFound = throw new WebApplicationException(Response.status(Status.NOT_FOUND).build)
}

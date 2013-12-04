package ngANU

import javax.ws.rs.ext.{ ParamConverter, ParamConverterProvider,Provider }
import java.lang.annotation.Annotation
import java.lang.reflect.Type
import scala.util.{Failure, Success, Try}

@Provider
class OptionIntProvider extends ParamConverterProvider {
  @Override
  def getConverter[T](t: Class[T], genericType: Type, annotations: Array[Annotation]): ParamConverter[T] = {
    if (t.equals(classOf[Option[Int]])) {
      OptionIntConverter.asInstanceOf[ParamConverter[T]]
    }
    else {
      null
    }
  }

  object OptionIntConverter extends ParamConverter[Option[Int]] {
    def fromString(p1: String): Option[Int] = {
      val i = Try(p1.toInt)
      i match {
        case Success(v) => Some(v)
        case Failure(e) => None
      }
    }

    def toString(p1: Option[Int]): String = p1.toString()
  }
}

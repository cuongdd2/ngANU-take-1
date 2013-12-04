import com.goldhuman.Common.Conf;
import com.goldhuman.Common.Octets;
import com.goldhuman.Common.ThreadPool;
import com.goldhuman.IO.PollIO;
import com.goldhuman.IO.Protocol.Protocol;
import com.goldhuman.service.GMServiceImpl;
import com.goldhuman.util.PresentGoodsId;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import protocol.ClientManager;
import protocol.DeliveryClientManager;
import protocol.IwebVersionManager;

public class Startup extends HttpServlet
{
    private static final Log log = LogFactory.getLog(Startup.class);

    public void destroy()
    {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {}

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {}

    public void init()
            throws ServletException
    {
        Conf conf = Conf.GetInstance("/etc/iweb.conf", null);
        Octets.setDefaultCharset("UTF-16LE");
        GMServiceImpl gmi = new GMServiceImpl();
//        try
//        {
//            Class.forName("protocol.DeliveryDB");
//            Runtime.getRuntime().exec("/bin/mkdir -p /var/spool/moverole/incoming");
//            Runtime.getRuntime().exec("/bin/mkdir -p /var/spool/moverole/outcoming");
//            Runtime.getRuntime().exec("/bin/mkdir -p /var/spool/rolexml/incoming");
//            Runtime.getRuntime().exec("/bin/mkdir -p /var/spool/rolexml/outcoming");
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        try
//        {
//            boolean pidon = conf.find("iweb", "pidon").equals("true");
//            if (pidon)
//            {
//                String pkey = "0123456789123456";
//                URL presentgoodsFile = Startup.class.getResource("/presentgoodsid.txt");
//                PresentGoodsId.getInstance(pkey, presentgoodsFile);
//                System.out.println("ids=" + PresentGoodsId.getInstance().toString());
//            }
//            else
//            {
//                PresentGoodsId.getInstance(null, null);
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
        IwebVersionManager.getInstance();
        try
        {
            Protocol.Client(ClientManager.GetInstance());
        }
        catch (Exception e)
        {
            System.out.println("connect Game  error");
            e.printStackTrace();
        }
        try
        {
            Protocol.Client(DeliveryClientManager.GetInstance());
        }
        catch (Exception e)
        {
            System.out.println("connect Delivery error");
            e.printStackTrace();
        }
        ThreadPool.AddTask(PollIO.GetTask());
    }
}

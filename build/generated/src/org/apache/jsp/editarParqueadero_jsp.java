package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import modelo.logica.Parqueadero;
import modelo.logica.GestorParqueadero;

public final class editarParqueadero_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    GestorParqueadero gestor = new GestorParqueadero();
    ArrayList<Parqueadero> cl = gestor.cargarParqueaderos();
    int i = 0;


      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<head>\n");
      out.write("    <meta charset='utf-8'>\n");
      out.write("    <title>Regsitro Parqueadero</title>\n");
      out.write("    <meta name='viewport' content='width=device-width, initial-scale=1'>\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Spartan:wght@300&display=swap\" rel=\"stylesheet\">\n");
      out.write("    <LINK REL=StyleSheet HREF=\"assets/style.css\" TYPE=\"text/css\" MEDIA=screen>\n");
      out.write("</head>\n");
      out.write("<script src=\"https://code.jquery.com/jquery-3.5.1.min.js\"\n");
      out.write("integrity=\"sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=\" crossorigin=\"anonymous\"></script>\n");
      out.write("<body>\n");
      out.write("    <header>\n");
      out.write("        <a href=\"index.html\">\n");
      out.write("            <img class=\"logo\" src=\"assets/img/logo-removebg-preview.png\" alt=\"\">\n");
      out.write("        </a>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"crearParqueadero.jsp\">Registro de parqueadero</a></li>\n");
      out.write("            <li><a id=\"elegida\" href=\"#\">Gestion de parqueadero</a></li>\n");
      out.write("            <li><a href=\"#\">Ingreso </a></li>\n");
      out.write("            <li><a href=\"#\">Historial Ingresos</a></li>\n");
      out.write("            <li><a href=\"crearContrato.jsp\">Vinculación contrato</a></li>\n");
      out.write("            <li><a href=\"#\">Gestión contratos</a></li>\n");
      out.write("            <li><a href=\"#\">Estadisticas</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </header>\n");
      out.write("    <div class=\"contenedor\">\n");
      out.write("        <h1 class=\"titulo\">Editar parqueadero</h1>\n");
      out.write("        <div id=\"contTabla\">\n");
      out.write("            <table>\n");
      out.write("                <tr>\n");
      out.write("                    <th>ID parqueadero</th>\n");
      out.write("                    <th>Nro de areas</th>\n");
      out.write("                    <th>NFS</th>\n");
      out.write("                    <th>Localidad</th>\n");
      out.write("                    <th>Dirección</th>\n");
      out.write("                    <th>DELETE</th>\n");
      out.write("                </tr>\n");
      out.write("                ");
 while (i < cl.size()) {
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>");
      out.print( cl.get(0).getK_idParqueadero());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( cl.get(0).getQ_areas());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( cl.get(0).getV_nfs());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( cl.get(0).getN_localidad());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( cl.get(0).getN_direccion());
      out.write("</td>\n");
      out.write("                    <td>DELETE</td>\n");
      out.write("                </tr>\n");
      out.write("                ");
i++;
                    }
      out.write("\n");
      out.write("\n");
      out.write("            </table>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</body>\n");
      out.write("<script type=\"text/javascript\" src=\"assets/scriptCrearParq.js\"></script>\n");
      out.write("\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

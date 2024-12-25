/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/11.0.2
 * Generated at: 2024-12-21 12:51:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;

public final class calcul_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Les JSPs ne permettent que GET, POST ou HEAD. Jasper permet aussi OPTIONS");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Résultat du Calcul d'Impôt</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <h1>Résultat du Calcul d'Impôt</h1>\r\n");
      out.write("    ");

        // Récupération des paramètres du formulaire
        double salaire = Double.parseDouble(request.getParameter("salaire"));
        String situation = request.getParameter("situation");
        int nbEnfants = Integer.parseInt(request.getParameter("enfants"));

        // Calcul du nombre de parts
        double nbParts = (situation.equals("celibataire")) ? 1.5 : (2 + nbEnfants / 2.0);

        // Calcul du revenu imposable
        double revenuImposable = 0.72 * salaire;

        // Calcul du quotient familial
        double quotientFamilial = revenuImposable / nbParts;

        // Tableau des seuils, taux et abattements
        double[][] tranches = {
            {12620, 0, 0},
            {13190, 0.05, 631},
            {15640, 0.1, 1290.5},
            {24740, 0.15, 2072.5},
            {31810, 0.2, 3309.5},
            {39970, 0.25, 4900},
            {48360, 0.3, 6898.5},
            {55790, 0.35, 9316.5},
            {92970, 0.4, 12106},
            {127860, 0.45, 16754.5},
            {151250, 0.5, 23147.5},
            {172040, 0.55, 30710},
            {195000, 0.6, 39312},
            {0, 0.65, 49062} // Dernière tranche par défaut
        };

        // Détermination du taux et de l'abattement
        double taux = 0;
        double abattement = 0;
        for (int i = 0; i < tranches.length; i++) {
            if (quotientFamilial <= tranches[i][0] || tranches[i][0] == 0) {
                taux = tranches[i][1];
                abattement = tranches[i][2];
                break;
            }
        }

        // Calcul de l'impôt
        double impot = (taux * revenuImposable) - (abattement * nbParts);

        // Si l'impôt est négatif, on le ramène à 0
        if (impot < 0) {
            impot = 0;
        }
    
      out.write("\r\n");
      out.write("    <p><strong>Salaire annuel :</strong> ");
      out.print( salaire );
      out.write(" €</p>\r\n");
      out.write("    <p><strong>Situation familiale :</strong> ");
      out.print( situation.equals("celibataire") ? "Célibataire" : "Marié" );
      out.write("</p>\r\n");
      out.write("    <p><strong>Nombre d'enfants :</strong> ");
      out.print( nbEnfants );
      out.write("</p>\r\n");
      out.write("    <p><strong>Nombre de parts :</strong> ");
      out.print( nbParts );
      out.write("</p>\r\n");
      out.write("    <p><strong>Revenu imposable :</strong> ");
      out.print( revenuImposable );
      out.write(" €</p>\r\n");
      out.write("    <p><strong>Quotient familial :</strong> ");
      out.print( quotientFamilial );
      out.write(" €</p>\r\n");
      out.write("    <p><strong>Taux appliqué :</strong> ");
      out.print( taux * 100 );
      out.write("%</p>\r\n");
      out.write("    <p><strong>Abattement par part :</strong> ");
      out.print( abattement );
      out.write(" €</p>\r\n");
      out.write("    <p><strong>Impôt à payer :</strong> ");
      out.print( impot );
      out.write(" €</p>\r\n");
      out.write("    <br>\r\n");
      out.write("    <a href=\"index.jsp\">Retour</a>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
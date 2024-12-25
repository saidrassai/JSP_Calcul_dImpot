<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Résultat du Calcul d'Impôt</title>
</head>
<body>
    <h1>Résultat du Calcul d'Impôt</h1>
    <%
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
    %>
    <p><strong>Salaire annuel :</strong> <%= salaire %> MAD</p>
    <p><strong>Situation familiale :</strong> <%= situation.equals("celibataire") ? "Célibataire" : "Marié" %></p>
    <p><strong>Nombre d'enfants :</strong> <%= nbEnfants %></p>
    <p><strong>Nombre de parts :</strong> <%= nbParts %></p>
    <p><strong>Revenu imposable :</strong> <%= revenuImposable %> MAD</p>
    <p><strong>Quotient familial :</strong> <%= quotientFamilial %> MAD</p>
    <p><strong>Taux appliqué :</strong> <%= taux * 100 %>%</p>
    <p><strong>Abattement par part :</strong> <%= abattement %> MAD</p>
    <p><strong>Impôt à payer :</strong> <%= impot %> MAD</p>
    <br>
    <a href="index.jsp">Retour</a>
</body>
</html>

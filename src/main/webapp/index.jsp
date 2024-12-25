<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Calculateur d'Impôt</title>
    <!-- Bootstrap CSS for styling -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white text-center">
                <h3>Calculateur d'Impôt</h3>
            </div>
            <div class="card-body">
                <form action="calcul.jsp" method="post" id="taxForm">
                    <!-- Salary Input -->
                    <div class="form-group">
                        <label for="salaire">Salaire annuel :</label>
                        <input type="number" id="salaire" name="salaire" class="form-control" required>
                    </div>

                    <!-- Family Status Radio Buttons -->
                    <div class="form-group">
                        <label>Situation familiale :</label><br>
                        <div class="form-check form-check-inline">
                            <input type="radio" id="celibataire" name="situation" value="celibataire" class="form-check-input" checked>
                            <label for="celibataire" class="form-check-label">Célibataire</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input type="radio" id="marie" name="situation" value="marie" class="form-check-input">
                            <label for="marie" class="form-check-label">Marié</label>
                        </div>
                    </div>

                    <!-- Number of Children Input -->
                    <div class="form-group">
                        <label for="enfants">Nombre d'enfants :</label>
                        <input type="number" id="enfants" name="enfants" class="form-control" min="0" value="0" required>
                    </div>

                    <!-- Submit Button -->
                    <button type="submit" class="btn btn-success btn-block">Calculer</button>
                </form>
            </div>
        </div>
    </div>

    <!-- JavaScript Section -->
    <script>
        // Wait for the DOM to load
        document.addEventListener('DOMContentLoaded', () => {
            const form = document.getElementById('taxForm');
            const salaireInput = document.getElementById('salaire');
            const enfantsInput = document.getElementById('enfants');

            // Add validation on form submission
            form.addEventListener('submit', (event) => {
                const salaire = parseFloat(salaireInput.value);
                const enfants = parseInt(enfantsInput.value, 10);

                // Validate salary
                if (salaire <= 0 || isNaN(salaire)) {
                    alert("Veuillez entrer un salaire annuel valide supérieur à 0.");
                    event.preventDefault(); // Prevent form submission
                    return;
                }

                // Validate number of children
                if (enfants < 0 || isNaN(enfants)) {
                    alert("Le nombre d'enfants doit être un nombre valide (0 ou plus).");
                    event.preventDefault(); // Prevent form submission
                }
            });
        });
    </script>
</body>
</html>

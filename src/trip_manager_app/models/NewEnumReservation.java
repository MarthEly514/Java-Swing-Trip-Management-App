/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package trip_manager_app.models;

/**
 *
 * @author estev
 */
public enum NewEnumReservation {
    EN_ATTENTE("En attente"),
    VALIDE("Validé"),
    ANNULE("Annulé");

    private final String libelle;

    NewEnumReservation(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    // Conversion depuis String (pour le DAO)
    public static NewEnumReservation fromString(String text) {
        for (NewEnumReservation statut : NewEnumReservation.values()) {
            if (statut.name().equalsIgnoreCase(text) ||
                    statut.getLibelle().equalsIgnoreCase(text)) {
                return statut;
            }
        }
        return EN_ATTENTE; // Valeur par défaut
    }
}

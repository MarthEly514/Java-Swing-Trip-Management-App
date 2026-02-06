/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package trip_manager_app.models;

/**
 *
 * @author estev
 */
public enum EnumModePaiement {
    CARD,
    MOBILE_MONEY,
    CASH;
    
    // Ajouter pour conversion depuis String
    public static EnumModePaiement fromString(String text) {
        for (EnumModePaiement mode : EnumModePaiement.values()) {
            if (mode.name().equalsIgnoreCase(text)) {
                return mode;
            }
        }
        return CARD; // Valeur par défaut
    }
    
}

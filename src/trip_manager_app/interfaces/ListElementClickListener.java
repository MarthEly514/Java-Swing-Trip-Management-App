/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.interfaces;
import trip_manager_app.models.ReservationModel;

/**
 *
 * @author ely
 */
@FunctionalInterface
public interface ListElementClickListener {
    
    void onReservationClicked(ReservationModel reservation);
    
}

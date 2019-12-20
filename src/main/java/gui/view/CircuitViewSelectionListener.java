package gui.view;


import java.util.EventListener;

/**
 * <p>Titre : Torcs Tune</p>
 * <p>Description : Torcs tuning</p>
 * <p>Copyright : Copyright (c) 2002 Patrice Espie/p>
 * <p>Soci : </p>
 *
 * @author Patrice Espie
 * @version 0.1a
 */

public interface CircuitViewSelectionListener extends EventListener {

  void circuitViewSelectionChanged(CircuitViewSelectionEvent e);

}

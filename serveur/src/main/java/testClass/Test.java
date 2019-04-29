/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testClass;

import database.GestionnaireMenu;
import database.SQLAble;
import database.XMLAble;

/**
 *
 * @author kadidiatou
 */
public class Test extends XMLAble {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      GestionnaireMenu m = new GestionnaireMenu();
      m.getCartesDB();
      // System.out.print(conn);
        // TODO code application logic here
    }
    
}

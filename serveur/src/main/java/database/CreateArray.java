
package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
/**
 *
 * @author Tariq
 */

public class CreateArray {

    public static ARRAY toARRAY(List<String> list, Connection conn) throws SQLException {
        
        ARRAY sqlArray;
        STRUCT[] dbRoles = new STRUCT[list.size()];

        //descriptors for OBJECT type defined in database
        StructDescriptor tyString = StructDescriptor.createDescriptor("ITEM_", conn);
        ArrayDescriptor ttyString = ArrayDescriptor.createDescriptor("LIST_", conn);

        for (int i = 0; i < list.size(); i++) {
            Object[] role = new Object[]{list.get(i)};
            dbRoles[i] = new STRUCT(tyString, conn, role);
        }

        sqlArray = new ARRAY(ttyString, conn, dbRoles);

        return sqlArray;

    }   
}



/*
 * 可指挥接口，老人家，蝎子精实现。
 * 实现此接口完成布阵。
 */
package items;

import exceptions.NoSpaceForFormationException;
import formations.Formation;

public interface Leader {
    <T extends Formation> void embattleFormation(Class<T> formType)
            throws NoSpaceForFormationException;
}

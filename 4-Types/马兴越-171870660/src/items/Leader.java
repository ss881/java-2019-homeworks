/*
 * 可指挥接口，老人家，蝎子精实现。
 * 实现此接口完成布阵。
 */
package items;

import exceptions.NoSpaceForFormationException;
import formations.Formation;

public interface Leader {
    void standAsFormation(Formation formation)
            throws NoSpaceForFormationException;
}

package Graph;

import sun.security.provider.PolicyParser;

import java.util.Collection;
import java.util.function.Consumer;

/*
* 图中的节点
* 可以获取相连的节点
* Caution：在某些图结构中，节点自身不需要存储相连信息，由图结构代为存储
* 建议使用图结构来处理节点
 */
public interface GraphNode {
    Collection<? extends GraphNode> getAdjacentNodes();

    void addAdjacentNode(GraphNode node);

    /*
    * 对每一个相邻的node进行操作
     */
    void forEachAdjacentNode(Consumer<? super GraphNode> consumer);
}

package Graph;

/*
* 图结构，负责储存、连接、处理节点等功能
 */
public interface Graph<GNode extends GraphNode> {
    void addNode(GNode node);

    /*
    * 单向连接
     */
    void linkNode(GNode node1, GNode node2);

    /*
    * 双向连接
     */
    void biLinkNode(GNode node1, GNode node2);
}

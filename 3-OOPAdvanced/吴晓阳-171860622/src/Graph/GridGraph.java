package Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

/*
* 格式的图结构
* 采用矩阵存储
* 相邻节点即是相邻位置的格子
* 通常将GridNode进行继承扩展来存储和提供数据
 */
public class GridGraph implements Graph<GridGraph.GridNode> {
    private int rowSize;

    private int colSize;

    private GridNode[][] nodeMap;

    public GridGraph(int rowSize, int colSize){
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.nodeMap = new GridNode[rowSize][colSize];
    }

    @Override
    public void addNode(GridNode node) {
        if(checkPos(node.row, node.col) == false){
            throw new IllegalArgumentException();
        }
        nodeMap[node.row][node.col] = node;
    }

    @Override
    public void linkNode(GridNode node1, GridNode node2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void biLinkNode(GridNode node1, GridNode node2) {
        throw new UnsupportedOperationException();
    }

    private boolean checkPos(int row, int col){
        return (checkRow(row) && checkCol(col));
    }

    private boolean checkRow(int row){
        return row > 0 && row < rowSize;
    }

    private boolean checkCol(int col){
        return col > 0 && col < rowSize;
    }

    class GridNode implements GraphNode{
        private int row;

        private int col;

        public GridNode(int row, int col){
            this.row = row;
            this.col = col;
        }

        @Override
        public Collection<? extends GraphNode> getAdjacentNodes() {
            Collection<GridNode> nodes = new ArrayList<>();
            if(checkRow(row - 1)) nodes.add(nodeMap[row - 1][col]);
            if(checkRow(row + 1)) nodes.add(nodeMap[row + 1][col]);
            if(checkCol(col - 1)) nodes.add(nodeMap[row][col - 1]);
            if(checkCol(col + 1)) nodes.add(nodeMap[row][col + 1]);
            return nodes;
        }

        @Override
        public void addAdjacentNode(GraphNode node) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachAdjacentNode(Consumer<? super GraphNode> consumer) {
            if(checkRow(row - 1)) consumer.accept(((nodeMap[row - 1][col])));
            if(checkRow(row + 1)) consumer.accept((nodeMap[row + 1][col]));
            if(checkCol(col - 1)) consumer.accept((nodeMap[row][col - 1]));
            if(checkCol(col + 1)) consumer.accept((nodeMap[row][col + 1]));
        }
    }
}

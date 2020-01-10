package war;

public class Index {

    /*
     * id
     * 0 1 2 3 4 5 6 7 $ #  A  B  C  D  E  F  G  H
     * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17
     * */
    static int[] arr1 = {0,1,2,3, 4, 5, 6, 7, '$', '#',  'A', 'B','C', 'D', 'E', 'F','G','H'};
    static int[] arr2 = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};

    private static class Singleton{
        private static Index index = new Index();
    }

    private Index(){
        ;
    }


    public int getViewIndex(int id){
        int i = 0;
        while (arr1[i] != id){
            i++;
        }
        if(i>=arr2.length)
            return -1;
        return arr2[i];
    }

    public int getId(int idx){
        int i = 0;
        while (arr2[i] != idx){
            i++;
        }
        if(i>=arr1.length)
            return -1;
        return arr1[i];
    }

    public static Index getInstance(){
        return Singleton.index;
    }

    /*public static void main(String[] args){
        Index index = Index.getInstance();
        System.out.println(index.getViewIndex((int)'$')+" "
                +index.getViewIndex((int)'H')+" "+index.getViewIndex(4)+" "+index.getViewIndex((int)'#'));
        System.out.println(index.getId(8)+" "+index.getId(9)+" "+index.getId(3));
    }*/
}

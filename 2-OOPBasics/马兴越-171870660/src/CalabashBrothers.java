import java.util.*;

public class CalabashBrothers {
    private List<Calabash> members;
    public CalabashBrothers(){
        members=new ArrayList<>(7);
        for(int i=0;i<7;i++){
            members.add(new Calabash(i+1));
        }
    }

    /*
     * 随意站队，从前至后，分别往前移动随机个位置。
     */
    public void standRandomly(){
        System.out.println("随意站队");
        Random random=new Random();
        for(int i=1;i<members.size();i++){
            int n=random.nextInt(i+1),cur=i;
            for(int j=0;j<n;j++)
                moveForward(cur--);
        }
        tellOrders();
    }

    /*按排行冒泡法排序*/
    public void sortByOrder(){
        int n=members.size();
        boolean flag;//标志某一轮是否交换过
        for(int i=0;i<n-1;i++){
            flag=false;
            for(int j=0;j<n-i-1;j++){
                if(members.get(j+1).orderBefore(members.get(j))){
                    moveForward(j+1);
                    flag=true;
                }
            }
            if(!flag)
                break;
        }
    }

    /*
     * 交换i,j位置的元素，同时报告。
     * 报告时的位置序列是1~7。
     * 交换只在相邻两个位置之间完成。
     * 撤销定义
     */
//    private void swap(int i,int j){
//        Calabash temp=members.get(i);
//        members.set(i,members.get(j));
//        members.set(j,temp);
//        System.out.println(members.get(j).getName()+":"+(i+1)+"->"+(j+1));
//        System.out.println(members.get(i).getName()+":"+(j+1)+"->"+(i+1));
//    }

    public void tellOrders(){
        System.out.print("按排行报数:");
        for (Calabash member : members) {
            System.out.print(member.getName() + " ");
        }
        System.out.println("");
    }

    /*
     * 按颜色进行二分法排序。
     */
    public void sortByColor(){
        int n=members.size();
        for(int i=1;i<n;i++){
            insert(i,members.get(i));
        }
    }

    /*
     * 二分找到要插入的位置，插入并移动数组元素，同时报告。
     * 查找[最后一个比c小的位置]
     * 有解区间[left,right)
     * precondition: 不会出现相等元素；下标为end的位置原来放的就是c。
     */
    private void insert(int end, Calabash c){
        int left=0,right=end;
        if(c.colorBefore(members.get(left)))
            left=-1;//插入位置为0
        else if(members.get(end-1).colorBefore(c))
            return;
        else
            /*
             * loop invariant:
             * members[left]<c, members[right-1]>c
             * 有解区间：[left,right)
             */
            while(right-left>1){
                int mid=(left+right)/2;
                if(c.colorBefore(members.get(mid)))
                    right=mid;
                else
                    left=mid;
            }
        // post condition: [left+1] is the right place to be inserted.
        while(end>left+1)
            moveForward(end--);
    }

    /*
     * 向前移动一个位置，同时报告。
     */
    private void moveForward(int cur){
        Calabash c=members.get(cur);
        members.set(cur,members.get(cur-1));
        members.get(cur).moveBackward();
        members.set(cur-1,c);
        c.moveForward();
//        assert members.get(cur).currentPosition()-1 == cur;
    }

    public void tellColors(){
        System.out.print("按颜色报告: ");
        for (Calabash member : members) {
            System.out.print(member.getColorName()+" ");
        }
        System.out.print("\n");
    }

}

import javax.management.relation.RoleStatus;

class Huluwa{
    int rank;
    String color;
    
    public Huluwa(int rank){
        this.rank=rank;
        switch(rank){
            case 1:color="red";break;
            case 2:color="orange";break;
            case 3:color="yellow";break;
            case 4:color="green";break;
            case 5:color="cyan";break;
            case 6:color="blue";break;
            case 7:color="purpul";break;
        }
    }

    public void Say_rank(){
        System.out.println(rank);
    }

    
    public void Say_color(){
        System.out.println(color);
    }

    public void Say_change(int a,int b){
        System.out.println("rank"+rank+" :"+a+"-->"+b);
    }

    public int Compare_rank(Huluwa hulu){
        if(hulu.rank>rank){
            return 1;
        }
        else{
            return 0;
        }
    }

    public int Compare_color(Huluwa hulu){
        if(hulu.color.compareTo(this.color)>0){
            return 1;
        }
        else{
            return 0;
        }
    }

}
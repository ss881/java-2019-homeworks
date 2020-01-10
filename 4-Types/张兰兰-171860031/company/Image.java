package com.company;

public interface Image {
    public static void show(Picture pic) {
        switch(pic){
            case BLOCK:
            System.out.print(" ");
            break;
            case GR:
                System.out.print("G");
                break;
            case CA1:
                System.out.print("1");
                break;
            case CA2:
                System.out.print("2");
                break;
            case CA3:
                System.out.print("3");
                break;
            case CA4:
                System.out.print("4");
                break;
            case CA5:
                System.out.print("5");
                break;
            case CA6:
                System.out.print("6");
                break;
            case CA7:
                System.out.print("7");
                break;
            case SN:
                System.out.print("N");
                break;
            case SC:
                System.out.print("C");
                break;
            case PI:
                System.out.print("P");
                break;
            default:
                break;
        }
    }

}

import java.util.*;

class Control {
    public void processControl() {
        State s = new State();
        Interface i = new Interface();
        
        i.display(s);
        s.start(i);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while(true) {
            if(str.length() == 1) {
                if(str.charAt(0) == 'q') {
                    break;
                }
                else if(str.charAt(0) >= '0' && str.charAt(0) <= '6') {
                    s.changeUnderlingForm(str.charAt(0), i);
                }
                i.display(s);
                str = sc.nextLine();
            }
        }
        sc.close();
    }
}
�Ҵ�����Hulu_bro��Ϊ����ʹ��int num����ʶ��ͬ�ĺ�«�ֵܣ�
�����˹���������ʼ����

ʹ��enum��ʹ��int����ɫ�������Сһһ��Ӧ��
enum��Ϊ�࣬���ǿ���ʹ��values()��������ת��Ϊ���飬��ʵ��һһ��Ӧ�Ĺ�ϵ��
enum My_color{
    ��ɫ,��ɫ,��ɫ,��ɫ,��ɫ,��ɫ,��ɫ
};

enum Rank{
    �ϴ�,�϶�,����,����,����,����,����
};

ʹ�ú�����ʹ�ú�«�ֵܱ��������������˶���֮����Ϣ���ݵĻ���
public void tell_self(){
        System.out.print(Rank.values()[num] + " ");
}

public void tell_col(){
       System.out.print(My_color.values()[num] + " ");
}

ʹ�ö����������洢��«�ֵܣ��Ըö����������ð�ݺͶ�������
Hulu_bro []bros;
bros = new Hulu_bro[7];

�Լ�ʹ����list����������ת��Ϊlist������Collections.shuffle(list); ��ʵ�������ֵ

�������ʱ��ʹ���˹����������洢Hulu_bro���������ʵ�ֶ���֮��Ľ���
class BroWrapper{
    Hulu_bro x;
    BroWrapper(Hulu_bro x){
        this.x=x;
    }
}

public static void swap(BroWrapper x1,BroWrapper x2)
    {
        Hulu_bro temp=x1.x;
        x1.x=x2.x;
        x2.x=temp;
    }

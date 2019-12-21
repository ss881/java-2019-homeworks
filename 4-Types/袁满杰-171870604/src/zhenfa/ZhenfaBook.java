package zhenfa;

import exception.ZhenfaIDOutOfNumException;

public class ZhenfaBook {
    public int[] search(int character_id, int zhenfa_id, int size) throws ZhenfaIDOutOfNumException {
        int[] res = new int[2];
        int target_x = size / 2 + 1;
        int target_y = size / 2;
        if(zhenfa_id<0 || zhenfa_id>7)
            throw new ZhenfaIDOutOfNumException();
        if(character_id==-1)//watcher
        {
            target_x=size-1;
        }else if(character_id==0)//leader
        {
            switch (zhenfa_id){
                case 1:target_x+=3;break;
                case 2:target_x+=2;break;
                case 6:target_x+=2;target_y+=1;break;
            }
        }else {
            switch (zhenfa_id) {
                case 0://changshe
                    target_x += character_id;
                    break;
                case 1://heyi
                    if ((character_id + 1) % 2 == 0) {
                        target_y += (character_id + 1) >> 1;
                    } else {
                        target_y -= (character_id + 1) >> 1;
                    }
                    target_x += 6 - (character_id) >> 1;
                    break;
                case 2://yanxing
                    target_y -= character_id - 3;
                    target_x -= character_id - 5;
                    if (character_id == 3) {
                        target_y -= 2;
                        target_x -= 2;
                    }
                    break;
                case 3://chonge
                    target_x += character_id;
                    target_y += character_id % 2;
                    break;
                case 4://yuling
                    if (character_id < 4) {
                        target_x += character_id;
                        target_y -= character_id;
                    } else if (character_id < 6) {
                        target_x += character_id - 2;
                        target_y -= character_id - 4;
                    } else if (character_id < 9) {
                        target_x += character_id - 4;
                        target_y -= character_id - 8;
                    } else {
                        target_x += 3;
                        target_y += 3;
                    }
                    break;
                case 5://fangyen
                    target_x += (character_id + 1) / 2;
                    int temp = (character_id + 1) >> 1;
                    if (character_id % 2 == 0) {
                        if (temp < 2)
                            target_y += temp;
                        else
                            target_y += 4 - temp;
                    } else {
                        if (temp < 2)
                            target_y -= temp;
                        else
                            target_y -= 4 - temp;
                    }
                    break;
                case 6://yanyue
                    target_x += 2;
                    target_y += 1;
                    if (character_id == 18) {
                        target_x -= 1;
                    } else if (character_id == 17) {
                        target_x += 1;
                    } else if (character_id <= 5) {
                        target_y -= 1;
                        target_x += character_id - 3;
                    } else if (character_id <= 12) {
                        target_y -= 2;
                        target_x += character_id - 9;
                    } else if (character_id == 13) {
                        target_x += 3;
                        target_y -= 3;
                    } else if (character_id == 14) {
                        target_x -= 3;
                        target_y -= 3;
                    } else if (character_id == 15) {
                        target_x += 4;
                        target_y -= 3;
                    } else if (character_id == 16) {
                        target_x -= 4;
                        target_y -= 3;
                    }
                    break;
                case 7://fengshi
                    if (character_id <= 6) {
                        if ((character_id + 1) % 2 == 0) {
                            target_y += (character_id + 1) >> 1;
                        } else {
                            target_y -= (character_id + 1) >> 1;
                        }
                        target_x += (character_id + 1) >> 1;
                    } else {
                        target_x += character_id - 6;
                    }
                    break;
            }
        }
        res[0] = target_x;
        res[1] = target_y;
        return res;
    }
}
package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Formation {
	//0
    private Coordinate ufishscale[] = {
        new Coordinate(0, 7),
        new Coordinate(1, 6), new Coordinate(1, 8),
        new Coordinate(2, 5), new Coordinate(2, 7),
        new Coordinate(3, 6), new Coordinate(3, 8),
        new Coordinate(4, 7)
    };
    /*final char ufishscale[][] = {
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', 'U'},
        {' ', ' ', ' ', ' ', ' ', 'U', ' ', 'U', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', 'U'},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' '}
    };*/
    //1
    private Coordinate ucrescentmoon[] = {
        new Coordinate(0, 7),
        new Coordinate(1, 5), new Coordinate(1, 6),
        new Coordinate(2, 5), new Coordinate(2, 6),
        new Coordinate(3, 5), new Coordinate(3, 6),
        new Coordinate(4, 7)
    };
    /*final char ucrescentmoon[][] = {
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' '},
        {' ', ' ', ' ', ' ', ' ', 'U', 'U', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'U', 'U', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'U', 'U', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' '}
    };*/
    //2
    private Coordinate uarrow[] = {
        new Coordinate(0, 7),
        new Coordinate(1, 6),
        new Coordinate(2, 5), new Coordinate(2, 6), new Coordinate(2, 7), new Coordinate(2, 8),
        new Coordinate(3, 6),
        new Coordinate(4, 7)
    };
    /*final char uarrow[][] = {
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'U', 'U', 'U', 'U'},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' '}
    };*/
    
    //0
    private Coordinate cfishscale[] = {
    	new Coordinate(0, 1),
    	new Coordinate(1, 0), new Coordinate(1, 2),
    	new Coordinate(2, 1), new Coordinate(2, 3),
    	new Coordinate(3, 0), new Coordinate(3, 2),
    	new Coordinate(4, 1)
    };
    /*final char cfishscale[][] = {
    	{' ', 'U', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    	{'U', ' ', 'U', ' ', ' ', ' ', ' ', ' ', ' '},
    	{' ', 'U', ' ', 'U', ' ', ' ', ' ', ' ', ' '},
    	{'U', ' ', 'U', ' ', ' ', ' ', ' ', ' ', ' '},
    	{' ', 'U', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };*/
    //1
    private Coordinate ccrescentmoon[] = {
        new Coordinate(0, 1),
        new Coordinate(1, 2), new Coordinate(1, 3),
        new Coordinate(2, 2), new Coordinate(2, 3),
        new Coordinate(3, 2), new Coordinate(3, 3),
        new Coordinate(4, 1)
    };
    /*final char ccrescentmoon[][] = {
        {' ', 'U', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', 'U', 'U', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', 'U', 'U', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', 'U', 'U', ' ', ' ', ' ', ' ', ' '},
        {' ', 'U', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };*/
    //2
    private Coordinate carrow[] = {
        new Coordinate(0, 1),
        new Coordinate(1, 2),
        new Coordinate(2, 0), new Coordinate(2, 1), new Coordinate(2, 2), new Coordinate(2, 3),
        new Coordinate(3, 2),
        new Coordinate(4, 1)
    };
    /*final char carrow[][] = {
        {' ', 'U', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', 'U', ' ', ' ', ' ', ' ', ' ', ' '},
        {'U', 'U', 'U', 'U', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', 'U', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', 'U', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };*/
    
    static char curform[][] = {
    	{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };

    private Coordinate calabashform[];
    private Coordinate underlingform[];
    
    static Object mutex = new Object();

    
    public void setCalabashForm(int i) {
    	switch(i) {
        	case 0: {
        		calabashform = cfishscale;
        		break;
        	}
        	case 1: {
        		calabashform = ccrescentmoon;
        		break;
        	}
        	case 2: {
        		calabashform = carrow;
        		break;
        	}
        	default: {
        		;
        	}
    	}
    }

    public void setUnderlingForm (int i) {
        switch(i) {
            case 0: {
                underlingform = ufishscale;
                break;
            }
            case 1: {
                underlingform = ucrescentmoon;
                break;
            }
            case 2: {
                underlingform = uarrow;
                break;
            }
            default: {
                ;
            }
        }
    }

    public Coordinate getCalaCoordinate(int i) {
        return new Coordinate(calabashform[i].getX(), calabashform[i].getY());
    }

    public Coordinate getUnderlingCoordinate(int i) {
        return new Coordinate(underlingform[i].getX(), underlingform[i].getY());
    }
    
    public void setCurForm(int ci, int ui) {
    	//curform[0][0] = 'G';
    	curform[0][8] = 'S';
    	curform[4][8] = 'N';
    	
    	curform[calabashform[0].getX()][calabashform[0].getY()] = '1';
    	curform[calabashform[1].getX()][calabashform[1].getY()] = '2';
    	curform[calabashform[2].getX()][calabashform[2].getY()] = '3';
    	curform[calabashform[3].getX()][calabashform[3].getY()] = '4';
    	curform[calabashform[4].getX()][calabashform[4].getY()] = '5';
    	curform[calabashform[5].getX()][calabashform[5].getY()] = '6';
    	curform[calabashform[6].getX()][calabashform[6].getY()] = '7';
    	
    	curform[calabashform[7].getX()][calabashform[7].getY()] = 'G';
    	
    	for(int i = 0; i < MainCanvas.UNDERLING_NUM; i++) {
    		curform[underlingform[i].getX()][underlingform[i].getY()] = 'U';
    	}
    	
    	/*for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 9; j++) {
    			System.out.print(curform[i][j]);
    		}
    		System.out.print("\n");
    	}*/
    }
    
    public static boolean nextStep(Coordinate curc) {
    	
    	synchronized(Formation.class) {
    		
    		Queue<Coordinate> q = new LinkedList<Coordinate>();
    		int x = curc.getX();
    		int y = curc.getY();
    		q.add(new Coordinate(x, y));
    		boolean visited[][] = new boolean[MainCanvas.ROW][MainCanvas.COLUMN];
    		for(int i = 0; i < MainCanvas.ROW; i++) {
    			for(int j = 0; j < MainCanvas.COLUMN; j++) {
    				visited[i][j] = false;
    			}
    		}
        
    		Coordinate prev[][] = new Coordinate[MainCanvas.ROW][MainCanvas.COLUMN];
    		for(int m = 0; m < MainCanvas.ROW; m++) {
    			for(int n = 0; n < MainCanvas.COLUMN; n++) {
    				prev[m][n] = new Coordinate(0, 0);
    			}
    		}
    		//ºùÂ«ÍÞ&Ò¯Ò¯
    		if((Formation.curform[x][y] >= '1' && Formation.curform[x][y] <= '7') || Formation.curform[x][y] == 'G') {
        	
    			if(x - 1 >= 0 && !visited[x - 1][y]) {
    				if(Formation.curform[x - 1][y] == 'S' || Formation.curform[x - 1][y] == 'N' || Formation.curform[x - 1][y] == 'U') {
    					Formation.curform[x][y] = ' ';
    					return false;
    				}
    			}
        
    			if(x + 1 < MainCanvas.ROW && !visited[x + 1][y]) {
    				if(Formation.curform[x + 1][y] == 'S' || Formation.curform[x + 1][y] == 'N' || Formation.curform[x + 1][y] == 'U') {
    					Formation.curform[x][y] = ' ';
    					return false;
    				}
    			}
        
    			if(y - 1 >= 0 && !visited[x][y - 1]) {
    				if(Formation.curform[x][y - 1] == 'S' || Formation.curform[x][y - 1] == 'N' || Formation.curform[x][y - 1] == 'U') {
    					Formation.curform[x][y] = ' ';
    					return false;
    				}
    			}
        
    			if(y + 1 < MainCanvas.COLUMN && !visited[x][y + 1]) {
    				if(Formation.curform[x][y + 1] == 'S' || Formation.curform[x][y + 1] == 'N' || Formation.curform[x][y + 1] == 'U') {
    					Formation.curform[x][y] = ' ';
    					return false;
    				}
    			}
        
    			boolean found = false;
    			while(!q.isEmpty() && !found) {
    				Coordinate c = q.poll();
    				int cx = c.getX();
    				int cy = c.getY();
    				//System.out.print(cx + "," + cy + "\n");
            
    				if(cx - 1 >= 0 && !visited[cx - 1][cy]) {
    					if(Formation.curform[cx - 1][cy] == ' ') {
    						q.add(new Coordinate(cx - 1, cy));
    						visited[cx - 1][cy] = true;
    						prev[cx - 1][cy] = c;
    					}
    					else if(Formation.curform[cx - 1][cy] == 'S' || Formation.curform[cx - 1][cy] == 'N' || Formation.curform[cx - 1][cy] == 'U') {
    						found = true;
    					}
    				}
            
    				if(cx + 1 < MainCanvas.ROW && !visited[cx + 1][cy]) {
    					if(Formation.curform[cx + 1][cy] == ' ') {
    						q.add(new Coordinate(cx + 1, cy));
    						visited[cx + 1][cy] = true;
    						prev[cx + 1][cy] = c;
    					}
    					else if(Formation.curform[cx + 1][cy] == 'S' || Formation.curform[cx + 1][cy] == 'N' || Formation.curform[cx + 1][cy] == 'U') {
    						found = true;
    					}
    				}
            
    				if(cy - 1 >= 0 && !visited[cx][cy - 1]) {
    					if(Formation.curform[cx][cy - 1] == ' ') {
    						q.add(new Coordinate(cx, cy - 1));
    						visited[cx][cy - 1] = true;
    						prev[cx][cy - 1] = c;
    					}
    					else if(Formation.curform[cx][cy - 1] == 'S' || Formation.curform[cx][cy - 1] == 'N' || Formation.curform[cx][cy - 1] == 'U') {
    						found = true;
    					}
    				}
            
    				if(cy + 1 < MainCanvas.COLUMN && !visited[cx][cy + 1]) {
    					if(Formation.curform[cx][cy + 1] == ' ') {
    						q.add(new Coordinate(cx, cy + 1));
    						visited[cx][cy + 1] = true;
    						prev[cx][cy + 1] = c;
    					}
    					else if(Formation.curform[cx][cy + 1] == 'S' || Formation.curform[cx][cy + 1] == 'N' || Formation.curform[cx][cy + 1] == 'U') {
    						found = true;
    					}
    				}
            
    				if(found) {
    					int srcx = x;
    					int srcy = y;
    					int dstx = cx;
    					int dsty = cy;
    					ArrayList<Coordinate> route = new ArrayList<Coordinate>();
    					route.add(new Coordinate(dstx, dsty));
    					Coordinate temp;
    					while(dstx != srcx || dsty != srcy) {
    						temp = prev[dstx][dsty];
    						route.add(temp);
    						dstx = temp.getX();
    						dsty = temp.getY();
    						//System.out.print("temp: " + x + "," + y + "\n");
    					}
    					if(route.size() >= 2) {
    						Coordinate newc = route.get(route.size() - 2);
    	            		int newx = newc.getX(), newy = newc.getY(), oldx = curc.getX(), oldy = curc.getY();
    	                	Formation.curform[newx][newy] = Formation.curform[oldx][oldy];
    	                	Formation.curform[oldx][oldy] = ' ';
    	                	curc.setX(newx);
    	                	curc.setY(newy);
    	                	return true;
    					}
    				}
    			}
    			//System.out.println("curc: " + curc.getX() + ", " + curc.getY() + "\t" + "cala: (-1, -1)");
    			return true;
    		}
    		//Ñý¹Ö
    		else {
    			if(x - 1 >= 0 && !visited[x - 1][y]) {
    				if((Formation.curform[x - 1][y] >= '1' && Formation.curform[x - 1][y] <= '7') || Formation.curform[x - 1][y] == 'G') {
    					Formation.curform[x][y] = ' ';
    					return false;
    				}
    			}
            
    			if(x + 1 < MainCanvas.ROW && !visited[x + 1][y]) {
    				if((Formation.curform[x + 1][y] >= '1' && Formation.curform[x + 1][y] <= '7') || Formation.curform[x + 1][y] == 'G') {
    					Formation.curform[x][y] = ' ';
    					return false;
    				}
    			}
            
    			if(y - 1 >= 0 && !visited[x][y - 1]) {
    				if((Formation.curform[x][y - 1] >= '1' && Formation.curform[x][y - 1] <= '7') || Formation.curform[x][y - 1] == 'G') {
    					Formation.curform[x][y] = ' ';
    					return false;
    				}
    			}
            
    			if(y + 1 < MainCanvas.COLUMN && !visited[x][y + 1]) {
    				if((Formation.curform[x][y + 1] >= '1' && Formation.curform[x][y + 1] <= '7') || Formation.curform[x][y + 1] == 'G') {
    					Formation.curform[x][y] = ' ';
    					return false;
    				}
    			}
            
    			boolean found = false;
    			while(!q.isEmpty() && !found) {
    				Coordinate c = q.poll();
    				int cx = c.getX();
    				int cy = c.getY();
    				//System.out.print(cx + "," + cy + "\n");
                
    				if(cx - 1 >= 0 && !visited[cx - 1][cy]) {
    					if(Formation.curform[cx - 1][cy] == ' ') {
    						q.add(new Coordinate(cx - 1, cy));
    						visited[cx - 1][cy] = true;
    						prev[cx - 1][cy] = c;
    					}
    					else if((Formation.curform[cx - 1][cy] >= '1' && Formation.curform[cx - 1][cy] <= '7') || Formation.curform[cx - 1][cy] == 'G') {
    						found = true;
    					}
    				}
                
    				if(cx + 1 < MainCanvas.ROW && !visited[cx + 1][cy]) {
    					if(Formation.curform[cx + 1][cy] == ' ') {
    						q.add(new Coordinate(cx + 1, cy));
    						visited[cx + 1][cy] = true;
    						prev[cx + 1][cy] = c;
    					}
    					else if((Formation.curform[cx + 1][cy] >= '1' && Formation.curform[cx + 1][cy] <= '7') || Formation.curform[cx + 1][cy] == 'G') {
    						found = true;
    					}
    				}
                
    				if(cy - 1 >= 0 && !visited[cx][cy - 1]) {
    					if(Formation.curform[cx][cy - 1] == ' ') {
    						q.add(new Coordinate(cx, cy - 1));
    						visited[cx][cy - 1] = true;
    						prev[cx][cy - 1] = c;
    					}
    					else if((Formation.curform[cx][cy - 1] >= '1' && Formation.curform[cx][cy - 1] <= '7') || Formation.curform[cx][cy - 1] == 'G') {
    						found = true;
    					}
    				}
                
    				if(cy + 1 < MainCanvas.COLUMN && !visited[cx][cy + 1]) {
    					if(Formation.curform[cx][cy + 1] == ' ') {
    						q.add(new Coordinate(cx, cy + 1));
    						visited[cx][cy + 1] = true;
    						prev[cx][cy + 1] = c;
    					}
    					else if((Formation.curform[cx][cy + 1] >= '1' && Formation.curform[cx][cy + 1] <= '7') || Formation.curform[cx][cy + 1] == 'G') {
    						found = true;
    					}
    				}
                
    				if(found) {
    					int srcx = x;
    					int srcy = y;
    					int dstx = cx;
    					int dsty = cy;
    					ArrayList<Coordinate> route = new ArrayList<Coordinate>();
    					route.add(new Coordinate(dstx, dsty));
    					Coordinate temp;
    					while(dstx != srcx || dsty != srcy) {
    						temp = prev[dstx][dsty];
    						route.add(temp);
    						dstx = temp.getX();
    						dsty = temp.getY();
    						//System.out.print("temp: " + x + "," + y + "\n");
    					}
                    
    					if(route.size() >= 2) {
    						Coordinate newc = route.get(route.size() - 2);
    	            		int newx = newc.getX(), newy = newc.getY(), oldx = curc.getX(), oldy = curc.getY();
    	                	Formation.curform[newx][newy] = Formation.curform[oldx][oldy];
    	                	Formation.curform[oldx][oldy] = ' ';
    	                	curc.setX(newx);
    	                	curc.setY(newy);
    	                	return true;
    					}
    				}
    			}
    			//System.out.println("curc: " + curc.getX() + ", " + curc.getY() + "\t" + "underling: (-1, -1)");
    			return true;
        	}
		}
    }

    /*public boolean isInUnderlingCoordinate(int x, int y) {
        for(int i = 0; i < underlingform.length; i++) {
            if(underlingform[i].getX() == x && underlingform[i].getY() == y) {
                return true;
            }
        }
        return false;
    }*/
}
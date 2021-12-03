package com.sxt;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
	//当前场景所显示的图像
	private BufferedImage bgImage = null;
	//记录当前为第几个场景
	private int sort; 
	//判断是否为最后一个场景
	private boolean flag;
	//用于存放所有障碍物图像
	private List<Obstacle> obstacleList = new ArrayList<>();
	//用于存放所有敌人图像
	private List<Enemy> enemyList = new ArrayList<>();
	//用于显示旗杆
	private BufferedImage gan = null;
	//用于显示城堡
	private BufferedImage burger = null;
	//判断坤坤是否到达旗杆位置
	private boolean isReach = false;
	//判断旗子是否落地
	private boolean isBase = false;
	//空参构造
	public BackGround()
	{
		
	}
	
	//带参构造
	public BackGround(int sort,boolean flag) {
		this.setSort(sort);
		this.setFlag(flag);
		
		if(flag) {
			setBgImage(StaticValue.bg2);
		}else {
			setBgImage(StaticValue.bg);
		}
		
		//以下代码用于绘制各关卡场景
		//type=1为上地面，type=2为下地面,type=0为可破坏的普通砖块，type=7为不可破坏的砖块,type=3，4，5，6为水管壁,type=8为旗子
		//窗口大小为800，图片大小为30，因此需要循环26次。
		
		//判断是否为第一关
		if(sort == 1) {
			//绘制第一关场景
			
			//绘制上地面
			for(int i = 0;i<27;i++) {
				obstacleList.add(new Obstacle(i*30,420,1,this));
			}
			
			//绘制下地面
			for(int j = 0;j<=120;j+=30) {
				for(int i = 0;i<27;i++) {
			
				obstacleList.add(new Obstacle(i*30,570-j,2,this));
				}
			}
			
			//绘制砖块A
			for(int i =120;i<=150;i+=30) {
				obstacleList.add(new Obstacle(i,300,7,this));
			}
			
			//绘制砖块B-F
			for(int i =300;i<=570;i+=30) {
				if(i==360||i==390||i==480||i==510||i==540) {
						obstacleList.add(new Obstacle(i,300,7,this));
				}else {
						obstacleList.add(new Obstacle(i,300,0,this));
				}
			}
			
			//绘制砖块G
			for(int i =420;i<=450;i+=30) {
				obstacleList.add(new Obstacle(i,240,7,this));
			}
			
			//绘制水管
			for(int i=360;i<=650;i+=25) {
				if(i==360) {
					obstacleList.add(new Obstacle(620,i,3,this));
					obstacleList.add(new Obstacle(650,i,4,this));
				}else {
					obstacleList.add(new Obstacle(620,i,5,this));
					obstacleList.add(new Obstacle(650,i,6,this));
				}
			}
			
			//绘制第一关蘑菇怪
			enemyList.add(new Enemy(580,385,true,1,this));
			//绘制第一关食人花
			enemyList.add(new Enemy(635,420,true,2,328,428,this));
			
		}
		//判断是否为第二关
		if(sort==2) {
		//绘制第二关场景
			
			//绘制上地面
			for(int i = 0;i<27;i++) {
				obstacleList.add(new Obstacle(i*30,420,1,this));
			}
			
			//绘制下地面
			for(int j = 0;j<=120;j+=30) {
				for(int i = 0;i<27;i++) {
			
				obstacleList.add(new Obstacle(i*30,570-j,2,this));
				}
			}
			
			//绘制第一个水管
			for(int i=360;i<=650;i+=25) {
				if(i==360) {
					obstacleList.add(new Obstacle(60,i,3,this));
					obstacleList.add(new Obstacle(85,i,4,this));
				}else {
					obstacleList.add(new Obstacle(60,i,5,this));
					obstacleList.add(new Obstacle(85,i,6,this));
				}
			}
			
			//绘制第二个水管
			for(int i=330;i<=650;i+=25) {
				if(i==330) {
					obstacleList.add(new Obstacle(620,i,3,this));
					obstacleList.add(new Obstacle(650,i,4,this));
				}else {
					obstacleList.add(new Obstacle(620,i,5,this));
					obstacleList.add(new Obstacle(650,i,6,this));
				}
			}
			
			//绘制砖块C
			obstacleList.add(new Obstacle(300,330,0,this));
			
			//绘制砖块B,E,G
			for(int i =270;i<=330;i+=30) {
				if(i==270||i==330) {
						obstacleList.add(new Obstacle(i,360,0,this));
				}else {
						obstacleList.add(new Obstacle(i,360,7,this));
				}
			}
			
			//绘制砖块A,D,F,H,I
			for(int i =240;i<=360;i+=30) {
				if(i==240||i==360) {
						obstacleList.add(new Obstacle(i,390,0,this));
				}else {
						obstacleList.add(new Obstacle(i,390,7,this));
				}
			}
			
			//绘制妨碍1砖块
			obstacleList.add(new Obstacle(240,300,0,this));
			
			//绘制空1-4砖块
			for(int i =360;i<=540;i+=60) {
						obstacleList.add(new Obstacle(i,270,7,this));
				}
			//绘制第二关第一只蘑菇怪
			enemyList.add(new Enemy(200,385,true,1,this));
			//绘制第二关第二只蘑菇怪
			enemyList.add(new Enemy(500,385,true,1,this));
			//绘制第二关第一朵食人花
			enemyList.add(new Enemy(75,420,true,2,328,418,this));
			//绘制第二关第二朵食人花
			enemyList.add(new Enemy(635,420,true,2,298,388,this));
			}
		
		//判断是否为第三关
		if(sort == 3) {
		//绘制第三关场景
			
			//绘制上地面
			for(int i = 0;i<27;i++) {
				obstacleList.add(new Obstacle(i*30,420,1,this));
			}
			
			//绘制下地面
			for(int j = 0;j<=120;j+=30) {
				for(int i = 0;i<27;i++) {
			
				obstacleList.add(new Obstacle(i*30,570-j,2,this));
				}
			}
			
			//绘制A-O砖块
			int temp = 290;
			for(int i=390;i>=270;i-=30) {
				for(int j=temp;j<=410;j+=30){
					obstacleList.add(new Obstacle(j,i,7,this));
				}
				temp+=30;
			}
			
			//绘制P-R砖块
			temp = 60;
			for(int i=390;i>=360;i-=30) {
				for(int j=temp;j<=90;j+=30){
					obstacleList.add(new Obstacle(j,i,7,this));
				}
				temp+=30;
			}
			
			//绘制旗杆
			setGan(StaticValue.gan);
			
			//绘制城堡
			setBurger(StaticValue.burger);
			
			//将旗子添加到旗杆上
			obstacleList.add(new Obstacle(520,220,8,this));
			
		}
		//绘制第三关蘑菇怪
		enemyList.add(new Enemy(150,385,true,1,this));
	}
	

	public BufferedImage getBgImage() {
		return bgImage;
	}

	public void setBgImage(BufferedImage bgImage) {
		this.bgImage = bgImage;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<Obstacle> getObstacleList() {
		return obstacleList;
	}

	public void setObstacleList(List<Obstacle> obstacleList) {
		this.obstacleList = obstacleList;
	}

	public BufferedImage getGan() {
		return gan;
	}

	public void setGan(BufferedImage gan) {
		this.gan = gan;
	}

	public BufferedImage getBurger() {
		return burger;
	}

	public void setBurger(BufferedImage burger) {
		this.burger = burger;
	}

	public boolean isReach() {
		return isReach;
	}

	public void setReach(boolean isReach) {
		this.isReach = isReach;
	}

	public boolean isBase() {
		return isBase;
	}

	public void setBase(boolean isBase) {
		this.isBase = isBase;
	}

	public List<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(List<Enemy> enemyList) {
		this.enemyList = enemyList;
	}
	
}

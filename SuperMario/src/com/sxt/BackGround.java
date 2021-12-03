package com.sxt;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
	//��ǰ��������ʾ��ͼ��
	private BufferedImage bgImage = null;
	//��¼��ǰΪ�ڼ�������
	private int sort; 
	//�ж��Ƿ�Ϊ���һ������
	private boolean flag;
	//���ڴ�������ϰ���ͼ��
	private List<Obstacle> obstacleList = new ArrayList<>();
	//���ڴ�����е���ͼ��
	private List<Enemy> enemyList = new ArrayList<>();
	//������ʾ���
	private BufferedImage gan = null;
	//������ʾ�Ǳ�
	private BufferedImage burger = null;
	//�ж������Ƿ񵽴����λ��
	private boolean isReach = false;
	//�ж������Ƿ����
	private boolean isBase = false;
	//�ղι���
	public BackGround()
	{
		
	}
	
	//���ι���
	public BackGround(int sort,boolean flag) {
		this.setSort(sort);
		this.setFlag(flag);
		
		if(flag) {
			setBgImage(StaticValue.bg2);
		}else {
			setBgImage(StaticValue.bg);
		}
		
		//���´������ڻ��Ƹ��ؿ�����
		//type=1Ϊ�ϵ��棬type=2Ϊ�µ���,type=0Ϊ���ƻ�����ͨש�飬type=7Ϊ�����ƻ���ש��,type=3��4��5��6Ϊˮ�ܱ�,type=8Ϊ����
		//���ڴ�СΪ800��ͼƬ��СΪ30�������Ҫѭ��26�Ρ�
		
		//�ж��Ƿ�Ϊ��һ��
		if(sort == 1) {
			//���Ƶ�һ�س���
			
			//�����ϵ���
			for(int i = 0;i<27;i++) {
				obstacleList.add(new Obstacle(i*30,420,1,this));
			}
			
			//�����µ���
			for(int j = 0;j<=120;j+=30) {
				for(int i = 0;i<27;i++) {
			
				obstacleList.add(new Obstacle(i*30,570-j,2,this));
				}
			}
			
			//����ש��A
			for(int i =120;i<=150;i+=30) {
				obstacleList.add(new Obstacle(i,300,7,this));
			}
			
			//����ש��B-F
			for(int i =300;i<=570;i+=30) {
				if(i==360||i==390||i==480||i==510||i==540) {
						obstacleList.add(new Obstacle(i,300,7,this));
				}else {
						obstacleList.add(new Obstacle(i,300,0,this));
				}
			}
			
			//����ש��G
			for(int i =420;i<=450;i+=30) {
				obstacleList.add(new Obstacle(i,240,7,this));
			}
			
			//����ˮ��
			for(int i=360;i<=650;i+=25) {
				if(i==360) {
					obstacleList.add(new Obstacle(620,i,3,this));
					obstacleList.add(new Obstacle(650,i,4,this));
				}else {
					obstacleList.add(new Obstacle(620,i,5,this));
					obstacleList.add(new Obstacle(650,i,6,this));
				}
			}
			
			//���Ƶ�һ��Ģ����
			enemyList.add(new Enemy(580,385,true,1,this));
			//���Ƶ�һ��ʳ�˻�
			enemyList.add(new Enemy(635,420,true,2,328,428,this));
			
		}
		//�ж��Ƿ�Ϊ�ڶ���
		if(sort==2) {
		//���Ƶڶ��س���
			
			//�����ϵ���
			for(int i = 0;i<27;i++) {
				obstacleList.add(new Obstacle(i*30,420,1,this));
			}
			
			//�����µ���
			for(int j = 0;j<=120;j+=30) {
				for(int i = 0;i<27;i++) {
			
				obstacleList.add(new Obstacle(i*30,570-j,2,this));
				}
			}
			
			//���Ƶ�һ��ˮ��
			for(int i=360;i<=650;i+=25) {
				if(i==360) {
					obstacleList.add(new Obstacle(60,i,3,this));
					obstacleList.add(new Obstacle(85,i,4,this));
				}else {
					obstacleList.add(new Obstacle(60,i,5,this));
					obstacleList.add(new Obstacle(85,i,6,this));
				}
			}
			
			//���Ƶڶ���ˮ��
			for(int i=330;i<=650;i+=25) {
				if(i==330) {
					obstacleList.add(new Obstacle(620,i,3,this));
					obstacleList.add(new Obstacle(650,i,4,this));
				}else {
					obstacleList.add(new Obstacle(620,i,5,this));
					obstacleList.add(new Obstacle(650,i,6,this));
				}
			}
			
			//����ש��C
			obstacleList.add(new Obstacle(300,330,0,this));
			
			//����ש��B,E,G
			for(int i =270;i<=330;i+=30) {
				if(i==270||i==330) {
						obstacleList.add(new Obstacle(i,360,0,this));
				}else {
						obstacleList.add(new Obstacle(i,360,7,this));
				}
			}
			
			//����ש��A,D,F,H,I
			for(int i =240;i<=360;i+=30) {
				if(i==240||i==360) {
						obstacleList.add(new Obstacle(i,390,0,this));
				}else {
						obstacleList.add(new Obstacle(i,390,7,this));
				}
			}
			
			//���Ʒ���1ש��
			obstacleList.add(new Obstacle(240,300,0,this));
			
			//���ƿ�1-4ש��
			for(int i =360;i<=540;i+=60) {
						obstacleList.add(new Obstacle(i,270,7,this));
				}
			//���Ƶڶ��ص�һֻĢ����
			enemyList.add(new Enemy(200,385,true,1,this));
			//���Ƶڶ��صڶ�ֻĢ����
			enemyList.add(new Enemy(500,385,true,1,this));
			//���Ƶڶ��ص�һ��ʳ�˻�
			enemyList.add(new Enemy(75,420,true,2,328,418,this));
			//���Ƶڶ��صڶ���ʳ�˻�
			enemyList.add(new Enemy(635,420,true,2,298,388,this));
			}
		
		//�ж��Ƿ�Ϊ������
		if(sort == 3) {
		//���Ƶ����س���
			
			//�����ϵ���
			for(int i = 0;i<27;i++) {
				obstacleList.add(new Obstacle(i*30,420,1,this));
			}
			
			//�����µ���
			for(int j = 0;j<=120;j+=30) {
				for(int i = 0;i<27;i++) {
			
				obstacleList.add(new Obstacle(i*30,570-j,2,this));
				}
			}
			
			//����A-Oש��
			int temp = 290;
			for(int i=390;i>=270;i-=30) {
				for(int j=temp;j<=410;j+=30){
					obstacleList.add(new Obstacle(j,i,7,this));
				}
				temp+=30;
			}
			
			//����P-Rש��
			temp = 60;
			for(int i=390;i>=360;i-=30) {
				for(int j=temp;j<=90;j+=30){
					obstacleList.add(new Obstacle(j,i,7,this));
				}
				temp+=30;
			}
			
			//�������
			setGan(StaticValue.gan);
			
			//���ƳǱ�
			setBurger(StaticValue.burger);
			
			//��������ӵ������
			obstacleList.add(new Obstacle(520,220,8,this));
			
		}
		//���Ƶ�����Ģ����
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

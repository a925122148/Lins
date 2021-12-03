package com.sxt;

import java.awt.image.BufferedImage;

public class Enemy implements Runnable {
	//存储当前坐标
	private int x;
	private int y;
	//存储敌人类型,type=1时敌人为蘑菇怪,type=2时敌人为食人花，
	private int type;
	//判断敌人运动的方向,true时表示蘑菇怪朝左运动,食人花朝上移动
	private boolean face_to =true;
	//用于显示敌人的当前图像
	private BufferedImage show;
	//定义背景对象
	private BackGround bg;
	//食人花运动的极限范围
	private int max_up = 0;
	private int max_down = 0;
	//定义线程，实现敌人运动
	private Thread thread = new Thread(this);
	//定义当前图片状态
	private int image_type = 0;
	
	//蘑菇怪构造函数
	public Enemy(int x,int y,boolean face_to,int type,BackGround bg) {
		this.x = x;
		this.y = y;
		this.face_to = face_to;
		this.type = type;
		this.bg = bg;
		show = StaticValue.mogu.get(0);
		thread.start();
	}

	//食人花构造函数
	public Enemy(int x,int y,boolean face_to,int type,int max_up,int max_down,BackGround bg) {
		this.x = x;
		this.y = y;
		this.face_to = face_to;
		this.type = type;
		this.bg = bg;
		this.max_up = max_up;
		this.max_down = max_down;
		show = StaticValue.flower.get(0);
		thread.start();
	}
	
	//敌人死亡,玩家只可杀死蘑菇怪,不可杀死食人花
	public void death() {
		show = StaticValue.mogu.get(2);
		this.bg.getEnemyList().remove(this);
	}
	

	public BufferedImage getShow() {
		return show;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getType() {
		return type;
	}
	
	@Override
	public void run() {
		while(true) {
			//敌人是否为蘑菇怪
			if(type == 1) {//此时敌人为蘑菇怪
				if(face_to) {//此时face_to为true，敌人向左移动
					this.x -= 2;
				}else {//此时face_to为false，敌人向右移动
					this.x += 2;
				}			
				image_type = image_type == 1 ? 0 : 1 ;
			
				show = StaticValue.mogu.get(image_type);
			}

			//定义两个布尔变量，用于判断蘑菇怪是否可以向左或向右移动
			boolean canLeft = true;
			boolean canRight = true;
			
			for(int i=0;i<bg.getObstacleList().size();i++) {
				Obstacle ob1 = bg.getObstacleList().get(i);
				//判断是否可向右走
				if(ob1.getX() == this.x+36 && (ob1.getY()+65>this.y && ob1.getY()-35<this.y)) {
					canRight = false;
				}
				
				//判断是否可向左走
				if(ob1.getX() == this.x-36 && (ob1.getY()+65>this.y && ob1.getY()-35<this.y)) {
					canLeft = false;
				}
			}
			
			if(face_to && !canLeft ||this.x == 0) {//此时蘑菇怪在左侧碰到了障碍物或走到了屏幕最左侧
				//此时调转蘑菇怪的方向，使之向右走
				face_to = false;
			}else if((!face_to) && (!canRight) ||this.x == 764) {//此时蘑菇怪在右侧碰到了障碍物或走到了屏幕最右侧
				//此时调转蘑菇怪的方向，使之向左走
				face_to = true;
			}
			
			//判断敌人是否为食人花
			if(type == 2) {
				if(face_to) {//此时朝上移动
					this.y -= 2;
				}else {//此时朝下移动
					this.y += 2;
				}
				
				image_type = image_type == 1 ? 0 : 1 ;
				
				//判断食人花是否到达极限位置
				if(face_to && (this.y == max_up)) {
					face_to = false;
				}
				if((!face_to) && (this.y == max_down)) {
					face_to = true;
				}
				
				show = StaticValue.flower.get(image_type);
			}
			
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}

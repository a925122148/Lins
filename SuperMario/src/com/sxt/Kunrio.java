package com.sxt;

import java.awt.image.BufferedImage;

public class Kunrio implements Runnable{
	
	//用于表示横坐标
	private int x;
	
	//用于表示纵坐标
	private int y;
	
	//用于表示当前状态
	private String status;
	
	//用于显示当前状态所对应的图像
	private BufferedImage show = null;
	
	//定义一个BackGround对象，用于获取障碍物信息
	private BackGround backGround = new BackGround();
	
	//用于实现玩家的动作
	private Thread thread = null;
	
	//坤坤的移动速度
	private int xSpeed;
	
	//坤坤的跳跃速度
	private int ySpeed;
	
	//定义一个索引
	private int index;
	
	//表示坤坤处于空中的时间
	private int upTime = 0;
	
	//判断坤坤是否位于城堡门口
	private boolean isOK;
	
	//判断坤坤是否失败
	private boolean isDeath = false;
	
	//表示分数,破坏砖块得1分，消灭敌人得2分
	private int score = 0;
	
	//空参构造
	public Kunrio() {
		
	}
	
	public Kunrio(int x,int y) {
		this.setX(x);
		this.setY(y);
		setShow(StaticValue.stand_R);
		this.status="stand--right";
		thread = new Thread(this);
		thread.start();
	}

	//坤坤失败
	public void death() {
		isDeath = true;
	}
	
	//坤坤向左移动
	public void leftMove() {
		//设定速度
		xSpeed = -5;
		//判断坤坤是否碰到旗子,若是则无法移动
		if(backGround.isReach()) {
			xSpeed=0;
		}
		//判断坤坤是否处于空中
		if(status.indexOf("jump")!=-1) {
			status = "jump--left";
		}else {
			status = "move--left";
		}
	}
	
	//坤坤向右移动
	public void rightMove() {
		//设定速度
		xSpeed = 5;
		//判断坤坤是否碰到旗子,若是则无法移动
		if(backGround.isReach()) {
			xSpeed=0;
		}
		//判断坤坤是否处于空中
		if(status.indexOf("jump")!=-1) {
			status = "jump--right";
		}else {
			status = "move--right";
		}
	}
	
	//坤坤向左停止
	public void leftStop() {
		//改变速度
		xSpeed = 0;
		//判断坤坤是否处于空中
		if(status.indexOf("jump")!=-1) {
			status = "jump--left";
		}else {
			status = "stop--left";
		}
	}	
	
	//坤坤向右停止
		public void rightStop() {
			//改变速度
			xSpeed = 0;
			//判断坤坤是否处于空中
			if(status.indexOf("jump")!=-1) {
				status = "jump--right";
			}else {
				status = "stop--right";
			}
		}
		
	//坤坤跳跃
		public void jump() {
			if(status.indexOf("jump")==-1) {//等于-1时表示坤坤处于空中
				if(status.indexOf("left")!=-1) {//方向为左
					status = "jump--left";
				}else {//方向为右
					status = "jump--right";
				}				
				//此时坤坤跳跃高度为70
				ySpeed = -10;
				upTime = 7;
			}
			
			//判断坤坤是否碰到旗子,若是则无法移动
			if(backGround.isReach()) {
				ySpeed = 0;
			}
		}
	//坤坤下落
		public void fall() {
				if(status.indexOf("left")!=-1) {//方向为左
					status = "jump--left";
				}else {//方向为右
					status = "jump--right";
				}				
				//此时坤坤下落速度为10
				ySpeed = 10;
			}
	
	@Override
	public void run() {
		while(true) {
			//判断是否处于障碍物上，置初始值为否
			boolean onObstacle = false;
			
			//判断是否可以向右走，置初始值为可
			boolean canRight = true;
				
			//判断是否可以向左走，置初始值为可
			boolean canLeft = true;
			
			//判断坤坤是否到达旗杆位置,若是则坤坤与旗子一同下落且坤坤将走进城堡,否则进行其他操作
			//旗杆的横坐标为500
			if(backGround.isFlag() && this.x>=500) {
				this.backGround.setReach(true);
				
				//判断旗子是否已完成下落,若完成则坤坤向城堡移动
				if(this.backGround.isBase()) {
					status = "move--right";
					if(x < 680) {//若坤坤未走到城堡中间，则继续前进
						x += 5;
					}else {//坤坤已走到城堡中间
						isOK = true;
					}
				}else {//此时旗子未完成下落,将坤坤横坐标移速度置为0，且纵坐标递增
					if(y < 395) {
						xSpeed = 0;
						this.y+=5;
						status = "jump--right";
					}
					
					if(y > 395) {//此时坤坤完成下落
						this.y = 395;
						status = "stop--right";
					}
				}
				
			}else {
				
			//遍历当前场景内所有障碍物
			for(int i=0;i<backGround.getObstacleList().size();i++) {
				Obstacle ob = backGround.getObstacleList().get(i);
				//判断坤坤是否位于障碍物上
				
				//getY()与getX()分别为坤坤的纵坐标与横坐标
				//this.x与this.y分别为障碍的横坐标与纵坐标
				if(ob.getY()==this.y+25 && (ob.getX()>this.x-30 && ob.getX()<this.x+25)) {
					onObstacle = true;
				}
				
				//判断是否跳起来顶到砖块
				if((ob.getY()>=this.y-30 && ob.getY()<=this.y-20) && (ob.getX()>this.x-30 && ob.getX()<this.x+25)) {
					if(ob.getType()==0) {//若顶到的砖块为普通砖块
						backGround.getObstacleList().remove(ob);
						score += 1;
					}
					upTime = 0;//顶到障碍物时立刻下落
				}
				
				//判断是否可以向右走
				if(ob.getX()==this.x+25 && (ob.getY()>this.y-30 && ob.getY()<this.y+25)) {
					canRight = false;//此时右侧有障碍物
				}
				
				//判断是否可以向左走
				if(ob.getX()==this.x-30 && (ob.getY()>this.y-30 && ob.getY()<this.y+25)) {
					canLeft = false;//此时左侧有障碍物
				}
				
			}
			
			//判断坤坤是否碰到敌人失败或踩死蘑菇怪
			for(int i=0;i<backGround.getEnemyList().size();i++) {
				Enemy e = backGround.getEnemyList().get(i);
				
				if(e.getY() == this.y+20 && (e.getX()-25 <= this.x && e.getX()+36 >= this.x)) {//此时坤坤位于敌人头上
						if(e.getType() == 1) {//此时敌人为蘑菇怪，坤坤不会失败
							e.death();
							score += 2;
							upTime = 3;
							ySpeed = -10;
						}else if(e.getType() == 2) {//此时敌人为食人花，坤坤失败
							//此时坤坤位于食人花头上，失败
							death();
						}
				}
				if((e.getX()+35>this.x && e.getX()-25<this.x) && (e.getY()+35>this.y && e.getY()-20<this.y)) {//判断坤坤是否触碰敌人
					//此时坤坤触碰到敌人，失败
					death();
				}
			}
			
			//坤坤跳跃操作
			//判断坐标改变是否位于障碍物上进行
			if(onObstacle && upTime == 0) {//此时坤坤未进行跳跃操作
				if(status.indexOf("left")!=-1) {//判断是否面向左边
					if(xSpeed!=0) {//判断是否在移动，若是，则表示此时坤坤正向左移动
						status="move--left";
					}else {//否则，则表示面向左边停止动作
						status="stop--left";
					}
					
				}else {//此时坤坤面向右边
					if(xSpeed!=0) {//判断是否在移动，若是，则表示此时坤坤正向右移动
						status="move--right";
					}else {
						status="stop--right";//否则，则表示面向右边停止动作
					}
				}
			}else {//此时坤坤正处于浮空状态
				if(upTime != 0) {//此时为跳跃状态
					upTime--;//跳跃状态持续时间递减
				}else {//此时为下降状态
					fall();
				}
			y+=ySpeed;//纵坐标随坤坤落下改变
			}
		}	
			if((canLeft && xSpeed<0)||(canRight && xSpeed>0)) {
				x+=xSpeed;//若可以往左或右方向走且速度不为0
				//判断坤坤是否到了最左边，若是则不可再往左移动，置横坐标为0
				if(x<0) {
					x=0;
				}
			}
			//判断是否处于移动状态
			if(status.contains("move")) {
				index = index == 0 ? 1 : 0;
			}
			
            //判断是否向左移动
            if ("move--left".equals(status)) {
                show = StaticValue.run_L.get(index);
            }
			
            //判断是否向右移动
            if ("move--right".equals(status)) {
                show = StaticValue.run_R.get(index);
            }
			
			//判断是否向左停止
			if("stop--left".equals(status)) {
				show = StaticValue.stand_L;
			}
			
			//判断是否向右停止
			if("stop--right".equals(status)) {
				show = StaticValue.stand_R;
			}
			
			//判断是否向左跳跃
			if("jump--left".equals(status)) {
				show = StaticValue.jump_L;
			}
			
			//判断是否向右跳跃
			if("jump--right".equals(status)) {
				show = StaticValue.jump_R;
			}
			
			//使线程休眠50毫秒
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}		

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage getShow() {
		return show;
	}

	public void setShow(BufferedImage show) {
		this.show = show;
	}

	public BackGround getBackGround() {
		return backGround;
	}

	public void setBackGround(BackGround backGround) {
		this.backGround = backGround;
	}

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public boolean isDeath() {
		return isDeath;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}

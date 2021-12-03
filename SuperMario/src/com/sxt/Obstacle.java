package com.sxt;

import java.awt.image.BufferedImage;

public class Obstacle implements Runnable{
	//坐标
	private int x;
	private int y;
	//记录障碍物类型
	private int type;
	//显示图像
	private BufferedImage show = null;
	//定义当前场景对象
	private BackGround bg = null;
	//定义用于表示旗子降落过程的线程对象
	private Thread thread = new Thread(this);
	
	public Obstacle(int x,int y,int type,BackGround bg) {
		this.setX(x);
		this.setY(y);
		this.setType(type);
		this.bg = bg;
		setShow(StaticValue.obstacle.get(type));
		//若障碍物为旗子，启动线程
		if(type == 8) {
			thread.start();
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public BufferedImage getShow() {
		return show;
	}

	public void setShow(BufferedImage show) {
		this.show = show;
	}

	@Override
	public void run() {
		while(true) {
			if(this.bg.isReach()) {//判断坤坤是否到达旗杆位置
				if(this.y<374) {//若旗子未落地，纵坐标递增
					this.y += 5;
				}else {
					this.bg.setBase(true);//旗子落地
				}
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
